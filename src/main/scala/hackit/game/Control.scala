package hackit.game

import scala.scalajs.js

trait Control {
  def setSpeed(speed: Int): Boolean
  def setRotation(rotation: Int): Boolean
}
