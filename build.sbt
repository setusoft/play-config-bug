import play.sbt.{ PlayLayoutPlugin, PlayScala }
import sbt._

////*******************************
//// Auth module
////*******************************
val modelerAuth: Project = Project(
  id = "auth",
  base = file("auth"),
  settings = Seq(
    libraryDependencies ++= Seq(
      specs2 % Test
    )
  )
).enablePlugins(
  PlayScala
).disablePlugins(
  PlayLayoutPlugin
)

////*******************************
//// Root module
////*******************************
val root: Project = Project(
  id = "root",
  base = file("."),
  aggregate = Seq(
    modelerAuth
  ),
  dependencies = Seq(
    modelerAuth
  )
).enablePlugins(
  PlayScala
).disablePlugins(
  PlayLayoutPlugin
)
