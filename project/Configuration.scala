import sbt.Keys.*
import sbt.{ Def, Tests, * }


object Configuration {
  val commonSettings: Seq[ Def.Setting[ ? >: String & Task[ Seq[ String ] ] & Boolean & Task[ Seq[ TestOption ] ] ] ] = Seq(
    organization := "com.bank",
    scalaVersion := "2.13.3",
    scalacOptions := {
      val default = Seq(
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
    javaOptions += "-Duser.timezone=UTC",
    exportJars := true,
    Test / fork := false,
    Test / parallelExecution := false,
    Test / testOptions ++= Seq(
      Tests.Argument( TestFrameworks.ScalaTest, "-u", "target/test-reports" ),
      Tests.Argument( "-oDF" )
    ),
    cancelable in Global := true,
  )
}
