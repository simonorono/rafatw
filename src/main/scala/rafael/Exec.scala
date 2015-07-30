package rafael

import twitter4j.auth.AccessToken
import twitter4j._

object Exec {
  def main(args: Array[String]): Unit = {
    val config = new Config("config.json")
    val settings = config.read().getOrElse(Map())

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