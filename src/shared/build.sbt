disablePlugins(AssemblyPlugin)

Configuration.commonSettings

Compile / scalaSource := baseDirectory.value / "main/"
Test / scalaSource := baseDirectory.value / "test/"
Compile / resourceDirectory := baseDirectory.value / "conf"

libraryDependencies ++= Dependencies.shared

TaskKey[Unit]("createDbTables") := (Compile / runMain)
  .toTask(" tv.codely.shared.infrastructure.environment.DbTablesCreator")
  .value

assembly /test  := {}
