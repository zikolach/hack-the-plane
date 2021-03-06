package hackit.game.space

import scala.scalajs.js.annotation.{JSExportAll, JSExport}

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

case class UnknownSpaceObject(x: Double, y: Double, r: Int, angle: Double) extends SpaceObject {
  override def position: (Double, Double) = (x, y)

  override def dimensions: (Int, Int) = (r, r)

  override def orientation: Double = angle
}

@JSExportAll
case class CourseObject(angle: Double, distance: Double)