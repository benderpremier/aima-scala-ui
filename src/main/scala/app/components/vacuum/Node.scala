package app.components.vacuum

import aima.core.agent.Agent
import aima.core.environment.vacuum.{
  DirtyPercept,
  VacuumAction,
  VacuumMapNode,
  VacuumPercept
}
import japgolly.scalajs.react.ScalaComponent
import japgolly.scalajs.react.vdom.html_<^._
import scalacss.ScalaCssReact._
import aima.core.environment.vacuum.Vacuum

object Node {

  def renderAgent(agent: Agent[Vacuum, VacuumPercept, VacuumAction]) =
    <.i(
      ^.cls := "fas fa-robot align-middle"
    )

  val component = ScalaComponent
    .builder[VacuumMapNode]("VacuumMapNode")
    .render_P { node =>
      <.div(
        ^.cls := "d-inline-block align-top text-center",
        Styles.node,
        node.maybeAgent.whenDefined(renderAgent),
        (Styles.dirty).when(node.dirtStatus == DirtyPercept)
      )
    }
    .build

  def apply(node: VacuumMapNode) = component(node)

}
