package de.htwg.se.stadtlandfluss.model.fileIoComponent.fileIoXMLImpl

import com.google.inject.Guice
import com.google.inject.name.Names
import de.htwg.se.stadtlandfluss.SLFModule
import de.htwg.se.stadtlandfluss.controller.ControllerInterface
import de.htwg.se.stadtlandfluss.model.Round
import de.htwg.se.stadtlandfluss.model.fileIoComponent.FileIOInterface
import de.htwg.se.stadtlandfluss.model.gridComponent.GridInterface
import de.htwg.se.stadtlandfluss.model.gridComponent.gridBaseImpl.GridCreator
import de.htwg.se.stadtlandfluss.model.playerComponent.Player

import scala.xml.{Elem, NodeSeq, PrettyPrinter, XML}

class FileIo extends FileIOInterface {

  override def restoreSnapshot(controller: ControllerInterface): GridInterface = {
    val file = scala.xml.XML.loadFile("snapshot.xml")
    val players = file \\ "Player"
    for (p <- players) {
      controller.addPlayer(List("p", (p \ "firstname").text, (p \ "lastname").text, (p \ "age").text))
    }

    val field = file \\ "grid"
    var grid: GridInterface = new GridCreator(controller.getStaticNumberOfColumns, (field \ "@height").text.toInt).createGrid()
    val cells = field \ "cell"
    for (cellElement <- cells) {
      val row = (cellElement \ "@row").text.toInt
      val col = (cellElement \ "@col").text.toInt
      val content = (cellElement \\ "content").text

      val character = (cellElement \\ "character").text
      controller.setCharacterForRow(row, character.charAt(1))
      grid = grid.set(row, col, content)
    }
    grid
  }

  override def save(controller: ControllerInterface): Unit = {
    import java.io._
    val pw = new PrintWriter(new File("snapshot.xml"))
    val prettyPrinter = new PrettyPrinter(120,6)

    val xml = prettyPrinter.format(saveGame(controller))
    pw.write(xml)
    pw.close
  }


  def saveGame(controller: ControllerInterface): Elem = {
    <game>
      {players_toXML()}
      {gridToXml(controller)}
    </game>
  }

  //In Liste
  def players_toXML(): Elem = {
    val playerMap = Round.getPlayerMap
    <Players>
      {for (i <- 0 until  playerMap.size) yield playerToXML(playerMap(i))}
    </Players>
  }
  //Player in XML
  def playerToXML(player: Player): Elem = {
    <Player>
      <firstname>{player.firstname}</firstname>
      <lastname>{player.lastname}</lastname>
      <age>{player.age}</age>
      <points>{player.getPoints()}</points>
    </Player>
  }
  def gridToXml(controller: ControllerInterface):Elem = {
    <grid height={ controller.getAmountOfRows.toString } width={ controller.getAmountOfColumns.toString} >
      {
      for (row <- 0 until controller.getAmountOfRows; col <- 0 until controller.getAmountOfColumns)
        yield cellToXml(controller, row, col, controller.getCharacterForRow(row))
      }
    </grid>
  }

  def cellToXml(controller: ControllerInterface, row: Int, col: Int, character: Char):Elem = {
    <cell row={ row.toString } col={ col.toString } >
      <content>
        { controller.getCell(row, col).value }
      </content>

      <character>
        { controller.getCharacterForRow(row) }
      </character>
    </cell>
  }


}