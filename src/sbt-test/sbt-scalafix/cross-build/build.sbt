import _root_.scalafix.sbt.{BuildInfo => Versions}
inThisBuild(
  List(
    updateOptions := updateOptions.value.withLatestSnapshots(false),
    scalacOptions += "-Ywarn-adapted-args", // For NoAutoTupling
    resolvers += Resolver.sonatypeRepo("public")
  )
)

lazy val scalafixSettings = List(
  addCompilerPlugin(scalafixSemanticdb),
  scalacOptions += "-Yrangepos"
)

def jdkVersion: Int = {
  // format of v is "1.n" for java 1.8- and "n" for java 9+
  val v = sys.props("java.specification.version")
  v.substring(v.lastIndexOf('.') + 1).toInt
}

lazy val scala210Ref: Seq[ProjectReference] =
  if (jdkVersion > 8) Nil else Seq[ProjectReference](scala210)

lazy val root = project
  .in(file("."))
  .aggregate(javaProject, scala211, scala212)
  .aggregate(scala210Ref: _*)

lazy val scala210 = project.settings(
  scalaVersion := "2.10.5"
)
lazy val scala211 = project.settings(
  scalaVersion := Versions.scala211,
  scalafixSettings
)
lazy val scala212 = project
  .configs(IntegrationTest)
  .settings(
    Defaults.itSettings,
    inConfig(IntegrationTest)(scalafixConfigSettings(IntegrationTest)),
    unmanagedSources.in(Compile, scalafix) :=
      unmanagedSources
        .in(Compile)
        .value
        .filterNot(_.getAbsolutePath.contains("IgnoreMe")),
    scalaVersion := Versions.scala212,
    scalafixSettings
  )
lazy val javaProject = project.settings(
  scalaVersion := Versions.scala212,
  scalafixSettings
)
