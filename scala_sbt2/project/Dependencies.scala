import sbt._

object Dependencies {
  // Versions
  lazy val akkaVersion = "2.3.8"

  // Libraries
  val scalaTest = "org.scalatest" %% "scalatest" % "3.0.5"
  val gigahorse = "com.eed3si9n" %% "gigahorse-okhttp" % "0.3.1"
  val playJson = "com.typesafe.play" %% "play-json" % "2.6.9"

  // Projects
  val helloCoreDeps = Seq(gigahorse, playJson, scalaTest % Test)
}