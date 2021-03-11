package de.htwg.se.stadtlandfluss.model.fileIOComponent.fileIOXMLImpl



  import com.google.inject.Guice
  import com.google.inject.name.Names
  import de.htwg.se.stadtlandfluss.SLFModule
  import de.htwg.se.stadtlandfluss.model.fileIOComponent.FileIOInterface
  import de.htwg.se.stadtlandfluss.model.gridComponent.GridInterface
  import de.htwg.se.stadtlandfluss.model.gridComponent.gridBaseImpl.GridCreator

  import scala.xml.{NodeSeq, PrettyPrinter}
  import scala.xml
  import scala.xml.{NodeSeq, PrettyPrinter}
  class FileIO extends FileIOInterface {

    override def load: GridInterface = {
      var grid: GridInterface = null
      val file = scala.xml.XML.loadFile("grid.xml")
      val heightAttr = (file \\ "grid" \ "@height")
      val height = heightAttr.text.toInt
      val widthAttr = (file \\ "grid" \ "@width")
      val width = widthAttr.text.toInt
      val injector = Guice.createInjector(new SLFModule)
      height match {
        //case 4 => grid = injector.instance[GridInterface](Names.named("quicky"))
        //case 8 => grid = injector.instance[GridInterface](Names.named("extended"))
        case _ => grid = new GridCreator(width, height).createGrid()
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
      val prettyPrinter = new PrettyPrinter(120,6)
      val xml = prettyPrinter.format(gridToXml(grid))
      pw.write(xml)
      pw.close
    }

    def gridToXml(grid: GridInterface) = {
      <grid height={ grid.height.toString } width={grid.width.toString} >
        {
        for {
          row <- 0 until grid.height
          col <- 0 until grid.width
        } yield cellToXml(grid, row, col)
        }
      </grid>
    }

    def cellToXml(grid: GridInterface, row: Int, col: Int) = {
      <cell row={ row.toString } col={ col.toString }  >
        { grid.cell(row, col).value }
      </cell>
    }


}