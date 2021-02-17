name := """blank-ssr-play-scalajs"""

version := "0.2.14"


resolvers += "Local Ivy Repository" at "file:///" + Path.userHome.absolutePath + "/.ivy2/local"
ThisBuild / useCoursier := false


val shared_framework_JVM = ProjectRef(file("../PlayScalaJSFramework"), "shared_frameworkJVM")

val shared_framework_JS =  ProjectRef(file("../PlayScalaJSFramework"), "shared_frameworkJS")

val server_framework =  ProjectRef(file("../PlayScalaJSFramework"), "server_framework")

val client_framework =  ProjectRef(file("../PlayScalaJSFramework"), "client_framework")




import sbtcrossproject.CrossPlugin.autoImport.{crossProject, CrossType}


lazy val shared = {
  crossProject(JSPlatform, JVMPlatform).crossType(CrossType.Pure) in file("shared")
}



lazy val sharedJS     = shared.js.settings(name := "sharedJS").dependsOn(shared_framework_JS)
lazy val sharedJVM    = shared.jvm.settings(name := "sharedJVM").dependsOn(shared_framework_JVM)

lazy val server = (project in file("server"))
  .settings(
    libraryDependencies ++= Seq(
      guice,
      "commons-io" % "commons-io" % "2.6",
      "com.typesafe" % "config" % "1.3.3",
      "org.scala-lang.modules" %% "scala-xml" % "0.0.0+4-ba989258+20210214-2158-SNAPSHOT"

    )
  )

  .settings(
    scalaJSProjects := Seq(client),
    pipelineStages in Assets := Seq(scalaJSPipeline),
    dependencyOverrides += "org.scala-lang.modules" %% "scala-xml" % "0.0.0+4-ba989258+20210214-2158-SNAPSHOT",
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
  .dependsOn(server_framework)
  .dependsOn(sharedJVM)



routesGenerator := InjectedRoutesGenerator

lazy val client = (project in file("client"))
  .settings(name := "client")
  .settings(projectSettings)
  .settings(skip in packageJSDependencies := false)
  .settings(scalaJSUseMainModuleInitializer := false)
  .settings(JsEngineKeys.engineType := JsEngineKeys.EngineType.Node)
  .settings(crossTarget in(Compile, fastOptJS) := new java.io.File("server/public/javascripts"))
  .settings(crossTarget in(Compile, packageJSDependencies) := new java.io.File("server/public/javascripts"))
  .settings(crossTarget in(Compile, fullOptJS) := new java.io.File("server/public/javascripts"))
  .enablePlugins(ScalaJSPlugin, ScalaJSWeb)
  .dependsOn(client_framework)
  .dependsOn(sharedJS)


lazy val projectSettings = Seq(
  organization := "lbriot.eu",
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
