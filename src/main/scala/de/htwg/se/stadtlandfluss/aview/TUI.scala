package de.htwg.se.stadtlandfluss.aview

import de.htwg.se.stadtlandfluss.model.{Category, Grid}

class Tui {

  def processInputLine(input: String, grid:Grid):Grid = {
    input match {
      case "q" => grid
      case "n"=> new Grid(5)
      case "r" => new Category(9).setRandomcategory(grid)

      }
    }

}