package app.components.chapter

import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.html_<^._

object Three {

  val component = ScalaComponent.builder
    .static("Two")(<.div("This is chapter 3 :), testing some live reloading"))
    .build

  def apply() = component().vdomElement

}
