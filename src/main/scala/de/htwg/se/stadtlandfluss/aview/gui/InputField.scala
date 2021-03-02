package de.htwg.se.stadtlandfluss.aview.gui

import de.htwg.se.stadtlandfluss.controller.Controller

import scala.swing._
import javax.swing.table._
import scala.swing.event._

class InputField(row: Int, column: Int, controller: Controller) extends FlowPanel {

  def getCellContent(row: Int, columns: Int): String = {
    println(controller.getCell(row, columns))
    "foo!"
  }

//  val label =
//    new Label {
//      text = cellText(row, column)
//      font = new Font("Verdana", 1, 36)
//    }
}
