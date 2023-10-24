import sbt.Keys.*
import sbt.{ Tests, * }


object Configuration {
  val commonSettings = Seq(
    organization := "com.mybank",
    scalaVersion := "2.13.10",
    scalacOptions := {
      val default = Seq(
        "-Xlint",
        "-Xfatal-warnings",
        "-unchecked",
        "-deprecation",
        "-feature",
        "-language:higherKinds"
      )
      if ( version.value.endsWith( "SNAPSHOT" ) ) {
        default :+ "-Xcheckinit"
      } else {
        default
      } // check against early initialization
    },
    ( Test / console / scalacOptions ) --= Seq( "-Ywarn-unused:imports", "-Xfatal-warnings" ),
    ( Test / console / scalacOptions ) ++= Seq( "-Ywarn-unused:-imports" ),
    exportJars := true
  )
}
