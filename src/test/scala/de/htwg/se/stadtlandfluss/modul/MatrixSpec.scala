package de.htwg.se.stadtlandfluss.modul

import de.htwg.se.stadtlandfluss.model.{Cell, Matrix}
import org.scalatest.{Matchers, WordSpec}

class MatrixSpec extends WordSpec with Matchers {
  "A Matrix is a tailor-made immutable data type that contains a two-dimentional Vector of Cells. A Matrix" when {
    "empty " should {
      "be created by using a dimention and a sample cell" in {
        val matrix = new Matrix[Cell](2, 2, Cell(""))
        matrix.width should be(2)
      }
      "for test purposes only be created with a Vector of Vectors" in {
        val testMatrix = Matrix(Vector(Vector(Cell(""))))
        testMatrix.width should be(1)
      }

    }
    "filled" should {
      val matrix = new Matrix[Cell](2, 2, Cell(""))
      "give access to its cells" in {
        matrix.cell(0, 0) should be(Cell(""))
      }
      "replace cells and return a new data structure" in {
        val returnedMatrix = matrix.replaceCell(0, 0, Cell(""))
        matrix.cell(0, 0) should be(Cell(""))
        returnedMatrix.cell(0, 0) should be(Cell(""))
      }
      "be filled using fill operation" in {
        val returnedMatrix = matrix.fill(Cell(""))
        returnedMatrix.cell(0,0) should be(Cell(""))
      }
    }
  }

}