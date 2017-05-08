import play.routes.compiler.InjectedRoutesGenerator
import play.sbt.Play
import play.sbt.PlayImport.PlayKeys
import play.sbt.routes.RoutesKeys.{ routesGenerator, routesImport }
import play.twirl.sbt.Import.TwirlKeys
import sbt.Keys._
import sbt._

////*******************************
//// Basic settings
////*******************************
object BasicSettings extends AutoPlugin {
  override def trigger: PluginTrigger = allRequirements

  override def projectSettings: Seq[Setting[_]] = Seq(
    organization := "test",
    version := "0.1.0",
    scalaVersion := crossScalaVersions.value.head,
    crossScalaVersions := Seq("2.11.8"),
    scalacOptions ++= Seq(
      "-deprecation", // Emit warning and location for usages of deprecated APIs.
      "-feature", // Emit warning and location for usages of features that should be imported explicitly.
      "-unchecked", // Enable additional warnings where generated code depends on assumptions.
      "-Xfatal-warnings", // Fail the compilation if there are any warnings.
      "-Xlint", // Enable recommended additional warnings.
      "-Ywarn-adapted-args", // Warn if an argument list is modified to match the receiver.
      "-Ywarn-dead-code", // Warn when dead code is identified.
      "-Ywarn-inaccessible", // Warn about inaccessible types in method signatures.
      "-Ywarn-nullary-override", // Warn when non-nullary overrides nullary, e.g. def foo() over def foo.
      "-Ywarn-numeric-widen" // Warn when numerics are widened.
    ),
    scalacOptions in Test ~= { (options: Seq[String]) =>
      options filterNot (_ == "-Ywarn-dead-code") // Allow dead code in tests (to support using mockito).
    },
    parallelExecution in Test := false,
    fork in Test := true
  )
}

////*******************************
//// Play settings
////*******************************
object PlaySettings extends AutoPlugin {
  override def requires: Plugins = Play

  override def projectSettings: Seq[Setting[_]] =
    Seq(
      // Monitor Twirl templates with SBT layout
      PlayKeys.playMonitoredFiles ++= (sourceDirectories in (Compile, TwirlKeys.compileTemplates)).value,

      // Router settings
      routesGenerator := InjectedRoutesGenerator,
      //routesImport += "utils.route.Binders._",

      // Disable documentation
      sources in (Compile, doc) := Seq.empty,
      publishArtifact in (Compile, packageDoc) := false
    )
}
