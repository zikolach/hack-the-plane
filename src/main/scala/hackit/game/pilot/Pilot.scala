package hackit.game.pilot

import hackit.game.Control

abstract class Pilot {
  protected var control: Option[Control] = None

  def setControl(control: Control): Unit = {
    this.control = Some(control)
  }

  def drive(): Unit
}
