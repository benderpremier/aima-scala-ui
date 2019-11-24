package app.routes

import app.components.chapter._
import app.pages.ChapterPage
import japgolly.scalajs.react.extra.router.RouterConfigDsl
import japgolly.scalajs.react.vdom.VdomElement

sealed abstract class Chapter(
                             val chap: Int,
                             val routerPath: String,
                             val render: () => VdomElement
                             )

object Chapter {

  case object Chapter1 extends Chapter(1, "chapter1", () => One())
  case object Chapter2 extends Chapter(2, "chapter2", () => Two())
  case object Chapter3 extends Chapter(3, "chapter3", () => Three())

  val menu = Vector(Chapter1, Chapter2, Chapter3)

  val routes = RouterConfigDsl[Chapter].buildRule { dsl =>
    import dsl._
    menu
      .map { m =>
        staticRoute(m.routerPath, m) ~> renderR(
          r => ChapterPage(ChapterPage.Props(m,r))
        )
      }
      .reduce(_ | _)

  }

}
