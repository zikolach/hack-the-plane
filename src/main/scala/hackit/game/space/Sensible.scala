package hackit.game.space



trait Sensible {
  def getDistance: Int
  def sonar: List[UnknownSpaceObject]
  def radar: Option[CourseObject]
}
