package org.nikosoft.oanda

import org.nikosoft.oanda.api.InstrumentApiImpl
import org.nikosoft.oanda.api.model.ApiModel.InstrumentModel.CandlestickGranularity
import org.nikosoft.oanda.api.model.ApiModel.PrimitivesModel.InstrumentName

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

  InstrumentApiImpl.candles(InstrumentName("EUR_USD"), granularity = CandlestickGranularity.H1, count = Option(10)) match {
    case \/-(response) => response.candles.foreach(println)
    case -\/(err) =>
  }

}
