package app.components

import japgolly.scalajs.react.ScalaComponent
import japgolly.scalajs.react.vdom.html_<^._
import scalacss.ScalaCssReact._

object SideBar {
  val component =
    ScalaComponent.builder[Unit]("SideBar")
      .renderStatic(<.div(
        ^.cls := "col-4",
        <.h2("AIMA")
      ))
      .build
  def apply() = component()
}
