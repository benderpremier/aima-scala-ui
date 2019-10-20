package app.components.chapter

import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.html_<^._

object Two {

  val component = ScalaComponent.builder
    .static("Two")(<.div("This is chapter two"))
    .build

  def apply() = component().vdomElement

}
