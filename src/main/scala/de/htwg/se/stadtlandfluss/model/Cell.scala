package de.htwg.se.stadtlandfluss.model

case class Cell(value: Int) {
  def isSet: Boolean = value != 0
}