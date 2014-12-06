package hackit.game

trait Controllable {
  def setSpeed(speed: Int): Boolean
  def setRotation(rotation: Int): Boolean
}
