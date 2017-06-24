package org.nikosoft.oanda

import org.nikosoft.oanda.api.ApiModel.AccountModel.AccountID
import org.nikosoft.oanda.api.ApiModel.InstrumentModel.CandlestickGranularity
import org.nikosoft.oanda.api.ApiModel.OrderModel.{LimitOrderRequest, OrderSpecifier, OrderState}
import org.nikosoft.oanda.api.ApiModel.PricingModel.PriceValue
import org.nikosoft.oanda.api.ApiModel.PrimitivesModel.InstrumentName
import org.nikosoft.oanda.api.ApiModel.TradeModel.TradeState
import org.nikosoft.oanda.api.`def`.OrderApi.CreateOrderRequest
import org.nikosoft.oanda.api.impl.{OrderApiImpl, TradeApiImpl}

import scalaz.{-\/, \/-}

object Main extends App {

/*
  AccountsApiImpl.accounts match {
    case \/-(result) =>
      result.accounts.foreach(println)
      result.accounts.map(account => AccountsApiImpl.accountDetails(account.id)).foreach(println)
      result.accounts.map(account => AccountsApiImpl.accountSummary(account.id)).foreach(println)
      result.accounts.map(account => AccountsApiImpl.accountInstruments(account.id, Seq(InstrumentName("EUR_USD"), InstrumentName("EUR_CHF")))).foreach(println)
    case -\/(error) => println(error)
  }
*/

/*
  InstrumentApiImpl.candles(InstrumentName("EUR_USD"), granularity = CandlestickGranularity.H1, count = Option(10)) match {
    case \/-(response) => response.candles.foreach(println)
    case -\/(err) =>
  }
*/

  val accountId = System.getProperty("accountID")

//  OrderApiImpl.order(AccountID(accountId), OrderRequestWrapper(LimitOrderRequest(instrument = InstrumentName("EUR_USD"), units = 1000, price = PriceValue("0.1"))))

  val \/-(orders) = OrderApiImpl.orders(AccountID(accountId), state = OrderState.FILLED)
  orders.orders.foreach(println)

//  println(OrderApiImpl.cancelOrder(AccountID(accountId), OrderSpecifier("237")))

}
