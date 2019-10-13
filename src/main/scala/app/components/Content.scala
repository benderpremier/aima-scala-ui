package app.components

import japgolly.scalajs.react.ScalaComponent
import japgolly.scalajs.react.vdom.html_<^._
import scalacss.ScalaCssReact._

object Content {
  val component =
    ScalaComponent.builder[Unit]("Content")
      .renderStatic(<.div(
        ^.cls := "row",
        <.h1("AIMA ui")
      ))
      .build
  def apply() = component()
}