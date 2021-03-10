package de.htwg.se.stadtlandfluss

import com.google.inject.AbstractModule
import com.google.inject.name.Names
import de.htwg.se.stadtlandfluss.SLF.injector
import net.codingwell.scalaguice.ScalaModule
import de.htwg.se.stadtlandfluss.model.gridComponent.GridInterface
import de.htwg.se.stadtlandfluss.model.gridComponent.gridBaseImpl.{Grid, GridCreator}
import de.htwg.se.stadtlandfluss.controller._
class SLFModule extends AbstractModule with ScalaModule {

  override def configure(): Unit = {
    bind[GridInterface].to[Grid]
    bind[ControllerInterface].to[controllerBaseImpl.Controller]
    bind[GridInterface].annotatedWithName("quicky").toInstance(new GridCreator(4,4 ).createGrid())
    bind[GridInterface].annotatedWithName("extended").toInstance(new GridCreator(8,4 ).createGrid())
  }
}
