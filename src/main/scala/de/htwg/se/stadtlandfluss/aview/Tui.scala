package de.htwg.se.stadtlandfluss.aview

import de.htwg.se.stadtlandfluss.controller.Controller
import de.htwg.se.stadtlandfluss.model.{Grid, GridCreator, Solver}
import de.htwg.se.stadtlandfluss.util.Observer

class Tui(controller: Controller) extends Observer{
  controller.add(this)

  def processInputLine(input: String):Unit = {

    input match {
      case "q" =>
      case s"n-$i" => controller.createRandomGrid(4, i.toInt, i.toInt, 4)
      case "s" =>
        controller.solve()
      case _ => {
        input.split(",|;|:|-").toList match {

          case column :: value :: Nil => controller.set(1, column.toInt, value)
          case value :: Nil => controller.set(8, 4, value)
          case _ =>
        }
      }
    }

  }
  override def update: Unit = { println(controller.gridToString);}
}
