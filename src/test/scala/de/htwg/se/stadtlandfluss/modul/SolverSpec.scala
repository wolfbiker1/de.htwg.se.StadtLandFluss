package de.htwg.se.stadtlandfluss.modul

import de.htwg.se.stadtlandfluss.model.{GridCreator, Solver}
import org.scalatest.{Matchers, WordSpec}

class SolverSpec extends WordSpec with Matchers {
  "A solver " should {
    "solve a Grid and fill it with cells with a creation strategy" in {
      val tinyGrid = new GridCreator(2, 2).createGrid()
      val solver = new Solver().solveGame(tinyGrid)
      solver.cell(1, 1).value should not be ("")
      //      tinyGrid.cell(0, 0).value should be("automarke")
    }
    "be empty" in {
      val tinyGrid = new GridCreator(2, 2).createGrid()
      val solver = new Solver()
      solver.getVector("none") should be equals(Vector())
    }
    "be an error" in {
      val tinyGrid = new GridCreator(2, 2).createGrid()
      val solver = new Solver()
      solver.getVector(2.toString) should not be equals(Vector())
    }
  }
}
