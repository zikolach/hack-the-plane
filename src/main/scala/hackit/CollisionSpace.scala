package hackit

import hackit.game.SpaceObject

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

}
