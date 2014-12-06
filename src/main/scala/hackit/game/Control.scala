package hackit.game

trait Control {
  def setSpeed(speed: Int): Boolean
  def setRotation(rotation: Int): Boolean
}
