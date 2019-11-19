package app.components

import app.menu.Menu
import app.routes.AppRouter.AppPage
import japgolly.scalajs.react.{Reusability, ScalaComponent}
import japgolly.scalajs.react.extra.router.RouterCtl
import japgolly.scalajs.react.vdom.html_<^._
import scalacss.ScalaCssReact._

object Header {

  case class Props(menus: Vector[Menu],
                   selectedPage: AppPage,
                   ctrl: RouterCtl[AppPage])

  val dataToggle = VdomAttr("data-toggle")
  val dataTarget = VdomAttr("data-target")
  val component =
    ScalaComponent
      .builder[Props]("Header")
      .render_P { P =>
        <.header(
          ^.cls := "navbar navbar-expand-md navbar-dark bg-dark",
          <.a(
            ^.cls := "navbar-brand",
            ^.href := "#",
            "AIMA"
          ),
          <.button(
            ^.cls := "navbar-toggler",
            ^.`type` := "button",
            dataToggle := "collapse",
            dataTarget := "#navbarNav",
            ^.aria.controls := "navbarNav",
            ^.aria.expanded := "false",
            ^.aria.label := "Toggle navigation",
            <.span(
              ^.cls := "navbar-toggler-icon"
            )
          ),
          <.div(
            ^.cls := "collapse navbar-collapse",
            ^.id := "navbarNav",
            <.div(
              ^.cls := "navbar-nav",
              P.menus.toTagMod{ menu =>
                <.a(
                  ^.cls := "nav-item nav-link",
                  menu.name,
                  P.ctrl setOnClick menu.route
                )
              }
            )
          )
        )
      }
      //.configure(Reusability.shouldComponentUpdate)
      .build
  def apply(props: Props) = component(props)
}
