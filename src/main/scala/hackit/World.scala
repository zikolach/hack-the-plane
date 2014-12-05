package hackit

import org.scalajs.dom.{CanvasRenderingContext2D, HTMLCanvasElement}
import org.scalajs.jquery.jQuery


class World(canvasId: String) {

  val element = jQuery(s"#$canvasId")(0)
  val canvas = element.asInstanceOf[HTMLCanvasElement]
  val ctx = canvas.getContext("2d").asInstanceOf[CanvasRenderingContext2D]

  def drawScene(): Unit = {
    val (w, h) = (40, 60)
    ctx.clearRect(0, 0, canvas.width, canvas.height)
    ctx.translate(canvas.width/2, canvas.height/2)
    ctx.rotate(Math.PI / 180 * 5)
    ctx.translate(-canvas.width/2, -canvas.height/2)
    ctx.strokeRect(canvas.width/2-w/2, canvas.height/2-h/2, w, h)
  }

}
