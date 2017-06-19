package org.nikosoft.oanda.api.generator

object ApiModelGeneratorRunner extends App {

//  val transactionModel = ApiModelGenerator.generateScalaModel("TransactionModel", "http://developer.oanda.com/rest-live-v20/transaction-df/")
//  val accountModel = ApiModelGenerator.generateScalaModel("AccountModel", "http://developer.oanda.com/rest-live-v20/account-df/")
//  val instrumentModel = ApiModelGenerator.generateScalaModel("InstrumentModel", "http://developer.oanda.com/rest-live-v20/instrument-df/")
//  val orderModel = ApiModelGenerator.generateScalaModel("OrderModel", "http://developer.oanda.com/rest-live-v20/order-df/")
//  val tradeModel = ApiModelGenerator.generateScalaModel("TradeModel", "http://developer.oanda.com/rest-live-v20/trade-df/")
//  val positionModel = ApiModelGenerator.generateScalaModel("PositionModel", "http://developer.oanda.com/rest-live-v20/position-df/")
  val pricingModel = ApiModelGenerator.generateScalaModel("PricingModel", "http://developer.oanda.com/rest-live-v20/pricing-df/")
//  val primitivesModel = ApiModelGenerator.generateScalaModel("PrimitivesModel", "http://developer.oanda.com/rest-live-v20/primitives-df/")

  println(pricingModel)

}
