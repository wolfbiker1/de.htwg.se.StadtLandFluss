package de.htwg.se.stadtlandfluss.aview.gui

import de.htwg.se.stadtlandfluss.controller.Controller

import scala.swing._

class SwingGui(controller: Controller) extends Frame {
  listenTo(controller)
  title = "FOOBAR"
  centerOnScreen()
  title = "Stadt Land -fluss"

  //
  val tableOfInputFields = Array.ofDim[InputField](controller.getNumberOfColumns(), controller.getRound())
  title = "Flow Panel"
  contents = new FlowPanel {
    contents += new Label("A Label")
    contents += Swing.HStrut(30)
    contents += new Button("A Button")
    contents += new Button("Another Button")
    contents += Button("Close") { sys.exit(0) }
  }
}


