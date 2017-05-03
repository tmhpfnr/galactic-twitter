package com.michalplachta.galactic

import com.michalplachta.galactic.service.Followers
import com.michalplachta.galactic.service.Followers.Version4._

import scala.annotation.tailrec
import scala.util.{ Failure, Success }

object GalacticFollowersApp extends App {
  @tailrec
  def runFollowersApp(getFollowersDescription: String ⇒ String): Unit = {
    println("Enter Citizen's name: ")
    val name = io.StdIn.readLine()
    println(s"Getting followers for $name")
    val followersDescription = getFollowersDescription(name)
    println(s"$name has $followersDescription followers!")
    runFollowersApp(getFollowersDescription)
  }

  def getAndDescribe(name: String): String = {
    Followers.Version1.getFollowers(name).toString
  }

  def getAndDescribeUsingCache(name: String): String = {
    val cachedFollowers = Followers.Version2.getCachedFollowers(name)
    cachedFollowers.map(_.toString).getOrElse("(not available)")
  }

  def getAndDescribeUsingCacheWithFailures(name: String): String = {
    val cachedTriedFollowers = Followers.Version3.getCachedTriedFollowers(name)
    cachedTriedFollowers map {
      case Success(followers)    ⇒ followers.toString
      case Failure(errorMessage) ⇒ s"(failed to get followers: $errorMessage)"
    } getOrElse "(not available)"
  }

  def getAndDescribeUsingADTs(name: String): String = {
    val remoteFollowers: RemoteFollowersData = Followers.Version4.getRemoteFollowers(name)
    remoteFollowers match {
      case NotRequestedYet()    ⇒ "(not requested yet)"
      case Loading()            ⇒ "(loading...)"
      case Fetched(count)       ⇒ count.toString
      case Failed(errorMessage) ⇒ s"(failed to get followers: $errorMessage)"
    }
  }

  runFollowersApp(getAndDescribeUsingADTs)
}
