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
    val name = List("","heinrich","hans","14")
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
      val controller = Controller.getController
      controller.createRandomGrid(4, 4)


      " should be ready" in {
        controller.isReady should be ( false)
      }
      "should have a status" in{
        controller.inGameStatus should be("Player not set")
      }
      "add player" in {
        controller.addPlayer(name)
        controller.addPlayer(name)
      }
      "be ready" in{
        controller.isReady should be ( true)
      }
      "have a turn" in{
        controller.getRound should be ( 1)
      }

    }

  }
}