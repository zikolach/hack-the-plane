package hackit.game.pilot

import hackit.game.space.UnknownSpaceObject

import scala.scalajs.js
import scala.scalajs.js.annotation.JSExport

class CodePilot(code: String) extends Pilot {

  override def drive(): Unit = {
    val f = new js.Function(code)
    f.call(this.asInstanceOf[js.Object])
  }

  @JSExport
  def setSpeed(speed: Double): Unit = {
    control.foreach(_.setSpeed(speed))
  }

  @JSExport
  def setRotation(angle: Double): Unit = {
    control.foreach(_.setRotation(angle))
  }

  @JSExport
  def getDistance(): Int = control.map(_.getDistance).getOrElse(0)

  @JSExport
  def sonar(): js.Array[js.Object] = control.map(_.sonar).getOrElse(List.empty).asInstanceOf[js.Array[js.Object]]

  @JSExport
  def log(text: js.Any): Unit = {
    control.map(_.log(if (text != null) text.toString else ""))
  }

  @JSExport
  def radar(): js.Object = control.flatMap(_.radar) match {
    case Some(obj) => obj.asInstanceOf[js.Object]
    case None => null
  }

}
