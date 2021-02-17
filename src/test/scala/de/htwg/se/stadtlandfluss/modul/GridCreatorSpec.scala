package de.htwg.se.stadtlandfluss.modul

import de.htwg.se.stadtlandfluss.model.{GridCreator}
import org.scalatest.{Matchers, WordSpec}

class GridCreatorSpec extends WordSpec with Matchers {
  "A GridCreator " should {
    "create an empty Grid and fill it with cells with a creation strategy" in {
      val tinyGrid = new GridCreator(1, 1).createGrid(1, 1)
      tinyGrid.cell(0, 0).value should be("automarke")
    }
  }
}
