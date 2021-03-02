package de.htwg.se.stadtlandfluss.aview.gui

import scala.swing._
import de.htwg.se.stadtlandfluss.controller._

class SwingGui(controller: Controller) extends Frame {
  listenTo(controller)
}
