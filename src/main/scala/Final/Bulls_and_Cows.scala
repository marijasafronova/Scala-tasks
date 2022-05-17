package Final

import scala.io.StdIn.readLine

object Bulls_and_Cows extends App {

  val playerA = readLine("Player A what is your name?")
  var playerB = readLine("Player B what is your name? (press ENTER for computer) ")
  if (playerB == "") playerB = "COMPUTER"

  println(s"Player $playerA and Player $playerB let us play Bulls and Cows!")

  var win = false // can put inside class
  var isPlayerATurn=true // can put inside class
  val BullsAndCowsGame = new BullsAndCows(playerA, playerB, isPlayerATurn, win)

  var numberA = readLine(s"Player $playerA, enter your 4 digit number: ").trim.map(_.asDigit).toList
  while(!BullsAndCowsGame.checkNumber(numberA)) {
        numberA = readLine("Your number must contain 4 unique digits, try again: ").trim.map(_.asDigit).toList
  }

  var numberB = BullsAndCowsGame.getNumberB

  while (BullsAndCowsGame.isGameActive) {
    if (BullsAndCowsGame.currentPlayer==playerA) {
      val input = readLine(s"Player $playerA, your guess: ").trim.map(_.asDigit).toList
      BullsAndCowsGame.BullsCowsCounter(numberB, input)
    } else  {
      if (!BullsAndCowsGame.isSecondPlayerAComputer) {
        val input = readLine(s"Player $playerB, your guess: ").trim.map(_.asDigit).toList
        BullsAndCowsGame.BullsCowsCounter(numberA, input)
      }
      else {
        val computerGuess = BullsAndCowsGame.randomizeNumber
        println(s"Computer guess: ${computerGuess.mkString}")
        BullsAndCowsGame.BullsCowsCounter(numberA, computerGuess)
      }
    }
    BullsAndCowsGame.nextPlayer()
  }

}
