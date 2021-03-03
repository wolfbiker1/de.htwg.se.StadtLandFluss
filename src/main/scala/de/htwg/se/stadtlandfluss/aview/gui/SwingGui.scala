package de.htwg.se.stadtlandfluss.aview.gui

import scala.swing._

class SwingGui extends MainFrame {
  title = "Flow Panel"
  contents = new FlowPanel {
    contents += new Label("A Label")
    contents += Swing.HStrut(30)
    contents += new Button("A Button")
    contents += new Button("Another Button")
    contents += Button("Close") { sys.exit(0) }
  }
}


