package Conspect
import scala.xml.{Node, XML}

object Day22ParsingXML extends App {
  val src = "src/resources/xml/books.xml"

  val xml = XML.loadFile(src) //so we load the whole structure into xml Elem data type - so root element

  println(xml)

  //in Scala 2 i can still make XML directly, - XML is a first class citizen
  val myOwnXMl = <book id="bk305">
    <title>Best Fruit Cocktails
    </title>
    <author>Doe, John
    </author></book>

  println(myOwnXMl)

  val books = xml \ "book" //i am saying get all book tags that are direct children of this xml element(catalog here)

  val firstBook = books.head
  println(firstBook)

  //we can get the contents of some attribute considering the fact that the atribute might not exist at all
  val id = firstBook.attribute("id").getOrElse("bk0").toString
  println(s"Our book id is $id")

  //get children tag text contents
  println((firstBook \ "author").text) //theoretically if we had multiple author tags we would get ALL the text from those tags
  println((firstBook \ "title").text)
  println((firstBook \ "genre").text)
  println((firstBook \ "price").text)

  def getBook(node: Node):Book = {
    val id = node.attribute("id").getOrElse("bk0").toString
    val author = (node \ "author").text
    val title = (node \ "title").text
    val genre = (node \ "genre").text
    val price = (node \ "price").text.toDouble
    //TODO add the missing fields
    Book(id, author, title, genre, price)
  }

  val bookUnits = (for (book <- books) yield getBook(book)).toArray

  println(bookUnits.head)
  println(bookUnits.last)

  val genres = bookUnits.map(_.genre).distinct
  println(genres.mkString(","))

  val expensiveBooks = bookUnits.sortBy(_.price).reverse
  println("The most expensive books are: ")
  expensiveBooks.take(5).foreach(println)

  val cheapBooks = bookUnits.sortBy(_.price)
  println("The most cheapest books are: ")
  cheapBooks.take(5).foreach(println)

  val romanceBooks = bookUnits.filter(_.genre == "Romance")
  println("The romance books are: ")
  println(romanceBooks.mkString(", "))


  val authors = bookUnits.map(_.author).distinct
  println("Authors:")
  println(authors.sorted.mkString)

}