package Conspect

object Weekexercise15 extends App {

  def getDay(day: Int): String = day match {
    case 1 => "monday"
    case 2 => "tuesday"
    case 3 => "wednesday"
    case 4 => "thursday"
    case 5 => "friday"
    case 6 => "saturday"
    case 7 => "sunday"
    case _ => "unknown"
  }

  def getDayType(day: String): String = day match {
    case "monday" | "tuesday" | "wednesday" | "thursday" | "friday" => "weekday"
    case "saturday" | "sunday" => "weekend"
    case _ => "unknown"
  }

}
