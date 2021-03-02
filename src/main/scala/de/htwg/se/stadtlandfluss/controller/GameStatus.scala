package de.htwg.se.stadtlandfluss.controller

object GameStatus extends Enumeration{
  type GameStatus = Value
  type PlayerStatus = Value
  val IDLE, RESIZE, SET, NEW, UNDO, REDO, CANDIDATES, SOLVED,ERROR,TURNP1,TURNP2, NA,  NOT_SOLVABLE = Value

  val map = Map[GameStatus, String](
    IDLE -> "Willkommen beim Spiel des Jahrtausends, wer das liest ist doof..",
    NEW -> "A new game was created",
    SET -> "A Cell was set",
    RESIZE -> "Game was resized",
    UNDO -> "Undone one step",
    CANDIDATES -> "Showing candidates",
    REDO -> "Redone one step",
    SOLVED ->"Game successfully solved",
    ERROR ->"Error in Game",
    TURNP1 ->"Player 1 turn",
    TURNP2 ->"Player2 turn",
    NOT_SOLVABLE ->"Game not solvable")

  val playerMap = Map[GameStatus, String] (
    NA -> "Player not set",
    TURNP1 ->"Player 1 turn",
    TURNP2 ->"Player2 turn",
  )

  def message(gameStatus: GameStatus) = {
    map(gameStatus)
  }

  def playerMessage(playerStatus: PlayerStatus) = {
    playerMap(playerStatus)
  }
}
