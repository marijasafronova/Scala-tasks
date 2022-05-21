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

  // Creating database
  val db = new BullsAndCowsDatabase("src/resources/db/game.db")
  db.Migrate()

  // Guess history
  private var turnHistory: ArrayBuffer[(String, String)] = ArrayBuffer()

  private def isSecondPlayerAComputer: Boolean = playerB == "COMPUTER"

  private def nextPlayer: Unit = isPlayerATurn = !isPlayerATurn

  // Get input in int list
  private def getInputInList(msg: String): List[Int] = readLine(msg).trim.map(_.asDigit).toList
  // Get current player
  private def getCurrentPlayer: String = if (isPlayerATurn) playerA else playerB
  // Get opposite player
  private def getOppositePlayer: String = if (isPlayerATurn) playerB else playerA


  // Function which checks users guess
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
    while (playerA==playerB)
      {
        playerB = readLine(s"Player B $playerB, your name is already taken, please, choose another nickname for your game: ")
      }

    db.insertPlayer(playerA)
    db.insertPlayer(playerB)

    println("\nGame begins!")
    println(s"$playerA VS $playerB\n")
  }

  private def Menu: Unit = {
    println("*" * 10 + " Bulls & Cows " + "*" * 10)
    println("-" * 10 + "  Game Menu  " + "-" * 10)
    println("1. Player vs Player")
    println("2. Player vs Computer")
    println("4. Scoreboard")
    println("5. Rules")
    println("6. Exit")
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
 // ToDO 0B0C for the database
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

        db.insertResult(getCurrentPlayer, getOppositePlayer)
        turnHistory.append((input.mkString, s"B$bulls"+s"C$cows"))
        db.insertHistory(turnHistory)
      }
      else {
        println( s"Bulls: $bulls Cows: $cows")
        turnHistory.append((input.mkString, s"B$bulls"+s"C$cows"))
      }
    }
  }

 // TODO 3rd mode
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

  private def Scoreboard: Unit = {
    println("\n")
    db.getScoreboard
    println("\n")
    Play
  }

  private def Rules: Unit = {
    println("\n"*2)
    println("The players each write a 4-digit secret number.\n" +
      "The digits must be all different.\n" +
      "Then, in turn, the players try to guess their opponent's number who gives the number of matches.\n" +
      "The digits of the number guessed also must all be different.\n" +
      "If the matching digits are in their right positions, they are \"bulls\",\n" +
      "if in different positions, they are \"cows\"\n")
    Play
  }

  def Play: Unit = {
    var gameOption = 0

    Menu
    try {
      gameOption = readLine("Choose game mode: ").toInt
    }
    catch {
      case e: NumberFormatException => {
        println("Your choice must be a number! Try again!")
        Play
      }
    }

    gameOption match {
      case 1 => PlayerVsPlayer
      case 2 => PlayerVsComputer
      // case 3 => 2PlayerVSComputer
      case 4 => Scoreboard
      case 5 => Rules
      case 6 => // does not return anything, app closes
      case _ => Play // in case input number is higher than 6
    }
  }
}