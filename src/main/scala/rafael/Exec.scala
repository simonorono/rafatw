package rafael

import java.nio.file.{Files, NoSuchFileException, Paths}
import java.util

import org.json.simple.parser.{JSONParser, ParseException}
import twitter4j._
import twitter4j.auth.AccessToken

import scala.collection.JavaConverters._

/**
  * Entry point for the application. Setups the required classes and starts
  * streaming from twitter.
  */
object Exec extends App {
  /**
    * Reads a configuration files and loads its content into a Map
    * @param fileName The name of the file
    * @return The Map with all the configuration pairs.
    */
  def readConfig(fileName: String): Option[Map[String, String]] = {
    try {
      val parser = new JSONParser
      val path = Paths.get(fileName)
      val content = new String(Files.readAllBytes(path))
      val obj = parser.parse(content)
      Some(obj.asInstanceOf[util.HashMap[String, String]].asScala.toMap)
    } catch {
      case ex: NoSuchFileException =>
        println("File " + fileName + " not found")
        None
      case ex: ParseException =>
        println(fileName + ": Bad JSON format")
        None
    }
  }

  val settings = readConfig("config.json").getOrElse(Map())

  val data = new Data(settings)

  val factory = new TwitterStreamFactory()
  val twitterStream = factory.getInstance()

  twitterStream.setOAuthConsumer(
    settings("consumer-key"),
    settings("consumer-secret")
  )

  twitterStream.setOAuthAccessToken(
    new AccessToken(
      settings("access-token"),
      settings("access-token-secret")
    )
  )

  twitterStream.addListener(new Saver(data))
  twitterStream.user()
}
