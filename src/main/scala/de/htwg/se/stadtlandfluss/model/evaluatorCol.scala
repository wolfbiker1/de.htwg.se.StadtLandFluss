package de.htwg.se.stadtlandfluss.model

class evaluatorCol extends evaluateStrategyTemplate {

  override def evaluateGame(grid: Grid): Vector[Int] = {
    var player0 = 0
    var player1 = 0
    for (row <- 1 until grid.height - 1 by 2; column <- 0 until grid.width) {

      // stays the same for every row
      val category: String = grid.cell(0, column).toString

      // depends on current letter
      val currentChar: String = Round.getCharacterForRound(row).toString
      val allWordsForCategory: Vector[String] = categories(category)
      val wordsForCurrentChar: Vector[String] = allWordsForCategory.filter(_.startsWith(currentChar))

      val resultEven = grid.cell(row, column).toString
      val resultOdd = grid.cell(row+1, column).toString

      if (wordsForCurrentChar.contains(resultEven) && resultEven == resultOdd) {
        player0 += 1
        player1 += 1
      } else if (wordsForCurrentChar.contains(resultEven) && wordsForCurrentChar.contains(resultOdd)) {
        player0 += 2
        player1 += 2
      } else if (wordsForCurrentChar.contains(resultEven) && !wordsForCurrentChar.contains(resultOdd)) {
        player0 += 0
      } else if (!wordsForCurrentChar.contains(resultEven) && wordsForCurrentChar.contains(resultOdd)) {
        player1 += 0
      }
    }
    println("is done")
    // todo: set state of winner
    Vector[Int](player0, player1)
  }
}
