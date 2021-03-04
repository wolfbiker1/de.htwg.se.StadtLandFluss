package de.htwg.se.stadtlandfluss.aview.gui

import de.htwg.se.stadtlandfluss.controller.{CandidatesChanged, CellChanged, Controller, GridSizeChanged, gameStarted}

import scala.swing.Swing.LineBorder
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
    // confirm
    val confirm = new Button("Confirm") {
      name = "confirm"
    }
    contents += button3
    contents += button4
    contents += confirm
  }
  // listeners
  listenTo(amount_panel.button3)
  listenTo(amount_panel.button4)
  listenTo(amount_panel.confirm)

  val set_name_panel = new FlowPanel {
    val label = new Label("Player " + "X" + " whats your Name and age?")

    //  name
    val labelFirstName = new Label("Name:")
    contents += labelFirstName
    val textfieldFirstName = new TextField("", 50)
    contents += textfieldFirstName

    //  name
    val labelLastName = new Label("LastName:")
    contents += labelLastName
    val textfieldLastName = new TextField("", 50)
    contents += textfieldLastName

    //  name
    val age = new Label("age:")
    contents += age
    val textfieldAge = new TextField("", 50)
    contents += age
    contents += textfieldAge
  }

  listenTo(set_name_panel.textfieldFirstName)
  listenTo(set_name_panel.textfieldLastName)
  listenTo(set_name_panel.textfieldAge)

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
  def getCellContent(row: Int, columns: Int): String = {
    val current = controller.getCell(row, columns).toString
    if (current.length == 0) {
      return "________________"
    }
    current
  }

  val gridPanel = new GridPanel(controller.getAmountOfRows, controller.getAmountOfColumns) {
        border = LineBorder(java.awt.Color.BLACK, 2)
//        background = java.awt.Color.BLACK
        controller.setUpRandomCharacters(4)
        controller.createRandomGrid(4,4)
        val width = 4
        val height = 4

        for (row <- 0 until height; column <- 0 until width) {
//          val cellPanel = new InputField(row, column, controller)

          val label = new Label {
            text = getCellContent(row, column)
            font = new Font("Verdana", 1, 36)
          }

          val cellPanel = new BoxPanel(Orientation.Vertical) {

            preferredSize = new Dimension(51, 51)
            border = Swing.BeveledBorder(Swing.Raised)

            if (row == 0) {
              contents += label
            } else {
              val textfieldLastName = new TextField(" <text here> ", 50)
              contents += textfieldLastName
            }



            listenTo(mouse.clicks)
            listenTo(controller)

            reactions += {
              case e: CellChanged => {
                repaint
              }
            }
          }

          contents += cellPanel
        }


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
  }

  contents = new BorderPanel {
    add(amount_panel, BorderPanel.Position.North)
    add(gridPanel, BorderPanel.Position.Center)
    add(set_name_panel, BorderPanel.Position.South)
  }

  set_name_panel.visible = false
  gridPanel.visible = false

  def addPlayer(): Unit = {

    println(set_name_panel.textfieldFirstName.text)
    val firstName = set_name_panel.textfieldFirstName.text
    val lastName = set_name_panel.textfieldLastName.text
    val age = set_name_panel.textfieldAge.text
    val p = List(firstName, lastName, age)
    println(p)
    controller.addPlayer(p)
  }

  reactions += {
    case ButtonClicked(b) => {
      set_name_panel.visible = true
      if (b.name == "4") {
        gridPanel.visible = true
      } else if (b.name == "confirm") {
        addPlayer()
      }
    }
    case event: gameStarted => game_start()
    case event: GridSizeChanged => resize(event.newSize)
    case event: CellChanged => redraw
    case event: CandidatesChanged => redraw
  }


  visible = true
  redraw

  def redraw = {
  }
}