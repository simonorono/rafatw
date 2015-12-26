package rafael

import twitter4j._

/**
  * Class that handles the events in the Twitter Stream
  * @param data An instance of Data used to save the information
  */
class Saver(data: Data) extends UserStreamListener {
  override def onFriendList(friendIds: Array[Long]) {}

  override def onUserListUnsubscription(subscriber: User, listOwner: User, list: UserList) {}

  override def onBlock(source: User, blockedUser: User) {}

  override def onUserListSubscription(subscriber: User, listOwner: User, list: UserList) {}

  override def onFollow(source: User, followedUser: User) {}

  override def onUserListMemberAddition(addedMember: User, listOwner: User, list: UserList) {}

  override def onDirectMessage(directMessage: DirectMessage) {}

  override def onUnblock(source: User, unblockedUser: User) {}

  override def onUserListUpdate(listOwner: User, list: UserList) {}

  override def onUnfollow(source: User, unfollowedUser: User) {}

  override def onUserProfileUpdate(updatedUser: User) {}

  override def onUserListMemberDeletion(deletedMember: User, listOwner: User, list: UserList) {}

  override def onUserDeletion(deletedUser: Long) {}

  override def onRetweetedRetweet(source: User, target: User, retweetedStatus: Status) {}

  override def onFavoritedRetweet(source: User, target: User, favoritedRetweeet: Status) {}

  override def onDeletionNotice(directMessageId: Long, userId: Long) {}

  override def onFavorite(source: User, target: User, favoritedStatus: Status) {}

  override def onQuotedTweet(source: User, target: User, quotingTweet: Status) {}

  override def onUnfavorite(source: User, target: User, unfavoritedStatus: Status) {}

  override def onUserSuspension(suspendedUser: Long) {}

  override def onUserListDeletion(listOwner: User, list: UserList) {}

  override def onUserListCreation(listOwner: User, list: UserList) {}

  override def onStallWarning(warning: StallWarning) {}

  override def onDeletionNotice(statusDeletionNotice: StatusDeletionNotice) {}

  override def onScrubGeo(userId: Long, upToStatusId: Long) {}

  /**
    * Executes every time the stream receives a status update. Saves the status.
    * @param status The status to be saved
    */
  override def onStatus(status: Status): Unit = {
    data.saveStatus(status)
  }

  override def onTrackLimitationNotice(numberOfLimitedStatuses: Int): Unit = {}

  override def onException(ex: Exception) {}
}
