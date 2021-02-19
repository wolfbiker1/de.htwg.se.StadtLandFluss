package de.htwg.se.stadtlandfluss.model

import de.htwg.se.stadtlandfluss.util.PlayerPlan

case class Builder() {
  var firstname: String = ""
  var lastname: String = ""
  var age: Int = 0

  def buildPlayerFirstname(_firstname: String): Builder = {
    firstname = _firstname
    this
  }

  def buildPlayerLastname(_lastname: String): Builder = {
    lastname = _lastname
    this
  }
  def buildPlayerAge(_age: Int): Builder = {
    age = _age
    this
  }


  def build(): Player_ = {
    Player_(firstname, lastname, age)
  }
}

case class Player_(_firstname: String, _lastname: String, _age: Int) {
  override def toString:String = _firstname + " " + _lastname + " " + _age
}
