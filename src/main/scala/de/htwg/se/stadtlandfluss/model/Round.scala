package de.htwg.se.stadtlandfluss.model

import de.htwg.se.stadtlandfluss.controller.GameStatus

object Round {
  private var playerMap = Map[Int, Player]()
  def getPlayerMap: Map[Int, Player] = playerMap
  def setPlayer(p: Player): Unit = {
    playerMap = playerMap.updated(playerMap.size, p)
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
