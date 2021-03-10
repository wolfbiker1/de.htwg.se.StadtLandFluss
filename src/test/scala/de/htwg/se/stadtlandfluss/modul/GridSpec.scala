package de.htwg.se.stadtlandfluss.modul

import de.htwg.se.stadtlandfluss.model.gridComponent.gridBaseImpl.{Cell, Grid, Matrix}
import org.scalatest.{Matchers, WordSpec}

class GridSpec extends WordSpec with Matchers {
  "A Grid is the playingfield of Stadt Land Fluss. A Grid" when {
    "to be constructed" should {
      "be created with the length of its edges as size. Practically relevant are size 1, 4, and 9" in {
        val tinygrid = new Grid(1, 1)
        val smallGrid = new Grid(4, 6)
        val normalGrid = new Grid(9, 9)
        val awkwardGrid = new Grid(2, 7)
      }
      "for test purposes only created with a Matrix of Cells" in {
        val awkwardGrid = Grid(new Matrix(2, 5, Cell("")))
        val testGrid = Grid(Matrix[Cell](Vector(Vector(Cell("Tauber"), Cell("hund")), Vector(Cell(""), Cell("")))))
      }
    }
    "created properly but empty" should {
      val tinygrid = new Grid(1, 1)
      val smallGrid = new Grid(4, 6)
      val normalGrid = new Grid(9, 9)
      val awkwardGrid = new Grid(2, 7)
      "give access to its Cells" in {
        tinygrid.cell(0, 0) should be(Cell(""))
        smallGrid.cell(0, 0) should be(Cell(""))
        smallGrid.cell(0, 1) should be(Cell(""))
        smallGrid.cell(1, 0) should be(Cell(""))
        smallGrid.cell(1, 1) should be(Cell(""))
      }
      "allow to set individual Cells and remain immutable" in {
        val changedGrid = smallGrid.set(0, 0, "")
        changedGrid.cell(0, 0) should be(Cell(""))
        smallGrid.cell(0, 0) should be(Cell(""))
      }
    }
    "prefilled with values 1 to n" should {
      val tinyGrid = Grid(new Matrix[Cell](Vector(Vector(Cell("")))))
      val smallGrid = Grid(new Matrix[Cell](Vector(Vector(Cell(""), Cell("hund")), Vector(Cell("grün"), Cell("Deutschland")))))
      "have the right values in the right places" in {
        smallGrid.cell(0, 0) should be(Cell(""))
        smallGrid.cell(0, 1) should be(Cell("hund"))
        smallGrid.cell(1, 0) should be(Cell("grün"))
        smallGrid.cell(1, 1) should be(Cell("Deutschland"))
      }
    }
    "printed as string" should {
      val tinyGrid = Grid(new Matrix[Cell](Vector(Vector(Cell("")))))

    }
  }

}
