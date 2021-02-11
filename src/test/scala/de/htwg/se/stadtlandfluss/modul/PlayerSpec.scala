package de.htwg.se.stadtlandfluss.modul

import de.htwg.se.stadtlandfluss.model.Player
import org.scalatest._

class PlayerSpec extends WordSpec with Matchers{

  "A Player" when { "new" should {
    val player = Player("Your Name")
    "have a name"  in {
      player.name should be("Your Name")
    }
    "have a nice String representation" in {
      player.toString should be("Your Name")
    }
  }}

}