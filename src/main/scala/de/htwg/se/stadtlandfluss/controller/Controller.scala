package de.htwg.se.stadtlandfluss.controller

import de.htwg.se.stadtlandfluss.model.{Grid, GridCreator, Solver}
import de.htwg.se.stadtlandfluss.util.Observable

class Controller(var grid: Grid) extends Observable {
  def createEmptyGrid(width: Int, height: Int): Unit = {
    grid = new Grid(width, height)
    notifyObservers
  }

  def createRandomGrid(width: Int, height: Int, randomCells: Int, heights: Int): Unit = {
    grid = new GridCreator(width, height).createGrid(randomCells, heights)
    notifyObservers
  }


  def gridToString: String = grid.toString

  def set(row: Int, col: Int, value: String): Unit = {
    grid = grid.set(row, col, value)
    notifyObservers
  }

  def solve() = {
    grid = new Solver().solveGame(grid)
    notifyObservers
  }

}

