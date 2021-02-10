package de.htwg.se.stadtlandfluss.model

import scala.math.sqrt

case class Grid(private val cells:Matrix[Cell]) {
  def this(size:Int) = this(new Matrix[Cell](size, Cell("")))
  val size:Int = cells.size
  def cell(row:Int, col:Int):Cell = cells.cell(row, col)
  def set(row:Int, col:Int, value: String):Grid = copy(cells.replaceCell(row, col, Cell(value)))
  
}

