package Conspect

import Tasks.Text_tools

import java.nio.file.{Files, Paths}
import scala.xml.XML

object day21XML extends App {

  val url = "https://gist.github.com/Ram-N/5189462/raw/46db0b43ad7bf9cbd32a8932f3ab981bd4b4da7c/books.xml"

  val dstFolder = "src/resources/xml"

  Files.createDirectories(Paths.get(dstFolder))

  val fileName = "books.xml"
  val dst = s"$dstFolder/$fileName"

  val text = Text_tools.getTextFromWebAndSave(url,dst)

  //parsing xmal

  val src = "src/resources/xml/books.xml"

  val xml = XML.loadFile(src)

  println(xml)

  val myOwnXML = <book>
  <title> Best Fruit Cocktails</title>
    <author> Doe, John</author>
  </book>
  println(myOwnXML)
//
//  val book = xml \ "Abstract"
//
//  val firstBook = book.head
//  println(firstBook)
//
//  println((firstBook \ "AbstractText Label=\"COMPARISON\"").text)
}
