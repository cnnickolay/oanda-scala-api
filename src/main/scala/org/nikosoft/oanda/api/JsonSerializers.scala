package org.nikosoft.oanda.api

import org.joda.time.DateTime
import org.json4s.JsonAST.{JInt, JString}
import org.json4s.ext.EnumNameSerializer
import org.json4s.{CustomSerializer, DefaultFormats, _}
import org.nikosoft.oanda.api.ApiModel.AccountModel.AccountFinancingMode
import org.nikosoft.oanda.api.ApiModel.OrderModel._
import org.nikosoft.oanda.api.ApiModel.PricingModel.PriceStatus
import org.nikosoft.oanda.api.ApiModel.PrimitivesModel.InstrumentType
import org.nikosoft.oanda.api.ApiModel.TradeModel.TradeState
import org.nikosoft.oanda.api.ApiModel.TransactionModel._


object JsonSerializers {

  private object LongSerializer extends CustomSerializer[Long](format => ( {
    case JString(x) => x.toLong
    case JInt(x) => x.toLong
  }, {
    case x: Long => JInt(x)
    case x: Double => JString(x.toString)
  }))

  private object StringToDouble extends CustomSerializer[Double](format => ( {
    case JString(x) => x.toDouble
  }, {
    case x: Double => JString(x.toString)
  }))

  private object DateTimeSerializer extends CustomSerializer[DateTime](format => ( {
    case JString(date) => DateTime.parse(date)
  }, {
    case date: DateTime => JString(date.toString)
  }))

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

  private[api] def formats = formatsHints +
    StringToDouble +
    LongSerializer +
    DateTimeSerializer +
    new EnumNameSerializer(AccountFinancingMode) +
    new EnumNameSerializer(OrderPositionFill) +
    new EnumNameSerializer(OrderState) +
    new EnumNameSerializer(OrderTriggerCondition) +
    new EnumNameSerializer(OrderType) +
    new EnumNameSerializer(TimeInForce) +
    new EnumNameSerializer(PriceStatus) +
    new EnumNameSerializer(InstrumentType) +
    new EnumNameSerializer(TradeState) +
    new EnumNameSerializer(FundingReason) +
    new EnumNameSerializer(LimitOrderReason) +
    new EnumNameSerializer(MarketIfTouchedOrderReason) +
    new EnumNameSerializer(MarketOrderMarginCloseoutReason) +
    new EnumNameSerializer(MarketOrderReason) +
    new EnumNameSerializer(OrderCancelReason) +
    new EnumNameSerializer(OrderFillReason) +
    new EnumNameSerializer(StopLossOrderReason) +
    new EnumNameSerializer(StopOrderReason) +
    new EnumNameSerializer(TakeProfitOrderReason) +
    new EnumNameSerializer(TrailingStopLossOrderReason) +
    new EnumNameSerializer(TransactionRejectReason)
}
