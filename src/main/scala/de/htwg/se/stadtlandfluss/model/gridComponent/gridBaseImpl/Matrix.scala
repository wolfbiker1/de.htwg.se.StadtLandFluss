package de.htwg.se.stadtlandfluss.model.gridComponent.gridBaseImpl

case class Matrix[T](rows: Vector[Vector[T]]) {
  def this(width: Int, height: Int, filling: T) = this(Vector.tabulate(width, height) { (row, col) => filling }
  )

  val width: Int = rows(0).size
  val height: Int = rows.size

  def cell(row: Int, col: Int): T = rows(row)(col) // rows[row][col]
  def fill(filling: T): Matrix[T] = copy(Vector.tabulate(width, height) { (row, col) => filling })

  def replaceCell(row: Int, col: Int, cell: T): Matrix[T] = copy(
    rows.updated(row,
      rows(row).updated(col, cell)))
}
