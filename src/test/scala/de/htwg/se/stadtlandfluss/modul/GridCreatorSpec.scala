package de.htwg.se.stadtlandfluss.modul

import de.htwg.se.stadtlandfluss.model.gridComponent.gridBaseImpl.GridCreator
import org.scalatest.{Matchers, WordSpec}

class GridCreatorSpec extends WordSpec with Matchers {
  "A GridCreator " should {
    "create an empty Grid and fill it with cells with a creation strategy" in {
      val tinyGrid = new GridCreator(4, 4).createGrid()
      tinyGrid.cell(3, 3).value should be("")
    }
  }
}
