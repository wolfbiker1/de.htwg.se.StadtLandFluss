package de.htwg.se.stadtlandfluss.aview.gui

import de.htwg.se.stadtlandfluss.controller.{CandidatesChanged, CellChanged, Controller, GridSizeChanged, gameStarted}

import scala.swing._
import scala.swing.event.{ButtonClicked, Event, Key, KeyTyped}

class SwingGui(controller: Controller) extends Frame {
  listenTo(controller)
  centerOnScreen()
  title = "Stadt Land -fluss"

  override def closeOperation(): Unit = {
    System.exit(-1)
  }
  var state: Event = new gameStarted
  var table = Array.ofDim[InputField](controller.getAmountOfColumns, controller.getAmountOfRows)


  def game_start(): Unit = {
//    val amount_panel = new FlowPanel {
//      contents += new Label("How many players ?")
//      val button3 = new Button("1 Players") {
//        name = "3"
//      }
//      val button4 = new Button("2 Players") {
//        name = "4"
//      }
//      contents += button3
//      contents += button4
//    }
//    contents = amount_panel
//    // listeners
//    listenTo(amount_panel.button3)
//    listenTo(amount_panel.button4)
  }

  val amount_panel = new FlowPanel {
    contents += new Label("How many players ?")
    val button3 = new Button("1 Players") {
      name = "3"
    }
    val button4 = new Button("2 Players") {
      name = "4"
    }
    contents += button3
    contents += button4
  }
  // listeners
  listenTo(amount_panel.button3)
  listenTo(amount_panel.button4)

  val set_name_panel = new FlowPanel {
    val label = new Label("Player " + "X" + " whats your Name and age?")
    val labelName = new Label("Name:")
    contents += labelName
    val textfieldName = new TextField("Enter your name here ...", 50)
    contents += textfieldName
  }

  listenTo(set_name_panel.textfieldName)

  def set_name(activePlayer_idx: Int): Unit = {
    set_name_panel.label.text = "Player " + activePlayer_idx + " whats your Name ?"
    contents = set_name_panel
  }

  def create_player(input: List[String]): Unit = {
    input match {
      case _ => controller.addPlayer(input)
    }
  }


  def check_Amount(input: String): Int = {
    if (!List("1", "1").contains(input)) {
      println("There may only be 1 or 2 players!")
      return -1
    }
    input.toInt
  }


  reactions += {
    case ButtonClicked(b) => {
      println(b)
      set_name_panel.visible = true
    }
    case event: gameStarted => game_start()
    case event: GridSizeChanged => resize(event.newSize)
    case event: CellChanged => redraw
    case event: CandidatesChanged => redraw
  }
  def gridPanel = new GridPanel(controller.getAmountOfRows, controller.getAmountOfColumns) {
    //    border = LineBorder(java.awt.Color.BLACK, 2)
    //    background = java.awt.Color.BLACK
    //    for {
    //      outerRow <- 0 until controller.getAmountOfRows
    //      outerColumn <- 0 until controller.getAmountOfColumns
    //    } {
    //      contents += new GridPanel(controller.getAmountOfRows, controller.getAmountOfColumns) {
    //        border = LineBorder(java.awt.Color.BLACK, 2)
    //        for {
    //          innerRow <- 0 until controller.getAmountOfRows
    //          innerColumn <- 0 until controller.getAmountOfColumns
    //        } {
    //          val x = outerRow * controller.getAmountOfRows + innerRow
    //          val y = outerColumn * controller.getAmountOfColumns + innerColumn
    //          val cellPanel = new InputField(x, y, controller)
    //          table(x)(y) = cellPanel
    //          contents += cellPanel
    //          listenTo(cellPanel)
    //        }
    //      }
    //    }
  }

  val statusline = new TextField(controller.statusText, 20)

  def resize(gridSize: Int) = {
    //    table = Array.ofDim[InputField](controller.getAmountOfRows, controller.getAmountOfColumns)
    //    contents = new BorderPanel {
    //
    //      add(gridPanel, BorderPanel.Position.Center)
    //      add(statusline, BorderPanel.Position.South)
    //    }
  }

  contents = new BorderPanel {
    add(amount_panel, BorderPanel.Position.North)
    add(set_name_panel, BorderPanel.Position.Center)
  }

  set_name_panel.visible = false
  visible = true
  redraw

  def redraw = {
    //    for {
    //      row <- 0 until controller.getAmountOfRows
    //      column <- 0 until 4
    //    } table(row)(column).repaint()
    //    statusline.text = controller.statusText
    //    repaint
  }
}