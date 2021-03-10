package de.htwg.se.stadtlandfluss.model.playerComponent

case class Player(firstname: String, lastname: String, age: Int, points: Int = 0) {
  private var pointsMember = points

  def getPoints(): Int = {
    pointsMember
  }

  def setPoints(): Unit = {
    pointsMember += 1
  }

  override def toString: String = s"$firstname $lastname $age"
}
