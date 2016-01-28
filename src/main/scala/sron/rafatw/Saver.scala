/*
 * Copyright 2016 Simón Oroño
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package sron.rafatw

import twitter4j._

/**
  * Class that handles the events in the Twitter Stream
  *
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
    *
    * @param status The status to be saved
    */
  override def onStatus(status: Status): Unit = {
    data.saveStatus(status)
  }

  override def onTrackLimitationNotice(numberOfLimitedStatuses: Int): Unit = {}

  override def onException(ex: Exception) {}
}
