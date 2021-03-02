package de.htwg.se.stadtlandfluss.model

case class Cell(value: String) {
  def isSet: Boolean = (value.length != 0)

  // empty grid shall be displayed as empty, not as cell()
  override def toString: String = value.toString.replace("0", " ")

}