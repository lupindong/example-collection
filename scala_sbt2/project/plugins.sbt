addSbtPlugin("com.eed3si9n" % "sbt-assembly" % "0.14.10")

resolvers += Resolver.url("bintray", url("http://dl.bintray.com/sbt/sbt-plugin-releases"))(Resolver.ivyStylePatterns)

resolvers += Resolver.url("artifactory", url("http://scalasbt.artifactoryonline.com/scalasbt/sbt-plugin-releases"))(Resolver.ivyStylePatterns)