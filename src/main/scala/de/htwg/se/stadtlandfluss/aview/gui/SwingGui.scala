package de.htwg.se.stadtlandfluss.aview.gui

import scala.swing._
import de.htwg.se.stadtlandfluss.controller._

class SwingGui(controller: Controller) extends Frame {
  listenTo(controller)
  title = "FOOBAR"

  //
  val tableOfInputFields = Array.ofDim[InputField](controller.getAmountOfColumns, controller.getAmountOfRows)


  val cp = new InputField(0, 0, controller)
  reactions += {
    case event: CellChanged => redraw
    case event: CandidatesChanged => redraw
  }

  /*
   * Attach a menue bar
   *
   */
  menuBar = new MenuBar {
    contents += new Menu("File") {
      contents += new MenuItem(Action("New") {})
      contents += new MenuItem(Action("Quit") {
        System.exit(0)
      })
    }
    contents += new Menu("Edit") {
      contents += new MenuItem(Action("Undo") {
        controller.undo
      })
      contents += new MenuItem(Action("Redo") {
        controller.redo
      })
    }
    contents += new Menu("Evaluate") {
      contents += new MenuItem(Action("Solve") {
        controller.solve
      })
      contents += new MenuItem(Action("Judge") {
        controller.solve
      })
    }
    contents += new Menu("Strategy") {
      contents += new MenuItem(Action("Match Columns") {})
      contents += new MenuItem(Action("Match Row") {})

    }
  }

  /*
 * Create Game Settings
 *
 */
  def gameSettings = new FlowPanel {
      contents += new Label("A label")
      contents += Swing.HStrut(30)
      contents += new Button("A Button")
      contents += new Button("Another Button")
      contents += Button("Close") {
        sys.exit(0)
      }
  }


  // stick them together...
  contents = new BorderPanel {
    add(gameSettings, BorderPanel.Position.North)
  }

  def redraw = {
    title = cp.getCellContent(0, 0)
    repaint
  }

  visible = true
}
