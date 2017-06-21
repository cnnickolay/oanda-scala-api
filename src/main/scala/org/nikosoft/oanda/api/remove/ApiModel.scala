package org.nikosoft.oanda.api.remove

import org.joda.time.DateTime
import org.nikosoft.oanda.api.remove.ApiModel.AccountCall.ClientExtension
import org.nikosoft.oanda.api.remove.ApiModel.OrderState.OrderState

object ApiModel {

  trait Response

  object OrderState extends Enumeration {
    type OrderState = Value
    val PENDING, FILLED, TRIGGERED, CANCELLED = Value
  }

  case class ClientExtensions(id: String, tag: String, comment: String)
  case class TrailingStopLossDetails(distance: Double, timeInForce: String, gtdTime: DateTime, clientExtensions: ClientExtension)
  case class StopLossDetails(price: Double, timeInForce: String, gtdTime: DateTime, clientExtensions: ClientExtension)
  case class TakeProfitDetails(price: Double, timeInForce: String, gtdTime: DateTime, clientExtensions: ClientExtension)
  case class MarketOrderTradeClose(tradeId: String, clientTradeID: String, units: String)
  case class MarketOrderPositionCloseout(instrument: String, units: String)

  abstract class Order(val orderId: Long, val createTime: DateTime, val state: OrderState, val clientExtensions: ClientExtension)

  case class MarketOrder(override val orderId: Long,
                         override val createTime: DateTime,
                         override val state: OrderState,
                         override val clientExtensions: ClientExtension,
                         instrument: String,
                         units: Double,
                         timeInForce: String,
                         priceBound: Double,
                         positionFill: String,
                         tradeClose: MarketOrderTradeClose,
                         longPositionCloseout: MarketOrderPositionCloseout,
                         shortPositionCloseout: MarketOrderPositionCloseout,
                         marginCloseout: MarketOrderPositionCloseout,
                         delayedTradeClose: MarketOrderPositionCloseout,
                         takeProfitOnFill: TakeProfitDetails,
                         stopLossOnFill: StopLossDetails,
                         trailingStopLossOnFill: TrailingStopLossDetails,
                         tradeClientExtensions: ClientExtension,
                         fillingTransactionID: String,
                         filledTime: DateTime,
                         tradeOpenedID: String,
                         tradeReducedID: String,
                         tradeClosedIDs: Seq[String],
                         cancellingTransactionID: String,
                         cancelledTime: DateTime) extends Order(orderId, createTime, state, clientExtensions)

  case class LimitOrder(override val orderId: Long,
                        override val createTime: DateTime,
                        override val state: OrderState,
                        override val clientExtensions: ClientExtension,
                        instrument: String,
                        units: Double,
                        price: Double,
                        timeInForce: String,
                        gtdTime: DateTime,
                        positionFill: String,
                        triggerCondition: String,
                        takeProfitOnFill: TakeProfitDetails,
                        stopLossOnFill: StopLossDetails,
                        trailingStopLossOnFill: TrailingStopLossDetails,
                        tradeClientExtensions: ClientExtension,
                        fillingTransactionID: String,
                        filledTime: DateTime,
                        tradeOpenedID: String,
                        tradeReducedID: String,
                        tradeClosedIDs: Seq[String],
                        cancellingTransactionID: String,
                        cancelledTime: DateTime,
                        replacesOrderID: String,
                        replacedByOrderID: String
                       ) extends Order(orderId, createTime, state, clientExtensions)

  case class StopOrder(override val orderId: Long,
                       override val createTime: DateTime,
                       override val state: OrderState,
                       override val clientExtensions: ClientExtension,
                       instrument: String,
                       units: Double,
                       price: Double,
                       priceBound: Option[Double],
                       timeInForce: String,
                       gtdTime: DateTime,
                       positionFill: String,
                       triggerCondition: String,
                       takeProfitOnFill: TakeProfitDetails,
                       stopLossOnFill: StopLossDetails,
                       trailingStopLossOnFill: TrailingStopLossDetails,
                       tradeClientExtensions: ClientExtension,
                       fillingTransactionID: String,
                       filledTime: DateTime,
                       tradeOpenedID: String,
                       tradeReducedID: String,
                       tradeClosedIDs: Seq[String],
                       cancellingTransactionID: String,
                       cancelledTime: DateTime,
                       replacesOrderID: String,
                       replacedByOrderID: String
                      ) extends Order(orderId, createTime, state, clientExtensions)

