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
  def getTextFromFile(src: String):String = {
    val bufferedSource = Source.fromFile(src) //think of bufferedSource as a stream of bytes
    val text = bufferedSource.mkString //we convert this stream into actual string
    bufferedSource.close() //important to close the file
    text
  }
  def getWordCountPerLine(lines: Array[String], sep: String = " +"): Array[Int] = {
    val wordsLines = lines.map(_.split(sep)) //so we get an Array of Array of words
    val wordsPerLine = wordsLines.map(_.length)
    wordsPerLine
  }
  def getLinesFromFile(src: String):Array[String] = {
    val bufferedSource = Source.fromFile(src)
    val lines = bufferedSource.getLines().toArray
    bufferedSource.close()
    lines
  }

  def getListOfFiles(dir: String, regex: String=".*"): List[File] ={
    val d = new File(dir)
    if (d.exists && d.isDirectory) {
      d.listFiles.filter(file => file.isFile && file.getName.matches(regex)).toList
    } else {
      List[File]()
    }
  }
  def myRound(n: Double, precision: Int = 0): Double = {
    //so the trick would be to multiply by some power of 10 then divide by that mulitplier
    //so (n*100).round/100 would give us 2 points of precision
    //why because built in round gives us 0 precision
    //so if we have 100 that is 10 to the 2nd power(squared)
    //we can use the built in Math.pow to do that
    val multiplier = Math.pow(10, precision) //so precision 0 will give us 10 to 0 which is 1

    //    n.round //only 0 precision
    (n * multiplier).round / multiplier //we utilize the built in round
  }

}
