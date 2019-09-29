name := "aima-scala-ui"

version := "0.1"

scalaVersion := "2.12.8"

lazy val librarySettings = Seq(
  "com.github.aimacode.aima-scala"  %% "core"  % "0.1.0-SNAPSHOT"  ,
)

lazy val hello = (project in file("."))
  .settings(
    name := "ui",
    libraryDependencies ++= librarySettings
  )
