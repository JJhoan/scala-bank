Configuration.commonSettings

Compile / scalaSource := baseDirectory.value / "main/"
Test / scalaSource := baseDirectory.value / "test/"
Compile / resourceDirectory := baseDirectory.value / "conf"

libraryDependencies ++= Dependencies.shared

run / fork := true
run / connectInput := true

// Assembly
assembly / mainClass := Some("com.bank.Launcher")
assembly  / assemblyJarName  := "bank.jar"
assembly / assemblyOutputPath := baseDirectory.value.toPath.resolve("../package").resolve((assembly / assemblyJarName).value).toFile

assembly / test := {}
