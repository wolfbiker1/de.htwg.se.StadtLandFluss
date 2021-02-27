package de.htwg.se.stadtlandfluss.util

trait Command {
  def doStep:Unit
  def undoStep:Unit
  def redoStep:Unit
}
