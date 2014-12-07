package hackit.blockly

import org.scalajs.dom.HTMLElement

import scala.scalajs.js
import scala.scalajs.js.annotation.JSName

trait JavaScript extends js.Object {
  def workspaceToCode(): String = ???
}

trait Xml extends js.Object {
  def workspaceToDom(workspace: js.Dynamic): js.Object = ???
  def domToText(xml: js.Object): js.String = ???
  def textToDom(text: js.Dynamic): js.Object = ???
  def domToWorkspace(workspace: js.Dynamic, dom: js.Object): js.Undefined = ???
}

@JSName("Blockly")
object Blockly extends js.Object {
  def JavaScript: JavaScript = ???
  def Xml: Xml = ???
  def inject(elt: HTMLElement, config: js.Object): Unit = ???
  def Blocks: js.Dictionary[js.Object] = ???
  def mainWorkspace: js.Dynamic = ???
}
