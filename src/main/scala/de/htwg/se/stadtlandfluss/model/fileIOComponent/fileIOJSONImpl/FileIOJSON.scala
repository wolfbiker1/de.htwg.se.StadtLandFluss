package de.htwg.se.stadtlandfluss.model.fileIOComponent.fileIOJSONImpl

import com.google.inject.Guice
import com.google.inject.name.Names
import de.htwg.se.stadtlandfluss.SLFModule
import de.htwg.se.stadtlandfluss.model.gridComponent.GridInterface

import de.htwg.se.stadtlandfluss.model.fileIoComponent.FileIOInterface
import de.htwg.se.stadtlandfluss.model.gridComponent.gridBaseImpl.GridCreator
import play.api.libs.json.{JsNumber, JsValue, Json}

import scala.io.Source

class FileIOJSON extends FileIOInterface {

    override def load: GridInterface = {
      var grid: GridInterface = null
      val source: String = Source.fromFile("grid.json").getLines.mkString
      val json: JsValue = Json.parse(source)
      val height = (json \ "grid" \ "height").get.toString.toInt
      val width = (json \ "grid" \ "width").get.toString.toInt
      val injector = Guice.createInjector(new SLFModule)
      height match {
        case 4 => grid = injector.instance[GridInterface](Names.named("quicky"))
        case 8 => grid = injector.instance[GridInterface](Names.named("extended"))
        case _ => grid = new GridCreator(width, height).createGrid()
      }
      grid
    }
  override def save(grid: GridInterface): Unit = {
    import java.io._
    val pw = new PrintWriter(new File("grid.json"))
    pw.write(Json.prettyPrint(gridToJson(grid)))
    pw.close
  }
  def gridToJson(grid: GridInterface) = {
    Json.obj(
      "grid" -> Json.obj(
        "height" -> JsNumber(grid.height),
        "width" -> JsNumber(grid.width),

        "cells" -> Json.toJson(
          for {
            row <- 0 until grid.height;
            col <- 0 until grid.width
          } yield {
            Json.obj(
              "row" -> row,
              "col" -> col,
              "cell" -> Json.toJson(grid.cell(row, col))
            )
          }
        )

      )
    )
  }
}
