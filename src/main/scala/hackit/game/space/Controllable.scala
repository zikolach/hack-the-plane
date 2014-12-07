package hackit.game.space

trait Controllable {
  def setSpeed(speed: Double): Boolean
  def setRotation(rotation: Double): Boolean
}
