package de.htwg.se.stadtlandfluss

import de.htwg.se.stadtlandfluss.model.Player

object SLF {
  def main(args: Array[String]): Unit = {
    val student = Player("Your Name")
    println("Hello, " + student.name)

  }
}