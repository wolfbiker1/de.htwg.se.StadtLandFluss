package de.htwg.se.stadtlandfluss.aview.gui

import java.awt.Color
import de.htwg.se.stadtlandfluss.controller.{CandidatesChanged, CellChanged, Controller, GridSizeChanged, PlayerAdded, gameStarted}

import scala.swing.Swing.LineBorder
import scala.swing._
import scala.swing.event.{ButtonClicked, Event, Key, KeyTyped, MouseClicked, MousePressed}

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

    val btnPlayer1: Button = new Button("<html><font color='#eceff4'> Player 1 </font></html>") {
      name = "player1"
      this.background = new Color(76, 86, 106)
    }
    val btnPlayer2: Button = new Button("<html><font color='#eceff4'> Player 2 </font></html>") {
      name = "player2"
      this.background = new Color(76, 86, 106)
    }
    val btnSelectRounds: Button = new Button("<html><font color='#eceff4'> Roundoptions </font></html>") {
      name = "selectRound"
      this.background = new Color(76, 86, 106)
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

    textfieldRounds.background = new Color(216, 222, 233)


    val btnRunGame: Button = new Button("<html><font color='#eceff4'> Run Game </font></html>") {
      name = "runGame"
      this.background = new Color(76, 86, 106)
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
    val labelFirstName: Label = new Label("<html><font color='#eceff4'>Name:</font></html>")
    labelFirstName.background = new Color(216, 222, 233)
    contents += labelFirstName
    val textfieldFirstName = new TextField("", 50)
    textfieldFirstName.background = new Color(216, 222, 233)
    contents += textfieldFirstName
    updateStatus

    //  lastname
    val labelLastName = new Label("<html><font color='#eceff4'>Last Name:</font></html>")
    contents += labelLastName
    val textfieldLastName = new TextField("", 50)
    textfieldLastName.background = new Color(216, 222, 233)
    contents += textfieldLastName
    updateStatus

    //  age
    val age = new Label("<html><font color='#eceff4'>Age:</font></html>")
    contents += age
    val textfieldAge = new TextField("", 50)
    textfieldAge.background = new Color(216, 222, 233)
    contents += textfieldAge

    // confirm button
    val btnConfirmPlayer: Button = new Button("<html><font color='#eceff4'> Confirm </font></html>") {
      name = "confirmPlayer"
      this.background = new Color(76, 86, 106)
    }
    contents += btnConfirmPlayer

    updateStatus
  }


  /*
   * Main Game Board
   */
  def gridPanel: GridPanel = new GridPanel(controller.getAmountOfRows, controller.getAmountOfColumns) {
    border = LineBorder(java.awt.Color.BLACK, 2)
    val width = 4
    val height: Int = controller.getAmountOfRows

    for (row <- 0 until height; column <- 0 until width) {
      val inputField = new InputField(row, column, controller)

      val boxForTextFields = new BoxPanel(Orientation.Vertical) {
        listenTo(mouse.clicks)
        preferredSize = new Dimension(51, 51)
        border = Swing.BeveledBorder(Swing.Raised)
        val textfieldLastName: TextField = new TextField(inputField.getCellContent(row, column), 50)
        textfieldLastName.enabled = false
        textfieldLastName.background =  new Color(59, 66, 82)

          if (controller.getRound() == row) {
            textfieldLastName.background =  new Color(76, 86, 106)
            textfieldLastName.enabled = true

          textfieldLastName.foreground = new Color(236, 239, 244)
          }
      }
      boxForTextFields.contents += new TextField(inputField.getCellContent(row, column), 50) {
        listenTo(mouse.clicks)
        this.name = "row"
        this.reactions +=  {
          case MouseClicked(s, p, _, _, _) => {
            println(s.name)
            println("get")
          }
          case e: CellChanged => {
            repaint
          }
        }
      }
      contents += boxForTextFields


      reactions += {
        case MouseClicked(_, p, _, _, _) => {
          println(p)
        }
        case e: CellChanged => {
          repaint
        }
      }
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
    case MousePressed(_, p, _, _, _) => {
      println(p)
      this.closeOperation()
    }
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

//    rebuild gridPanel
//    idea: attach, detach, flush panel
  }
}