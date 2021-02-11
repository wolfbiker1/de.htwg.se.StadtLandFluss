package de.htwg.se.stadtlandfluss.model

case class Cell(value: String) {
  def isSet: Boolean = (value.length != 0)
  override def toString: String = value.toString.replace("", " ")
}