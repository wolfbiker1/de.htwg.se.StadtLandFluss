package de.htwg.se.stadtlandfluss

import com.google.inject.Guice
import de.htwg.se.stadtlandfluss.aview.Tui
import de.htwg.se.stadtlandfluss.aview.gui.SwingGui
import de.htwg.se.stadtlandfluss.controller.controllerBaseImpl.Controller
import de.htwg.se.stadtlandfluss.controller.controllerBaseImpl.{CellChanged, gameStarted}

import scala.io.StdIn.readLine

object SLF {
  val injector = Guice.createInjector(new SLFModule)
  val controller = Controller.getController
  //val controller = injector.getInstance(classOf[Controller])
//  val controller: Controller = injector.getInstance(s.getClass)



  controller.publish(new CellChanged)

  val tui = new Tui(controller)
  val gui = new SwingGui(controller)

  def main(args: Array[String]): Unit = {
    var input: String = ""
    controller.publish(new gameStarted)
    do {
      input = readLine()

      tui.processInputLine(input)
    } while (input != "q")
  }
}