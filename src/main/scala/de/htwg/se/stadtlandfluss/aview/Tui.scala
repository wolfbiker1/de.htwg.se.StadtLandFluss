package de.htwg.se.stadtlandfluss.aview

import de.htwg.se.stadtlandfluss.controller._
import de.htwg.se.stadtlandfluss.model.{Grid, GridCreator, Solver}
import de.htwg.se.stadtlandfluss.util.Observer

import scala.util.Try


class Tui(controller: Controller) extends Observer {
  controller.add(this)
  println(GameStatus.message(controller.gameStatus))
  def processInputLine(input: String): Unit = {
    input match {
      case "q" =>
      case s"n-$userInput" => {
        toInt(userInput) match {
          case Some(value) =>
            controller.createRandomGrid(controller.getNumberOfColumns, value)
          case None =>
            // fail silently...
        }
      }
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
          case column :: value :: Nil => controller.set(controller.getRound(), column.toInt - 1, value)
          case _ =>
        }
      }
    }

  }

  def toInt(s: String): Option[Int] = {
    Try(s.toInt).toOption
//    val i = Try(s.toInt).toOption
//    i match {
//      case Some(value) =>
//        value
//      case None =>
//        0
//    }
  }


  override def update: Boolean = {
    println(controller.gridToString)
    println(GameStatus.message(controller.gameStatus))
    println(GameStatus.playerMessage(controller.playerStatus))
    true
  }
}
