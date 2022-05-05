package Conspect


import au.com.bytecode.opencsv.CSVWriter

import java.io.{BufferedWriter, FileWriter}
import java.sql.DriverManager
import scala.collection.mutable.{ArrayBuffer, ListBuffer}


case class Album(AlbumId: Int,
                 Title: String,
                 ArtistId: Int)
case class Track(TrackId: Int,
                 Name: String,
                 AlbumId: Int,
                 MediaTypeId: Int,
                 GenreId: Int,
                 Composer: String,
                 Milliseconds: Int,
                 Bytes: Int,
                 UnitPrice: Double
                )


object da25Exercise extends App {

  val dbPath = "src/resources/db/chinook (1).db"

  val url = s"jdbc:sqlite:$dbPath" // will connect sqlite database at the following url:$url

  // TODO ALBUM

  val connection = DriverManager.getConnection(url)
  println(connection.getClientInfo())

  val statement = connection.createStatement() // we create statement object

  val sql1 =
    """
      |SELECT * FROM albums;
      |""".stripMargin
  val resultSet = statement.executeQuery(sql1)
  val metaData = resultSet.getMetaData

  val albumBuffer = ArrayBuffer[Album]()

  while (resultSet.next()) {
    println(resultSet.getInt(1), resultSet.getString(2),resultSet.getInt(3))
    for (i <- 1 to metaData.getColumnCount) {
      print(resultSet.getString(i) + " ")
    }
    val album = Album(resultSet.getInt("albumId"),
                resultSet.getString("Title"),
                resultSet.getInt("ArtistId"))
    albumBuffer.append(album)
    println()
  }


  connection.close

  val albumCollection = albumBuffer.toArray
  albumCollection.take(5).foreach(println)


  // TODO TRACKS
  val connection2 = DriverManager.getConnection(url)
  println(connection2.getClientInfo())

  val statement2 = connection2.createStatement()
  val sql2 =
    """
      |SELECT * FROM tracks;
      |""".stripMargin
  val resultSet2 = statement2.executeQuery(sql2)
  val metaData2 = resultSet2.getMetaData

  val trackBuffer = ArrayBuffer[Track]()

  while (resultSet2.next()) {
    println(resultSet2.getInt(1),
            resultSet2.getString(2),
            resultSet2.getInt(3),
            resultSet2.getInt(4),
            resultSet2.getInt(5),
            resultSet2.getString(6),
            resultSet2.getInt(7),
            resultSet2.getInt(8),
            resultSet2.getDouble(9))

    for (i <- 1 to metaData2.getColumnCount) {
      print(resultSet2.getString(i) + " ")
    }
    val tracks = Track(resultSet2.getInt("TrackId"),
      resultSet2.getString("Name"),
      resultSet2.getInt("AlbumId"),
      resultSet2.getInt("MediaTypeId"),
      resultSet2.getInt("GenreId"),
      resultSet2.getString("Composer"),
      resultSet2.getInt("Milliseconds"),
      resultSet2.getInt("Bytes"),
      resultSet2.getDouble("UnitPrice"),
    )
    trackBuffer.append(tracks)
    println()
  }
  connection2.close

  val trackCollection = trackBuffer.toArray
  trackCollection.take(5).foreach(println)

  //TODO save into CSV

  val outputFile = new BufferedWriter(new FileWriter("src/resources/csv/tracks.csv"))
  val csvWriter = new CSVWriter(outputFile)

  var listOfTracks = new ListBuffer[Array[String]]()

  // could not figure out how to finish it

//  csvWriter.writeAll(???.toList)
//  outputFile.close()



}
