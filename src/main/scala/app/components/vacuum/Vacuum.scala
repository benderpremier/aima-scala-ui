package app.components.vacuum

import japgolly.scalajs.react.ScalaComponent
import japgolly.scalajs.react.component.Scala.BackendScope
import japgolly.scalajs.react.vdom.html_<^._
import aima.core.agent.{AgentProgram, Agent}
import japgolly.scalajs.react.Callback
import aima.core.environment.vacuum.{Vacuum => VacuumEnvironment, _}
import aima.core.agent.{Actuator, Sensor}
import aima.core.agent.Environment

object Vacuum {

  val agent: Agent[VacuumEnvironment, VacuumPercept, VacuumAction] =
    new Agent[VacuumEnvironment, VacuumPercept, VacuumAction] {
      val agentProgram = new SimpleReflexVacuumAgentProgram
      val actuators = List[Actuator[VacuumEnvironment, VacuumAction]](
        new SuckerActuator(this),
        new MoveActuator(this)
      )
      lazy val sensors = List[Sensor[VacuumEnvironment, VacuumPercept]](
        new DirtSensor(this, NoPercept),
        new AgentLocationSensor(this, NoPercept)
      )
    }

  val vacuumEnvironment = VacuumEnvironment().addAgent(agent)

  case class State(env: VacuumEnvironment)

  class Backend($ : BackendScope[Unit, State]) {
    def programStep =
      $.modState(s => s.copy(env = agent.run(s.env)))

    def render(s: State): VdomElement = <.div(
      <.button(
        "Advance",
        ^.onClick --> programStep
      ),
      <.div(Map(s.env.map))
    )
  }

  val component = ScalaComponent
    .builder[Unit]("VacuumEnvironment")
    .initialState(State(vacuumEnvironment))
    .renderBackend[Backend]
    .build

  def apply() = component()

}
