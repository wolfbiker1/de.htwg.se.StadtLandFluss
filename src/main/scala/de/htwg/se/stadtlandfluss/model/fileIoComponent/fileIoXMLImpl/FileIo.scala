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
    var grid: GridInterface = null
    val file = scala.xml.XML.loadFile("game.xml")
    val players = file \\ "Player"
    var pList = List[Player]()
    for (p <- players.indices) {
      val firstName = (players(p)\ "firstname").text
      val lastName = (players(p)\ "firstname").text
      val age = (players(p)\ "age").text

      pList.appended(Player(firstName, lastName ,age.toInt))
      // controller.addPlayer(pList)
    }



    val player2F= (file \\ "Player" \ "@player2")
    val player2 = player2F.text
    val rounds: Int = (file \ "Game" \ "rounds").text.trim().replace("\n", "").toInt
    val injector = Guice.createInjector(new SLFModule)

    val cellNodes = (file \\ "cell")
    grid
  }

  override def save(controller: ControllerInterface): Unit = scala.xml.XML.save("player.xml", players_toXML())




//  def save(player:  List[Player]): Unit = scala.xml.XML.save("player.xml", players_toXML(player))



  def saveString(player:  List[Player]): Unit = {
    import java.io._
    val pw = new PrintWriter(new File("player.xml"))
    val prettyPrinter = new PrettyPrinter(120,6)
    val xml = prettyPrinter.format(players_toXML())
    pw.write(xml)
    pw.close
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
  def gridToXml(grid: GridInterface):Elem = {
    <grid height={ grid.height.toString } width={grid.width.toString} >
      {
      for {
        row <- 0 until grid.height
        col <- 0 until grid.width
      } yield cellToXml(grid, row, col)
      }
    </grid>
  }

  def cellToXml(grid: GridInterface, row: Int, col: Int):Elem = {
    <cell row={ row.toString } col={ col.toString }  >
      { grid.cell(row, col).value }
    </cell>
  }


}