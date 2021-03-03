package de.htwg.se.stadtlandfluss.model

class GridCreator(width: Int, height: Int) {
  val categories: List[String] = List("automarke", "land", "fluss", "farbe")
  def createGrid(): Grid = {
    var grid = new Grid(height, width)
    val columnsCount: Int = 4
    for {index <- 0 until columnsCount} {
      grid = setHeadline(grid, index)
    }
    grid
  }

  private def setHeadline(grid: Grid, column: Int): Grid = {
    grid.set(0, column, categories(column))
  }
}
