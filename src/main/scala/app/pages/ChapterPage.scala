package app.pages

import app.routes.Chapter

import japgolly.scalajs.react._
import japgolly.scalajs.react.extra.router.RouterCtl
import japgolly.scalajs.react.vdom.html_<^._

object ChapterPage {

  val component = ScalaComponent
    .builder[Props]("ChapterPage")
    .render_P { P =>

    }

  case class Props(chapter: Chapter, ctrl: RouterCtl[Chapter])

  def apply(): ChapterPage = new ChapterPage()
}
