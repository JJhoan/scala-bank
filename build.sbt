name := "bank"
version := "1.0"

disablePlugins( AssemblyPlugin )

lazy val root = ( project in file( "." ) ).aggregate( app, shared, mooc, backoffice )

lazy val app = Project( id = "app", base = file( "app/" ) )
  .dependsOn( mooc % "compile->compile;test->test" )
  .dependsOn( backoffice % "compile->compile;test->test" )
  .dependsOn( shared % "compile->compile;test->test" )

lazy val shared = Project( id = "shared", base = file( "src/shared" ) )

lazy val mooc       = Project( id = "mooc", base = file( "src/mooc" ) )
  .dependsOn( shared % "compile->compile;test->test" )

lazy val backoffice =
  Project( id = "backoffice", base = file( "src/backoffice" ) ).dependsOn( shared % "compile->compile;test->test" )

// Adds additional packages into Twirl
//TwirlKeys.templateImports += "youbank.controllers._"

// Adds additional packages into conf/routes
// play.sbt.routes.RoutesKeys.routesImport += "youbank.binders._"

lazy val pack = taskKey[ Unit ]( "Packages application as a fat jar" )
pack := {
  ( app / assembly ).toTask.value
}

