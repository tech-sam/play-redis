import play.sbt.PlayImport

name := """play-java-seed"""
organization := "com.example"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.12.6"

libraryDependencies ++= Seq(
  "com.typesafe.play" %% "play-json" % "2.6.10",
  "com.github.karelcemus" %% "play-redis" % "2.3.0",
  // runtime DI
  PlayImport.guice,
  // runtime DI
  PlayImport.cacheApi
)

libraryDependencies += guice
resolvers += "Greytip Proxy Repository" at "https://nexus.greytip.com/repository/maven-public/"

