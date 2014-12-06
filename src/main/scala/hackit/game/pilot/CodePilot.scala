package hackit.game.pilot

import scala.scalajs.js
import scala.scalajs.js.annotation.JSExport

class CodePilot(code: String) extends Pilot {

  override def drive(): Unit = {
    val f = new js.Function(code)
    f.call(this.asInstanceOf[js.Object])
  }

  @JSExport
  def setSpeed(speed: Int): Unit = {
    control.foreach(_.setSpeed(speed))
  }

  @JSExport
  def setRotation(angle: Int): Unit = {
    control.foreach(_.setRotation(angle))
  }

  @JSExport
  def getDistance(): Int = control.map(_.getDistance).getOrElse(0)

}
