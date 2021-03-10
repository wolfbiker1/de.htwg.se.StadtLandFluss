package de.htwg.se.stadtlandfluss.controller.controllerBaseImpl

import de.htwg.se.stadtlandfluss.util.Command

class SetCommand(row:Int, col: Int, value: String, controller: Controller) extends Command {
  override def doStep: Unit =   controller.grid = controller.grid.set(row, col, value)

  override def undoStep: Unit = controller.grid = controller.grid.set(row, col, "")

  override def redoStep: Unit = controller.grid = controller.grid.set(row, col, value)
}