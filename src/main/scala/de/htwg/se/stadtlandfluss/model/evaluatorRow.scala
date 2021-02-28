package de.htwg.se.stadtlandfluss.model

class evaluatorRow extends evaluateStrategyTemplate {

  override def evaluateGame(grid: Grid): Vector[Int] = {
    var player0 = 0
    var player1 = 0
    for (row <- 1 until grid.height - 1 by 2) {
      var player0HasRow = true
      var player1HasRow = true
      for (column <- 0 until grid.width) {
        val category: String = grid.cell(0, column).toString

        val resultEven = grid.cell(row, column).toString
        val resultOdd = grid.cell(row + 1, column).toString

        if (!categories(category).contains(resultEven)) {
          player0HasRow = false
        }
        if (!categories(category).contains(resultOdd)) {
          player1HasRow = true
        }
      }

      if (player0HasRow) {
        player0 += 1
      }
      if (player1HasRow) {
        player1 += 1
      }
    }
    // todo: set state of winner
    Vector[Int](player0, player1)
  }
}