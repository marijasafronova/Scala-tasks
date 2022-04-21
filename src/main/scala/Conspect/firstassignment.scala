package Conspect

import scala.io.StdIn.readLine

object firstassignment extends App {
  val name = readLine("Enter your name: ")
  val height = readLine("Enter tree height: ").toInt


  def printTree(name: String, height: Int, symbol: Char = '*', maximumHeight: Int = 40): Unit = {
    var charCounter = 0
    if (height <= maximumHeight) {
      println(" " * (height - 1) + symbol)
      for (i <- 1 until height) {
        println(" " * (height - 1 - i) + name(charCounter).toString * (i * 2 + 1))
        if (charCounter != name.length() - 1) {
          charCounter = charCounter + 1
        } else {
          charCounter = 0
        }
      }
    } else {
      println("Maximum height exceeded")
    }
  }

  printTree(name, height)
}
