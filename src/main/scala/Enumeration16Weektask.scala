import Weekdays.Weekday

object Weekdays extends Enumeration {
 type Weekday = Value

  val Mon, Tue, Wed, Thr, Fr, Sat, Sun = Value

}

object Enumeration16Weektask extends App {
  def isWeekend(someDay: Weekday): Boolean = {
    (someDay == Weekdays.Sat) || (someDay ==Weekdays.Sun)

  }
  println(isWeekend(Weekdays.Wed))
  println(isWeekend(Weekdays.Sun))

}
