package rafael

import java.sql.{DriverManager, Timestamp, Types}
import java.util.Properties

import twitter4j.{Status, User}

class Data(conf: Map[String, String]) {
  Class.forName("org.postgresql.Driver")

  private val props = new Properties()

  props.setProperty("user", conf("user"))
  props.setProperty("password", conf("password"))

  private val conn = DriverManager.getConnection(conf("connstr"), props)

  conn.setAutoCommit(true)

  private val insertStmt = conn.prepareStatement(
    """
      |INSERT INTO tweet (
      |  id, longitude, latitude, created_at, in_reply_to_status_id,
      |  in_reply_to_user_id, lang, source, twtext, user_id
      |)
      |VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);
    """.stripMargin
  )

  private val updateUsrStmt = conn.prepareStatement(
    """
      |UPDATE usr SET
      |  created_at = ?, description = ?, followers_count = ?,
      |  friends_count = ?, lang = ?, location = ?, name = ?,
      |  profile_image_url = ?, protected = ?, screen_name = ?,
      |  statuses_count = ?, timezone = ?, url = ?, verified = ?
      |WHERE
      |  id = ?;
    """.stripMargin
  )

  private val insertUsrStmt = conn.prepareStatement(
    """
      |INSERT INTO usr (
      |  id, created_at, description, followers_count, friends_count, lang,
      |  location, name, profile_image_url, protected, screen_name,
      |  statuses_count, timezone, url, verified
      |)
      |VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);
    """.stripMargin
  )

  private val existsUsrStmt = conn.prepareStatement(
    "SELECT 1 FROM usr WHERE id = ?;"
  )

  def existsUser(id: Long): Boolean = {
    existsUsrStmt.setLong(1, id)
    val rs = existsUsrStmt.executeQuery()
    while (rs.next()) {
      return true
    }
    false
  }

  def saveStatus(s: Status): Unit = {
    upsertUsr(s.getUser)

    if (s.getGeoLocation != null) {
      insertStmt.setDouble(2, s.getGeoLocation.getLatitude)
      insertStmt.setDouble(3, s.getGeoLocation.getLatitude)
    } else {
      insertStmt.setNull(2, Types.DOUBLE)
      insertStmt.setNull(3, Types.DOUBLE)
    }

    if (s.getInReplyToStatusId != -1) {
      insertStmt.setLong(5, s.getInReplyToStatusId)
    } else {
      insertStmt.setNull(5, Types.BIGINT)
    }

    if (s.getInReplyToUserId != -1) {
      insertStmt.setLong(6, s.getInReplyToUserId)
    } else {
      insertStmt.setNull(6, Types.BIGINT)
    }

    if (s.getLang.length != 0) {
      insertStmt.setString(7, s.getLang)
    } else {
      insertStmt.setNull(7, Types.VARCHAR)
    }

    insertStmt.setLong(1, s.getId)
    insertStmt.setTimestamp(4, new Timestamp(s.getCreatedAt.getTime))
    insertStmt.setString(8, s.getSource)
    insertStmt.setString(9, s.getText)
    insertStmt.setLong(10, s.getUser.getId)
    insertStmt.execute()
  }

  def upsertUsr(u: User): Unit = {
    if (existsUser(u.getId)) {

      updateUsrStmt.setNull(2, Types.VARCHAR)
      if (u.getDescription != null) {
        if (u.getDescription.length != 0) {
          updateUsrStmt.setString(2, u.getDescription)
        }
      }

      updateUsrStmt.setNull(6, Types.VARCHAR)
      if (u.getLocation != null) {
        if (u.getLocation.length != 0) {
          updateUsrStmt.setString(6, u.getLocation)
        }
      }

      updateUsrStmt.setNull(12, Types.VARCHAR)
      if (u.getTimeZone != null) {
        if (u.getTimeZone.length != 0) {
          updateUsrStmt.setString(12, u.getTimeZone)
        }
      }

      updateUsrStmt.setNull(13, Types.VARCHAR)
      if (u.getURL != null) {
        if (u.getURL.length != 0) {
          updateUsrStmt.setString(13, u.getURL)
        }
      }

      updateUsrStmt.setTimestamp(1, new Timestamp(u.getCreatedAt.getTime))
      updateUsrStmt.setInt(3, u.getFollowersCount)
      updateUsrStmt.setInt(4, u.getFriendsCount)
      updateUsrStmt.setString(5, u.getLang)
      updateUsrStmt.setString(7, u.getName)
      updateUsrStmt.setString(8, u.getBiggerProfileImageURLHttps)
      updateUsrStmt.setBoolean(9, u.isProtected)
      updateUsrStmt.setString(10, u.getScreenName)
      updateUsrStmt.setInt(11, u.getStatusesCount)
      updateUsrStmt.setBoolean(14, u.isVerified)
      updateUsrStmt.setLong(15, u.getId)
      updateUsrStmt.execute()
    } else {

      insertUsrStmt.setNull(3, Types.VARCHAR)
      if (u.getDescription != null) {
        if (u.getDescription.length != 0) {
          insertUsrStmt.setString(3, u.getDescription)
        }
      }

      insertUsrStmt.setNull(7, Types.VARCHAR)
      if (u.getLocation != null) {
        if (u.getLocation.length != 0) {
          insertUsrStmt.setString(7, u.getLocation)
        }
      }

      insertUsrStmt.setNull(13, Types.VARCHAR)
      if (u.getTimeZone != null) {
        if (u.getTimeZone.length != 0) {
          insertUsrStmt.setString(13, u.getTimeZone)
        }
      }

      insertUsrStmt.setNull(14, Types.VARCHAR)
      if (u.getURL != null) {
        if (u.getURL.length != 0) {
          insertUsrStmt.setString(14, u.getURL)
        }
      }

      insertUsrStmt.setLong(1, u.getId)
      insertUsrStmt.setTimestamp(2, new Timestamp(u.getCreatedAt.getTime))
      insertUsrStmt.setInt(4, u.getFollowersCount)
      insertUsrStmt.setInt(5, u.getFriendsCount)
      insertUsrStmt.setString(6, u.getLang)
      insertUsrStmt.setString(8, u.getName)
      insertUsrStmt.setString(9, u.getBiggerProfileImageURLHttps)
      insertUsrStmt.setBoolean(10, u.isProtected)
      insertUsrStmt.setString(11, u.getScreenName)
      insertUsrStmt.setInt(12, u.getStatusesCount)
      insertUsrStmt.setBoolean(15, u.isVerified)
      insertUsrStmt.execute()
    }
  }
}
