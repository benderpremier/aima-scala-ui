package app.components.vacuum

import aima.core.environment.vacuum.{SimpleReflexVacuumAgent, VacuumMap}
import japgolly.scalajs.react.ScalaComponent
import japgolly.scalajs.react.vdom.html_<^._

object Vacuum {

  //TODO make this evolve over time
  val vacuum = VacuumMap().addAgent(new SimpleReflexVacuumAgent)

  val component = ScalaComponent.builder[Unit]("VacuumEnvironment")
    .render_(
      <.div(Map(vacuum))
    )
    .build

  def apply() = component()

}
