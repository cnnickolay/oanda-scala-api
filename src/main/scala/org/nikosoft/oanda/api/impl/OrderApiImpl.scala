package org.nikosoft.oanda.api.impl

import org.apache.http.client.fluent.Request
import org.apache.http.entity.ContentType
import org.json4s.native.Serialization.write
import org.nikosoft.oanda.api.ApiCommons
import org.nikosoft.oanda.api.ApiModel.AccountModel.AccountID
import org.nikosoft.oanda.api.ApiModel.OrderModel.OrderRequest
import org.nikosoft.oanda.api.Errors.Error
import org.nikosoft.oanda.api.`def`.OrderApi
import org.nikosoft.oanda.api.`def`.OrderApi.OrdersResponse

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

    val url = s"$baseUrl/accounts/${accountId.value}/orders"
    val content = Request
      .Post(url)
      .addHeader("Authorization", token)
      .bodyString(jsonBody, ContentType.APPLICATION_JSON)
      .execute()
      .returnContent()
      .toString

    handleRequest[OrdersResponse](content)
  }

}
