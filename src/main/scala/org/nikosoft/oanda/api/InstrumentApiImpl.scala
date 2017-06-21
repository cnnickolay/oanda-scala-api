package org.nikosoft.oanda.api

import org.apache.http.client.fluent.Request
import org.nikosoft.oanda.api.Errors.Error
import org.nikosoft.oanda.api.InstrumentApi.CandlesResponse
import org.nikosoft.oanda.api.model.ApiModel.InstrumentModel.CandlestickGranularity.CandlestickGranularity
import org.nikosoft.oanda.api.model.ApiModel.InstrumentModel.WeeklyAlignment.WeeklyAlignment
import org.nikosoft.oanda.api.model.ApiModel.PrimitivesModel.{InstrumentName, DateTime}

import scalaz.\/

/**
  * Created by Nikolai Cherkezishvili on 21/06/2017
  */
object InstrumentApiImpl extends InstrumentApi with ApiCommons {

  /**
    * @param instrument        Name of the Instrument
    * @param price             The Price component(s) to get candlestick data for. Can contain any combination of the characters “M” (midpoint candles) “B” (bid candles) and “A” (ask candles). [default=M]
    * @param granularity       The granularity of the candlesticks to fetch [default=S5]
    * @param count             The number of candlesticks to return in the reponse. Count should not be specified if both the start and end parameters are provided, as the time range combined with the graularity will determine the number of candlesticks to return. [default=500, maximum=5000]
    * @param from              The start of the time range to fetch candlesticks for.
    * @param to                The end of the time range to fetch candlesticks for.
    * @param smooth            A flag that controls whether the candlestick is “smoothed” or not. A smoothed candlestick uses the previous candle’s close price as its open price, while an unsmoothed candlestick uses the first price from its time range as its open price. [default=False]
    * @param includeFirst      A flag that controls whether the candlestick that is covered by the from time should be included in the results. This flag enables clients to use the timestamp of the last completed candlestick received to poll for future candlesticks but avoid receiving the previous candlestick repeatedly. [default=True]
    * @param dailyAlignment    The hour of the day (in the specified timezone) to use for granularities that have daily alignments. [default=17, minimum=0, maximum=23]
    * @param alignmentTimezone The timezone to use for the dailyAlignment parameter. Candlesticks with daily alignment will be aligned to the dailyAlignment hour within the alignmentTimezone. [default=America/New_York]
    * @param weeklyAlignment   The day of the week used for granularities that have weekly alignment. [default=Friday]
    * @return Fetch candlestick data for an instrument.
    */
  def candles(instrument: InstrumentName,
              price: String,
              granularity: CandlestickGranularity,
              count: Option[Int],
              from: Option[DateTime],
              to: Option[DateTime],
              smooth: Boolean,
              includeFirst: Boolean,
              dailyAlignment: Int,
              alignmentTimezone: String,
              weeklyAlignment: WeeklyAlignment): \/[Error, CandlesResponse] = {

    val params = Seq(
      Option(s"price=$price"),
      Option(s"granularity=${granularity.toString}"),
      count.map(s"count=" + _),
      from.map(s"from=" + _),
      to.map(s"to=" + _),
      Option(s"smooth=${if (smooth) "True" else "False"}"),
      Option(s"includeFirst=${if (includeFirst) "True" else "False"}"),
      Option(s"dailyAlignment=$dailyAlignment"),
      Option(s"alignmentTimezone=$alignmentTimezone"),
      Option(s"weeklyAlignment=$weeklyAlignment")
    ).flatten.mkString("&")

    val url = s"$baseUrl/instruments/${instrument.value}/candles?$params"
    val content = Request
      .Get(url)
      .addHeader("Authorization", token)
      .execute()
      .returnContent()
      .toString

    handleRequest[CandlesResponse](content)
  }
}
