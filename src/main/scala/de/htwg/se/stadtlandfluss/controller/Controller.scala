package de.htwg.se.stadtlandfluss.controller

import de.htwg.se.stadtlandfluss.model.{Builder, EvaluatorCol, EvaluatorRow, Grid, GridCreator, Player, Round, Solver}
import de.htwg.se.stadtlandfluss.controller.GameStatus._
import de.htwg.se.stadtlandfluss.util.{Observable, UndoManager}

import scala.swing.Publisher

class Controller private(var grid: Grid) extends Publisher {

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
    // if (Round.playersAreSet()) {
    grid = new GridCreator(width, height).createGrid()
    //    } else {
    //      gameStatus = PERROR
    //    }
    publish(new CellChanged)
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
    publish(new CellChanged)
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
    publish(new CellChanged)
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

  def getCell(row: Int, column: Int) = grid.cell(row, column)

  def getAmountOfColumns = grid.width

  def getAmountOfRows = grid.height

  def gameIsReady: Boolean = (systemStatus == READY)

  def statusText: String = GameStatus.message(gameStatus)

}

object Controller {
  val controller = new Controller(new Grid(4, 4))

  def getController: Controller = controller
}