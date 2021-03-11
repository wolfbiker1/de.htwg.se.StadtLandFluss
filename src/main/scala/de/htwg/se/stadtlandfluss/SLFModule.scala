package de.htwg.se.stadtlandfluss

import com.google.inject.AbstractModule
import net.codingwell.scalaguice.ScalaModule
import com.google.inject.name.Names
import de.htwg.se.stadtlandfluss.model.gridComponent.GridInterface
import de.htwg.se.stadtlandfluss.model.gridComponent.gridBaseImpl.{EvaluateStrategyTemplate, EvaluatorCol, EvaluatorRow, Grid, GridCreator}
class SLFModule extends AbstractModule with ScalaModule {

  override def configure(): Unit = {
    bindConstant().annotatedWith(Names.named("default")).to(5)
    bind[GridInterface].to[Grid]
    bind[GridInterface].annotatedWithName("quicky").toInstance(new GridCreator(4,4 ).createGrid())
    bind[GridInterface].annotatedWithName("extended").toInstance(new GridCreator(4,8 ).createGrid())
    bind[EvaluateStrategyTemplate].annotatedWithName("row").toInstance(new EvaluatorRow)
    bind[EvaluateStrategyTemplate].annotatedWithName("col").toInstance(new EvaluatorCol)
  }
}
