package app.components

import app.routes.Chapter
import japgolly.scalajs.react.ScalaComponent
import japgolly.scalajs.react.extra.router.RouterCtl
import japgolly.scalajs.react.vdom.html_<^.{<, _}

object SideBar {

  case class Props(menus: Vector[Chapter],
                   selectedPage: Chapter,
                   ctrl: RouterCtl[Chapter])

  val component =
    ScalaComponent
      .builder[Props]("SideBar")
      .render_P { P =>
        <.nav(
          ^.cls := "nav flex-column",
          P.menus.toTagMod(chapter =>
              <.a(
                ^.href := P.ctrl.urlFor(chapter).value,
                ^.cls := "nav-link",
                chapter.routerPath,
                P.ctrl.setOnLinkClick(chapter))
          )
        )
      }
      .build

  def apply(props: Props) = component(props)
}
