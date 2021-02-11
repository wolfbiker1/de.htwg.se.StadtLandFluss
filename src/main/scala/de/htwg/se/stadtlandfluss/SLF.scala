package de.htwg.se.stadtlandfluss

import de.htwg.se.stadtlandfluss.model.Player
import de.htwg.se.stadtlandfluss.model.Grid

object SLF {
  var grid = new Grid(9)
  def main(args: Array[String]): Unit = {
//    val student = Player("Your Name")
//    println("Hello, " + student.name)
    println(grid)
  }
}