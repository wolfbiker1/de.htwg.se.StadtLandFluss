package de.htwg.se.stadtlandfluss.model

object Round {
  private var cache = Map[Int, Player]()
  def getCache = cache
  def setCache(p: Player): Unit = {
    cache = cache.updated(cache.size, p)
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
