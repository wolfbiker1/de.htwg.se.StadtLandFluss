package de.htwg.se.stadtlandfluss.aview.gui

import de.htwg.se.stadtlandfluss.controller.{CandidatesChanged, CellChanged, Controller, GridSizeChanged, gameStarted}

import scala.swing.Swing.LineBorder
import scala.swing._
import scala.swing.event.{ButtonClicked, Event, Key, KeyTyped}

class SwingGui(controller: Controller) extends Frame {
  listenTo(controller)
  centerOnScreen()
  this.title = "Stadt Land Fluss"
  override def closeOperation(): Unit = {
    System.exit(-1)
  }

  class Foo extends TextField {
    preferredSize = new Dimension(32, 32)
  }

  def game_start(): Unit = {
  }

  val amountPanel = new FlowPanel {

    val btnPlayer1 = new Button("Player 1") {
      name = "player1"
    }
    val btnPlayer2 = new Button("Player 2") {
      name = "player2"
    }
    // confirm
    val btnConfirm = new Button("Confirm") {
      name = "confirm"
    }
    val btnSelectRounds = new Button("Select Rounds") {
      name = "selectRound"
    }
    val btnConfirmRounds = new Button("Confirm Rounds") {
      name = "confirmRound"
    }
    btnConfirmRounds.enabled = false
    contents += btnPlayer1
    contents += btnPlayer2
    contents += btnConfirm
    contents += btnSelectRounds
    contents += btnConfirmRounds
  }
  // listeners
  listenTo(amountPanel.btnPlayer1)
  listenTo(amountPanel.btnPlayer2)
  listenTo(amountPanel.btnConfirm)
  listenTo(amountPanel.btnSelectRounds)
  listenTo(amountPanel.btnConfirmRounds
  )
  val setRounds = new FlowPanel {
    val labelRounds = new Label("Rounds")
    val textfieldRounds = new TextField("", 50)
    contents += labelRounds
    listenTo(labelRounds)
    contents += textfieldRounds
  }


  listenTo(setRounds.labelRounds)
  listenTo(setRounds.textfieldRounds)
  val setNamePanel = new GridPanel(4, 1) {
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
    val age = new Label("Age:")
    contents += age
    val textfieldAge = new TextField("", 50)
    contents += textfieldAge
  }

  listenTo(setNamePanel.textfieldFirstName)
  listenTo(setNamePanel.textfieldLastName)
  listenTo(setNamePanel.textfieldAge)

  //  def set_name(activePlayer_idx: Int): Unit = {
  //    setNamePanel.label.text = "Player " + activePlayer_idx + " whats your Name ?"
  //    contents = setNamePanel
  //  }

  val gridPanel = new GridPanel(controller.getAmountOfRows, controller.getAmountOfColumns) {
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

  val statusLine = new TextField(controller.statusText, 20)


  val bp = new BoxPanel(Orientation.Vertical)
  bp.contents += amountPanel
  bp.contents += setNamePanel
  bp.contents += gridPanel
  bp.visible = true
  this.contents = bp
//  contents = new BorderPanel {
//    add(amountPanel, BorderPanel.Position.North)
//    add(gridPanel, BorderPanel.Position.Center)
//    add(setNamePanel, BorderPanel.Position.West)
//    add(setRounds, BorderPanel.Position.East)
//  }

  setRounds.visible = false
//  setNamePanel.visible = false
//  gridPanel.visible = false

  def addPlayer(): Unit = {
    val firstName = setNamePanel.textfieldFirstName.text
    val lastName = setNamePanel.textfieldLastName.text
    val age = setNamePanel.textfieldAge.text
    val p = List("p", firstName, lastName, age)
    controller.addPlayer(p);
  }

  def selectRound(): Unit = {
    val rounds = setRounds.textfieldRounds.text
    controller.createRandomGrid(4, rounds.toInt)
  }

  def clearTextFields(): Unit = {
    setNamePanel.textfieldFirstName.text = ""
    setNamePanel.textfieldLastName.text = ""
    setNamePanel.textfieldAge.text = ""

  }

  def clearRound(): Unit = {
    setRounds.textfieldRounds.text = ""
  }

  reactions += {
    case ButtonClicked(b) => {

      if (b.name == "player1" || b.name == "player2") {
//        setNamePanel.visible = true
        bp.contents -= amountPanel
        this.pack()
      } else if (b.name == "confirm") {
        addPlayer()
        clearTextFields()
        setNamePanel.visible = false
      } else if (b.name == "selectRound") {
        setRounds.visible = true
      } else if (b.name == "confirmRound") {
        selectRound()
        clearRound()
      }

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