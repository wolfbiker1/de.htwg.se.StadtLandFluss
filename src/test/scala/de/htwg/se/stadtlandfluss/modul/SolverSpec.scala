package de.htwg.se.stadtlandfluss.modul

import de.htwg.se.stadtlandfluss.model.gridComponent.gridBaseImpl.{GridCreator, Solver}
import org.scalatest.{Matchers, WordSpec}

class SolverSpec extends WordSpec with Matchers {
  "A solver " should {
    "solve a Grid and fill it with cells with a creation strategy" in {
      val tinyGrid = new GridCreator(4, 4).createGrid()
      val solver = new Solver().solveGame(tinyGrid)
      solver.cell(2, 2).value should not be ("")
      //      tinyGrid.cell(0, 0).value should be("automarke")
    }
    "be empty" in {
      val tinyGrid = new GridCreator(4, 2).createGrid()
      val solver = new Solver()
      solver.getVector("none") should be equals("")
    }
    "be an error" in {
      val tinyGrid = new GridCreator(4, 2).createGrid()
      val solver = new Solver()
      solver.getVector(2.toString) should not be equals(Vector())
    }
  }
}
