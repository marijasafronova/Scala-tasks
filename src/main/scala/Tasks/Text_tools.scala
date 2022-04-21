package Tasks
import java.io.{File, FileWriter}
import scala.io.Source

object Text_tools {
  def getTextFromWeb(url: String): String = {
    val html = Source.fromURL(url) // this gets us buffersource stream
    html.mkString
  }

  def saveText(dstPath: String, text: String, append: Boolean = false, verbose: Boolean = false): Unit = {
    //    import java.io.{PrintWriter, File} //explicit import
    if (verbose) println(s"Saving ${text.length} characters to $dstPath")
    //so writing to file can be done either by overwriting the whole file (the default)
    //or by appending to the end of the file
    val fw = new FileWriter(dstPath, append) //so by default old dstPath will be overWritten
    //    val pw = new PrintWriter(new File(dstPath))
    if (append) fw.write("\n")
    fw.write(text)
    fw.close() //when writing it is especially important to close as early as possible
  }

  def saveLines(dstPath: String, lines: Array[String], append:Boolean=false, lineEnd:String="\n"):Unit = {
    saveText(dstPath, lines.mkString(lineEnd), append)
  }


  def getTextFromWebAndSave(url: String, dst: String): String = {
    val text = getTextFromWeb(url)
    saveText(dst, text)
    text //we return the text just in case we want to save and do some work as well
  }

  def getWordCountPerLine(lines: Array[String], sep: String = " +"): Array[Int] = {
    val wordsLines = lines.map(_.split(sep)) //so we get an Array of Array of words
    val wordsPerLine = wordsLines.map(_.length)
    wordsPerLine
  }

  def getListOfFiles(dir: String, regex: String=".*"): List[File] ={
    val d = new File(dir)
    if (d.exists && d.isDirectory) {
      d.listFiles.filter(file => file.isFile && file.getName.matches(regex)).toList
    } else {
      List[File]()
    }
  }

}
