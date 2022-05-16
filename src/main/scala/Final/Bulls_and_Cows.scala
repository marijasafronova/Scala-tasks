package Final

import scala.io.StdIn.readLine
import scala.util.Random

object Bulls_and_Cows extends App {

  val playerA = readLine("Player A what is your name?")
  var playerB = readLine("Player B what is your name? (press ENTER for computer) ")
  if (playerB == "") playerB = "COMPUTER"

  println(s"Player $playerA and Player $playerB let us play Bulls and Cows!")

  def checkForRightNumber(number: List[Int]): Boolean = {
    val string = number.mkString
    if (string.length == 4) {
      string.length == string.distinct.length
    }
    else false
  } //we check if number has right length and unique digits

  var numberA = readLine(s"Player $playerA, enter your 4 digit number: ").trim.map(_.asDigit).toList
  while (!checkForRightNumber(numberA)) {
    if (!checkForRightNumber(numberA)) {
      numberA = readLine("Your number must contain 4 unique digits, try again: ").trim.map(_.asDigit).toList
    }
  } //in loop we are checking for correct input

  def getNumberB(playerB: String): List[Int] = {
    var numB = List[Int]()
    if (playerB != "COMPUTER") { //player vs player
      numB = readLine(s"Player $playerB, enter your 4 digit number: ").trim.map(_.asDigit).toList
      while (!checkForRightNumber(numB)) {
        if (!checkForRightNumber(numB)) {
          numB = readLine("Your number must contain 4 unique digits, try again: ").trim.map(_.asDigit).toList
        } //in loop we are checking for correct input
      }
    }
    else {
      while (numB.size < 4) { // player vs computer
        val digit = Random.nextInt(9) + 1
        if (numB.contains(digit))
          numB = numB :+ digit
      }
    }
    numB
  }
  val numberB = getNumberB(playerB) // getting second number
  var isPlayerAStarting = true

  val newGame = new BCgame(playerA, playerB, numberA, numberB, isPlayerAStarting)

    if (isPlayerAStarting) {
    val consoleInput = readLine(s"Player $playerA, your guess: ").toInt
      val input = newGame.splitNumber(consoleInput)
    newGame.BCounter(numberA, input)
    isPlayerAStarting = false
    }
    else {
      if (playerB == "COMPUTER") {
        var guess = List[Int]()
        while (guess.size < 4) { // player vs computer
          val d = Random.nextInt(9) + 1
          if (guess.contains(d))
            guess = guess :+ d
        }
        println(s"COMPUTER guess: $guess")
        newGame.BCounter(numberB, guess)
      }
      else {
        val consoleInput = readLine(s"Player $playerB, your guess: ").toInt // player vs player
        val input = newGame.splitNumber(consoleInput)
        newGame.BCounter(numberB, input)
      }
      isPlayerAStarting = true
    }




}
