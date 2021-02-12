package de.htwg.se.stadtlandfluss.model

class GridCreator(size: Int) {
  val categories: List[String] = List("foo", "bar", "baz", "foo", "bar", "baz")

  def createGrid(num: Int): Grid = {
    var grid = new Grid(size)
    for {index <- 0 to num} {
      grid = setCell(grid, index)
    }
    grid
  }

  private def setCell(grid:Grid, column: Int): Grid = {
    grid.set(0, column, categories(column))
  }
}
