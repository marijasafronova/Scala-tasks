package Tasks

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import scala.io.Source

class Document (val title: String = "",
                val author: String = "",
                val url: String = "",
                val rows: Array[String] = Array[String]()) {

  val rowCount: Int = rows.length
  val wordCount: Int = Text_tools.getWordCountPerLine(rows).sum

  def Save(dst: String = "", folder: String = "src/resources/texts"): Unit = {

    val stringBuilder = new StringBuilder()
    stringBuilder ++= s"URL: $url"
    stringBuilder += '\n'
    stringBuilder ++= author
    stringBuilder += '\n'
    stringBuilder ++= title
    stringBuilder ++= "\n\n\n\n"
    stringBuilder ++= rows.mkString

    var filename = ""
    if (dst.isEmpty) {
      if (author.nonEmpty && title.nonEmpty) {
        filename = author.slice(0, 10) + '_' + title.slice(0, 15) + '_' + DateTimeFormatter.ofPattern("yyyy_MM_dd_HH_mm").format(LocalDateTime.now) + ".txt"
      }

      Text_tools.saveText(folder+'/'+filename, stringBuilder.mkString)
    }
    else {
      Text_tools.saveText(folder+'/'+dst, stringBuilder.mkString)
    }
  }

}

object URL_Task extends App {

  def getAuthor(lines:Array[String], prefix:String="Author:"):String = {
    val authorLines = lines.filter(_.startsWith(prefix))
    if (authorLines.length > 0) authorLines.head.replace(prefix, "").trim
    else "NO AUTHOR"

  }

  def getTitle(lines: Array[String], prefix:String="Title:"):String = {
    val titleLines = lines.filter(_.startsWith(prefix))
    if (titleLines.length > 0) titleLines.head.replace(prefix, "").trim
    else "NO TITLE"
  }

  def getLinesFromFile(src: String):Array[String] = {
    val bufferedSource = Source.fromFile(src)
    val lines = bufferedSource.getLines().toArray
    bufferedSource.close()
    lines
  }

  def getLinesFromURL(src: String):Array[String] = {
    val bufferedSource = Source.fromURL(src)
    val lines = bufferedSource.getLines().toArray
    bufferedSource.close()
    lines
  }

  var filePath = "src/resources/webPages.txt"

  if (args.length != 0) {
    filePath = args(0)
  }

  var links = getLinesFromFile(filePath)
  var url = s""
  for (link <- links) {
    if (!link.contains("https://") && !link.contains("http://")) {
      url = "https://" + link
    } else {
      url = link
    }
    val newText = getLinesFromURL(url)
    val author = getAuthor(newText)
    val title = getTitle(newText)
    val doc = new Document(title, author, url, newText)
    doc.Save()
    Thread.sleep(200)
  }
}
