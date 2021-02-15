package de.htwg.se.stadtlandfluss.aview

import de.htwg.se.stadtlandfluss.model.{Grid, GridCreator, Solver}

class Tui {

  def processInputLine(input: String, grid:Grid):Grid = {
    input match {
      case "q" => grid
      case "n" => new GridCreator(9).createGrid(3)
      case "s" =>
        new Solver().solveGame(grid)

      case _ => {
        grid
        }
      }

  }
}
