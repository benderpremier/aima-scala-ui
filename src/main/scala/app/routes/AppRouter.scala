package app.routes

import app.components.Header
import app.menu.Menu
import japgolly.scalajs.react.extra.router.{Resolution, RouterConfigDsl, RouterCtl, _}
import japgolly.scalajs.react.vdom.html_<^._
import scalajsreact.template.pages.HomePage

object AppRouter {

  sealed trait AppPage

  case object Home extends AppPage
  case class Chapters(c: Chapter) extends AppPage

  val config = RouterConfigDsl[AppPage].buildConfig { dsl =>
    import dsl._
    val chapterRoutes: Rule =
      Chapter.routes.prefixPath_/("#chapter").pmap[AppPage](Chapters) {
        case Chapters(c) => c
      }
    (trimSlashes
      | staticRoute(root, Home) ~> render(HomePage())
      | chapterRoutes)
      .notFound(redirectToPage(Home)(Redirect.Replace))
      .renderWith(layout)
  }

  val mainMenu = Vector(
    Menu("Home", Home),
    Menu("Chapters", Chapters(Chapter.Chapter1))
  )

  def layout(c: RouterCtl[AppPage], r: Resolution[AppPage]) =
    <.div(
      Header(Header.Props(mainMenu, r.page, c)),
      r.render()
    )

  val baseUrl = BaseUrl.fromWindowOrigin / "index.html"

  val router = Router(baseUrl, config)


}
