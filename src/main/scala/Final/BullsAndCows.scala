package Final
import scala.collection.mutable.ArrayBuffer
import scala.io.StdIn.readLine
import scala.util.Random

class BullsAndCows {
  // What player turn
  private var isPlayerATurn = true
  // Game state
  private var gameWon = false

  // Storing players numbers for the game
  private var numberA = List[Int]()
  private var numberB = List[Int]()

  // Storing player names for the game
  private var playerA = ""
  private var playerB = ""

  // Guess history
  private var guessesArray: ArrayBuffer[(String, String)] = ArrayBuffer()

  private def isSecondPlayerAComputer: Boolean = playerB == "COMPUTER"

  private def nextPlayer: Unit = isPlayerATurn = !isPlayerATurn

  // Get history
  private def getGuesses: Array[(String, String)] = guessesArray.toArray
  // Get input in int list
  private def getInputInList(msg: String): List[Int] = readLine(msg).trim.map(_.asDigit).toList
  // Get current player
  private def getCurrentPlayer: String = if (isPlayerATurn) playerA else playerB

  private def getGuess(msg: String): List[Int] = {
    var guess = getInputInList(msg)

    while (!checkNumber(guess)) {
      guess = getInputInList("Incorrect guess, enter again: ")
    }

    guess
  }

  private def getPlayerNumber: Unit = {
    var input = List[Int]()

    if (!isPlayerATurn && isSecondPlayerAComputer) {
      input = randomizeNumber
      println(s"Computer generated a number: ${input.mkString}")

      numberB = input
    }
    else {
      input = getInputInList(s"Player $getCurrentPlayer, enter your 4 digit number: ")

      while (!checkNumber(input)) {
        input = getInputInList("Your number must contain 4 unique digits, try again: ")
      }

      if (isPlayerATurn)
        numberA = input
      else
        numberB = input
    }
  }

  private def Greetings: Unit = {
    playerA = readLine("Player A what is your name? ")
    playerB = readLine("Player B what is your name? ")

    println("\nGame begins!")
    println(s"$playerA VS $playerB\n")
  }

  private def Menu: Unit = {
    println("*" * 10 + " Bulls & Cows " + "*" * 10)
    println("-" * 10 + "  Game Menu  " + "-" * 10)
    println("1. Player vs Player")
    println("2. Player vs Computer")
  }

  private def checkNumber(number: List[Int]): Boolean = {
    val string = number.mkString
    if (number(0) == 0) {
      false
    }
    else if (string.length == 4) {
      string.length == string.distinct.length
    }
    else
      false
  }

   private def randomizeNumber: List[Int] = {
    var number = List[Int]()
    while (number.size < 4) { // player vs computer
      val d = Random.nextInt(10)
      if (!number.contains(d))
        if (number.size == 0 && d != 0)
          number = number :+ d
        else if (number.size > 0)
          number = number :+ d
    }
    number
  }

  private def BullsCowsCounter(playerNum: List[Int], input: List[Int]): Unit = {
    var cows = 0
    var bulls = 0

    if (!gameWon) {
      for (i <- 0 to 3) {
        if (playerNum(i) == input(i)) {
          bulls += 1
        }
        else {
          if (playerNum.contains(input(i)))
            cows += 1
        }
      }

      if (bulls == 4) {
        gameWon = true
        println(s"$getCurrentPlayer you won, congratulations!")
      }
      else {
        println( s"Bulls: $bulls Cows: $cows")
      }
    }
  }

  private def printGuesses(): Array[(String, String)] = {
    for (((guess, result), index) <- guessesArray.zipWithIndex) {
      val playerName = if (index % 2 == 0) playerA else playerB
      println(s"Guess ${index + 1}. $playerName guessed $guess resulting in $result.")
    }
    guessesArray.toArray
  }

  private def PlayerVsPlayer: Unit = {
    Greetings
    getPlayerNumber
    nextPlayer
    getPlayerNumber
    nextPlayer

    while (!gameWon) {
      if (isPlayerATurn) {
        BullsCowsCounter(numberB, getGuess(s"Player $getCurrentPlayer, your guess: "))
        nextPlayer
      }
      else {
        BullsCowsCounter(numberA, getGuess(s"Player $getCurrentPlayer, your guess: "))
        nextPlayer
      }
    }
  }

  private def PlayerVsComputer: Unit = {
    playerA = readLine("Player, what is your name? ")
    playerB = "COMPUTER"

    println("\nGame begins!")
    println(s"$playerA VS Computer\n")

    nextPlayer
    getPlayerNumber
    nextPlayer

    while (!gameWon) {
      BullsCowsCounter(numberB, getGuess(s"Player $getCurrentPlayer, your guess: "))
    }
  }

  def Play: Unit = {
    Menu
    val gameOption = readLine("Choose game mode: ").toInt

    gameOption match {
      case 1 => PlayerVsPlayer
      case 2 => PlayerVsComputer
      case _ => Play
    }
  }
}