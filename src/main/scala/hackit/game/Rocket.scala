package hackit.game

import hackit.CollisionSpace
import hackit.game.pilot.Pilot
import org.scalajs.dom.CanvasRenderingContext2D

class Rocket(val pilot: Pilot, val space: CollisionSpace) extends SpaceObject with Controllable with Sensible  {

  val maxSpeed = 20
  val maxRotation = 10
  val (w, h) = (20, 30)

  var x: Double = 0
  var y: Double = 0
  var angle: Double = 0

  var speed: Int = 0 // 0..100%
  var rotation: Int = 0 // -100%..100%

  var collision: Boolean = false
  var destroyed: Boolean = false

  def this(x: Int, y: Int, angle: Double, pilot: Pilot, space: CollisionSpace) = {
    this(pilot, space)
    this.x = x
    this.y = y
    this.angle = angle
    pilot.setControl(this)
  }

  def setSpeed(speed: Int): Boolean = {
    if (speed >= 0 && speed <= 100) {
      this.speed = speed
      true
    } else false
  }

  def setRotation(rotation: Int): Boolean = {
    if (rotation >= -100 && rotation <= 100) {
      this.rotation = rotation
      true
    } else false
  }

  def draw(ctx: CanvasRenderingContext2D): Unit = {
//    val canvas = ctx.canvas
    ctx.setTransform(1, 0, 0, 1, 0, 0)
    ctx.translate(x, y)
    ctx.rotate(Math.toRadians(angle))

    ctx.strokeStyle = if (collision || destroyed) "red" else "black"

    // draw shape
    ctx.moveTo(h/2, 0)
    ctx.beginPath()
    ctx.lineTo(-h/2, -w/2)
    ctx.lineTo(-h/2, w/2)
    ctx.lineTo(h/2, 0)
    ctx.closePath()
    ctx.stroke()
    //
    ctx.translate(-x, -y)
    ctx.setTransform(1, 0, 0, 1, 0, 0)
    ctx.strokeStyle = "black"
  }

  def step(): Unit = destroyed match {
    case false =>
      pilot.drive()

      val ds: Double = speed.toDouble * maxSpeed / 100
      val rad = Math.toRadians(angle)
      println(angle)

      angle = (angle + rotation * maxRotation / 100) % 360
      if (angle < 0) angle = 360 - angle
      x += Math.cos(rad) * ds
      y += Math.sin(rad) * ds

      if (space.outOfSpace(this))
        destroyed = true
      else
        collision = space.checkCollisions(this)

    case true =>
  }

  override def toString = {
    s"Rocket($x:$y:$angle)"
  }

  override def position: (Double, Double) = (x, y)

  override def dimensions: (Int, Int) = {
    // TODO: calculate dimensions according angle
    (h, h)
  }

  override def getDistance: Int = space.distanceToObject(this)

  override def orientation: Double = angle
}
