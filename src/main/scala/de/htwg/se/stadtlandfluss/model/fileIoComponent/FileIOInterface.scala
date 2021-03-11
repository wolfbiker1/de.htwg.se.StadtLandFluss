package de.htwg.se.stadtlandfluss.model.fileIoComponent

import de.htwg.se.stadtlandfluss.controller.ControllerInterface
import de.htwg.se.stadtlandfluss.model.gridComponent.GridInterface

trait FileIOInterface {
  def load: GridInterface
  def save(grid: GridInterface, controller: ControllerInterface): Unit

}
