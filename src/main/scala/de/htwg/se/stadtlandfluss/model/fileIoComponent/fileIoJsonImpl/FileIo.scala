package de.htwg.se.stadtlandfluss.model.fileIoComponent.fileIoJsonImpl

import com.google.inject.Guice
import com.google.inject.name.Names
import de.htwg.se.stadtlandfluss.controller.ControllerInterface
import de.htwg.se.stadtlandfluss.model.fileIoComponent.FileIOInterface
import de.htwg.se.stadtlandfluss.model.gridComponent.{CellInterface, GridInterface}
import net.codingwell.scalaguice.InjectorExtensions._
import play.api.libs.json._

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.io._
class FileIo extends FileIOInterface {


  implicit val cellWrites = new Writes[CellInterface] {
    def writes(cell: CellInterface) = Json.obj(
      "text" -> cell.value,
    )
  }

  def createJsonSnapshot(grid: GridInterface, controller: ControllerInterface): JsValue = {
    val playerMap = controller.getPlayer
    val p1 = playerMap(0)
    val p2 = playerMap(1)
    Json.obj(
      "date" ->     LocalDateTime.now.format(DateTimeFormatter.ofPattern("YYYY-MM-dd_HH-mm-ss")),
    "player" -> Json.obj(
        "player1" -> Json.obj(
            "firstName" -> p1.firstname,
                  "lastName" -> p1.lastname,
                  "age" -> p1.age
        ),
        "player2" -> Json.obj(
          "firstName" -> p2.firstname,
                "lastName" -> p2.lastname,
                "age" -> p2.age
        )
      ),
      "field" -> Json.obj(
        "rounds" -> JsNumber(grid.height),
        "textFields" -> Json.toJson(
          for (row <- 0 until controller.getAmountOfRows; col <- 0 until controller.getAmountOfColumns) yield {
            Json.obj(
              "row" -> row,
              "col" -> col,
              "text" -> controller.getCell(row, col)
            )
          }
        )
      )
    )
  }

  override def save(grid: GridInterface, controller: ControllerInterface): Unit = {
    println("saving...")
    val w = new PrintWriter(new File("save.json"))
    w.write(Json.prettyPrint(createJsonSnapshot(grid, controller)))
    w.close
  }

  override def load: GridInterface = {
    var grid: GridInterface = null
    grid
  }
}
