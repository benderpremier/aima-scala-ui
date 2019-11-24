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

// hot reloading configuration:
// inspired by https://github.com/OutWatch/seed.g8/blob/update/src/main/g8/build.sbt
// https://github.com/scalacenter/scalajs-bundler/issues/180
addCommandAlias("dev", "; compile; fastOptJS::startWebpackDevServer; devwatch; fastOptJS::stopWebpackDevServer")
addCommandAlias("devwatch", "~; fastOptJS; copyFastOptJS")

//version in webpack := "4.41.2"
//version in startWebpackDevServer := "3.1.1"
webpackDevServerExtraArgs := Seq("--progress", "--color")
webpackConfigFile in fastOptJS := Some(baseDirectory.value / "webpack.config.dev.js")

webpackBundlingMode in fastOptJS := BundlingMode.LibraryOnly() // https://scalacenter.github.io/scalajs-bundler/cookbook.html#performance

// when running the "dev" alias, after every fastOptJS compile all artifacts are copied into
// a folder which is served and watched by the webpack devserver.
// this is a workaround for: https://github.com/scalacenter/scalajs-bundler/issues/180
lazy val copyFastOptJS = TaskKey[Unit]("copyFastOptJS", "Copy javascript files to target directory")
copyFastOptJS := {
  val inDir = (crossTarget in (Compile, fastOptJS)).value
  val outDir = (crossTarget in (Compile, fastOptJS)).value / "dev"
  val files = Seq(
    name.value.toLowerCase + "-fastopt-loader.js",
    name.value.toLowerCase + "-fastopt-library.js",
    name.value.toLowerCase + "-fastopt.js"
  ) map { p => (inDir / p, outDir / p) }
  IO.copy(files, overwrite = true, preserveLastModified = true, preserveExecutable = true)
}
