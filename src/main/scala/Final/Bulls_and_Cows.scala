package Final

import scala.io.StdIn.readLine

object Bulls_and_Cows extends App {
  var isNewGameNeeded = true

  /** Game starts with creating new BullsAndCows class
   * Game is been played
   * All guesses are printed
   * If new game is needed, the process repeats itself
   * If not needed, app closes
   */
  while (isNewGameNeeded) {
    val BullsAndCowsGame = new BullsAndCows
    BullsAndCowsGame.play
    BullsAndCowsGame.printGuesses
    val nextGameInput = readLine("Do you want to play another game? (Y/N) ")
    if (!nextGameInput.toLowerCase.startsWith("y")) {
      isNewGameNeeded = false
    }
  }
  System.exit(0)

  //  BullsAndCowsGame.db.dropAllTables()
}
