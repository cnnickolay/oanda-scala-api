package org.nikosoft.oanda.api

import org.joda.time.DateTime
import org.json4s.JsonAST.{JInt, JString}
import org.json4s.{CustomSerializer, DefaultFormats, _}
import org.nikosoft.oanda.api.ApiModel._


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

  def formatsHints: DefaultFormats = new DefaultFormats {
    override val typeHintFieldName: String = "type"
    override val typeHints: TypeHints = new TypeHints {
      override val hints: List[Class[_]] = List(
        classOf[MarketOrder],
        classOf[LimitOrder],
        classOf[StopOrder],
        classOf[MarketIfTouchedOrder],
        classOf[TakeProfitOrder],
        classOf[StopLossOrder],
        classOf[TrailingStopLossOrder]
      )

      override def classFor(hint: String): Option[Class[_]] = hint match {
        case "TRAILING_STOP_LOSS" => Option(classOf[TrailingStopLossOrder])
        case "STOP_LOSS" => Option(classOf[StopLossOrder])
        case "TAKE_PROFIT" => Option(classOf[TakeProfitOrder])
        case "MARKET_IF_TOUCHED" => Option(classOf[MarketIfTouchedOrder])
        case "STOP" => Option(classOf[StopOrder])
        case "LIMIT" => Option(classOf[LimitOrder])
        case "MARKET" => Option(classOf[MarketOrder])
      }

      override def hintFor(clazz: Class[_]): String = clazz match {
        case _: Class[TrailingStopLossOrder] => "TRAILING_STOP_LOSS"
        case _: Class[StopLossOrder] => "STOP_LOSS"
        case _: Class[TakeProfitOrder] => "TAKE_PROFIT"
        case _: Class[MarketIfTouchedOrder] => "MARKET_IF_TOUCHED"
        case _: Class[StopOrder] => "STOP"
        case _: Class[LimitOrder] => "LIMIT"
        case _: Class[MarketOrder] => "MARKET"
      }
    }
  }

  private[api] def formats(classes: List[Class[_]]): Formats = formatsHints + StringToDouble + LongSerializer + DateTimeSerializer

}
