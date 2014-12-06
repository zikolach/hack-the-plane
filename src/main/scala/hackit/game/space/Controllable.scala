package hackit.game.space

trait Controllable {
  def setSpeed(speed: Int): Boolean
  def setRotation(rotation: Int): Boolean
}
