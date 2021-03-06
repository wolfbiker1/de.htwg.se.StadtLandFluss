package de.htwg.se.stadtlandfluss.aview.gui

import java.awt.Color

import de.htwg.se.stadtlandfluss.controller.{CandidatesChanged, CellChanged, Controller, GridSizeChanged, PlayerAdded, gameStarted}

import scala.swing.Swing.LineBorder
import scala.swing._
import scala.swing.event.{ButtonClicked, Event, Key, KeyTyped}

class SwingGui(controller: Controller) extends Frame {
  listenTo(controller)
  centerOnScreen()
  this.title = "Stadt Land Fluss"
  val statusLine = new TextField(controller.statusText, 20)
  statusLine.background = new Color(94, 129, 172)

  override def closeOperation(): Unit = {
    System.exit(-1)
  }

  /*
   * Methods
   *
   */
  def game_start(): Unit = {
    if (controller.gameIsReady){
      updateStatus
    }
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
    println(rounds.toInt)
    controller.setUpRandomCharacters(rounds.toInt)
    controller.createRandomGrid(4, rounds.toInt)
  }
  def cellInput(): Unit ={
//    val input = gridPanel.cells
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
    pack()
  }

  /*
   * Panels
   *
   */
  val controlPanel = new FlowPanel {
    background = new Color(46, 52, 64)

    val btnPlayer1: Button = new Button("<html><font color='#5e81ac'> Player 1 </font></html>") {
      name = "player1"
      this.background = new Color(5, 25, 100)
    }
    val btnPlayer2: Button = new Button("<html><font color='#5e81ac'> Player 2 </font></html>") {
      name = "player2"
      this.background = new Color(5, 25, 100)
    }
    val btnSelectRounds: Button = new Button("<html><font color='#5e81ac'> Roundoptions </font></html>") {
      name = "selectRound"
      this.background = new Color(5, 25, 100)
    }
    contents += btnPlayer1
    contents += btnPlayer2
//    contents += runGame
    contents += btnSelectRounds
  }



  val setRoundsPanel = new FlowPanel {
    background = new Color(46, 52, 64)
    val labelRounds: Label = new Label("Rounds")
    val textfieldRounds = new TextField("", 50)

    textfieldRounds.background = new Color(94, 129, 172)


    val btnRunGame: Button = new Button("Run Game") {
      name = "runGame"
      this.background = new Color(5, 25, 100)
    }

    contents += labelRounds
    contents += textfieldRounds
    contents += btnRunGame
    updateStatus

  }
def updateStatus: Unit ={
  statusLine.text = controller.statusText
}


  val setNamePanel = new GridPanel(5, 1) {
    background = new Color(46, 52, 64)
    //  name
    val labelFirstName: Label = new Label("<html><font color='#5e81ac'>Name:</font></html>")
    labelFirstName.background = new Color(5, 25, 100)
    contents += labelFirstName
    val textfieldFirstName = new TextField("", 50)
    textfieldFirstName.background = new Color(94, 129, 172)
    contents += textfieldFirstName
    updateStatus

    //  lastname
    val labelLastName = new Label("<html><font color='#5e81ac'>Last Name:</font></html>")
    contents += labelLastName
    val textfieldLastName = new TextField("", 50)
    textfieldLastName.background = new Color(94, 129, 172)
    contents += textfieldLastName
    updateStatus

    //  age
    val age = new Label("<html><font color='#5e81ac'>Age:</font></html>")
    contents += age
    val textfieldAge = new TextField("", 50)
    textfieldAge.background = new Color(94, 129, 172)
    contents += textfieldAge

    // confirm button
    val btnConfirmPlayer: Button = new Button("<html><font color='#5e81ac'> Confirm </font></html>") {
      name = "confirmPlayer"
      this.background = new Color(5, 25, 100)
    }
    contents += btnConfirmPlayer

    updateStatus
  }


  def gridPanel: GridPanel = new GridPanel(controller.getAmountOfRows, controller.getAmountOfColumns) {
    border = LineBorder(java.awt.Color.BLACK, 2)
    background = new Color(46, 52, 64)
//    controller.setUpRandomCharacters(controller.getAmountOfRows)
    val rounds = setRoundsPanel.textfieldRounds.text
//    controller.createRandomGrid(4, controller.getAmountOfRows)
    val width = 4
    println("->", controller.getAmountOfRows)
    val height = controller.getAmountOfRows
    val textfieldLastName = new TextField(" <text here> ", 50)
    for (row <- 0 until height; column <- 0 until width) {
      val cellPanels = new InputField(row, column, controller)

      val label = new Label {
        text = cellPanels.getCellContent(row, column)
//        text = s"<html><font color='red'>" + cellPanels.getCellContent(row, column) + "</font></html>"
        font = new Font("Verdana", 1, 36)
//        new Font()
        background = new Color(46, 52, 64)
      }

      val cellPanel = new BoxPanel(Orientation.Vertical) {
//        background = new Color(46, 52, 64)
        preferredSize = new Dimension(51, 51)
        border = Swing.BeveledBorder(Swing.Raised)

        if (row == 0) {
          contents += label
        } else {
          val textfieldLastName = new TextField(s"<html><font color='red'>" + "foobar"+ "</font></html>", 50)
          textfieldLastName.background =  new Color(46, 52, 64)
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
      updateStatus
    }
  }

  val panelSouth = new BoxPanel(Orientation.Vertical)
  panelSouth.visible = true
  val panelCenter = new BoxPanel(Orientation.Vertical)
  panelCenter.visible = true

  contents = new BorderPanel {
    add(controlPanel, BorderPanel.Position.North)
    add(panelCenter, BorderPanel.Position.Center)
    add(panelSouth, BorderPanel.Position.South)
  }

  /*
   * Listeners
   */
  listenTo(controlPanel.btnPlayer1)
  listenTo(controlPanel.btnPlayer2)
  listenTo(controlPanel.btnSelectRounds)
  listenTo(setNamePanel.btnConfirmPlayer)
  listenTo(setNamePanel.textfieldFirstName)
  listenTo(setNamePanel.textfieldLastName)
  listenTo(setNamePanel.textfieldAge)
  listenTo(setRoundsPanel.labelRounds)
  listenTo(setRoundsPanel.textfieldRounds)
  listenTo(setRoundsPanel.btnRunGame)

  reactions += {

    case ButtonClicked(b) => {
      if (b.name == "player1" || b.name == "player2") {
        panelSouth.contents -= setRoundsPanel
        panelSouth.contents += setNamePanel
        panelSouth.contents += statusLine
      } else if (b.name == "confirmPlayer") {
        panelSouth.contents -= setNamePanel
        addPlayer()
        clearTextFields()
      } else if (b.name == "selectRound") {
        panelSouth.contents += setRoundsPanel
        panelSouth.contents -= setNamePanel
      } else if(b.name == "runGame"){
        selectRound()
        clearRound()
        panelSouth.contents -= setRoundsPanel
        panelCenter.contents += gridPanel
        game_start()
      }
      flushPanel()
    }
    case event: gameStarted => game_start()
//    case event: CellChanged => redraw
    case event: CandidatesChanged => redraw
    case event: PlayerAdded => redraw

  }


  visible = true
  redraw

  def redraw = {
    statusLine.text = controller.statusText
  }
}