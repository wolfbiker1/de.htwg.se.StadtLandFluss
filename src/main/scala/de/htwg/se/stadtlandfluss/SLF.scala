package de.htwg.se.stadtlandfluss

import de.htwg.se.stadtlandfluss.aview.Tui
import de.htwg.se.stadtlandfluss.model.Grid
import de.htwg.se.stadtlandfluss.model.GridCreator

import scala.io.StdIn.readLine

object SLF {
  var grid = new Grid(4, 4)
  val tui = new Tui
  def main(args: Array[String]): Unit = {
    println("How many Rounds?")


    var input: String = ""

    do {


      println("normal Grid : " + grid.toString)

      input = readLine()

      grid = tui.processInputLine(input, grid)
    } while (input != "q")
  }
}