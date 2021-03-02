package de.htwg.se.stadtlandfluss.model

case class Player(firstname: String, lastname: String, age: Int){
  override def toString:String = s"$firstname $lastname $age"
}

object store {

}