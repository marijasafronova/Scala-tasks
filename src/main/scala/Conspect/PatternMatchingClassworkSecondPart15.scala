package Conspect

abstract class Notification

case class Email(sender: String, title: String, body: String) extends Notification

case class SMS(caller: String, message: String) extends Notification

case class VoiceRecording(contactname: String, link: String) extends Notification

object PatternMatchingClassworkSecondPart15 extends App{

  val someSms = SMS("123", "HEY")
  val someVR = VoiceRecording("Maria", "www.www")
  val someEmail = Email("Mum", "Hello", "How are you")

  def showNotification(notification: Notification): String = notification match {
    case Email(sender, title, _) => "Conspect.Email.." // текст серый, тк дальше не используется, тоже самое что и _,_,_
    case SMS(caller, message) => s"you got sms from $caller" // тут используется
    case VoiceRecording(contactname, link) => "new vr"
  }

  println(showNotification(someSms)) // с помощью функции выведем сообщение (от переменной someSms)

  def showImportantNotification (notification: Notification, importantPeopleInfo: Seq[String]) : String = {
    notification match {
      case Email(sender, _ , _) if importantPeopleInfo.contains(sender) =>
        s"You got an email from $sender"
      case other => showNotification(other)
    }
  }

  val importantSMS = SMS("1888", "Ads")
  val importantContacts = Array("1888", "1234")

  println((showImportantNotification(importantSMS,importantContacts)))

  val someVal = 23
  


}
