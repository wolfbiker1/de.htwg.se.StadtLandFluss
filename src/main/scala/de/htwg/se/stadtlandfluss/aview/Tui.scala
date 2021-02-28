package de.htwg.se.stadtlandfluss.aview

import de.htwg.se.stadtlandfluss.controller._
import de.htwg.se.stadtlandfluss.util.Observer

class Tui(controller: Controller) extends Observer {
  controller.add(this)

  def processInputLine(input: String): Unit = {

    input match {
      case "q" =>
      case s"n-$i" => controller.createRandomGrid(4, i.toInt, i.toInt, 4)
      case "z" => controller.undo
      case "s" =>
        controller.solve()
      case s"p-$f-$l-$a" => {
        val playerInfo = input.split(",|;|:|-").toList
        playerInfo match {
          case "p" :: firstname :: lastname :: age :: Nil => controller.addPlayer(playerInfo)
        }
      }
      case _ => {
        input.split(",|;|:|-").toList match {
          case column :: value :: Nil => controller.set(1, column.toInt - 1, value)
          case _ =>
        }
      }
    }

  }

  override def update: Boolean = {
    println(controller.gridToString)
    true
  }
}
