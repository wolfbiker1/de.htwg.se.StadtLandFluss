package de.htwg.se.stadtlandfluss.util

trait Builder {
  var firstname: String = ""
  var lastname: String = ""

  def buildPlayerFirstname: String
  def buildPlayerLastname: String
}
