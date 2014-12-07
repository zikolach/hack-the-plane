package hackit

import hackit.blockly.Blockly
import hackit.game.pilot.{CodePilot, RandomPilot}

import scala.scalajs.js
import scala.scalajs.js.JSApp
import org.scalajs.jquery.jQuery
import org.scalajs.dom._


object App extends JSApp {

  def setupUI(): Unit = {
    Blockly.inject(jQuery("#blocklyDiv")(0), js.Dynamic.literal(
      "toolbox" -> jQuery("#toolbox")(0)
    ))
    jQuery("#click-to-code").click(printCode _)
    jQuery("#click-to-clear").click(clear _)
  }

  def setupWorld(): Unit = {
    w = new World("scene")
    for (i <- 1 to 20) {
      w.addRocket(new RandomPilot())
    }
    setInterval(render _, 1000 / 30)
  }

  def printCode(): Unit = {
    val code = Blockly.JavaScript.workspaceToCode()
    jQuery("#code").text(code)
    w.addRocket(new CodePilot(code))
  }




  def clear(): Unit = {
    w.destroyRockets()
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
