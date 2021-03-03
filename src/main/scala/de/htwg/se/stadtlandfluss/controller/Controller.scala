package de.htwg.se.stadtlandfluss.controller

import de.htwg.se.stadtlandfluss.model.{Builder, Grid, GridCreator, Player, Round, Solver, EvaluatorCol, EvaluatorRow}
import de.htwg.se.stadtlandfluss.controller.GameStatus._
import de.htwg.se.stadtlandfluss.util.{Observable, UndoManager}

class Controller private(var grid: Grid) extends Observable {

  /*
   * Gamestates
   */
  var gameStatus: GameStatus = IDLE
  var playerStatus: PlayerStatus = NA
  var systemStatus: SystemStatus = NOTREADY


  /*
   * Local definitions
   */
  private val undoManager = new UndoManager
  private val numberOfColumns = 4

  def createRandomGrid(width: Int, height: Int): Unit = {
    if (Round.playersAreSet()) {
      grid = new GridCreator(width, height).createGrid()
    } else {
      gameStatus = PERROR
    }
    notifyObservers
  }

  def getNumberOfColumns() = numberOfColumns

  def addPlayer(credentials: List[String]): Unit = {
    val builder = Builder()
    val player = builder
      .setPlayerFirstname(credentials(1))
      .setPlayerLastname(credentials(2))
      .setPlayerAge(credentials(3))
      .build()
    Round.setPlayer(player)
  }

  def gridToString: String = grid.toString

  def set(row: Int, col: Int, value: String): Unit = {
      undoManager.doStep(new SetCommand(row, col, value.toUpperCase, this))
      gameStatus = SET
      notifyObservers
  }

  def setUpRandomCharacters(numOfRounds: Int): Unit = {
    Round.setUpRandomCharacters(numOfRounds)
  }

  def evaluate(isCol: Boolean): Unit = {
    val evaluator = if (isCol) new EvaluatorCol else new EvaluatorRow
    if (evaluator.evaluateGame(grid, Round.getPlayerMap) == 0) {
      playerStatus = ITSP1
    } else {
      playerStatus = ITSP2
    }
    gameStatus = SOLVED
    notifyObservers
  }

  def isReady(): Unit = {
    if (Round.getPlayerMap.size < 2) {
      systemStatus = NOTREADY
    }
    systemStatus = READY
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
    notifyObservers
  }


  def undo: Unit = {
    undoManager.undoStep
    gameStatus = UNDO
    notifyObservers
  }

  def redo: Unit = {
    undoManager.redoStep
    gameStatus = REDO
    notifyObservers
  }
}

object Controller {
  val controller = new Controller(new Grid(4, 4))

  def getController: Controller = controller
}

