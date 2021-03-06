import sbt._

object Dependencies {

  final val Scala_2_11 = "2.11.12"
  final val Scala_2_12 = "2.12.12"
  final val Scala_2_13 = "2.13.5"

  final val ScalaCheck_1_12 = "1.12.6"
  final val ScalaCheck_1_13 = "1.13.5"
  final val ScalaCheck_1_14 = "1.14.3"
  final val ScalaCheck_1_15 = "1.15.2"

  final private val ScalaTest_2 = "2.2.6"

  // Newer versions of ScalaTest separate the scalatestplus %% scalacheck-1-X dependencies,
  // but do not support ScalaCheck 1.13.x
  // Once we no longer support ScalaCheck 1.13.x, we can upgrade to the latest version of
  // ScalaTest and always pull in the appropriate ScalaTestPlus artifact for ScalaCheck >= 1.14
  final private val ScalaTest_3_0 = "3.0.5"
  final private val ScalaTest_3_2 = "3.2.7"

  final private val ScalaTestPlusScalaCheckVersion = "3.2.2.0"

  final private val JodaTimeVersion = "2.10.10"
  final private val TaggingVersion = "2.3.0"

  val jodaTime: ModuleID = "joda-time" % "joda-time" % JodaTimeVersion
  val tagging: ModuleID = "com.softwaremill.common" %% "tagging" % TaggingVersion

  def scalaCheck(scalaCheckVersion: String): ModuleID = {
    "org.scalacheck" %% "scalacheck" % scalaCheckVersion
  }

  def scalaTest(scalaCheckVersion: String): ModuleID = {
    val scalaTestVersion = scalaCheckVersion match {
      case ScalaCheck_1_12 => ScalaTest_2
      case ScalaCheck_1_13 => ScalaTest_3_0
      case ScalaCheck_1_14 | ScalaCheck_1_15 => ScalaTest_3_2
    }
    "org.scalatest" %% "scalatest" % scalaTestVersion
  }

  def scalaTestPlusScalaCheck(scalaCheckVersion: String): ModuleID = {
    val artifactName = scalaCheckVersion match {
      case ScalaCheck_1_14 => "scalacheck-1-14"
      case ScalaCheck_1_15 => "scalacheck-1-15"
    }
    "org.scalatestplus" %% artifactName % ScalaTestPlusScalaCheckVersion
  }
}

