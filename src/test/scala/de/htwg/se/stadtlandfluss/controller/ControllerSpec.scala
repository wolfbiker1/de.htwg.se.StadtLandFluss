package de.htwg.se.stadtlandfluss.controller

import de.htwg.se.stadtlandfluss.SLF.controller
import de.htwg.se.stadtlandfluss.aview.Tui
import de.htwg.se.stadtlandfluss.controller.controllerBaseImpl.Controller
import de.htwg.se.stadtlandfluss.model.gridComponent.gridBaseImpl.Grid
import de.htwg.se.stadtlandfluss.util.Observer
import org.scalatest.{Matchers, WordSpec}

import scala.swing.Publisher

class ControllerSpec extends WordSpec with Matchers {

  "A Controller" when {
    val controller = Controller.getController
    val name = "heinrich" :: ("hans" :: "14" :: Nil)
    "a new grid" should {
      val smallGrid = new Grid(4, 4)
      controller.createRandomGrid(4, 4)
      "get the Amount of Columns" in {
        controller.getAmountOfColumns should be(4)
      }
      "get the Number of Columns" in {
        controller.getNumberOfColumns should be(4)
      }
      "get the Amount of Rows" in {
        controller.getAmountOfRows should be(4)
      }

    }

    " a grid with player" should {
      controller.addPlayer(name)
      controller.addPlayer(name)
      " should be ready" in {
        controller.isReady should be ( true)
      }
    }

  }
}