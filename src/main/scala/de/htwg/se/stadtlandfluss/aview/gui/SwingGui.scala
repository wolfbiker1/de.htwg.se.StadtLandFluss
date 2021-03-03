package de.htwg.se.stadtlandfluss.aview.gui

import scala.swing._
import scala.swing.Swing.LineBorder
import de.htwg.se.stadtlandfluss.controller._

import scala.swing.event.{ButtonClicked, Event}

class SwingGui(controller: Controller) extends Frame {
  listenTo(controller)
  centerOnScreen()
  title = "Stadt Land -fluss"

  //
  val tableOfInputFields = Array.ofDim[InputField](controller.getAmountOfColumns, controller.getAmountOfRows)


  val cp = new InputField(0, 0, controller)


  /*
   * Attach a menue bar
   *
   */
  menuBar = new MenuBar {
    contents += new Menu("File") {
      contents += new MenuItem(Action("New"){controller.createEmptyGrid(4,4 )} )
      contents += new MenuItem(Action("Random"){controller.createRandomGrid(4, 6, 6, 4)})
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

    contents += Swing.HStrut(30)
    contents += new Button("3 Kategorien")
    contents += new Button("4 Kategorien")

    contents += Button("Close") {
      sys.exit(0)

    }


  }
  def gridPanel = new GridPanel(controller.getAmountOfColumns, controller.getAmountOfRows) {
    border = LineBorder(java.awt.Color.BLACK, 2)
    background = java.awt.Color.BLACK
    for {
      outerRow <- 1 until controller.getAmountOfRows
      outerColumn <- 0 until controller.getAmountOfColumns
    } {
      contents += new GridPanel(controller.getAmountOfColumns, controller.getAmountOfRows) {
        border = LineBorder(java.awt.Color.BLACK, 2)
        for {
          innerRow <- 1 until controller.getAmountOfRows
          innerColumn <- 0 until controller.getAmountOfColumns
        } {
          val x = outerRow * controller.getAmountOfRows + innerRow
          val y = outerColumn * controller.getAmountOfColumns + innerColumn
          val cellPanel = new InputField(x, y, controller)
          tableOfInputFields(x)(y) = cellPanel
          contents += cellPanel
          listenTo(cellPanel)
        }
      }
    }
    val statusline = new TextField(controller.statusText, 20)

  }
  def game_start(): Unit = {
    val amount_panel = new FlowPanel {
      contents += new Label("How many players ?")
      val button3 = new Button("3 Players") {
        name = "3"
      }
      val button4 = new Button("4 Players") {
        name = "4"
      }
      val button5 = new Button("5 Players"){
        name = "5"
      }
      val button6 = new Button("6 Players"){
        name = "6"
      }
      contents+=button3
      contents+=button4
      contents+=button5
      contents+=button6
    }
    listenTo(amount_panel.button3)
    listenTo(amount_panel.button4)
    listenTo(amount_panel.button5)
    listenTo(amount_panel.button6)

    contents = amount_panel

  }

  reactions += {
    case ButtonClicked(b) => println(b.text + " has been clicked!")
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
