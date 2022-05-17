package Final
import scala.collection.mutable.ArrayBuffer
import scala.io.StdIn.readLine
import scala.util.Random

class BullsAndCows(
                    val playerA:String,
                    val playerB:String,
                    var isPlayerATurn:Boolean = true,
                    var win: Boolean = false) {
  println("Created a new game object of CowsAndBulls")
  var currentState = true
  var currentPlayer: String = if (isPlayerATurn) playerA else playerB
  var guessesArray: ArrayBuffer[(String, String)] = ArrayBuffer()

  def isSecondPlayerAComputer: Boolean = playerB == "COMPUTER"

  def checkNumber(number: List[Int]): Boolean = {
    val string = number.mkString
    if (string.length == 4) {
      string.length == string.distinct.length
    }
    else false
  }

  def randomizeNumber: List[Int] = {
    var number = List[Int]()
    while (number.size < 4) { // player vs computer
      val d = Random.nextInt(9) + 1
      if (!number.contains(d))
        number = number :+ d
    }
    number
  }

  def getNumberB: List[Int] = {
    var numB = List[Int]()
    if (!isSecondPlayerAComputer) { //player vs player
      numB = readLine(s"Player $playerB, enter your 4 digit number: ").trim.map(_.asDigit).toList
      while (!checkNumber(numB)) {
        numB = readLine("Your number must contain 4 unique digits, try again: ").trim.map(_.asDigit).toList
        //in loop we are checking for correct input
      }
    }
    else {
      numB = randomizeNumber
      println(s"Computer generated a number: ${numB.mkString}")
    }
    numB
  }

  def isGameActive: Boolean = currentState != win

  def BullsCowsCounter(playerNum: List[Int], input: List[Int]): Unit = {
    var cows = 0
    var bulls = 0
    if (isGameActive) {
      for (i <- 0 to 3)
        if (playerNum(i) == input(i)) {
          bulls += 1
        }
        else {
          if (playerNum.contains(input(i)))
            cows += 1
        }
      if (bulls == 4) {
        win = true
        println(s"$currentPlayer you won, congratulations!")
      } else {
        println(s"Cows: $cows " +
          s"Bulls: $bulls")
      }
    }
  }

  def getGuesses: Array[(String, String)] = guessesArray.toArray

  //FIX IT
  def nextPlayer(): String = {
    isPlayerATurn = !isPlayerATurn
    currentPlayer = if (isPlayerATurn) playerA else playerB
    currentPlayer
  }

//  def getLoser: String = {
//    if (isGameActive) "N/A" // could be empty string
//    else currentPlayer //since currentPlayer with no moves to make is the winner
//  }
//
//  def getWinner: String = {
//    if (isGameActive) "N/A" // could be empty string
//    else { //game is finished
//      if (isPlayerATurn) playerB else playerA
//    }
//  }


    def printGuesses(): Array[(String, String)] = {
      for (((guess, result), index) <- guessesArray.zipWithIndex) {
        val playerName = if (index % 2 == 0) playerA else playerB
        println(s"Guess ${index + 1}. $playerName guessed $guess resulting in $result.")
      }
      guessesArray.toArray
    }

  }