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
  /** Version of the Web Application */
  val appVersion = "1.0-SNAPSHOT"

  /** Version of the DL Learner Libraries */
  val dlLearnerVersion = "1.0-SNAPSHOT"

  /**
   * Depend on DL Learner Core Components for algorithmic functionality.
   * Also depend on interfaces for the bootstrapping capabilities.
   * However, there are a lot of dependencies in there so exclude all transitive dependencies from
   * the interfaces library (http://bit.ly/HRrL63)
   */
  val appDependencies = Seq(
    "org.dllearner" % "components-core" % dlLearnerVersion,
    "org.dllearner" % "interfaces" % dlLearnerVersion excludeAll()
  )

  val main = PlayProject(appName, appVersion, appDependencies, mainLang = JAVA).settings(
    /**The three slashes for the local maven repository are required on windows - may not be the same on linux  */
    resolvers ++= Seq(
      "Local Maven Repository" at "file:///" + Path.userHome.absolutePath + "/.m2/repository",
      "University Leipzig, AKSW Maven2 Repository Releases" at "http://maven.aksw.org/archiva/repository/internal",
      "University Leipzig, AKSW Maven2 Repository Snapshots" at "http://maven.aksw.org/archiva/repository/snapshots"
      )
  )

}
