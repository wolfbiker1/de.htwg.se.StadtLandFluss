package de.htwg.se.stadtlandfluss.controller.controllerBaseImpl

import de.htwg.se.stadtlandfluss.controller.GameStatus._
import de.htwg.se.stadtlandfluss.controller.{ControllerInterface, GameStatus}
import de.htwg.se.stadtlandfluss.model._
import de.htwg.se.stadtlandfluss.model.gridComponent.gridBaseImpl.{EvaluateStrategyTemplate, EvaluatorCol, EvaluatorRow, Grid, GridCreator, Solver}
import de.htwg.se.stadtlandfluss.util.UndoManager
import com.google.inject.name.Names
import com.google.inject.{Guice, Inject}
import de.htwg.se.stadtlandfluss.SLFModule
import de.htwg.se.stadtlandfluss.model.fileIoComponent.FileIOInterface
import de.htwg.se.stadtlandfluss.model.gridComponent.{CellInterface, GridInterface}
import de.htwg.se.stadtlandfluss.model.playerComponent.Player
import net.codingwell.scalaguice.InjectorExtensions._

import scala.swing.Publisher

class Controller @Inject private (var grid: GridInterface) extends ControllerInterface with Publisher {

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

  val injector = Guice.createInjector(new SLFModule)
  val fileIo = injector.instance[FileIOInterface]

  def createRandomGrid(width: Int, height: Int): Unit = {
    height match {
      case 4 => grid = injector.instance[GridInterface](Names.named("quicky"))
      case 8 => grid = injector.instance[GridInterface](Names.named("extended"))
      case _ => grid = new GridCreator(width, height).createGrid()
    }
    publish(new CellChanged)
  }

  def getNumberOfColumns: Int = numberOfColumns

  def getPlayer: Map[Int, Player] = {
    Round.getPlayerMap
  }

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

  def setupEvaluator(isCol: Boolean): Unit = {
    val evaluator = if (isCol) {
      injector.instance[EvaluateStrategyTemplate](Names.named("col"))
    } else {
      injector.instance[EvaluateStrategyTemplate](Names.named("row"))
    }
    evaluate(evaluator)
  }

  def evaluate(evaluator: EvaluateStrategyTemplate): Unit = {
    if (evaluator.evaluateGame(grid, Round.getPlayerMap) == 0) {
      playerStatus = ITSP1
    } else {
      playerStatus = ITSP2
    }
    gameStatus = SOLVED
  }

  def isReady: Boolean = {
    Round.getPlayerMap.size == 2
  }

  def saveGame(): Unit = {
    fileIo.save(grid, this)
    publish(new CellChanged)
  }

  def loadGame(): Unit = {
    grid = fileIo.restoreSnapshot(this)
    publish(new CellChanged)
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

  def getCell(row: Int, column: Int): CellInterface = grid.cell(row, column)

  def getAmountOfColumns = grid.width

  def getAmountOfRows = grid.height

  def getStaticNumberOfColumns: Int = numberOfColumns
  def gameIsReady: Boolean = systemStatus == READY
  def statusText: String = GameStatus.message(gameStatus)
  def inGameStatus: String = GameStatus.playerMessage(playerStatus)
  def currentLetter: Char = Round.getCharacterForRound(this.getRound())
  def getCharacterForRow(pos: Int): Char = {
    Round.getCharacterForRound(pos)
  }
  def setCharacterForRow(pos: Int, char: Character): Unit = {
    Round.storeCharacters(pos, char)
  }
}

object Controller {
  val controller = new Controller(new Grid(4, 4))
  def getController: Controller = controller
}