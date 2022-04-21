package Conspect

object TuplesClasswork15 extends App {

  val myTuple = ("Marija", 162, "LU")

  println(myTuple)
  println(myTuple._1)
  println(myTuple._3) // до 22 объектов, используется для передачи данных разных типов
  // распакуем Tuple
  val name, height, uni = myTuple
  println(name)
  println(height)
  println(uni)

  // loops

  val myArray = myTuple.productIterator.toArray
  for (item <- myArray) println(item)

  val stringArray = myArray.map(_.toString)
  for (item <- stringArray) println(item)


}
