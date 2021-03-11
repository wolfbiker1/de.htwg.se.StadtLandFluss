package de.htwg.se.stadtlandfluss.model.fileIOComponent

import de.htwg.se.stadtlandfluss.model.gridComponent.GridInterface

trait FileIOInterface {
  def load: GridInterface
  def save(grid: GridInterface): Unit

}
