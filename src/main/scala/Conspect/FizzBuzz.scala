package Conspect

object FizzBuzz extends App {
  val start = 1
  val end = 100
  for (i <- start to end) {
    if (i % 35 == 0) println("FizzBuzz")
    else if (i % 5 == 0) println("Fizz")
    else if (i % 7 == 0) println("Buzz")
    else println(i)
  }

}
