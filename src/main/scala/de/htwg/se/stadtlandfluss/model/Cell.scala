package de.htwg.se.stadtlandfluss.model

case class Cell(value: String) {
  def isSet: Boolean = (value.length != 0)
}