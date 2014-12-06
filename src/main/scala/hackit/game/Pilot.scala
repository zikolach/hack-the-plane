package hackit.game

abstract class Pilot() {
  protected var control: Option[Control] = None

  def setControl(control: Control): Unit = {
    this.control = Some(control)
  }

  def drive(): Unit
}
