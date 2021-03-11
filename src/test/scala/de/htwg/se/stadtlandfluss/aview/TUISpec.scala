package de.htwg.se.stadtlandfluss.aview

import de.htwg.se.stadtlandfluss.controller.GameStatus
import de.htwg.se.stadtlandfluss.controller.controllerBaseImpl.Controller
import de.htwg.se.stadtlandfluss.model.gridComponent.gridBaseImpl.Grid
import org.scalatest.{Matchers, WordSpec}

class TuiSpec  extends WordSpec with Matchers{


  "A Sudoku Tui" should {
    val controller = Controller.getController
    val tui = new Tui(controller)

    //   "create a Sudoku on input s'n-$i'" in {// todo ändern logik
    //      tui.processInputLine("n-8")
    //     controller.createRandomGrid(4,8,8,4) should be(new Grid(8,4))
    //  }
    " create to playera" in {
      tui.processInputLine("p-hans-g-14")
      tui.processInputLine("p-hansi-h-11")
      tui.processInputLine("n-8")
      controller.getAmountOfColumns should be (4)
    }

    "solve a Sudoku on input 'eRow'" in {

      tui.processInputLine("eRow")
      controller.setupEvaluator(false) should be(controller.setupEvaluator(false))

    }
    "solve a Sudoku on input 'z'" in {

      tui.processInputLine("z")
      controller.undo should be(controller.setupEvaluator(false))

    }
    "solve a Sudoku on input 'eCol'" in {
      tui.processInputLine("p-hans-g-14")
      tui.processInputLine("p-hansi-h-11")
      tui.processInputLine("n-8")
      tui.processInputLine("eCol")
      controller.setupEvaluator(false) should be(controller.setupEvaluator(true))



    }

    "do nothing on input 'q'" in {
      tui.processInputLine("q")
    }

  }

}

