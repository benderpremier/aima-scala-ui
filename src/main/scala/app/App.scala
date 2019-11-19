package app

import org.scalajs.dom.document
import CssSettings._
import app.routes.AppRouter
import scalacss.ScalaCssReact._

object App {
  def main(args: Array[String]): Unit = {
    Styles.addToDocument()
    AppRouter.router().renderIntoDOM(document.getElementById("root"))
  }
}
