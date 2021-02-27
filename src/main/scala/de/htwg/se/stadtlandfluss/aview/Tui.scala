package de.htwg.se.stadtlandfluss.aview

import de.htwg.se.stadtlandfluss.controller.Controller
import de.htwg.se.stadtlandfluss.util.Observer
import de.htwg.se.stadtlandfluss.model.Builder

class Tui(controller: Controller) extends Observer{
  controller.add(this)

  def processInputLine(input: String):Unit = {

    input match {
      case "q" =>
      case s"n-$i" => controller.createRandomGrid(4, i.toInt, i.toInt, 4)
      case "s" =>
        controller.solve()
      case s"p-$f-$l-$a" => {
        val b = Builder()
        val y = b.setPlayerFirstname(s"$f").setPlayerLastname(s"$l").setPlayerAge(a.toInt).build()
      }
      case _ => {
        input.split(",|;|:|-").toList match {
          case row:: column :: value :: Nil => controller.set(row.toInt-1, column.toInt-1, value)
          case column :: value :: Nil => controller.set(1, column.toInt-1, value)
          case value :: Nil => controller.set(8-1, 4-1, value)
          case _ =>
        }
      }
    }

  }
  override def update: Boolean = { println(controller.gridToString);true}
}
