package de.htwg.se.stadtlandfluss.model

class evaluatorCol extends evaluateStrategyTemplate {

  override def evaluateGame(grid: Grid): Vector[Int] = {
    var player0 = 0
    var player1 = 0
    for (row <- 1 until grid.height - 1 by 2; column <- 0 until grid.width) {
      val category: String = grid.cell(0, column).toString

      val resultEven = grid.cell(row, column).toString
      val resultOdd = grid.cell(row+1, column).toString

      if (categories(category).contains(resultEven) && resultEven == resultOdd) {
        player0 += 1
        player1 += 1
      } else if (categories(category).contains(resultEven) && categories(category).contains(resultOdd)) {
        player0 += 2
        player1 += 2
      } else if (categories(category).contains(resultEven) && !categories(category).contains(resultOdd)) {
        player0 += 0
      } else if (!categories(category).contains(resultEven) && categories(category).contains(resultOdd)) {
        player1 += 0
      }
    }
    println("is done")
    // todo: set state of winner
    Vector[Int](player0, player1)
  }
}
