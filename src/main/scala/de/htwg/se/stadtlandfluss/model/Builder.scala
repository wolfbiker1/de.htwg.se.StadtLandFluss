package de.htwg.se.stadtlandfluss.model

import de.htwg.se.stadtlandfluss.util.PlayerPlan

case class Builder() extends PlayerPlan {
  var firstname: String = ""
  var lastname: String = ""
  var age: Int = 0

  override def setPlayerFirstname(_firstname: String): Builder = {
    firstname = _firstname
    this
  }

  override def setPlayerLastname(_lastname: String): Builder = {
    lastname = _lastname
    this
  }

  override def setPlayerAge(_age: Int): Builder = {
    age = _age
    this
  }

  def build(): Player = {
    Player(firstname, lastname, age)
  }

}