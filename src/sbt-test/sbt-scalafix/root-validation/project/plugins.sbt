resolvers += Resolver.sonatypeRepo("public")
addSbtPlugin(
  sys.props("plugin.org") % "sbt-scalafix" % sys.props("plugin.version")
)
