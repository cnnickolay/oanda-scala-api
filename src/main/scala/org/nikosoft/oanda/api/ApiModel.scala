package org.nikosoft.oanda.api

import org.joda.time.DateTime

object ApiModel {

  trait Response

  object AccountsCall {
    case class Account(id: String, tags: Seq[String], mt4AccountID: Option[String])
    case class AccountsResponse(accounts: Seq[Account]) extends Response
  }

  object AccountCall {
    case class ClientExtension(id: Long, clientTag: Option[String], comment: Option[String])
    case class TradeSummary(id: String, instrument: String, price: Double, openTime: DateTime, initialUnits: Double, state: String, currentUnits: Double, realizedPL: Double, financing: Double, clientExtensions: ClientExtension, takeProfitOrderID: String, stopLossOrderID: String, trailingStopLossOrderID: Option[String])
    case class PositionSide(units: Double, averagePrice: Option[Double], tradeIDs: Seq[String], pl: Double, unrealizedPL: Double, resettablePL: Double)
    case class Position(instrument: String, pl: Double, unrealizedPL: Double, resettablePL: Double, long: PositionSide, short: PositionSide)
    case class Order(id: String, createTime: DateTime, `type`: String, tradeID: Long, clientTradeID: Long, price: Double, timeInForce: String, triggerCondition: String, state: String)
    
    case class AccountResponse(id: String,
                               createdTime: DateTime,
                               currency: String,
                               createdByUserID: Long,
                               alias: String,
                               marginRate: Double,
                               hedgingEnabled: Boolean,
                               lastTransactionID: Long,
                               balance: Double,
                               openTradeCount: Long,
                               openPositionCount: Long,
                               pendingOrderCount: Long,
                               pl: Double,
                               resettablePL: Double,
                               financing: Double,
                               commission: Double,
                               orders: Seq[Order],
                               positions: Seq[Position],
                               trades: Seq[TradeSummary],
                               unrealizedPL: Double,
                               NAV: Double,
                               marginUsed: Double,
                               marginAvailable: Double,
                               positionValue: Double,
                               marginCloseoutUnrealizedPL: Double,
                               marginCloseoutNAV: Double,
                               marginCloseoutMarginUsed: Double,
                               marginCloseoutPositionValue: Double,
                               marginCloseoutPercent: Double,
                               withdrawalLimit: Double,
                               marginCallMarginUsed: Double,
                               marginCallPercent: Double) extends Response
  }

  object CandlesCall {
    case class CandlestickData(o: Double, h: Double, l: Double, c: Double)
    case class Candlestick(time: DateTime, bid: Option[CandlestickData], ask: Option[CandlestickData], mid: Option[CandlestickData], volume: Int, complete: Boolean)
    case class CandlesResponse(instrument: String, granularity: String, candles: Seq[Candlestick]) extends Response
  }

  object OrderCall {
    case class OrderRequest()
  }

  case class Error(clazz: String, message: String)

}
