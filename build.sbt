val circeParser = "io.circe" %% "circe-parser" % "0.9.3"
val circeDerivation = "io.circe" %% "circe-derivation" % "0.9.0-M3"

lazy val circeShaded = project
  .in(file("."))
  .enablePlugins(ShadingPlugin)
  .settings(
    name := "circe-shaded",
    scalaVersion := "2.12.8",
    version := "0.9.3",
    shadingNamespace := "bloop.shaded",
    shadeNamespaces := Set(
      "io.circe",
      "jawn",
      "org.typelevel.jawn",
      "machinist",
      "cats"
    ),
    libraryDependencies ++= List(
      (circeParser % Shaded).exclude("org.scala-lang", "scala-reflect"),
      (circeDerivation % Shaded).exclude("org.scala-lang", "scala-reflect"),
      "org.scala-lang" % "scala-reflect" % Keys.scalaVersion.value
    )
  )

