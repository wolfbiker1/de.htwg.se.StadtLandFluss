package de.htwg.se.stadtlandfluss.model.gridComponent.gridBaseImpl


import de.htwg.se.stadtlandfluss.model.Round
import de.htwg.se.stadtlandfluss.model.gridComponent.GridInterface

case class Grid(private val cells: Matrix[Cell]) extends GridInterface {
  def this(height: Int, width: Int) = this(new Matrix[Cell](height, width, Cell("")))

  val width: Int = cells.width
  val height: Int = cells.height


  // get method for Cell at position x, y
  def cell(row: Int, col: Int): Cell = cells.cell(row, col)

  // set function is a wrapper for Matrix.replaceCell
  def set(row: Int, col: Int, value: String): Grid = {
    copy(cells.replaceCell(row, col, Cell(value)))
  }

  override def toString: String = {
    var max = 0
    for (col <- 0 until width) {
      max = Math.max(cell(0, col).toString.length, max)
    }
    max += 5

    var separator = "-"
    for (id <- 0 until max) {
      separator ++= "-"
    }
    val lineSeparator = ("+-" + separator) * width + "+\n"
    val line = ("| " + ("x ")) * width + "|\n"
    var box = "\n" + (lineSeparator + line) * height
    for {
      row <- 0 until height
      col <- 0 until width
    } {
      val currentLength = cell(row, col).toString.length
      val toInsert: String = if (currentLength < max) {
        cell(row, col).toString + (" " * (max - currentLength))
      } else cell(row, col).toString
      box = box.replaceFirst("x", toInsert)
    }
    box += lineSeparator + "\n"
    box += "Current Letter: " + Round.getCharacterForRound(Round.getRound(this))
    box += "\n"
    box
  }
}
