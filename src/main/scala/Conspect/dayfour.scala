package Conspect

import scala.io.StdIn.readLine

object dayfour extends App {
  println("Xmas bonus calculator")
  //TODO
  //Ask for person's name
  //Ask for how long they have worked at the firm
  //Ask for monthly wage
  //Calculate Xmas bonus if they have worked at least 2  years otherwise print sorry no bonus
  //Bonus is years worked over 2 years * 15% of monthly wage
  //so 5 years worked, 1000 Euros wage would give 450 Euro bonus (3 years * 150)

  val Name = readLine("Hello. What is your name?")
  val WorkExpierience = readLine("How long have you worked at the firm?").toInt
  val Wage = readLine("What is your monthly wage?").toInt
  if (WorkExpierience < 2) {
    println("Sorry, no bonus")
  } else {
    val Bonus = (WorkExpierience - 2) * (Wage * 0.15)
    println(s"$Name, your bonus is $Bonus")
  }
}
