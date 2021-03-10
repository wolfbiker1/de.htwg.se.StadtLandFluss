package de.htwg.se.stadtlandfluss.controller

object GameStatus extends Enumeration {
  type GameStatus = Value
  type PlayerStatus = Value
  type SystemStatus = Value
  val IDLE, RESIZE, SET, NEW, UNDO, REDO, CANDIDATES,
  SOLVED, NOTREADY, ERROR, TURNP1, TURNP2, NA,
  READY, NOT_SOLVABLE, ITSP1, ITSP2, PERROR = Value

  val map = Map[GameStatus, String](
    IDLE -> "Willkommen",
    NEW -> "A new game was created",
    SET -> "A Cell was set",
    RESIZE -> "Game was resized",
    UNDO -> "Undone one step",
    CANDIDATES -> "Showing candidates",
    REDO -> "Redone one step",
    SOLVED -> "Game successfully solved. Goodbye",
    ERROR -> "Error in Game",
    PERROR -> "Did you forgt to assign Playernames?"
  )

  val systemMap = Map[SystemStatus, String](
    NOTREADY -> "Assign at least 2 Player characters!",
    READY -> ""
  )

  val playerMap = Map[GameStatus, String](
    NA -> "Player not set",
    ITSP1 -> "Player 1 won",
    ITSP2 -> "Player 2 won",
    TURNP1 -> "Player 1 turn",
    TURNP2 -> "Player 2 turn"
  )


  def message(gameStatus: GameStatus) = {
    map(gameStatus)
  }

  def playerMessage(playerStatus: PlayerStatus) = {
    playerMap(playerStatus)
  }

  def systemMessage(systemStatus: SystemStatus) = {
    systemMap(systemStatus)
  }
}
