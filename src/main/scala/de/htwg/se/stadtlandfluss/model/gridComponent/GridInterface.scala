package de.htwg.se.stadtlandfluss.model.gridComponent


trait GridInterface {
  def cell(row: Int, col: Int): CellInterface
  def set(row: Int, col: Int, value: String): GridInterface
}

trait CellInterface {
  def value: String
  def isSet: Boolean
}




