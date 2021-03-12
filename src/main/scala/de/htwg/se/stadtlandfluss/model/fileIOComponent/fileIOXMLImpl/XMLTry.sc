


import de.htwg.se.stadtlandfluss.model.fileIOComponent.fileIOXMLImpl.FileIO
import de.htwg.se.stadtlandfluss.model.gridComponent.gridBaseImpl.Grid


object ioWorksheet {
  1 + 2
  val height = 9
  val width = 4

  def toXml = {
    <grid height={height.toString} grid.width={width.toString}>
    </grid>
  }

  println(toXml)

  val grid = new Grid(5,5)
  val filledGrid = grid.set(1, 0, "H")
  println(filledGrid.toString)

  val fileIO = new FileIO()
  val xml = fileIO.gridToXml(grid)

}