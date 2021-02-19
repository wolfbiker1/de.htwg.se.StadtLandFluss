package de.htwg.se.stadtlandfluss.model

import de.htwg.se.stadtlandfluss.util.PlayerPlan

case class Player(name: String) extends PlayerPlan {


  override def toString:String = name

  override def setPlayerFirstname(firstname: String): String = ???

  override def setPlayerLastname(lastname: String): String = ???

  override def setPlayersAge(age: String): Int = ???
}
