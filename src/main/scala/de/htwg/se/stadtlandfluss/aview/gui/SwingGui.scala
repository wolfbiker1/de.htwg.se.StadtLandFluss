package de.htwg.se.stadtlandfluss.aview.gui

import java.awt.Color
import de.htwg.se.stadtlandfluss.controller.{CandidatesChanged, CellChanged, Controller, PlayerAdded, gameStarted}
import scala.swing.Swing.{BeveledBorder, LineBorder}
import scala.swing._
import scala.swing.event.{ButtonClicked, KeyPressed, MouseClicked}

class SwingGui(controller: Controller) extends Frame {
  listenTo(controller)
  centerOnScreen()
  this.title = "Stadt Land Fluss"
  val statusLine = new Label("<html><font color='#bf616a'>" + controller.statusText + "</font></html>")
  statusLine.background = new Color(59, 66, 82)

  override def closeOperation(): Unit = {
    System.exit(-1)
  }

  /*
   * Methods
   *
   */
  def game_start(): Unit = {
    if (controller.gameIsReady) {
      updateStatus()
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
    controller.setUpRandomCharacters(rounds.toInt)
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
      this.border = BeveledBorder(Swing.Raised)
    }
    val btnPlayer2: Button = new Button("<html><font color='#eceff4'> Player 2 </font></html>") {
      name = "player2"
      this.background = new Color(76, 86, 106)
      this.border = BeveledBorder(Swing.Raised)
    }
    val btnSelectRounds: Button = new Button("<html><font color='#eceff4'> Roundoptions </font></html>") {
      name = "selectRound"
      this.background = new Color(76, 86, 106)
      this.border = BeveledBorder(Swing.Raised)
      this.enabled = false
    }
    val btnUndo: Button = new Button("<html><font color='#eceff4'> Undo </font></html>") {
      name = "btnUndo"
      this.background = new Color(76, 86, 106)
      this.border = BeveledBorder(Swing.Raised)
    }
    val btnRedo: Button = new Button("<html><font color='#eceff4'> Redo </font></html>") {
      name = "btnRedo"
      this.background = new Color(76, 86, 106)
      this.border = BeveledBorder(Swing.Raised)
    }
    val btnSolve: Button = new Button("<html><font color='#a3be8c'> Solve </font></html>") {
      name = "btnSolve"
      this.background = new Color(76, 86, 106)
      this.border = BeveledBorder(Swing.Raised)
    }
    val btnExit: Button = new Button("<html><font color='#bf616a'> Exit </font></html>") {
      name = "btnExit"
      this.background = new Color(76, 86, 106)
      this.border = BeveledBorder(Swing.Raised)
    }
    contents += btnPlayer1
    contents += btnPlayer2

    contents += btnSelectRounds
    contents += btnUndo
    contents += btnRedo
    contents += btnSolve
    contents += btnExit
  }


  val setRoundsPanel = new FlowPanel {
    background = new Color(46, 52, 64)
    val labelRounds: Label = new Label("Rounds")
    val textfieldRounds = new TextField("", 50)

    textfieldRounds.background = new Color(216, 222, 233)


    val btnRunGame: Button = new Button("<html><font color='#bf616a'> Run Game </font></html>") {
      name = "runGame"
      this.background = new Color(76, 86, 106)
      this.border = BeveledBorder(Swing.Raised)
    }

    contents += labelRounds
    contents += textfieldRounds
    contents += btnRunGame
    updateStatus()
  }

  def updateStatus(): Unit = {
    val statusText: String = controller.statusText + " --- " + controller.inGameStatus
    val openColorTag: String = "<html><font color='#bf616a'>"
    val closingColorTag: String = "</font></html>"

    statusLine.text = openColorTag
    statusLine.text += statusText
    if (controller.isReady) {
      statusLine.text += " --- Current Letter: " + controller.currentLetter
    }

    statusLine.text += closingColorTag
  }

  def swapControlButtonsVisibility(): Unit = {
    controlPanel.btnPlayer1.visible = !controlPanel.btnPlayer1.visible
    controlPanel.btnPlayer2.visible = !controlPanel.btnPlayer2.visible
    controlPanel.btnSelectRounds.visible = !controlPanel.btnSelectRounds.visible
  }

