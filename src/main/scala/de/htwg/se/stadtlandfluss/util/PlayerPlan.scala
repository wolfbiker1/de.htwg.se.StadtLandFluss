package de.htwg.se.stadtlandfluss.util

import de.htwg.se.stadtlandfluss.model.Builder
trait PlayerPlan {
  def setPlayerFirstname(firstname: String): Builder
  def setPlayerLastname(lastname: String): Builder
  def setPlayerAge(age: Int): Builder
}
