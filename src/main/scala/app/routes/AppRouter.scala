package app.routes

import japgolly.scalajs.react.extra.router.{Resolution, RouterConfigDsl, RouterCtl, _}
import japgolly.scalajs.react.vdom.html_<^._

object AppRouter {

  sealed trait AppPage

  case object Home extends AppPage
  case class Chapters(c: Chapter) extends AppPage

  val config = RouterConfigDsl[AppPage]

}
