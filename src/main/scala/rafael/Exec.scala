package rafael

import twitter4j._
import twitter4j.auth.AccessToken

/**
 * Entry point for the application. Setups the required classes and starts
 * streaming from twitter.
 */
object Exec {
  def main(args: Array[String]): Unit = {
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
}