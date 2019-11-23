name := "aima-scala-ui"

version := "0.1"

scalaVersion := "2.13.1"

val scalac = Seq(
  "-language:postfixOps"
)
lazy val ui = (project in file("."))
  .settings(
    name := "ui",
    libraryDependencies ++= librarySettings.value,
    scalacOptions ++= scalac
  )
  .enablePlugins(ScalaJSPlugin, ScalaJSBundlerPlugin)

scalaJSUseMainModuleInitializer := true

lazy val librarySettings = Def.setting(
  Seq(
    "org.scala-js" %%% "scalajs-dom" % "0.9.7",
    //AIMA
    "com.github.aimacode.aima-scala"  %%% "core"  % "0.1.0-SNAPSHOT",
    // SCALA-JS REACT
    "com.github.japgolly.scalajs-react" %%% "core" % "1.5.0-RC1",
    "com.github.japgolly.scalajs-react" %%% "extra" % "1.5.0-RC1",
    "com.github.japgolly.scalacss" %%% "core" % "0.6.0-RC1",
    "com.github.japgolly.scalacss" %%% "ext-react" % "0.6.0-RC1"
  )
)

npmDependencies in Compile ++= Seq(
  "react" -> "16.7.0",
  "react-dom" -> "16.7.0"
)
