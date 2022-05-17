package Final

import scala.util.Random

object testingApp extends App {
  var digits = List[Int]()
  while (digits.size < 4) {
    val d = Random.nextInt(9) + 1
    if (!digits.contains(d))
      digits = digits :+ d

  }
  println(digits.mkString)
}
