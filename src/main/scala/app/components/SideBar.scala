package app.components

import japgolly.scalajs.react.ScalaComponent
import japgolly.scalajs.react.vdom.html_<^._
import scalacss.ScalaCssReact._

object SideBar {
  val component =
    ScalaComponent.builder[Unit]("SideBar")
      .renderStatic(<.div(
        ^.cls := "col-4",
        <.nav(
          ^.cls := "nav flex-column",
          <.a(
            ^.cls := "nav-link",
            ^.href := "#",
            "Chapter 1"
          ),
          <.a(
            ^.cls := "nav-link",
            ^.href := "#",
            "Chapter 2"
          )
        )
      ))
      .build

  def apply() = component()
}
