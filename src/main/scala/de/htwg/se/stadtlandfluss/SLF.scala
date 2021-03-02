package de.htwg.se.stadtlandfluss

import de.htwg.se.stadtlandfluss.aview.Tui
import de.htwg.se.stadtlandfluss.controller._
import de.htwg.se.stadtlandfluss.model.Round

import scala.io.StdIn.readLine

object SLF {
  val controller = Controller.getController
  controller.notifyObservers

  val tui = new Tui(controller)
  def main(args: Array[String]): Unit = {
    var input: String = ""
    do {
      input = readLine()

      tui.processInputLine(input)
    } while (input != "q")
  }
}