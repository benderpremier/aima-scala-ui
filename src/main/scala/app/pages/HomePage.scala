package scalajsreact.template.pages

import scalacss.Defaults._
import scalacss.ScalaCssReact._

import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.html_<^._

object HomePage {

//  object Style extends StyleSheet.Inline {
//    import dsl._
//    val content = style(textAlign.center,
//      fontSize(30.px),
//      minHeight(450.px),
//      paddingTop(40.px))
//  }

  val component =
    ScalaComponent.builder
      .static("HomePage")(<.div("AIMA UI Home Page"))
      .build

  def apply() = component()
}
