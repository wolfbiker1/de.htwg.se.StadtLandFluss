package de.htwg.se.stadtlandfluss.controller.controllerBaseImpl

import scala.swing.event.Event

class CellChanged extends Event
class PlayerAdded extends Event
class gameStarted extends Event
case class GridSizeChanged(newSize: Int) extends Event
class CandidatesChanged extends Event