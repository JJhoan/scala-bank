import sbt.*

object Dependencies {
  object Versions {
    val akka     = "2.8.0"
    val akkaHttp = "10.5.0"
  }
  
  val shared: Seq[ ModuleID ] = Seq(
    // Tes
    "org.scalatest" %% "scalatest" % "3.2.15" % Test,
    "com.typesafe.akka" %% "akka-testkit" % Versions.akka % Test,
    "com.typesafe.akka" %% "akka-http-testkit" % Versions.akkaHttp % Test,
    "org.reflections" % "reflections" % "0.10.2",
    "com.typesafe.slick" %% "slick" % "3.4.1",
    "com.typesafe.slick" %% "slick-hikaricp" % "3.4.1",
    "org.postgresql" % "postgresql" % "42.5.4", //org.postgresql.ds.PGSimpleDataSource dependency
    "com.github.nscala-time" %% "nscala-time" % "2.32.0",
    "com.typesafe.akka" %% "akka-http" % Versions.akkaHttp,
    "com.typesafe.akka" %% "akka-actor" % Versions.akka,
    "com.typesafe.akka" %% "akka-stream" % Versions.akka, // Explicit dependency due to: https://bit.ly/akka-http-25
    "com.typesafe.akka" %% "akka-http-spray-json" % Versions.akkaHttp,
    "org.tpolecat" %% "doobie-core" % "0.13.4",
    "mysql" % "mysql-connector-java" % "8.0.33",
    "com.github.scopt" %% "scopt" % "4.1.0", // Command Line Commands such as de DbTablesCreator
    "ch.qos.logback" % "logback-classic" % "1.4.7", // Logging backend implementation
    "com.typesafe.scala-logging" %% "scala-logging" % "3.9.5", // SLF4J Scala wrapper
    "net.logstash.logback" % "logstash-logback-encoder" % "7.3", // Log JSON encoder
    "com.newmotion" %% "akka-rabbitmq" % "6.0.0",
    "com.softwaremill.macwire" %% "macros" % "2.5.9" % "provided",
    "com.softwaremill.macwire" %% "macrosakka" % "2.5.9" % "provided",
    "com.softwaremill.macwire" %% "util" % "2.5.9",
    "com.softwaremill.macwire" %% "proxy" % "2.5.9",
    // Test
    "org.scalatest" %% "scalatest" % "3.2.15" % Test,
    "org.scalamock" %% "scalamock" % "5.2.0" % Test,
    "com.typesafe.akka" %% "akka-testkit" % Versions.akka % Test,
    "com.typesafe.akka" %% "akka-http-testkit" % Versions.akkaHttp % Test
  )
  
  val mooc: Seq[ ModuleID ] = Seq( )
  
  val backoffice: Seq[ ModuleID ] = Seq( )
}
