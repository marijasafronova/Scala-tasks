trait  FlightTrait {
  def Fly(): Unit
}
trait RunningTrait {
  def Run(): Unit
}
trait SwimmingTrait {
  def Swim(): Unit
}
class Penguin(var name: String) extends SwimmingTrait {
  def Swim(): Unit = println(s"Penguin $name is swimming")
}
class Chicken(var name: String) extends RunningTrait {
  def Run(): Unit = println(s"Chicken $name is running")
}
class Sparrow(var name: String) extends FlightTrait {
  def Fly(): Unit = println(s"Sparrow $name is flying")
}

object Birdsexercise extends App {
  val penguin1 = new Penguin("Arthur")
  val penguin2 = new Penguin("John")
  penguin1.Swim()
  penguin2.Swim()
  val chicken1 = new Chicken("Martha")
  val chicken2 = new Chicken("Elizabeth")
  chicken1.Run()
  chicken2.Run()
}
