package org.nikosoft.oanda.api

import org.joda.time.DateTime
import org.json4s.JsonAST.{JInt, JString}
import org.json4s.{CustomSerializer, DefaultFormats, _}


object JsonSerializers {

  private object LongSerializer extends CustomSerializer[Long](format => ( {
    case JString(x) => x.toLong
    case JInt(x) => x.toLong
  }, {
    case x: Long => JInt(x)
    case x: Double => JDouble(x)
  }))

  private object StringToDouble extends CustomSerializer[Double](format => ( {
    case JString(x) => x.toDouble
  }, {
    case x: Double => JDouble(x)
  }))

  private object DateTimeSerializer extends CustomSerializer[DateTime](format => ({
    case JString(date) => DateTime.parse(date)
  }, {
    case date: DateTime => JString(date.toString)
  }))

  trait Base
  case class Fish(id: String) extends Base
  case class Bear(name: Int) extends Base
  case class Container(base: Seq[Base])

  def formatsHints(classes: List[Class[_]]): DefaultFormats = new DefaultFormats {
    override val typeHintFieldName: String = "type"
    override val typeHints: TypeHints = new TypeHints {
      override val hints: List[Class[_]] = classes

      override def classFor(hint: String): Option[Class[_]] = hint match {
        case "FISHO" => Option(classOf[Fish])
        case "BEARO" => Option(classOf[Bear])
      }

      override def hintFor(clazz: Class[_]): String = clazz match {
        case clazz: Class[Fish] => "FISHO"
        case clazz: Class[Bear] => "BEARO"
      }
    }
  }

  private[api] def formats(classes: List[Class[_]]): Formats = formatsHints(classes) + StringToDouble + LongSerializer + DateTimeSerializer

}
