package de.htwg.se.stadtlandfluss.controller

import de.htwg.se.stadtlandfluss.model.{Grid, GridCreator, Solver, Builder}
import de.htwg.se.stadtlandfluss.controller.GameStatus._
import de.htwg.se.stadtlandfluss.util.{Observable, UndoManager}

class Controller private (var grid: Grid) extends Observable {
  var gameStatus: GameStatus = IDLE
  private val undoManager = new UndoManager
  def createEmptyGrid(width: Int, height: Int): Unit = {
    grid = new Grid(width, height)
    notifyObservers
  }

  def createRandomGrid(width: Int, height: Int, randomCells: Int, heights: Int): Unit = {
    grid = new GridCreator(width, height).createGrid(randomCells, heights)
    notifyObservers
  }

  def addPlayer(credentials: List[String]): Unit = {
    val builder = new Builder()
    val player = builder
      .setPlayerFirstname(credentials(1))
      .setPlayerLastname(credentials(2))
      .setPlayerAge(credentials(3).toInt)
      .build()
    // todo: store results somewhere
  }

  def gridToString: String = grid.toString

  def set(row: Int, col: Int, value: String): Unit = {
    undoManager.doStep(new SetCommand(row, col, value, this))
    notifyObservers
  }

  def solve() = {
    grid = new Solver().solveGame(grid)
    notifyObservers
  }


  def _solve: Unit = {
    notifyObservers
  }

  def undo: Unit = {
    undoManager.undoStep
    notifyObservers
  }

  def redo: Unit = {
    undoManager.redoStep
    notifyObservers
  }
}

object Controller {
  val controller = new Controller(new Grid(4, 4))
  def getController: Controller = controller
}

