package de.htwg.se.stadtlandfluss.aview

import de.htwg.se.stadtlandfluss.model.{Grid, GridCreator, Solver}

class Tui {

  def processInputLine(x:Int,input: String, grid:Grid):Grid = {



    input match {
      case "q" => grid
      case "n" =>new GridCreator(4, x).createGrid(x, 4)
      case "s" =>
        new Solver().solveGame(grid)

      case _ => { input.toList.filter(c => c != ' ').map(c => c.toString.toInt) match {
        case row :: column :: value :: Nil => grid.set(row, column, value.toString)

        case _ => grid
      }
        grid
        }
      }

  }
}
