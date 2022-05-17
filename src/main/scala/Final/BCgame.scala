package Final

class BCgame (var playerA: String,
              var playerB: String,
              var numberA: List[Int],
              var numberB: List[Int],
              var isPlayerAStarting: Boolean = true,
              var win: Boolean = false)  {

  //TODO function which splits 4 digit number and makes a list of digits

  def splitNumber (number: Int): List[Int] = {
   val digits = number.toString.map(_.asDigit).toList
    digits
  }
  //TODO function which counts bulls and cows

  def BCounter (playerNum: List[Int], input: List[Int]): Unit = {
    var cows = 0
    var bulls = 0
    while (!win) {
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
        println("You won")
      } else
        println(s"Cows: $cows " +
          s"Bulls: $bulls")
    }
  }

}
