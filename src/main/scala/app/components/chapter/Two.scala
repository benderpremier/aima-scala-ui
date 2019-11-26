package app.components.chapter

import app.components.vacuum.Vacuum
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.html_<^._

object Two {

  val component = ScalaComponent.builder[Unit]("Two")
    .render_(
      <.div(
        <.h1("This is chapter two"),
        Vacuum()
      )
    )
    .build

  def apply() = component()

}
