package app.routes

import app.components.chapter.Two
import japgolly.scalajs.react.extra.router.RouterConfigDsl
import japgolly.scalajs.react.vdom.VdomElement

sealed abstract class Chapter(
                             val chap: Int,
                             val routerPath: String,
                             val render: () => VdomElement
                             )

object Chapter {

  case object Chapter2 extends Chapter(2, "chapter2", () => Two())

  val menu = Vector(Chapter2)

  val routes = RouterConfigDsl[Chapter].buildRule { dsl =>
    import dsl._
    menu
      .map { i =>
        staticRoute(i.routerPath, i) ~> renderR(
          r =>
        )
      }

  }

}
