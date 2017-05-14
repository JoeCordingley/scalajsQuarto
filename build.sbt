enablePlugins(ScalaJSPlugin)

testFrameworks += new TestFramework("utest.runner.Framework")


jsDependencies += RuntimeDOM

skip in packageJSDependencies := false
jsDependencies +=
  "org.webjars" % "jquery" % "2.1.4" / "2.1.4/jquery.js"

name := """scalaJsTry"""

version := "1.0"

scalaVersion := "2.12.0"



libraryDependencies ++= Seq(
  "be.doeraene" %%% "scalajs-jquery" % "0.9.1",
  "com.lihaoyi" %%% "utest" % "0.4.4",
  "com.lihaoyi" %%% "scalatags" % "0.6.3",
  "com.lihaoyi" %%% "pprint" % "0.4.4"
)

