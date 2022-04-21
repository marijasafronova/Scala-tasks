package Conspect

case class Person(name: String,
                  relation: String,
                  var topSpeed: Int) {
  def increaseSpeed(delta: Int): Int = {
    topSpeed += delta
    topSpeed
  }

  println(s"person $name")
}
