name := "NPRG014 Scala Examples"

version := "1.0"

organization := "cz.cuni.mff.d3s"

scalaVersion := "2.9.1"

retrieveManaged := true

offline := true

// withSources() withJavadoc() see SBT library management: https://github.com/harrah/xsbt/wiki/Library-Management
libraryDependencies += "org.scalatest" %% "scalatest" % "1.6.1" % "test" withSources()

