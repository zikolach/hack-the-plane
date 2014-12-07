package hackit.game.space

import hackit.game.space.rocket.Rocket

trait CollisionSpace {

  def spaceObjects: List[SpaceObject]

  def spaceRect: (Int, Int, Int, Int)

  def checkCollisions(spaceObject: SpaceObject): Boolean =
    spaceObjects.count(anotherObject => {
      spaceObject != anotherObject && spaceObject.detectCollision(anotherObject)
    }) > 0

  def outOfSpace(spaceObject: SpaceObject): Boolean = {
    val (x, y, w, h) = (spaceObject.position._1, spaceObject.position._2, spaceObject.dimensions._1, spaceObject.dimensions._2)
    val sr = spaceRect
//    x - w / 2 <= sr._1 || x + w / 2 >= sr._3 || y - h / 2 <= sr._2 || y + h / 2 >= sr._4
    x + w / 2 <= sr._1 || x - w / 2 >= sr._3 || y + h / 2 <= sr._2 || y - h / 2 >= sr._4
  }

  def distanceToObject(spaceObject: SpaceObject): Int = {
    // TODO: add rockets

    // distance to borders
    val (x, y, angle) = (
      spaceObject.position._1,
      spaceObject.position._2,
      spaceObject.orientation)
    val rad = Math.toRadians(angle)
    val dist = angle match {
      case a if a >= 0 && a < 90 =>
        Math.min(
          Math.abs(spaceRect._3 - x) / Math.cos(rad).abs,
          Math.abs(spaceRect._4 - y) / Math.sin(rad).abs
        )
      case a if a >= 90 && a < 180 =>
        Math.min(
          Math.abs(spaceRect._1 - x) / Math.cos(rad).abs,
          Math.abs(spaceRect._4 - y) / Math.sin(rad).abs
        )
      case a if a >= 180 && a < 270 =>
        Math.min(
          Math.abs(spaceRect._1 - x) / Math.cos(rad).abs,
          Math.abs(spaceRect._2 - y) / Math.sin(rad).abs
        )
      case a if a >= 270 && a < 360 =>
        Math.min(
          Math.abs(spaceRect._3 - x) / Math.cos(rad).abs,
          Math.abs(spaceRect._2 - y) / Math.sin(rad).abs
        )
      case _ => 100
    }
    dist.toInt
  }

  def detectObjectsAt(spaceObject: SpaceObject, maxDistance: Int): List[UnknownSpaceObject] =
    spaceObjects.filter(otherObject => {
      otherObject != spaceObject &&
        distance(spaceObject.position._1, otherObject.position._1, spaceObject.position._2, otherObject.position._2) <= maxDistance
    }).map(obj => UnknownSpaceObject(obj.position._1, obj.position._2, Math.max(obj.dimensions._1, obj.dimensions._2), obj.orientation))

  def radar(obj: SpaceObject, maxDistance: Double): Option[CourseObject] = {
    spaceObjects
      .filterNot(_ == obj)
      .map(so => (so, distance(obj.position._1, obj.position._2, so.position._1, so.position._2)))
//      .filter(_._2 <= maxDistance)
      .sortBy(_._2)
      .headOption
      .map(no => CourseObject(
        angle = angleDiff(obj.orientation, angle(obj.position._1, obj.position._2, no._1.position._1, no._1.position._2)),
        distance = no._2)
      )
  }

  private def angle(x1: Double, y1: Double, x2: Double, y2: Double): Double =
    Math.toDegrees(Math.atan2(y2 - y1, x2 - x1))

  private def distance(x1: Double, y1: Double, x2: Double, y2: Double): Double =
    Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2))

  private def angleDiff(a1: Double, a2: Double): Double =
    (a2 - a1) % 360

}
