package hackit.blockly

import org.scalajs.dom.HTMLElement

import scala.scalajs.js
import scala.scalajs.js.annotation.JSName

//@JSName("JavaScript")
trait JavaScript extends js.Object {
  def workspaceToCode(): String = ???
}

@JSName("Blockly")
object Blockly extends js.Object {
  def JavaScript: JavaScript = ???
  def inject(elt: HTMLElement, config: js.Object): Unit = ???
  def Blocks: js.Dictionary[js.Object] = ???
}
