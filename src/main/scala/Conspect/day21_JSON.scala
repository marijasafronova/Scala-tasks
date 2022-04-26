package Conspect

import Tasks.Text_tools

import java.nio.file.{Files, Paths}

object day21_JSON extends App {
  val url = "https://www.fruityvice.com//api//fruit/all"

  val dstFolder = "src/resources/json"

  Files.createDirectories(Paths.get(dstFolder))

  val fileName = "fruits.json"
  val dst = s"$dstFolder/$fileName"

  val text = Text_tools.getTextFromWebAndSave(url,dst)
  //mvnrepository.com

  val src = "src/resources/json/fruits.json"
  val rawText = Text_tools.getTextFromFile(src)
  println(rawText.take(100))

  val data = ujson.read(rawText) // we use uPickle library
  println(data)
  //in order for arr method to work i need to know that my top level of data is actually an array (not an object or just a string)
  val arrData = data.arr.toArray
  println(arrData.head)
  println(arrData.last)

  val fruits = for (item <- arrData) yield {
    fruit(item("genus").str,
      item("name").str,
      item("id").num.toInt,
      item("family").str,
      item("order").str,
      item("nutritions")("carbohydrates").num,
      item("nutritions")("protein").num,
      item("nutritions")("fat").num,
      item("nutritions")("calories").num,
      item("nutritions")("sugar").num)
  }
  fruits.take(3).foreach(println)

 // val MostCalorieDense = fruits.map(_.calorie).max
 val mostCalorieDenseFruit=fruits.sortBy(_.calories).reverse
  print("The most calorie dense fruit is ")
  mostCalorieDenseFruit.take(1).foreach(println)

  val fatFruit=fruits.sortBy(_.calories).reverse
  print("The most fatty fruits are ")
  fatFruit.take(5).foreach(println)

  val proteinFruit=fruits.sortBy(_.calories).reverse
  print("Most protein fruits are ")
  proteinFruit.take(5).foreach(println)

  val leastSugarFruit=fruits.sortBy(_.calories)
  print("The least sugary fruits are ")
  leastSugarFruit.take(5).foreach(println)

  //TODO find 5 fruits with most carbohydrates that are not sugars

}
