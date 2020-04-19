resolvers += Resolver.sonatypeRepo("public")
libraryDependencies += "com.googlecode.java-diff-utils" % "diffutils" % "1.3.0"
addSbtPlugin(
  sys.props("plugin.org") % "sbt-scalafix" % sys.props("plugin.version")
)
