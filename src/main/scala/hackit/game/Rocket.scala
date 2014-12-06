package hackit.game

import org.scalajs.dom.CanvasRenderingContext2D

class Rocket(val pilot: Pilot) extends Control {

  val maxSpeed = 20
  val maxRotation = 10
  val (w, h) = (20, 30)

  var x: Int = 0
  var y: Int = 0
  var angle: Int = 0

  var speed: Int = 0 // 0..100%
  var rotation: Int = 0 // -100%..100%

  def this(x: Int, y: Int, angle: Int, pilot: Pilot) = {
    this(pilot)
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
    val canvas = ctx.canvas
    ctx.setTransform(1, 0, 0, 1, 0, 0)
    ctx.translate(x, y)
    ctx.rotate(Math.PI / 180 * angle)
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
  }

  def step(): Unit = {
    pilot.drive()
    val ds = speed * maxSpeed / 100
    val rad = angle * Math.PI / 180
    x += (Math.cos(rad) * ds).toInt
    y += (Math.sin(rad) * ds).toInt
    angle += rotation * maxRotation / 100
  }

  override def toString = {
    s"Rocket($x:$y:$angle)"
  }

}
