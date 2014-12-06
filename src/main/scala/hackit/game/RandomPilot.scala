package hackit.game

import scala.util.Random

class RandomPilot() extends Pilot {
  var rotation: Int = 0
  var speed: Int = 0

  override def drive(): Unit = {
    val dr = Random.nextInt(5) - 2
    val ds = Random.nextInt(11) - 5
    control.foreach(c => {
      if (c.setRotation(rotation + dr)) rotation += dr
      if (c.setSpeed(speed + ds)) speed += ds
    })
  }
}
