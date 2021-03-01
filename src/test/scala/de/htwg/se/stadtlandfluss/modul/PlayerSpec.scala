package de.htwg.se.stadtlandfluss.modul

import de.htwg.se.stadtlandfluss.model.Player
import org.scalatest._

class PlayerSpec extends WordSpec with Matchers{
  val p = Player("foo", "bar", 42)
  Player.unapply(p).get should be("foo", "bar", 42)
  "A Player" when { "new" should {
    val player = Player("foo", "bar", 42)
    "have a name"  in {
      player.toString should be("foo bar 42")
    }
    "have a nice String representation" in {
      player.toString should be("foo bar 42")
    }
  }}

}