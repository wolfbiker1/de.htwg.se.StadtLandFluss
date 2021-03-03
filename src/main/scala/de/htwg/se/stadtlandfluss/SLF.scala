package de.htwg.se.stadtlandfluss

import de.htwg.se.stadtlandfluss.aview.Tui
import de.htwg.se.stadtlandfluss.aview.gui.SwingGui
import de.htwg.se.stadtlandfluss.controller._

import scala.io.StdIn.readLine

object SLF {
  val controller = Controller.getController
  controller.publish(new CellChanged)

  val tui = new Tui(controller)
  val gui = new SwingGui()

  def main(args: Array[String]): Unit = {
    var input: String = ""
    do {
      input = readLine()

      tui.processInputLine(input)
    } while (input != "q")
  }
}