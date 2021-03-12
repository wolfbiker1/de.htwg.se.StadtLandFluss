package de.htwg.se.stadtlandfluss.model

import de.htwg.se.stadtlandfluss.controller.GameStatus
import de.htwg.se.stadtlandfluss.model.gridComponent.GridInterface
import de.htwg.se.stadtlandfluss.model.gridComponent.gridBaseImpl.Grid
import de.htwg.se.stadtlandfluss.model.playerComponent.Player

import scala.util.Random

object Round {
  private var playerMap = Map[Int, Player]()
  private var randomCharacters = Map[Int, Char]()

  def getPlayerMap: Map[Int, Player] = playerMap

  def setPlayer(p: Player): Unit = {
    playerMap = playerMap.updated(playerMap.size, p)
  }

  def playersAreSet() : Boolean = {
    playerMap.size == 2
  }

  def getCharacterForRound(currentRound: Int): Char = {
    randomCharacters(currentRound)
  }

  def storeCharacters(row: Int, character: Character): Unit = {
    randomCharacters = randomCharacters.updated(row, character)
  }

  def setUpRandomCharacters(numOfRounds: Int): Unit = {
    val r = scala.util.Random
    val chars = ('A' to 'Z').toList
    for (i <- 1 until numOfRounds) {
      var charToInsert: Char = chars(r.nextInt(25))
      while (randomCharacters.contains(charToInsert)) {
        charToInsert = chars(r.nextInt(25))
      }
      randomCharacters = randomCharacters.updated(i, charToInsert)
    }
    randomCharacters = randomCharacters.updated(0, '?')
  }

  def getRound(grid: GridInterface): Int = {
    for (row <- 1 until grid.height; column <- 0 until grid.width) {
      if (!grid.cell(row, column).isSet) {
        return row
      }
    }
    1
  }
}