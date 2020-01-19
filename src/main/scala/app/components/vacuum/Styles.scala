package app.components.vacuum

import app.CssSettings._

object Styles extends StyleSheet.Inline {
  import dsl._

  val row = style(
    )

  val node = style(
    border(1 px, solid, black),
    width(30 px),
    height(30 px)
  )

  val dirty = style(
    // backgroundImage := "radial-gradient(#212121 20%, transparent 20%)"
    backgroundColor(c"#919191")
  )

  val stepTable = style(
    )

}
