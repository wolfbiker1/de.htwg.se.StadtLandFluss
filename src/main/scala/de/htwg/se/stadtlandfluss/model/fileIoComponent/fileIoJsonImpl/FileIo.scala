package de.htwg.se.stadtlandfluss.model.fileIoComponent.fileIoJsonImpl

import com.google.inject.Guice
import com.google.inject.name.Names
import de.htwg.se.stadtlandfluss.SLFModule
import de.htwg.se.stadtlandfluss.controller.ControllerInterface
import de.htwg.se.stadtlandfluss.model.fileIoComponent.FileIOInterface
import de.htwg.se.stadtlandfluss.model.gridComponent.gridBaseImpl.GridCreator
import de.htwg.se.stadtlandfluss.model.gridComponent.{CellInterface, GridInterface}
import de.htwg.se.stadtlandfluss.model.playerComponent.Player
import net.codingwell.scalaguice.InjectorExtensions._
import play.api.libs.json.Format.GenericFormat
import play.api.libs.json._

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.io._
import scala.io.Source


class FileIo extends FileIOInterface {
  implicit val cellWrites = new Writes[CellInterface] {
    def writes(cell: CellInterface) = Json.toJson(
      cell.value
    )
  }

  implicit val cellWrites1 = new Writes[Char] {
    def writes(o: Char): JsValue = JsString(o.toString)
  }

  override def restoreSnapshot(controller: ControllerInterface): GridInterface = {
    val snapShot: String = Source.fromFile("snapshot.json").getLines.mkString
    val json: JsValue = Json.parse(snapShot)
    val rounds: Int = (json \ "field" \ "rounds").get.toString.toInt
    val player1 = (json \ "player" \ "player1")
    val player2 = (json \ "player" \ "player2")
//    really messy, sorry..
    val p1AsList: List[String] = List("p", player1.get("firstName").toString(), player1.get("lastName").toString(), player1.get("age").toString())
    val p2AsList: List[String] = List("p", player2.get("firstName").toString(), player2.get("lastName").toString(), player2.get("age").toString())
    controller.addPlayer(p1AsList)
    controller.addPlayer(p2AsList)

    var grid: GridInterface = new GridCreator(controller.getStaticNumberOfColumns, rounds).createGrid()

    for (blockNumber <- 0 until rounds * controller.getStaticNumberOfColumns) {
      val row = (json \\ "row")(blockNumber).as[Int]
      val col = (json \\ "col")(blockNumber).as[Int]
      val content = (json \\ "text")(blockNumber).as[String]
      val character = (json \\ "character")(blockNumber).as[String]
      controller.setCharacterForRow(row, character.charAt(0))
      grid = grid.set(row, col, content)
    }

    grid
  }

  def createJsonSnapshot(controller: ControllerInterface): JsValue = {
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
        "rounds" -> JsNumber(controller.getAmountOfRows),
        "textFields" -> Json.toJson(
          for (row <- 0 until controller.getAmountOfRows; col <- 0 until controller.getAmountOfColumns) yield {
            Json.obj(
              "row" -> row,
              "col" -> col,
              "text" -> controller.getCell(row, col),
              "character" -> Json.toJson(controller.getCharacterForRow(row))
            )
          }
        )
      )
    )
  }

  override def save(controller: ControllerInterface): Unit = {
    val w = new PrintWriter(new File("snapshot.json"))
    w.write(Json.prettyPrint(createJsonSnapshot(controller)))
    w.close()
  }
}
