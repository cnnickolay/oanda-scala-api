package org.nikosoft.oanda.api

import org.nikosoft.oanda.api.AccountsApi.AccountsResponse
import org.nikosoft.oanda.api.model.ApiModel.AccountModel.{Account, AccountID, AccountProperties}
import org.nikosoft.oanda.api.model.ApiModel.TransactionModel.TransactionID

import scalaz._

object AccountsApi {

  /**
    * @param accounts The list of Accounts the client is authorized to access and their associated properties.
    */
  case class AccountsResponse(accounts: Seq[AccountProperties])

  /**
    * @param account The full details of the requested Account.
    * @param lastTransactionID The ID of the most recent Transaction created for the Account.
    */
  case class AccountsDetailsResponse(account: Account, lastTransactionID: TransactionID)

  case class AccountSummaryResponse(account: Account)

}

trait AccountsApi {

  /**
    * Get a list of all Accounts authorized for the provided token.
    * @return The list of authorized Accounts has been provided.
    */
  def accounts: \/[ErrorResponse, AccountsResponse]

  /**
    * Get the full details for a single Account that a client has access to. Full pending Order, open Trade and open Position representations are provided.
    * @param accountId Account Identifier
    * @return The full Account details are provided
    */
  def accountDetails(accountId: AccountID): \/[ErrorResponse, AccountsResponse]

  def accountSummary(accountId: AccountID):

}
