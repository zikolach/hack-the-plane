package hackit

import hackit.blockly.Blockly
import hackit.game.RandomPilot

import scala.scalajs.js
import scala.scalajs.js.JSApp
import org.scalajs.jquery.jQuery
import org.scalajs.dom._

import scala.util.Random


object App extends JSApp {

  var counter = 0

  def setupUI(): Unit = {
    Blockly.inject(jQuery("#blocklyDiv")(0), js.Dynamic.literal(
      "toolbox" -> jQuery("#toolbox")(0)
    ))
    println(Blockly.Blocks.size)
    jQuery("#click-to-code").click(printCode _)
  }

  def setupWorld(): Unit = {
    w = new World("scene")
    for (i <- 1 to 10) {
      val r = w.addRocket(new RandomPilot())
      r.setRotation(Random.nextInt(200) - 100)
      r.setSpeed(20)
    }
    val timer = setInterval(render _, 1000 / 30)
//    println(timer)
  }

  def addMessage(): Unit = {
    jQuery("body").append(s"<p>$counter</p>")
    counter = counter + 1
  }

  def printCode(): Unit = {
    jQuery("#code").text(Blockly.JavaScript.workspaceToCode())
  }

  var w: World = null

  def render(): Unit = {
    w.drawScene()
  }

  def main(): Unit = {
    jQuery(setupUI _)
    setupWorld()
  }
}
