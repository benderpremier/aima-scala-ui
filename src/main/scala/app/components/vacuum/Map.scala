package app.components.vacuum

import aima.core.environment.vacuum.{VacuumMap}
import japgolly.scalajs.react.ScalaComponent
import japgolly.scalajs.react.vdom.html_<^._
import scalacss.ScalaCssReact._

object Map {
  val component = ScalaComponent
    .builder[VacuumMap]("VacuumMap")
    .render_P { map =>
      <.div(
        Styles.row,
        map.nodes.toTagMod(Node(_)) //This should be a row long term
      )
    }
    .build

  def apply(map: VacuumMap) = component(map)

}
