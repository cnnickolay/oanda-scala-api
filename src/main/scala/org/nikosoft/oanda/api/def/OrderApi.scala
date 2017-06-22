package org.nikosoft.oanda.api.`def`

import org.nikosoft.oanda.api.ApiModel.AccountModel.AccountID
import org.nikosoft.oanda.api.ApiModel.OrderModel.OrderRequest
import org.nikosoft.oanda.api.ApiModel.TransactionModel.{OrderCancelTransaction, OrderFillTransaction, Transaction, TransactionID}
import org.nikosoft.oanda.api.Errors.Error
import org.nikosoft.oanda.api.`def`.OrderApi.OrdersResponse

import scalaz.\/

object OrderApi {

  /**
    * @param orderCreateTransaction        The Transaction that created the Order specified by the request.
    * @param orderFillTransaction          The Transaction that filled the newly created Order. Only provided when the Order was immediately filled.
    * @param orderCancelTransaction        The Transaction that cancelled the newly created Order. Only provided when the Order was immediately cancelled.
    * @param orderReissueTransaction       The Transaction that reissues the Order. Only provided when the Order is configured to be reissued for its remaining units after a partial fill and the reissue was successful.
    * @param orderReissueRejectTransaction The Transaction that rejects the reissue of the Order. Only provided when the Order is configured to be reissued for its remaining units after a partial fill and the reissue was rejected.
    * @param relatedTransactionIDs         The IDs of all Transactions that were created while satisfying the request.
    * @param lastTransactionID             The ID of the most recent Transaction created for the Account
    */
  case class OrdersResponse(orderCreateTransaction: Transaction, orderFillTransaction: OrderFillTransaction, orderCancelTransaction: OrderCancelTransaction, orderReissueTransaction: Transaction, orderReissueRejectTransaction: Transaction, relatedTransactionIDs: Seq[TransactionID], lastTransactionID: TransactionID)

}

trait OrderApi {

  /**
    * Create an Order for an Account
    *
    * @param accountId Account Identifier [required]
    * @param order     Request body
    * @return The Order was created as specified
    */
  def order(accountId: AccountID, order: OrderRequest): \/[Error, OrdersResponse]

}
