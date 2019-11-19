package app.pages

import app.components.SideBar
import app.routes.Chapter
import japgolly.scalajs.react._
import japgolly.scalajs.react.extra.router.RouterCtl
import japgolly.scalajs.react.vdom.html_<^._

object ChapterPage {

  val component = ScalaComponent
    .builder[Props]("ChapterPage")
    .render_P { P =>
      <.div(
        ^.cls := "container-fluid",
        <.div(
          ^.cls := "row",
          <.div(
            ^.cls := "col-4",
            SideBar(SideBar.Props(Chapter.menu, P.chapter, P.ctrl))
          ),
          <.div(
            ^.cls := "col-8",
            P.chapter.render()
          )
        )
      )
    }
    .build

  case class Props(chapter: Chapter, ctrl: RouterCtl[Chapter])

  def apply(props: Props): VdomElement = component(props).vdomElement
}
