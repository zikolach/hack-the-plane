package hackit

import java.util.Date

import scala.scalajs.js.JSApp
import org.scalajs.dom
import dom.document

import scala.scalajs.js.annotation.JSExport

object App extends JSApp {

  var counter = 0

  def appendPar(targetNode: dom.Node, text: String): Unit = {
    val parNode = document.createElement("p")
    val textNode = document.createTextNode(text)
    parNode.appendChild(textNode)
    targetNode.appendChild(parNode)
  }

  @JSExport
  def addMessage(): Unit = {
    appendPar(document.body, counter.toString)
    counter = counter + 1
  }

  def main(): Unit = {
    appendPar(document.body, "Hello Zikolach")
  }
}
