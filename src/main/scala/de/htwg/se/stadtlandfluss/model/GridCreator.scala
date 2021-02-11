package de.htwg.se.stadtlandfluss.model

import scala.util.Random

class GridCreator(size: Int) {
  val head: List[String] = List("stadt", "land", "baz")

  def createRandom(num: Int): Grid = {
    var grid = new Grid(size)
    for {index <- 1 to num} {
      grid = setCell(grid, index)
    }
    grid
  }

  private def setCell(grid:Grid, column: Int): Grid = {
    val headline = head(column)
    grid.set(0, column, headline)
    grid
  }
}
