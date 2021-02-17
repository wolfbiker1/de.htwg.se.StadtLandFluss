package de.htwg.se.stadtlandfluss

import de.htwg.se.stadtlandfluss.aview.Tui
import de.htwg.se.stadtlandfluss.controller.Controller
import de.htwg.se.stadtlandfluss.model.Grid
import de.htwg.se.stadtlandfluss.model.GridCreator

import scala.io.StdIn.readLine

object SLF {
  var grid = new Grid(4, 4)
  val controller = new Controller(grid)
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