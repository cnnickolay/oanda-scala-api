package org.nikosoft.oanda.api.impl

import org.apache.http.client.fluent.Request
import org.nikosoft.oanda.api.ApiCommons
import org.nikosoft.oanda.api.ApiModel.AccountModel.AccountID
import org.nikosoft.oanda.api.ApiModel.PrimitivesModel.{DateTime, InstrumentName}
import org.nikosoft.oanda.api.Errors.Error
import org.nikosoft.oanda.api.`def`.PricingApi
import org.nikosoft.oanda.api.`def`.PricingApi.PricingResponse

import scalaz.\/

private[api] object PricingApiImpl extends PricingApi with ApiCommons {

  /**
    * Get pricing information for a specified list of Instruments within an Account.
    *
    * @param accountId   Account Identifier [required]
    * @param instruments List of Instruments to get pricing for. [required]
    * @param since       Date/Time filter to apply to the returned prices. Only prices with a time later than this filter will be provided.
    * @return Pricing information has been successfully provided.
    */
  def pricing(accountId: AccountID, instruments: Seq[InstrumentName], since: Option[DateTime]): \/[Error, PricingResponse] = {
    val params = Seq(
      Option(s"instruments=${instruments.map(_.value).mkString(",")}"),
      since.map("since=" + _.value)
    ).flatten.mkString("&")

    val url = s"$baseUrl/accounts/${accountId.value}/pricing?$params"
    val content = Request
      .Get(url)
      .addHeader("Authorization", token)
      .execute()
      .returnContent()
      .toString

    handleRequest[PricingResponse](content)
  }

}
