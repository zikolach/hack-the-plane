package hackit.game.space.rocket

import hackit.game.pilot.Pilot
import hackit.game.space._
import org.scalajs.dom.{HTMLElement, CanvasRenderingContext2D}
import org.scalajs.jquery._

class Rocket(val pilot: Pilot, val space: CollisionSpace)
  extends SpaceObject
  with Controllable
  with Sensible
  with Loggable {

  val minSpeed = 2
  val maxSpeed = 7
  val maxRotation = 10
  val (w, h) = (40, 40)

  var x: Double = 0
  var y: Double = 0
  var angle: Double = 0

  var speed: Double = 0 // 0..100%
  var rotation: Double = 0 // -100%..100%

  var collision: Boolean = false
  var destroyed: Boolean = false

  var img: HTMLElement = null

  def this(x: Int, y: Int, angle: Double, pilot: Pilot, space: CollisionSpace) = {
    this(pilot, space)
    this.x = x
    this.y = y
    this.angle = angle
    pilot.setControl(this)
    img = jQuery("#plane")(0)
  }

  def setSpeed(speed: Double): Boolean = {
    if (speed >= 0 && speed <= 100) {
      this.speed = speed
      true
    } else false
  }

  def setRotation(rotation: Double): Boolean = {
    if (rotation >= -100 && rotation <= 100) {
      this.rotation = rotation
      true
    } else false
  }

  def draw(ctx: CanvasRenderingContext2D): Unit = {
    ctx.setTransform(1, 0, 0, 1, 0, 0)
    ctx.translate(x, y)
    ctx.rotate(Math.toRadians(angle))

    ctx.strokeStyle = if (collision || destroyed) "red" else "black"

    // draw shape
    // ctx.moveTo(h/2, 0)
    if (collision) {
      ctx.beginPath()
      ctx.arc(0, 0, w / 2, 0, 2 * Math.PI)
      //    ctx.lineTo(-h/2, -w/2)
      //    ctx.lineTo(-h/2, w/2)
      //    ctx.lineTo(h/2, 0)
      //    ctx.closePath()
      ctx.lineWidth = 2
      ctx.stroke()
    }

    ctx.drawImage(img, 0, 0, 40, 40, -20, -20, 40, 40)
    //
    ctx.translate(-x, -y)
    ctx.setTransform(1, 0, 0, 1, 0, 0)
    ctx.strokeStyle = "black"
  }

  def step(): Unit = destroyed match {
    case false =>
      pilot.drive()

      val ds: Double = speed * (maxSpeed - minSpeed) / 100 + minSpeed
      val rad = Math.toRadians(angle)

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

  override def sonar: List[UnknownSpaceObject] = space.detectObjectsAt(this, 100)

  override def log(text: String): Unit = {
    val logElt = jQuery("#log")
    val oldText = logElt.text()
    logElt.text(s"$text\n$oldText".take(1024))
  }

  override def radar: Option[CourseObject] = space.radar(this, 200)
}