  def swapIngameButtons(): Unit = {
    controlPanel.btnUndo.visible = !controlPanel.btnUndo.visible
    controlPanel.btnRedo.visible = !controlPanel.btnRedo.visible
    controlPanel.btnSolve.visible = !controlPanel.btnSolve.visible
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

    //  lastname
    val labelLastName = new Label("<html><font color='#eceff4'>Last Name:</font></html>")
    contents += labelLastName
    val textfieldLastName = new TextField("", 50)
    textfieldLastName.background = new Color(216, 222, 233)
    contents += textfieldLastName

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
      this.border = BeveledBorder(Swing.Raised)
    }
    contents += btnConfirmPlayer
    updateStatus()
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
        border = Swing.BeveledBorder(Swing.Raised)
      }
      boxForTextFields.contents += new TextField() {
        listenTo(mouse.clicks)
        listenTo(keys)
        this.enabled = false
        this.background = new Color(59, 66, 82)

        if (controller.getRound() == row) {
          this.background = new Color(76, 86, 106)
          this.enabled = true
          this.foreground = new Color(236, 239, 244)
        }
        this.text = inputField.getCellContent(row, column)
        this.name = column.toString + this.text
        this.reactions += {
          case KeyPressed(s, c, _, _) =>
            if (c.toString == "Enter" || c.toString == "⏎") {
              controller.set(row, s.name.toInt, this.text)
              updateStatus
            }
          case MouseClicked(s, p, _, _, _) =>
        }
      }

      contents += boxForTextFields
      updateStatus
    }
  }

  val panelSouth = new BoxPanel(Orientation.Vertical)
  panelSouth.visible = true
  val panelCenter = new BoxPanel(Orientation.Vertical)
  panelCenter.visible = false


  swapIngameButtons()

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
  listenTo(controlPanel.btnUndo)
  listenTo(controlPanel.btnRedo)
  listenTo(controlPanel.btnSolve)
  listenTo(controlPanel.btnExit)
  listenTo(setNamePanel.btnConfirmPlayer)
  listenTo(setNamePanel.textfieldFirstName)
  listenTo(setNamePanel.textfieldLastName)
  listenTo(setNamePanel.textfieldAge)
  listenTo(setRoundsPanel.labelRounds)
  listenTo(setRoundsPanel.textfieldRounds)
  listenTo(setRoundsPanel.btnRunGame)

  reactions += {
    case ButtonClicked(b) =>
      if (b.name == "player1" || b.name == "player2") {
        panelSouth.contents -= setRoundsPanel
        panelSouth.contents += setNamePanel
        panelSouth.contents += statusLine
      } else if (b.name == "confirmPlayer") {
        panelSouth.contents -= setNamePanel
        addPlayer()
        clearTextFields()

        // not sure
        if (controller.isReady) {
          controlPanel.btnSelectRounds.enabled = true
        }
      } else if (b.name == "selectRound") {
        panelSouth.contents += setRoundsPanel
        panelSouth.contents -= setNamePanel
      } else if (b.name == "runGame") {
        selectRound()
        clearRound()
        swapIngameButtons()
        swapControlButtonsVisibility()
        panelSouth.contents -= setRoundsPanel
        panelCenter.visible = true
      } else if (b.name == "btnUndo") {
        controller.undo
      } else if (b.name == "btnRedo") {
        controller.redo
      } else if (b.name == "btnSolve") {
        controller.solve()
      } else if (b.name == "btnExit") {
        closeOperation()
      }

      flushPanel()
    case event: gameStarted => game_start()
    case event: CellChanged => redraw()
    case event: CandidatesChanged => redraw()
    case event: PlayerAdded => redraw()

  }


  visible = true
  redraw()

  def redraw(): Unit = {
    statusLine.text = controller.statusText
    panelCenter.contents.clear()
    panelCenter.contents += gridPanel
    panelSouth.contents += statusLine
    this.flushPanel()
  }
}