name := """blank-ssr-play-scalajs"""

version := "0.2.14"


resolvers += "Local Ivy Repository" at "file:///" + Path.userHome.absolutePath + "/.ivy2/local"
ThisBuild / useCoursier := false



import sbtcrossproject.CrossPlugin.autoImport.{crossProject, CrossType}


lazy val shared = {
  (crossProject(JSPlatform, JVMPlatform).crossType(CrossType.Pure) in file("shared"))
    .settings(
      libraryDependencies ++= Seq(
        "com.typesafe.akka" %% "akka-actor" % "2.6.10",
        "com.typesafe.akka" %% "akka-http" % "10.2.2",
        "com.lihaoyi" %% "upickle" % "1.2.2",
        "com.lihaoyi" %%% "autowire" % "0.3.2"

      )
    )
}


lazy val sharedJS     = {
  shared.js
    .settings(name := "sharedJS")
    .settings(
      libraryDependencies ++= Seq(
        "lbriot.eu" % "shared_frameworkjs_sjs1_2.12" % "1.0.0",
        "lbriot.eu" % "scala-xml-js_2.12" %"1.0.0",
        "lbriot.eu" % "rx_binding_sjs1_2.12" % "1.0.0",
        "org.scala-js" %%% "scalajs-dom" % "1.1.0"
      )
    )
}
lazy val sharedJVM    = {
  shared.jvm
    .settings(name := "sharedJVM")
    .settings(
      libraryDependencies ++= Seq(
        "lbriot.eu" % "shared_frameworkjvm_2.12" % "1.0.0",
        "lbriot.eu" % "scala-xml-jvm_2.12" %"1.0.0",
        "lbriot.eu" % "rx_binding_2.12" % "1.0.0"
      )
    )
}

lazy val server = (project in file("server"))
  .settings(
    libraryDependencies ++= Seq(
      guice,
      "commons-io" % "commons-io" % "2.6",
      "com.typesafe" % "config" % "1.3.3",
      "lbriot.eu" % "server_framework_2.12" % "1.0.0",
      "com.typesafe.akka" %% "akka-actor" % "2.6.10",
      "com.typesafe.akka" %% "akka-http" % "10.2.2",
      "com.lihaoyi" %% "upickle" % "1.2.2",
      "com.vmunier" %% "scalajs-scripts" % "1.1.4"
    )
  )

  .settings(
    scalaJSProjects := Seq(client),
    pipelineStages in Assets := Seq(scalaJSPipeline),
    compile in Compile := ((compile in Compile) dependsOn scalaJSPipeline).value,
    resolvers ++= Seq(
      "Atlassian Releases" at "https://maven.atlassian.com/public/",
      "Sonatype OSS Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots/", Resolver.jcenterRepo
    )
  )
  .settings(routesGenerator := InjectedRoutesGenerator)
  .settings(projectSettings)
  .enablePlugins(PlayScala, SbtWeb)
  .disablePlugins(PlayFilters)
  .dependsOn(sharedJVM)



routesGenerator := InjectedRoutesGenerator

lazy val client = (project in file("client"))
  .settings(name := "client")
  .settings(projectSettings)
  .settings(
    libraryDependencies ++= Seq(
      "lbriot.eu" % "client_framework_sjs1_2.12" % "1.0.0",
      "lbriot.eu" % "html_binding_sjs1_2.12" % "1.0.0",
      "org.akka-js" %%% "akkajsactor" % "2.2.6.9",
      "com.lihaoyi" %%% "upickle" % "1.2.2"
    )
  )
  .settings(skip in packageJSDependencies := false)
  .settings(scalaJSUseMainModuleInitializer := false)
  .settings(JsEngineKeys.engineType := JsEngineKeys.EngineType.Node)
  .settings(crossTarget in(Compile, fastOptJS) := new java.io.File("server/public/javascripts"))
  .settings(crossTarget in(Compile, packageJSDependencies) := new java.io.File("server/public/javascripts"))
  .settings(crossTarget in(Compile, fullOptJS) := new java.io.File("server/public/javascripts"))
  .enablePlugins(ScalaJSPlugin, ScalaJSWeb)
  .dependsOn(sharedJS)


lazy val projectSettings = Seq(
  organization := "eu.tetrao",
  scalaVersion := "2.12.12",
  javacOptions ++= Seq("-source", "1.8", "-target", "1.8"),
  scalacOptions ++= Seq(
    "-encoding", "UTF-8", // yes, this is 2 args
    "-deprecation",
    "-feature",
    "-unchecked",
    "-Yno-adapted-args",
    "-Ywarn-unused"
  ),/*
    scalacOptions in Compile := Seq(
      "-Ywarn-unused-import"
    ),*/
)
