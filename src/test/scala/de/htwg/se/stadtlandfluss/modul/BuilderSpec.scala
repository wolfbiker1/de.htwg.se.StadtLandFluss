package de.htwg.se.stadtlandfluss.modul
import de.htwg.se.stadtlandfluss.model.Builder
import de.htwg.se.stadtlandfluss.model.playerComponent.Player
import org.scalatest.{Matchers, WordSpec}

class BuilderSpec extends WordSpec with Matchers {
  "A builder " when {
    "build" should {
      "work" in {
        val b = new Builder
      }
      "should build a player" in {
        val b = new Builder
        b.setPlayerFirstname("hans") should be(Builder())
        b.setPlayerLastname("wurst") should be(Builder())
        b.setPlayerAge("42") should be(Builder())
        b.build() should be(Player("hans", "wurst", 42))
      }
    }
  }
}
