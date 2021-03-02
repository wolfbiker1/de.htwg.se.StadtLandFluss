package de.htwg.se.stadtlandfluss.controller

import de.htwg.se.stadtlandfluss.model.{Grid, GridCreator, Solver, Builder, Round}
import de.htwg.se.stadtlandfluss.controller.GameStatus._
import de.htwg.se.stadtlandfluss.util.{Observable, UndoManager}
import scala.swing.Publisher


class Controller private(var grid: Grid) extends Publisher {
  var gameStatus: GameStatus = IDLE
  var playerStatus: PlayerStatus = NA
  private val undoManager = new UndoManager

  def createEmptyGrid(width: Int, height: Int): Unit = {
    grid = new Grid(width, height)
    publish(new CellChanged)
  }

  def createRandomGrid(width: Int, height: Int, randomCells: Int, heights: Int): Unit = {
    grid = new GridCreator(width, height).createGrid(randomCells, heights)
    publish(new CellChanged)
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
    gameStatus = SET
    publish(new CellChanged)
  }

  def getRound(): Int = {
    val currentRound: Int = Round.getRound(grid)
    if ((currentRound % 2) == 0) {
      playerStatus = TURNP1
    } else {
      playerStatus = TURNP2
    }
    currentRound
  }

  def solve() = {
    grid = new Solver().solveGame(grid)
    gameStatus = SOLVED
    publish(new CellChanged)
  }


  def undo: Unit = {
    undoManager.undoStep
    gameStatus = UNDO
    publish(new CellChanged)
  }

  def redo: Unit = {
    undoManager.redoStep
    gameStatus = REDO
    publish(new CellChanged)
  }
}

object Controller {
  val controller = new Controller(new Grid(4, 4))

  def getController: Controller = controller
}

