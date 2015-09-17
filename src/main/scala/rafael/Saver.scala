package rafael

import twitter4j._

/**
 * Class that handles the events in the Twitter Stream
 * @param data An instance if Data used to save the information
 */
class Saver(data: Data) extends UserStreamListener {
  override def onFriendList(friendIds: Array[Long]): Unit = {}
  override def onUserListUnsubscription(subscriber: User, listOwner: User, list: UserList): Unit = {}
  override def onBlock(source: User, blockedUser: User): Unit = {}
  override def onUserListSubscription(subscriber: User, listOwner: User, list: UserList): Unit = {}
  override def onFollow(source: User, followedUser: User): Unit = {}
  override def onUserListMemberAddition(addedMember: User, listOwner: User, list: UserList): Unit = {}
  override def onDirectMessage(directMessage: DirectMessage): Unit = {}
  override def onUnblock(source: User, unblockedUser: User): Unit = {}
  override def onUserListUpdate(listOwner: User, list: UserList): Unit = {}
  override def onUnfollow(source: User, unfollowedUser: User): Unit = {}
  override def onUserProfileUpdate(updatedUser: User): Unit = {}
  override def onUserListMemberDeletion(deletedMember: User, listOwner: User, list: UserList): Unit = {}
  override def onUserDeletion(deletedUser: Long): Unit = {}
  override def onRetweetedRetweet(source: User, target: User, retweetedStatus: Status): Unit = {}
  override def onFavoritedRetweet(source: User, target: User, favoritedRetweeet: Status): Unit = {}
  override def onDeletionNotice(directMessageId: Long, userId: Long): Unit = {}
  override def onFavorite(source: User, target: User, favoritedStatus: Status): Unit = {}
  override def onQuotedTweet(source: User, target: User, quotingTweet: Status): Unit = {}
  override def onUnfavorite(source: User, target: User, unfavoritedStatus: Status): Unit = {}
  override def onUserSuspension(suspendedUser: Long): Unit = {}
  override def onUserListDeletion(listOwner: User, list: UserList): Unit = {}
  override def onUserListCreation(listOwner: User, list: UserList): Unit = {}
  override def onStallWarning(warning: StallWarning): Unit = {}
  override def onDeletionNotice(statusDeletionNotice: StatusDeletionNotice): Unit = {}
  override def onScrubGeo(userId: Long, upToStatusId: Long): Unit = {}

  /**
   * Executes every time the stream receives a status update. Saves the status.
   * @param status The status to be saved
   */
  override def onStatus(status: Status): Unit = {
    data.saveStatus(status)
  }
  override def onTrackLimitationNotice(numberOfLimitedStatuses: Int): Unit = {}

  /**
   * Executes every time the stream throws an exception. Does not terminate the
   * application.
   * @param ex The exception thrown.
   */
  override def onException(ex: Exception): Unit = {
    ex.printStackTrace()
  }
}
