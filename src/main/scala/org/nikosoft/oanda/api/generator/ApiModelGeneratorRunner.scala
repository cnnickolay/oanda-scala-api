package org.nikosoft.oanda.api.generator

import java.nio.file.{Files, Paths}

object ApiModelGeneratorRunner extends App {

  val transactionModel = ApiModelGenerator.generateScalaModel("TransactionModel", "http://developer.oanda.com/rest-live-v20/transaction-df/")
  val accountModel = ApiModelGenerator.generateScalaModel("AccountModel", "http://developer.oanda.com/rest-live-v20/account-df/")
  val instrumentModel = ApiModelGenerator.generateScalaModel("InstrumentModel", "http://developer.oanda.com/rest-live-v20/instrument-df/")
  val orderModel = ApiModelGenerator.generateScalaModel("OrderModel", "http://developer.oanda.com/rest-live-v20/order-df/")
  val tradeModel = ApiModelGenerator.generateScalaModel("TradeModel", "http://developer.oanda.com/rest-live-v20/trade-df/")
  val positionModel = ApiModelGenerator.generateScalaModel("PositionModel", "http://developer.oanda.com/rest-live-v20/position-df/")
  val pricingModel = ApiModelGenerator.generateScalaModel("PricingModel", "http://developer.oanda.com/rest-live-v20/pricing-df/")
  val primitivesModel = ApiModelGenerator.generateScalaModel("PrimitivesModel", "http://developer.oanda.com/rest-live-v20/primitives-df/")

  Files.write(Paths.get("src/main/scala/org/nikosoft/oanda/api/model/TransactionModel.scala"), transactionModel.getBytes("utf-8"))
  Files.write(Paths.get("src/main/scala/org/nikosoft/oanda/api/model/AccountModel.scala"), accountModel.getBytes("utf-8"))
  Files.write(Paths.get("src/main/scala/org/nikosoft/oanda/api/model/OrderModel.scala"), orderModel.getBytes("utf-8"))
  Files.write(Paths.get("src/main/scala/org/nikosoft/oanda/api/model/InstrumentModel.scala"), instrumentModel.getBytes("utf-8"))
  Files.write(Paths.get("src/main/scala/org/nikosoft/oanda/api/model/TradeModel.scala"), tradeModel.getBytes("utf-8"))
  Files.write(Paths.get("src/main/scala/org/nikosoft/oanda/api/model/PositionModel.scala"), positionModel.getBytes("utf-8"))
  Files.write(Paths.get("src/main/scala/org/nikosoft/oanda/api/model/PrimitivesModel.scala"), primitivesModel.getBytes("utf-8"))
  Files.write(Paths.get("src/main/scala/org/nikosoft/oanda/api/model/PricingModel.scala"), pricingModel.getBytes("utf-8"))

}
