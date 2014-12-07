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
    loadWorkspace()
    jQuery("#click-to-code").click(printCode _)
    jQuery("#click-to-clear").click(clear _)
    jQuery("#click-to-save").click(saveWorkspace _)
    jQuery("#click-to-load").click(loadWorkspace _)
    jQuery("#toggle-blockly").click(toggleBlockly _)
    jQuery("#toggle-log").click(toggleLog _)
  }

  def setupWorld(): Unit = {
    w = new World("scene")
    for (i <- 1 to 20) {
      w.addRocket(new RandomPilot())
    }
    setInterval(render _, 1000 / 30)
  }

  def toggleBlockly(): Unit = {
      jQuery("#blocklyDiv").toggleClass("hidden")
      jQuery("#code").toggleClass("hidden")
  }

  def toggleLog(): Unit = {
      jQuery("#log").toggleClass("hidden")
  }

  def printCode(): Unit = {
    val code = Blockly.JavaScript.workspaceToCode()
    jQuery("#code").text(code)
    w.addRocket(new CodePilot(code))
    jQuery("#blocklyDiv").addClass("hidden")
    jQuery("#code").addClass("hidden")
  }

  def saveWorkspace(): Unit = {
    val xml = Blockly.Xml.workspaceToDom(Blockly.mainWorkspace)
    val str = Blockly.Xml.domToText(xml)
    localStorage.setItem("data", str)
    println("Workspace saved")
  }

  def loadWorkspace(): Unit = {
    jQuery("#blocklyDiv").removeClass("hidden")
    jQuery("#code").removeClass("hidden")
    val str = localStorage.getItem("data")
    val xml = Blockly.Xml.textToDom(str)
    Blockly.Xml.domToWorkspace(Blockly.mainWorkspace, xml)
    println("Workspace restored")
  }

  def clear(): Unit = {
    w.destroyRockets()
  }

  var w: World = null

  def render(): Unit = {
    w.drawScene()
  }

  def main(): Unit = {
    localStorage.getItem("data") match {
      case s if s != null => //println(s.asInstanceOf[String])
      case _ =>
        localStorage.setItem("data", "<xml xmlns=\"http://www.w3.org/1999/xhtml\"><block type=\"variables_set\" id=\"1\" inline=\"true\" x=\"5\" y=\"12\"><field name=\"VAR\">distance</field><value name=\"VALUE\"><block type=\"radar_distance\" id=\"2\"></block></value><next><block type=\"variables_set\" id=\"3\" inline=\"true\"><field name=\"VAR\">angle</field><value name=\"VALUE\"><block type=\"map\" id=\"4\" inline=\"true\"><field name=\"FROM_MIN\">-180</field><field name=\"FROM_MAX\">180</field><field name=\"TO_MIN\">-50</field><field name=\"TO_MAX\">50</field><value name=\"VALUE\"><block type=\"radar_angle\" id=\"5\"></block></value></block></value><next><block type=\"controls_if\" id=\"6\" inline=\"false\"><mutation elseif=\"1\"></mutation><value name=\"IF0\"><block type=\"logic_compare\" id=\"7\" inline=\"true\"><field name=\"OP\">LT</field><value name=\"A\"><block type=\"border_distance\" id=\"8\"></block></value><value name=\"B\"><block type=\"math_number\" id=\"9\"><field name=\"NUM\">100</field></block></value></block></value><statement name=\"DO0\"><block type=\"rotate\" id=\"10\" inline=\"false\"><value name=\"ANGLE\"><block type=\"math_number\" id=\"11\"><field name=\"NUM\">50</field></block></value></block></statement><value name=\"IF1\"><block type=\"logic_compare\" id=\"14\" inline=\"true\"><field name=\"OP\">GT</field><value name=\"A\"><block type=\"variables_get\" id=\"15\"><field name=\"VAR\">distance</field></block></value><value name=\"B\"><block type=\"math_number\" id=\"16\"><field name=\"NUM\">50</field></block></value></block></value><statement name=\"DO1\"><block type=\"rotate\" id=\"19\" inline=\"false\"><value name=\"ANGLE\"><block type=\"variables_get\" id=\"20\"><field name=\"VAR\">angle</field></block></value></block></statement><next><block type=\"speed\" id=\"17\" inline=\"false\"><value name=\"SPEED\"><block type=\"math_number\" id=\"18\"><field name=\"NUM\">40</field></block></value></block></next></block></next></block></next></block></xml>")
    }
    jQuery(setupUI _)
    setupWorld()
  }
}
