package hackit

import hackit.game.RandomPilot

import scala.scalajs.js.JSApp
import org.scalajs.jquery.jQuery
import org.scalajs.dom._

import scala.util.Random


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
    for (i <- 1 to 10) {
      val r = w.addRocket(new RandomPilot())
      r.setRotation(Random.nextInt(200) - 100)
      r.setSpeed(20)
    }
    println(w)
    w.drawScene()
    val timer = setInterval(render _, 1000 / 30)
    println(timer)
  }
}
