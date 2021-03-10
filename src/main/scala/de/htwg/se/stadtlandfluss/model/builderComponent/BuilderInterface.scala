package de.htwg.se.stadtlandfluss.model.builderComponent

trait BuilderInterface {

}
case class Builder() extends PlayerPlan {
  var firstname: String = ""
  var lastname: String = ""
  var age: Int = 0
  var points: Int = 0
  override def setPlayerFirstname(_firstname: String): Builder = {
    firstname = _firstname
    this
  }

  override def setPlayerLastname(_lastname: String): Builder = {
    lastname = _lastname
    this
  }

  override def setPlayerAge(_age: String): Builder = {
    toInt(_age) match {
      case Some(value) =>
        age = value
      case None =>
        age = 66
      // fail silently...
    }
    this
  }

  def toInt(s: String): Option[Int] = {
    Try(s.toInt).toOption
  }


  def build(): Player = {
    Player(firstname, lastname, age,points)
  }

