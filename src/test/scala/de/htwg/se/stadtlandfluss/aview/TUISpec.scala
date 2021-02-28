package de.htwg.se.stadtlandfluss.aview

import de.htwg.se.stadtlandfluss.controller.Controller
import de.htwg.se.stadtlandfluss.model.Grid
import org.scalatest.{Matchers, WordSpec}

class TuiSpec  extends WordSpec with Matchers{

  "A Sudoku Tui" should {
    val controller = Controller.getController
    val tui = new Tui(controller)
    "do nothing on input 'q'" in {
      tui.processInputLine("q")
    }
 //   "create a Sudoku on input s'n-$i'" in {// todo ändern logik
//      tui.processInputLine("n-8")
 //     controller.createRandomGrid(4,8,8,4) should be(new Grid(8,4))
  //  }
    "set a cell on input '2,dk'" in {
      tui.processInputLine("2,dk")
      controller.grid.cell(1,1).value should be("dk") //2-1
    }
    "solve a Sudoku on input 's'" in {
      tui.processInputLine("n-8")
      tui.processInputLine("s")
     controller.solve should be() // todo boolean logik
    }

  }

}

