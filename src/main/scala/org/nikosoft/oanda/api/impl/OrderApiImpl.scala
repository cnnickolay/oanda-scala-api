package org.nikosoft.oanda.api.impl

import org.nikosoft.oanda.api.ApiCommons
import org.nikosoft.oanda.api.ApiModel.AccountModel.AccountID
import org.nikosoft.oanda.api.ApiModel.OrderModel.OrderRequest
import org.nikosoft.oanda.api.Errors.Error
import org.nikosoft.oanda.api.`def`.OrderApi
import org.nikosoft.oanda.api.`def`.OrderApi.OrdersResponse
import org.json4s.native.Serialization.{read, write}

import scalaz.\/

object OrderApiImpl extends OrderApi with ApiCommons {

  /**
    * Create an Order for an Account
    *
    * @param accountId Account Identifier [required]
    * @param order     Request body
    * @return The Order was created as specified
    */
  def order(accountId: AccountID, order: OrderRequest): \/[Error, OrdersResponse] = {
    val jsonBody = write(order)


  }

}
