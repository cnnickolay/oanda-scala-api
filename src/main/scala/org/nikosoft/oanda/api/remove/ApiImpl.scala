package org.nikosoft.oanda.api.remove

import java.util.Properties

import org.apache.http.client.fluent.Request
import org.json4s._
import org.json4s.jackson.JsonMethods._
import org.json4s.native.Serialization.read
import ApiModel.AccountCall.ClientExtension
import ApiModel.CandlesCall.CandlesResponse
import org.nikosoft.oanda.api.JsonSerializers

import scala.util.{Failure, Success, Try}

object ApiImpl extends Api {

  import ApiModel._

  implicit val formats = JsonSerializers.formats(List.empty)

  private val version = "v3"
  private val baseUrl = s"https://api-fxtrade.oanda.com/$version"

  private lazy val props: Properties = {
    val properties = new Properties()
    properties.load(getClass.getResourceAsStream("/api.properties"))
    properties
  }

  private val token = s"Bearer ${props.getProperty("token")}"

  def request[T <: Response](a: => T): Either[Error, T] = {
    Try(a) match {
      case Success(response) => Right(response)
      case Failure(t) => Left(Error(t.getClass.getName, t.getMessage))
    }
  }

  def accounts = request {
    val content = Request
      .Get(s"$baseUrl/accounts")
      .addHeader("Authorization", token)
      .execute()
      .returnContent()

    read[AccountsCall.AccountsResponse](content.toString)
  }

  def account(accountId: String) = request {
    val content = Request
      .Get(s"$baseUrl/accounts/$accountId")
      .addHeader("Authorization", token)
      .execute()
      .returnContent()

    parse(content.toString).children.head.extract[AccountCall.AccountResponse]
  }

  def candles(instrument: String = "EUR_USD",
              price: String = "M",
              granularity: String = "S5",
              range: CandleRange = CandleRange(500),
              smooth: Boolean = false,
              includeFirst: Boolean = true) = request {
    val url = s"$baseUrl/instruments/$instrument/candles?" +
      s"includeFirst=$includeFirst&" +
      s"smooth=$smooth&" +
      s"price=$price&" +
      s"granularity=$granularity&" +
      s"${range.toParams}"
    val content = Request
      .Get(url)
      .addHeader("Authorization", token)
      .execute()
      .returnContent()

    read[CandlesResponse](content.toString)
  }

  override def openMarketOrder(accountID: String, instrument: String, units: Double, timeInForce: String, priceBound: Double, positionFill: String, clientExtensions: Option[ClientExtension], takeProfitOnFill: TakeProfitDetails, stopLossOnFill: StopLossDetails, trailingStopLossOnFill: TrailingStopLossDetails, tradeClientExtensions: Option[ClientExtension]): Unit = ???
}
