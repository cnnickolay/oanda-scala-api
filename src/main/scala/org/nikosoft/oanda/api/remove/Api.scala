package org.nikosoft.oanda.api.remove

import ApiModel.AccountCall.{AccountResponse, ClientExtension}
import ApiModel.AccountsCall.AccountsResponse
import ApiModel.CandlesCall.CandlesResponse
import ApiModel.{Error, StopLossDetails, TakeProfitDetails, TrailingStopLossDetails}

trait Api {

  trait CandleRange {
    def toParams: String
  }

  case class CandleCount(count: Int) extends CandleRange {
    def toParams: String = s"count=$count"
  }

  case class CandleFromTo(from: Long, to: Long) extends CandleRange {
    def toParams: String = s"from=$from&to=$to"
  }

  object CandleRange {
    def apply(from: Long, to: Long) = CandleFromTo(from, to)

    def apply(count: Int) = CandleCount(count)
  }

  def accounts: Either[Error, AccountsResponse]

  def account(accountId: String): Either[Error, AccountResponse]

  def candles(instrument: String = "EUR_USD",
              price: String = "M",
              granularity: String = "S5",
              range: CandleRange = CandleRange(500),
              smooth: Boolean = false,
              includeFirst: Boolean = true): Either[Error, CandlesResponse]

  def openMarketOrder(accountID: String,
                      instrument: String,
                      units: Double,
                      timeInForce: String,
                      priceBound: Double,
                      positionFill: String = "DEFAULT",
                      clientExtensions: Option[ClientExtension],
                      takeProfitOnFill: TakeProfitDetails,
                      stopLossOnFill: StopLossDetails,
                      trailingStopLossOnFill: TrailingStopLossDetails,
                      tradeClientExtensions: Option[ClientExtension])

}
