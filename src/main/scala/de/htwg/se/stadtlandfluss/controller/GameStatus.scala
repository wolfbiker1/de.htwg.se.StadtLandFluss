package de.htwg.se.stadtlandfluss.controller

object GameStatus extends Enumeration{
  type GameStatus = Value
  val IDLE, RESIZE, SET, NEW, UNDO, REDO, CANDIDATES, SOLVED,ERROR,TURNP1,TURNP2, NOT_SOLVABLE = Value

  val map = Map[GameStatus, String](
    IDLE -> "",
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


  def message(gameStatus: GameStatus) = {
    map(gameStatus)
  }

}
