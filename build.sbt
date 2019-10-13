import Dependencies._

Global / onChangedBuildSource := ReloadOnSourceChanges

ThisBuild / scalaVersion     := "2.13.1"
ThisBuild / version          := "0.1.0-SNAPSHOT"
ThisBuild / organization     := "org.smop"
ThisBuild / organizationName := "SMOP"

lazy val root = (project in file("."))
  .settings(
    name := "SMOP Sound",
    libraryDependencies ++= Seq(
      "org.scodec" %% "scodec-bits" % "1.1.12",
      "org.scodec" %% "scodec-core" % "1.11.4"
    )
  )

// See https://www.scala-sbt.org/1.x/docs/Using-Sonatype.html for instructions on how to publish to Sonatype.
