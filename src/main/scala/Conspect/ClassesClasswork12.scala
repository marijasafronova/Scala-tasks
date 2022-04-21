package Conspect

class PlainHouse (var name:String,
                  var levels:Int,
                  var material:String = "brick",
                  val floorHeight: Double = 2.8,
                  val width: Double = 4,
                  val houseLength: Double = 3.7) {
  // можем менять имя, количество этажей, по умолчанию записывать поля
  val volume = levels*floorHeight*width*houseLength // площадь нашего дома
  var age = 0
  def yearPass (years: Int): Any = age += years // метод для изменения возраста

  private var securityKey = "" // мы ограничиваем видимость этой переменной

  private def generatePassword(text:String): String = { // пример приватного метода в классе, для внутреннего использования
    // мы прячем сложность класса, для этого метода не будет доступа в программе
    "Hey " + text
  }

  def updateSecurityKey(password:String): Unit = { // пишем метод для обновления приватной переменной (пароля)
    securityKey = generatePassword(password)
  }

  def retrieveSecurityKey(password:String): String = { // метод для извлечения пароля
    if (password == "silly text") securityKey else "No access"
  }

 // блок конструктр
 // пишем метод - функцию, которая живёт в классе
  def prettyPrint(): Unit = {
  println(s"House is called $name and has $levels levels and is made of $material")
   // таким образом можно написать функцию внутри класса
   // которая, например, будет печатать предложение с полями конкретного объекта
  }
  // далее как можно поменять встроенную по умолчанию функцию СКАЛА, для удобства
  override def toString: String = {
    s"House is called $name and has $levels levels and is made of $material"
  }
}

object ClassesClasswork12 extends App {

  val myPlainHouse = new PlainHouse("countryHouse", 2) // указываем в скобках информацию об объекте
  println(myPlainHouse)
  println(myPlainHouse.name)
  println(myPlainHouse.levels) // через точку можно печатать поля объекта

  myPlainHouse.name = "Castle" // таким образом можно поменять поля

  val anotherHouse = new PlainHouse("cityHouse", 3) // создали новый объект для того же класса

  anotherHouse.prettyPrint() // вызываем функцию (метод), который заранее прописали в классе

  println(anotherHouse.age)
  anotherHouse.age += 5
  println((anotherHouse.age))

  anotherHouse.updateSecurityKey("1234") // обновляем пароль
  println(anotherHouse.retrieveSecurityKey("silly text")) // извлекаем новый пароль введя кодовое слово
  println(anotherHouse.retrieveSecurityKey("silly tex")) // не можем извлечь пароль т.к. кодовое слово не верно

  anotherHouse.yearPass(6)

  println(s"Houses volume is ${anotherHouse.volume}") // выводим площадь на экран
}
