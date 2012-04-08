import sbt._
import Keys._
import PlayProject._

/**
 * This is the File responsible for building the application.
 *
 * If you're in the play console, you'll need to exit the console and re-enter (I think)
 */
object ApplicationBuild extends Build {

  val appName = "dl-learner-web-application"
  val appVersion = "1.0-SNAPSHOT"

  val appDependencies = Seq(
    "org.dllearner" % "components-core" % "1.0-SNAPSHOT"
  )

  val main = PlayProject(appName, appVersion, appDependencies, mainLang = JAVA).settings(
    /**The three slashes for the local maven repository are required on windows - may not be the same on linux  */
    resolvers ++= Seq(
      "Local Maven Repository" at "file:///" + Path.userHome.absolutePath + "/.m2/repository",
      "University Leipzig, AKSW Maven2 Repository Releases" at "http://maven.aksw.org/archiva/repository/internal",
      "University Leipzig, AKSW Maven2 Repository Snapshots" at "http://maven.aksw.org/archiva/repository/snapshots",
      "Java.net Repository for Maven" at "http://download.java.net/maven/2/",
      "Pellet Repository" at "http://on.cs.unibas.ch/maven/repository/",
      "Project Lombok" at "http://projectlombok.org/mavenrepo"
      )
  )

}
