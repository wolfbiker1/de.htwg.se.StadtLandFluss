package de.htwg.se.stadtlandfluss.model

class GridCreator(width: Int, height: Int) {
  val categories: List[String] = List("automarke", "land", "fluss", "farbe")

  def createGrid(rows: Int, columnsCount: Int): Grid = {
    var grid = new Grid(height, width)
    for {index <- 0 until columnsCount} {
      grid = setCell(grid, index)
    }
    grid
  }

  private def setCell(grid: Grid, column: Int): Grid = {
    grid.set(0, column, categories(column))
  }
}
