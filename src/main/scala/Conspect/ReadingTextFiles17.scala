package Conspect

import scala.io.Source

object ReadingTextFiles17 extends App {

  println("Text")

  println(System.getProperty("user.dir"))

  val absoluteFilePath = "C:\\Users\\mashu\\IdeaProjects\\untitled\\src\\resources\\testtext.txt"
  val relativeFilePath = "src/resources/testtext.txt"

  //for (char <- Source.fromFile(filePath)) print(char)
  // for (line <- Source.fromFile(filePath).getLines) print(line)

  val mySong = Source.fromFile(relativeFilePath).mkString

  println(mySong)

  // val mySongLines = Source.fromFile(absoluteFilePath).getLines.toArray
  val mySongLines = Source.fromFile(relativeFilePath).getLines.toArray

  println("*" * 40)

  for (line <- mySongLines) println(line)

  def getTextFromFile(src: String): String = {
    val bufferedSource = Source.fromFile(src)
    val text = bufferedSource.mkString
    bufferedSource.close()
    text

  }

  val myText = getTextFromFile(relativeFilePath)
  println(myText)

  def getLinesFromFile(src: String): Array[String] = {
    val bufferedSource = Source.fromFile(src)
    val lines = bufferedSource.getLines().toArray
    bufferedSource.close()
    lines
  }

  val maxLines = 5
  println(s"first $maxLines lines")
  val myLines = getLinesFromFile(relativeFilePath)
  myLines.slice(0, maxLines).foreach(println)

  println(myLines.slice(0, maxLines).mkString("\n"))
  //lines starting with "And"
  val andLines = myLines.filter(_.startsWith(("And")))
  andLines.foreach(println)

  val blueLines = myLines.filter(_.contains("blue"))
  blueLines.foreach(println)
  val endsWithBlue = for (line <- myLines if line.endsWith(("blue"))) yield line.toUpperCase
  endsWithBlue.foreach(println)

  val firstLine = myLines(0)
  println(firstLine)

}
