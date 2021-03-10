package de.htwg.se.stadtlandfluss.model.gridComponent.gridBaseImpl

import de.htwg.se.stadtlandfluss.model.gridComponent.CellInterface

case class Cell(value: String) extends CellInterface {
  def isSet: Boolean = value.nonEmpty

  // empty grid shall be displayed as empty, not as cell()
  override def toString: String = value.replace("0", " ")
}
