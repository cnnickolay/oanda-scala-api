package org.nikosoft.oanda.api

import org.nikosoft.oanda.api.ApiModel.AccountCall.AccountResponse
import org.nikosoft.oanda.api.ApiModel.AccountsCall.AccountsResponse
import org.nikosoft.oanda.api.ApiModel.CandlesCall.CandlesResponse
import org.nikosoft.oanda.api.ApiModel.Error

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

}
