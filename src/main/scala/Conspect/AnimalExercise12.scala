package Conspect


//  //TODO add class method makeSound which prints sound
//
//  //BONUS
//  //TODO add class method meet which has a parameter of contact:String
//  //TODO this meet should decide whether to greet if they like the contact
//  //TODO to run away if they do not like the contactclass Conspect.Animal(val name:String,
import scala.collection.mutable
import scala.language.postfixOps //scala.collection.mutable.Set
//we can use now mutable.Map, mutable.Set, mutable.Seq etc

class Animal(val name:String,
             val animalType:String,
             var likes:mutable.Set[String],
             sound:String="", //by skipping val or var in parameter I can only use sound in constructor that's it not in methods
             //so we can use it for initialization or as a private variable
             var age:Int=0) {
  private val animalSound = if (sound == "")
    animalType match {
      case "dog" => "woof"
      case "cat" => "meow"
      case "fox" => "dododododo what does the fox say"
      case _ => "no sound"
    } else sound

  // альтернативой может послужить словарь
  def makeSound(): Unit = {
    println(animalSound)
  }
}

object AnimalExercise12 extends App{
  val  tom = new Animal("Tom", "cat", mutable.Set("milk", "pets"))
  tom.makeSound()
  val jerry = new Animal("Jerry", "mouse", mutable.Set("cheese", "sleep"), "squeek")
  jerry.makeSound()

}
