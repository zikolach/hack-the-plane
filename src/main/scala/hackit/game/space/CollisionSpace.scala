package hackit.game.space

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
    x - w/2 <= sr._1 || x + w/2 >= sr._3 || y - h/2 <= sr._2 || y + h/2 >= sr._4
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

}
