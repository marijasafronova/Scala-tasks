package Conspect

import URL_Task.Text_Utilities

object ReadingDirectories extends App{
  val src = "src/resources"
  val files = Text_Utilities.getListOfFiles(src)
  //files.foreach(println)

  val AChristie = Text_Utilities.getListOfFiles(src, regex = "A*.txt")
  AChristie.foreach(println)

}
