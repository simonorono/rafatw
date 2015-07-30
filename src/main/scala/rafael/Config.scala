package rafael

import java.nio.file.{Files, NoSuchFileException, Paths}
import java.util

import org.json.simple.parser.{JSONParser, ParseException}

import scala.collection.JavaConverters._

class Config(fileName: String) {
  def read(): Option[Map[String, String]] = {
    try {
      val parser = new JSONParser
      val path = Paths.get(fileName)
      val content = new String(Files.readAllBytes(path))
      val obj = parser.parse(content)
      Some(obj.asInstanceOf[util.HashMap[String, String]].asScala.toMap)
    } catch {
      case ex: NoSuchFileException =>
        println("File " + fileName + " not found")
        None
      case ex: ParseException =>
        println(fileName + ": Bad JSON format")
        None
    }
  }
}
