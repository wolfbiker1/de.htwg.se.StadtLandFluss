package de.htwg.se.stadtlandfluss.model

import de.htwg.se.stadtlandfluss.controller.GameStatus

import scala.util.Random

object Round {
  private var playerMap = Map[Int, Player]()
  private var randomCharacters = Map[Int, Char]()

  def getPlayerMap: Map[Int, Player] = playerMap

  def setPlayer(p: Player): Unit = {
    playerMap = playerMap.updated(playerMap.size, p)
  }

  def getCharacterForRound(currentRound: Int): Char = {
    randomCharacters(currentRound)
  }

  def setUpRandomCharacters(numOfRounds: Int): Unit = {
    val r = scala.util.Random
    for (i <- 0 until numOfRounds) {
      randomCharacters = randomCharacters.updated(i, r.nextPrintableChar())
    }
  }

  def getRound(grid: Grid): Int = {
    for (row <- 1 until grid.height; column <- 0 until grid.width) {
      if (!grid.cell(row, column).isSet) {
        return row
      }
    }
    1
  }
}
