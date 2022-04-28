package Conspect

import Tasks.Text_tools

import java.nio.file.{Files, Paths}
import scala.xml.XML

object day21XML extends App {

  val url = "https://gist.github.com/Dhilip1997/8cddf24d5e2e3528091d18c06b462b8d/raw/8441ac04511f535c27cffc3cc9a1b22ac013e360/Raw%2520data.xml"

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
