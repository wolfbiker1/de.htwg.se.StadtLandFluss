package de.htwg.se.stadtlandfluss.controller

import de.htwg.se.stadtlandfluss.model.{Builder, Round}
import de.htwg.se.stadtlandfluss.controller.GameStatus._
import de.htwg.se.stadtlandfluss.model.gridComponent.gridBaseImpl.Cell
import de.htwg.se.stadtlandfluss.util.UndoManager

import scala.swing.Publisher


trait ControllerInterface extends Publisher {
  def createRandomGrid(width: Int, height: Int): Unit
  def getNumberOfColumns: Int
  def addPlayer(credentials: List[String]): Unit
  def gridToString: String
  def set(row: Int, col: Int, value: String): Unit
  def setUpRandomCharacters(numOfRounds: Int): Unit
  def evaluate(isCol: Boolean): Unit
  def isReady: Boolean
  def getRound(): Int
  def solve(): Unit
  def undo: Unit
  def redo: Unit
  def getCell(row: Int, column: Int): Cell
  def getAmountOfColumns: Int
  def getAmountOfRows: Int
  def gameIsReady: Boolean
  def statusText: String
  def inGameStatus: String
  def currentLetter: Char
}
