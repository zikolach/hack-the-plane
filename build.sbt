scalaJSSettings

name := "Scala.js HackIt Rockets"

scalaVersion := "2.11.2"

libraryDependencies += "org.scala-lang.modules.scalajs" %%% "scalajs-dom" % "0.6"

libraryDependencies += "org.scala-lang.modules.scalajs" %%% "scalajs-jquery" % "0.6"

skip in ScalaJSKeys.packageJSDependencies := false

ScalaJSKeys.persistLauncher in Compile := true
