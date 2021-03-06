package de.htwg.se.stadtlandfluss.aview.gui

import de.htwg.se.stadtlandfluss.controller.{CellChanged, Controller}

import scala.swing._
import javax.swing.table._

import scala.swing.event._

class InputField(row: Int, column: Int, controller: Controller) extends FlowPanel {
  listenTo(mouse.clicks)
  def getCellContent(row: Int, columns: Int): String = {
    val current = controller.getCell(row, columns).toString
    if (current.isEmpty) {
      return ""
    }
    current
  }

//  val label = new Label {
//    preferredSize = new Dimension(17, 17)
//      text = getCellContent(row, column)
//      font = new Font("Verdana", 1, 36)
//  }

//  val cell = new BoxPanel(Orientation.Vertical) {
//    contents += label
//    preferredSize = new Dimension(51, 51)
//    border = Swing.BeveledBorder(Swing.Raised)
//    listenTo()
//    listenTo(mouse.clicks)
//    listenTo(controller)
//    reactions += {
//      case e: CellChanged => {
//        repaint
//      }
//      case MouseClicked(src, pt, mod, clicks, pops) => {
//        println("foo")
//        repaint
//      }
//    }
//  }




}



