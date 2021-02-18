package de.htwg.se.stadtlandfluss.aview

import de.htwg.se.stadtlandfluss.controller.Controller
import de.htwg.se.stadtlandfluss.model.Grid
import org.scalatest.{Matchers, WordSpec}

class TuiSpec  extends WordSpec with Matchers{

  "A Sudoku Tui" should {
    val controller = new Controller(new Grid(8,4))
    val tui = new Tui(controller)
    "do nothing on input 'q'" in {
      tui.processInputLine("q")
    }
    //"create a Sudoku on input s'n-$i'" in {
    //  tui.processInputLine("n-8")
    //  controller.createRandomGrid(4,8,8,4) should be(new Grid(8,4))
   // }

  }

}

