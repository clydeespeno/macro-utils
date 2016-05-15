val org = "jce.macrotuils"

lazy val macroSettings = Seq(
  version := "0.0.1",
  organization := org,
  scalacOptions ++= Seq("-language:experimental.macros", "-deprecation", "-feature", "-unchecked", "-Ymacro-debug-lite"),
  scalaVersion := "2.11.8",
  resolvers ++= Seq(Resolver.mavenLocal, Resolver.sonatypeRepo("releases")),
  libraryDependencies ++= Seq(
    "org.scala-lang" % "scala-reflect" % scalaVersion.value,
    "org.scalatest" %% "scalatest" % "2.2.4" % Test
  ),
  addCompilerPlugin("org.scalamacros" % "paradise" % "2.1.0" cross CrossVersion.full)
)

lazy val root = (project in file("."))
  .settings(
    name := "macro-utils",
    organization := org,
    version := "0.0.1",
    scalaVersion := "2.11.8",
    resolvers ++= Seq(Resolver.mavenLocal, Resolver.sonatypeRepo("releases")))
  .aggregate(sealedtrait)
  .dependsOn(sealedtrait)

lazy val sealedtrait = (project in file("sealedtrait")).settings(macroSettings)
  .settings(
    name := "sealedtrait"
  )