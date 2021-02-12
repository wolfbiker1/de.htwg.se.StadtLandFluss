package de.htwg.se.stadtlandfluss.model


case class Grid(private val cells:Matrix[Cell]) {
  def this(size:Int) = this(new Matrix[Cell](size, Cell("")))

  val size:Int = cells.size
  val blocknum: Int = 5

  // get method for Cell at position x, y
  def cell(row:Int, col:Int):Cell = cells.cell(row, col)

  // set function is a wrapper for Matrix.replaceCell
  def set(row:Int, col:Int, value: String):Grid = {copy(cells.replaceCell(row, col, Cell(value)))}

  override def toString: String = {
    val lineseparator = ("*-" + ("--")) * blocknum + "+\n"
    val line = ("| " + ("x ")) * blocknum + "|\n"
    var box = "\n" + (lineseparator + (line)) * blocknum + lineseparator
    for {
      row <- 0 until size
      col <- 0 until size
    } {
        box = box.replaceFirst("x", cell(row, col).toString)
    }

    box
  }
}

