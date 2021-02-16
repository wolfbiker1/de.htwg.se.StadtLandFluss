package de.htwg.se.stadtlandfluss.aview

import de.htwg.se.stadtlandfluss.model.{Grid, GridCreator, Solver}

class Tui {

  def processInputLine(input: String, grid:Grid):Grid = {



    input match {
      case "q" => grid
      case s"n-$i"=>new GridCreator(4, i.toInt).createGrid(i.toInt, 4)
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
