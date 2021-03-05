package de.htwg.se.stadtlandfluss.aview.gui

import de.htwg.se.stadtlandfluss.controller.{CandidatesChanged, CellChanged, Controller, GridSizeChanged, gameStarted}

import scala.swing.Swing.LineBorder
import scala.swing._
import scala.swing.event.{ButtonClicked, Event, Key, KeyTyped}

class SwingGui(controller: Controller) extends Frame {
  listenTo(controller)
  centerOnScreen()
  this.title = "Stadt Land Fluss"
  val statusLine = new TextField(controller.statusText, 20)

  override def closeOperation(): Unit = {
    System.exit(-1)
  }

  /*
   * Methods
   *
   */
  def game_start(): Unit = {
  }

  def addPlayer(): Unit = {
    val firstName = setNamePanel.textfieldFirstName.text
    val lastName = setNamePanel.textfieldLastName.text
    val age = setNamePanel.textfieldAge.text
    val p = List("p", firstName, lastName, age)
    controller.addPlayer(p)
  }

  def selectRound(): Unit = {
    val rounds = setRoundsPanel.textfieldRounds.text
    controller.createRandomGrid(4, rounds.toInt)
  }

  def clearTextFields(): Unit = {
    setNamePanel.textfieldFirstName.text = ""
    setNamePanel.textfieldLastName.text = ""
    setNamePanel.textfieldAge.text = ""
  }

  def clearRound(): Unit = {
    setRoundsPanel.textfieldRounds.text = ""
  }

  def flushPanel(): Unit = {
    repaint()
    this.pack()
  }

  /*
   * Panels
   *
   */

  val controlPanel = new FlowPanel {

    val btnPlayer1: Button = new Button("Player 1") {
      name = "player1"
    }
    val btnPlayer2: Button = new Button("Player 2") {
      name = "player2"
    }
    // confirm
    val btnConfirm: Button = new Button("Confirm") {
      name = "confirm"
    }
    val btnSelectRounds: Button = new Button("Select Rounds") {
      name = "selectRound"
    }
    val btnConfirmRounds: Button = new Button("Confirm Rounds") {
      name = "confirmRound"
    }
    btnConfirmRounds.enabled = false
    contents += btnPlayer1
    contents += btnPlayer2
    contents += btnConfirm
    contents += btnSelectRounds
    contents += btnConfirmRounds
  }



  val setRoundsPanel = new FlowPanel {
    val labelRounds: Label = new Label("Rounds")
    val textfieldRounds = new TextField("", 50)
    contents += labelRounds
    listenTo(labelRounds)
    contents += textfieldRounds
  }



  val setNamePanel = new GridPanel(4, 1) {
    background = new Color(46, 52, 64)
    //  name
    val labelFirstName: Label = new Label("<html><font color='#5e81ac'>Name:</font></html>")
    labelFirstName.background = new Color(94, 129, 172)
    contents += labelFirstName
    val textfieldFirstName = new TextField("", 50)
    textfieldFirstName.background = new Color(46, 52, 64)
    contents += textfieldFirstName

    //  lastname
    val labelLastName = new Label("<html><font color='#5e81ac'>Last Name:</font></html>")
    contents += labelLastName
    val textfieldLastName = new TextField("", 50)
    contents += textfieldLastName

    //  age
    val age = new Label("<html><font color='#5e81ac'>Age:</font></html>")
    contents += age
    val textfieldAge = new TextField("", 50)
    contents += textfieldAge
  }

  val gridPanel: GridPanel = new GridPanel(controller.getAmountOfRows, controller.getAmountOfColumns) {
    border = LineBorder(java.awt.Color.BLACK, 2)
    //        background = java.awt.Color.BLACK
    controller.setUpRandomCharacters(4)
    controller.createRandomGrid(4, 4)
    val width = 4
    val height = 4

    for (row <- 0 until height; column <- 0 until width) {
      //          val cellPanel = new InputField(row, column, controller)

      val label = new Label {
        //ext = getCellContent(row, column)
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
  }

  val bp = new BoxPanel(Orientation.Vertical)
  bp.visible = true
  contents = new BorderPanel {
    add(controlPanel, BorderPanel.Position.North)
    add(gridPanel, BorderPanel.Position.Center)
    add(bp, BorderPanel.Position.South)
  }

  /*
   * Listeners
   */
  listenTo(controlPanel.btnPlayer1)
  listenTo(controlPanel.btnPlayer2)
  listenTo(controlPanel.btnConfirm)
  listenTo(controlPanel.btnSelectRounds)
  listenTo(controlPanel.btnConfirmRounds)
  listenTo(setNamePanel.textfieldFirstName)
  listenTo(setNamePanel.textfieldLastName)
  listenTo(setNamePanel.textfieldAge)
  listenTo(setRoundsPanel.labelRounds)
  listenTo(setRoundsPanel.textfieldRounds)


  reactions += {
    case ButtonClicked(b) => {
      if (b.name == "player1" || b.name == "player2") {
        bp.contents -= setRoundsPanel
        bp.contents += setNamePanel
      } else if (b.name == "confirm") {
        bp.contents -= setNamePanel
        addPlayer()
        clearTextFields()
      } else if (b.name == "selectRound") {
        bp.contents += setRoundsPanel
        bp.contents -= setNamePanel
      } else if (b.name == "confirmRound") {
        bp.contents -= setRoundsPanel
        selectRound()
        clearRound()
      }
      flushPanel()
    }
    case event: gameStarted => game_start()
    case event: CellChanged => redraw
    case event: CandidatesChanged => redraw
  }


  visible = true
  redraw

  def redraw = {
  }
}