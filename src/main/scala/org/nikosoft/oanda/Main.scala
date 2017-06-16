package org.nikosoft.oanda

import org.joda.time.DateTime
import org.nikosoft.oanda.api.ApiImpl
import org.nikosoft.oanda.api.ApiImpl.CandleRange

object Main extends App {

  ApiImpl.candles(
    includeFirst = true,
    granularity = "H1",
    range = CandleRange(2)
  ) match {
    case Right(candles) => candles.candles.foreach(println)
    case Left(error) => println(error)
  }

}
