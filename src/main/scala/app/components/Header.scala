package app.components

import japgolly.scalajs.react.ScalaComponent
import japgolly.scalajs.react.vdom.html_<^._
import scalacss.ScalaCssReact._

object Header {
  val component =
    ScalaComponent.builder[Unit]("Header")
      .renderStatic(<.header(
        ^.cls := "navbar navbar-dark bg-dark",
        <.a(
          ^.cls := "navbar-brand",
          ^.href := "#",
          "AIMA"
        )
      ))
      .build
  def apply() = component()
}
