resolvers += Resolver.sonatypeRepo("public")
addSbtPlugin(sys.props("plugin.org") % "sbt-scalafix" % sys.props("plugin.version"))
libraryDependencies += "com.googlecode.java-diff-utils" % "diffutils" % "1.3.0"
