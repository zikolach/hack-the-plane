package hackit

import java.util.Date

import scala.scalajs.js.JSApp
import org.scalajs.jquery.jQuery
import org.scalajs.dom._


object App extends JSApp {

  var counter = 0

  def setupUI(): Unit = {
    jQuery("body").append("<p>Hello World!!!</p>")
    jQuery("#click-me-button").click(addMessage _)
  }

  def addMessage(): Unit = {
    jQuery("body").append(s"<p>$counter</p>")
    counter = counter + 1
  }

  var w: World = null

  def render(): Unit = {
    w.drawScene()
  }

  def main(): Unit = {
    jQuery(setupUI _)
    w = new World("scene")
    w.drawScene()
    val timer = setInterval(render _, 100)
    println(timer)
  }
}
