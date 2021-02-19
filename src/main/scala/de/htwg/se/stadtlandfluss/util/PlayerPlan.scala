package de.htwg.se.stadtlandfluss.util

trait PlayerPlan {
  def setPlayerFirstname(firstname: String): String
  def setPlayerLastname(lastname: String): String
  def setPlayersAge(age: String): Int
}
