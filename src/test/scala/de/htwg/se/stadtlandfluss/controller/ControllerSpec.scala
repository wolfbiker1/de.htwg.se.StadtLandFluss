package de.htwg.se.stadtlandfluss.controller

import de.htwg.se.stadtlandfluss.model.Grid
import de.htwg.se.stadtlandfluss.util.Observer
import org.scalatest.{Matchers, WordSpec}

class ControllerSpec extends WordSpec with Matchers {

  "A Controller" when {
    "observed by an Observer" should {
      val controller = Controller.getController
      val observer = new Observer {
        var updated: Boolean = false
        def isUpdated: Boolean = updated
        override def update: Boolean = {updated = true; updated}
      }
      controller.add(observer)
      "notify its Observer after creation" in {
        controller.createEmptyGrid(4,4)
        observer.updated should be(true)
        controller.grid.width should be(4)
        controller.grid.height should be(4)
      }
      "notify its Observer after selecting Rounds " in {
        controller.rounds(4,4)
        observer.updated should be(true)
        controller.grid.width should be(4)
        controller.grid.height should be(4)
      }

      "notify its Observer after setting a cell" in {
        controller.set(1,1,"")
        observer.updated should be(true)
        controller.grid.cell(1,1).value should be ("")
      }

    }
  }

}
