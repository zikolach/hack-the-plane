package hackit

import hackit.game.pilot.Pilot
import hackit.game.space.rocket.Rocket
import hackit.game.space.{CollisionSpace, SpaceObject}
import org.scalajs.dom.{CanvasRenderingContext2D, HTMLCanvasElement}
import org.scalajs.jquery.jQuery

import scala.util.Random


class World(canvasId: String) extends CollisionSpace {

  private val element = jQuery(s"#$canvasId")(0)
  private val canvas = element.asInstanceOf[HTMLCanvasElement]
  private val ctx = canvas.getContext("2d").asInstanceOf[CanvasRenderingContext2D]
  canvas.width = org.scalajs.dom.window.innerWidth
  canvas.height = org.scalajs.dom.window.innerHeight

  private var rockets: List[Rocket] = List.empty

  def addRocket(pilot: Pilot): Rocket = {
    rockets ::= new Rocket(
      x = Random.nextInt(canvas.width - 50) + 25,
      y = Random.nextInt(canvas.height - 50) + 25,
      angle = Random.nextInt(360),
      pilot = pilot,
      space = this)
    rockets.head
  }

  def drawScene(): Unit = {
//    ctx.fillStyle = "#81c4f7"
//    ctx.fillStyle = "#a7d8f6"
    ctx.fillStyle = "#8ecdf4"
    ctx.fillRect(0, 0, canvas.width, canvas.height)
    ctx.strokeRect(0, 0, canvas.width, canvas.height)
    rockets.foreach(rocket => {
      rocket.step()
      rocket.draw(ctx)
    })
    rockets = rockets.filterNot(_.destroyed)
  }

  override def toString = {
    s"""World(
       |  ${rockets.mkString(",\n  ")}
       |)""".stripMargin
  }

  override def spaceObjects: List[SpaceObject] = rockets

  override def spaceRect: (Int, Int, Int, Int) = (0, 0, canvas.width, canvas.height)

  def destroyRockets(): Unit = {
    rockets = List.empty
  }

}
