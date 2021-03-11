package de.htwg.se.stadtlandfluss.model.fileIOComponent.fileIOXMLImpl

import com.fasterxml.jackson.core.PrettyPrinter
import com.google.inject.Guice
import com.google.inject.name.Names
import de.htwg.se.stadtlandfluss.SLFModule
import de.htwg.se.stadtlandfluss.model.gridComponent.GridInterface

class FileIO {
  override def load: GridInterface = {
    var grid: GridInterface = null
    val file = scala.xml.XML.loadFile("grid.xml")
    val sizeAttr = (file \\ "grid" \ "@size")
    val size = sizeAttr.text.toInt
    val injector = Guice.createInjector(new SLFModule)
    size match {
      case 1 => grid = injector.instance[GridInterface](Names.named("tiny"))
      case 4 => grid = injector.instance[GridInterface](Names.named("small"))
      case 9 => grid = injector.instance[GridInterface](Names.named("normal"))
      case _ =>
    }
    val cellNodes = (file \\ "cell")
    for (cell <- cellNodes) {
      val row: Int = (cell \ "@row").text.toInt
      val col: Int = (cell \ "@col").text.toInt
      val value: String = cell.text.trim
      grid = grid.set(row, col, value)

    }
    grid
  }

  def save(grid: GridInterface): Unit = saveString(grid)

  def saveXML(grid: GridInterface): Unit = {
    scala.xml.XML.save("grid.xml", gridToXml(grid))
  }

  def saveString(grid: GridInterface): Unit = {
    import java.io._
    val pw = new PrintWriter(new File("grid.xml"))
    val prettyPrinter = new PrettyPrinter(120, 4)
    val xml = prettyPrinter.format(gridToXml(grid))
    pw.write(xml)
    pw.close
  }
  def gridToXml(grid: GridInterface) = {
    <grid height={ grid.height.toString }>

      {
      for {
        row <- 0 until grid.height
        col <- 0 until grid.width
      } yield cellToXml(grid, row, col)
      }
    </grid>
  }

  def cellToXml(grid: GridInterface, row: Int, col: Int) = {
    <cell row={ row.toString } col={ col.toString } given={ grid.cell(row, col).toString } >
      { grid.cell(row, col).value }
    </cell>
  }
}
