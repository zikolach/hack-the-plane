package hackit.game.pilot

import hackit.game.space.{Loggable, Sensible, Controllable}

abstract class Pilot {
  protected var control: Option[Controllable with Sensible with Loggable] = None

  def setControl(control: Controllable with Sensible with Loggable): Unit = {
    this.control = Some(control)
  }

  def drive(): Unit
}
