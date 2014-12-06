package hackit.game.pilot

import hackit.game.Control

import scala.scalajs.js
import scala.scalajs.js.annotation.JSExport

class CodePilot(code: String) extends Pilot {
  override def drive(): Unit = {
    control.foreach(runCode)
  }

  @JSExport
  def setSpeed(speed: Int): Unit = {
    control.foreach(_.setSpeed(speed))
  }

  @JSExport
  def setRotation(angle: Int): Unit = {
    control.foreach(_.setRotation(angle))
  }

  def runCode(ctrl: Control): Unit = {
    val f = new js.Function(code)
    f.call(this.asInstanceOf[js.Object])
//    f.call(ctrl.asInstanceOf[js.Object])
//    js.eval(code)
  }
}
