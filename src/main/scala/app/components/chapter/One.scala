package app.components.chapter

import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.html_<^._

object One {

  val component = ScalaComponent.builder
    .static("Two")(<.div("This is chapter one :)"))
    .build

  def apply() = component().vdomElement

}
