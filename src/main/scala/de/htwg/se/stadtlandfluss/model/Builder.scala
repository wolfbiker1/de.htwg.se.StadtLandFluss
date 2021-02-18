package de.htwg.se.stadtlandfluss.model

import de.htwg.se.stadtlandfluss.util.Builder

  case class Builder() {
    var firstname: String = ""
    var lastname: String = ""

    def buildPlayerFirstname(_firstname: String): Builder = {
      firstname = _firstname
      this
    }

    def buildPlayerLastname(_lastname: String): Builder = {
      lastname = _lastname
      this
    }

    def build(): Builder = {
      this
    }
}
