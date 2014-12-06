package hackit.game.pilot

import hackit.game.{Sensible, Controllable}

abstract class Pilot {
  protected var control: Option[Controllable with Sensible] = None

  def setControl(control: Controllable with Sensible): Unit = {
    this.control = Some(control)
  }

  def drive(): Unit
}
