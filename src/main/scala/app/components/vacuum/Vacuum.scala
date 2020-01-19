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

  def vacuumEnvironment = VacuumEnvironment().addAgent(agent)

  /**
    * For each step we would like to know:
    * - The state of the node on which the agent is
    * - The list of percept the sensors are giving us
    * - The action returned by the program for each percept
    * - For each action the effect of the actuator (some are unreliable)
    */
  case class StepDetail(
      step: Int,
      agentNode: Option[VacuumMapNode],
      runDetails: agent.RunDetail
  )

  case class State(
      env: VacuumEnvironment,
      currentStep: Int,
      stepDetails: Vector[StepDetail]
  ) {
    def step: State = {
      val step                  = currentStep + 1
      val node                  = env.map.getAgentNode(agent)
      val (nextEnv, runDetails) = agent.run(env)
      val stepDetail            = StepDetail(step, node, runDetails)
      State(nextEnv, step, stepDetails :+ stepDetail)
    }
  }

  class Backend($ : BackendScope[Unit, State]) {
    def programStep =
      $.modState(s => s.step)

    def reset =
      $.modState(_ => State(vacuumEnvironment, 0, Vector.empty))

    def render(s: State): VdomElement = <.div(
      ^.cls := "border",
      <.div(
        ^.cls := "m-3",
        <.button(
          "Advance",
          ^.cls := "btn btn-primary btn-sm m-1",
          ^.onClick --> programStep
        ),
        <.button(
          "Reset",
          ^.cls := "btn btn-warning btn-sm m-1",
          ^.onClick --> reset
        )
      ),
      <.div(
        ^.cls := "m-3",
        Map(s.env.map)
      ),
      <.div(
        ^.cls := "m-3",
        StepTable(s.stepDetails)
      )
    )
  }

  val component = ScalaComponent
    .builder[Unit]("VacuumEnvironment")
    .initialState(State(vacuumEnvironment, 0, Vector.empty))
    .renderBackend[Backend]
    .build

  def apply() = component()

}
