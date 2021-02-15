package de.htwg.se.stadtlandfluss.modul

import de.htwg.se.stadtlandfluss.model.Cell
import org.scalatest._

class CellSpec extends WordSpec with Matchers {

  "A Cell" when {
    "not set to any value " should {
      val emptyCell = Cell("")
      "have value zero" in {
        emptyCell.value should be("")
      }
      "not be set" in {
        emptyCell.isSet should be(false)
      }
    }
    "set to a specific value" should {
      val nonEmptyCell = Cell("Hund")
      "return that value" in {
        nonEmptyCell.value should be("Hund")
      }
      "be set" in {
        nonEmptyCell.isSet should be(true)
      }
    }
  }

}