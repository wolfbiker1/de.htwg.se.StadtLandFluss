package de.htwg.se.stadtlandfluss.model

case class Row(private val cells: Matrix[Cell]) {

  //  how many cells are in one row
  val size:Int = cells.size

  


}