  case class MarketIfTouchedOrder(override val orderId: Long,
                                  override val createTime: DateTime,
                                  override val state: OrderState,
                                  override val clientExtensions: ClientExtension,
                                  instrument: String,
                                  units: Double,
                                  price: Double,
                                  priceBound: Option[Double],
                                  timeInForce: String,
                                  gtdTime: DateTime,
                                  positionFill: String,
                                  triggerCondition: String,
                                  initialMarketPrice: Double,
                                  takeProfitOnFill: TakeProfitDetails,
                                  stopLossOnFill: StopLossDetails,
                                  trailingStopLossOnFill: TrailingStopLossDetails,
                                  tradeClientExtensions: ClientExtension,
                                  fillingTransactionID: String,
                                  filledTime: DateTime,
                                  tradeOpenedID: String,
                                  tradeReducedID: String,
                                  tradeClosedIDs: Seq[String],
                                  cancellingTransactionID: String,
                                  cancelledTime: DateTime,
                                  replacesOrderID: String,
                                  replacedByOrderID: String
                                 ) extends Order(orderId, createTime, state, clientExtensions)

    case class TakeProfitOrder(override val orderId: Long,
                             override val createTime: DateTime,
                             override val state: OrderState,
                             override val clientExtensions: ClientExtension,
                             tradeId: String,
                             clientTradeId: String,
                             price: Double,
                             timeInForce: String,
                             gtdTime: DateTime,
                             triggerCondition: String,
                             fillingTransactionID: String,
                             filledTime: DateTime,
                             tradeOpenedID: String,
                             tradeReducedID: String,
                             tradeClosedIDs: Seq[String],
                             cancellingTransactionID: String,
                             cancelledTime: DateTime,
                             replacesOrderID: String,
                             replacedByOrderID: String
                            ) extends Order(orderId, createTime, state, clientExtensions)

  case class StopLossOrder(override val orderId: Long,
                           override val createTime: DateTime,
                           override val state: OrderState,
                           override val clientExtensions: ClientExtension,
                           tradeId: String,
                           clientTradeId: String,
                           price: Double,
                           timeInForce: String,
                           gtdTime: DateTime,
                           triggerCondition: String,
                           fillingTransactionID: String,
                           filledTime: DateTime,
                           tradeOpenedID: String,
                           tradeReducedID: String,
                           tradeClosedIDs: Seq[String],
                           cancellingTransactionID: String,
                           cancelledTime: DateTime,
                           replacesOrderID: String,
                           replacedByOrderID: String
                          ) extends Order(orderId, createTime, state, clientExtensions)

  case class TrailingStopLossOrder(override val orderId: Long,
                                   override val createTime: DateTime,
                                   override val state: OrderState,
                                   override val clientExtensions: ClientExtension,
                                   tradeId: String,
                                   clientTradeId: String,
                                   distance: Double,
                                   timeInForce: String,
                                   gtdTime: DateTime,
                                   triggerCondition: String,
                                   trailingStopValue: Double,
                                   fillingTransactionID: String,
                                   filledTime: DateTime,
                                   tradeOpenedID: String,
                                   tradeReducedID: String,
                                   tradeClosedIDs: Seq[String],
                                   cancellingTransactionID: String,
                                   cancelledTime: DateTime,
                                   replacesOrderID: String,
                                   replacedByOrderID: String
                                  ) extends Order(orderId, createTime, state, clientExtensions)

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

  abstract class Transaction(val id: String, val time: DateTime, val userID: Long, val accountID: String, val batchID: String, val requestID: String)

  case class TradeOpen(tradeID: String, units: Double, clientExtensions: ClientExtension)
  case class TradeReduce(tradeID: String, units: Double, realizedPL: Double, financing: Double)
  case class OrderFillTransaction(id: String, time: DateTime, userID: Long, accountID: String, batchID: String, requestID: String, `type`: String, orderID: String, clientOrderID: String, instrument: String, units: Double, price: Double, reason: String, pl: Double, financing: Double, accountBalance: Double, tradeOpened: TradeOpen, tradesClosed: Seq[TradeReduce], tradeReduced: TradeReduce)
  case class OrderCancelTransaction(id: String, time: DateTime, userID: Long, accountID: String, batchID: String, requestID: String, `type`: String, orderID: String, clientOrderID: String, reason: String, replacedByOrderID: String)

  case class Error(clazz: String, message: String)

}
