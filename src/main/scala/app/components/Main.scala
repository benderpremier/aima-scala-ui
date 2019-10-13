package app.components

import japgolly.scalajs.react.ScalaComponent
import japgolly.scalajs.react.vdom.html_<^._
import scalacss.ScalaCssReact._

object Main {
  val component = ScalaComponent.builder[Unit]("Main")
    .renderStatic(<.div(
      ^.cls := "container-fluid",
//      app.Styles.app,
        <.div(
          ^.cls := "row",
          SideBar(),
          Content()
        )))
    .build
  def apply() = component()

}
