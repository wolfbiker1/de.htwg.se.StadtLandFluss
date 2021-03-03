package de.htwg.se.stadtlandfluss.model

class EvaluatorRow extends EvaluateStrategyTemplate {

  override def evaluateGame(grid: Grid, playerMap: Map[Int, Player]): Int = {
    val player0 = playerMap(0)
    val player1 = playerMap(1)

    for (row <- 1 until grid.height) {
      var player0RulesRow = true
      var player1RulesRow = true

      // depends on current letter
      val currentChar: String = Round.getCharacterForRound(row).toString

      for (column <- 0 until grid.width) {
        // stays the same for every row
        val category: String = grid.cell(0, column).toString
        val allWordsForCategory: Vector[String] = categories(category)
        val allWordsForCategoryInUppercase: Vector[String] = for (upperCaseWord <- allWordsForCategory) yield upperCaseWord.toUpperCase()
        val wordsForCurrentChar: Vector[String] = allWordsForCategoryInUppercase.filter(_.startsWith(currentChar))
        val inputFromPlayer = grid.cell(row, column).toString

        if (wordsForCurrentChar.contains(inputFromPlayer) && (row % 2 == 1)) {
          player0RulesRow = false
        } else if (wordsForCurrentChar.contains(inputFromPlayer) && (row % 2 == 0)) {
          player1RulesRow = true
        }
      }

      if (player0RulesRow) {
        player0.setPoints()
      } else if (player1RulesRow) {
        player1.setPoints()
      }
    }
    if (player0.getPoints() > player1.getPoints()) 0 else 1
  }
}