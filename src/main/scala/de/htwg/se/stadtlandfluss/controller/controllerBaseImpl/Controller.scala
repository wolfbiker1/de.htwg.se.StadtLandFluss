package de.htwg.se.stadtlandfluss.controller.controllerBaseImpl

import de.htwg.se.stadtlandfluss.controller.GameStatus._
import de.htwg.se.stadtlandfluss.controller.GameStatus
import de.htwg.se.stadtlandfluss.model._
import de.htwg.se.stadtlandfluss.model.gridComponent.gridBaseImpl.{EvaluatorCol, EvaluatorRow, Grid, GridCreator, Solver}
import de.htwg.se.stadtlandfluss.util.UndoManager

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
    grid = new GridCreator(width, height).createGrid()
    publish(new CellChanged)
  }

  def getNumberOfColumns: Int = numberOfColumns

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
      println("1")
      playerStatus = ITSP1
    } else {
      println("2")
      playerStatus = ITSP2
    }
    gameStatus = SOLVED
    //    publish(new CellChanged)
  }

  def isReady: Boolean = {
    Round.getPlayerMap.size == 2
  }

  def getRound(): Int = {
    val currentRound: Int = Round.getRound(grid)
    if (!isReady || gameStatus == SOLVED) {
      playerStatus = NA
    } else if ((currentRound % 2) == 0) {
      playerStatus = TURNP1
    } else {
      playerStatus = TURNP2
    }
    currentRound
  }

  def solve(): Unit = {
    grid = Solver().solveGame(grid)
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

  def gameIsReady: Boolean = systemStatus == READY
  def statusText: String = GameStatus.message(gameStatus)
  def inGameStatus: String = GameStatus.playerMessage(playerStatus)
  def currentLetter: Char = Round.getCharacterForRound(this.getRound())
}

object Controller {
  val controller = new Controller(new Grid(4, 4))

  def getController: Controller = controller
}