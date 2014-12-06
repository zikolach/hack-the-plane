package hackit.game.space

trait SpaceObject {
  def detectCollision(anotherObject: SpaceObject): Boolean = {
    val dp1 = Math.abs(position._1 - anotherObject.position._1)
    val dp2 = Math.abs(position._2 - anotherObject.position._2)
    val min1 = (dimensions._1 + anotherObject.dimensions._1) / 2
    val min2 = (dimensions._2 + anotherObject.dimensions._2) / 2
    dp1 < min1 && dp2 < min2
  }
  def position: (Double, Double)
  def dimensions: (Int, Int)
  def orientation: Double
}
