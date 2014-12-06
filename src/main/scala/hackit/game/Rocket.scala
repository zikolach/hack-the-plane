package hackit.game

import hackit.CollisionSpace
import hackit.game.pilot.Pilot
import org.scalajs.dom.CanvasRenderingContext2D

class Rocket(val pilot: Pilot, val space: CollisionSpace) extends Control with SpaceObject {

  val maxSpeed = 20
  val maxRotation = 10
  val (w, h) = (20, 30)

  var x: Int = 0
  var y: Int = 0
  var angle: Int = 0

  var speed: Int = 0 // 0..100%
  var rotation: Int = 0 // -100%..100%

  var collision: Boolean = false

  def this(x: Int, y: Int, angle: Int, pilot: Pilot, space: CollisionSpace) = {
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
    ctx.rotate(Math.PI / 180 * angle)


    ctx.strokeStyle = if (collision) "red" else "black"


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

  def step(): Unit = {
    pilot.drive()

    val ds = speed * maxSpeed / 100
    val rad = angle * Math.PI / 180

    angle += rotation * maxRotation / 100
    x += (Math.cos(rad) * ds).toInt
    y += (Math.sin(rad) * ds).toInt

    if (space.outOfSpace(this)) {
      x -= (Math.cos(rad) * ds).toInt
      y -= (Math.sin(rad) * ds).toInt
      angle += 180
      angle %= 360
    }

    collision = space.checkCollisions(this)

  }

  override def toString = {
    s"Rocket($x:$y:$angle)"
  }

  override def position: (Int, Int) = (x, y)

  // TODO: calculate dimensions according angle
  override def dimensions: (Int, Int) = (h, h)
}
