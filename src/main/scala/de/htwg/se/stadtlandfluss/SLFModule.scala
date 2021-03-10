package de.htwg.se.stadtlandfluss

import com.google.inject.AbstractModule
import com.google.inject.name.Names

import net.codingwell.scalaguice.ScalaModule
import de.htwg.se.stadtlandfluss.model.gridComponent.GridInterface
import de.htwg.se.stadtlandfluss.model.gridComponent.gridBaseImpl.{Grid, GridCreator}
import de.htwg.se.stadtlandfluss.controller.ControllerInterface
import de.htwg.se.stadtlandfluss.controller.controllerBaseImpl.Controller

class SLFModule extends AbstractModule with ScalaModule {
  def setup() = {
    bind[GridInterface].to[Grid]
    bind[ControllerInterface].to[Controller]
    bind[GridInterface].annotatedWithName("quicky").toInstance(new GridCreator(4,4 ).createGrid())
    bind[GridInterface].annotatedWithName("extended").toInstance(new GridCreator(8,4 ).createGrid())
  }
}
