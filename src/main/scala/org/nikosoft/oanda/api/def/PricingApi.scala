package org.nikosoft.oanda.api.`def`

import org.nikosoft.oanda.api.ApiModel.AccountModel.AccountID
import org.nikosoft.oanda.api.ApiModel.PricingModel.Price
import org.nikosoft.oanda.api.ApiModel.PrimitivesModel.{DateTime, InstrumentName}
import org.nikosoft.oanda.api.Errors.Error
import org.nikosoft.oanda.api.`def`.PricingApi.PricingResponse

import scalaz.\/

object PricingApi {

  /**
    * @param prices The list of Price objects requested
    */
  case class PricingResponse(prices: Seq[Price])

}

trait PricingApi {

  /**
    * Get pricing information for a specified list of Instruments within an Account.
    *
    * @param accountId   Account Identifier [required]
    * @param instruments List of Instruments to get pricing for. [required]
    * @param since       Date/Time filter to apply to the returned prices. Only prices with a time later than this filter will be provided.
    * @return Pricing information has been successfully provided.
    */
  def pricing(accountId: AccountID, instruments: Seq[InstrumentName], since: Option[DateTime] = None): \/[Error, PricingResponse]

}
