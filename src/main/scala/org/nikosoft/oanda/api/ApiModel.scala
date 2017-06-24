package org.nikosoft.oanda.api

import org.joda.time
import org.joda.time.DateTime
import org.nikosoft.oanda.api.ApiModel.AccountModel.AccountFinancingMode.AccountFinancingMode
import org.nikosoft.oanda.api.ApiModel.TransactionModel._
import org.nikosoft.oanda.api.ApiModel.AccountModel._
import org.nikosoft.oanda.api.ApiModel.OrderModel.OrderPositionFill.OrderPositionFill
import org.nikosoft.oanda.api.ApiModel.OrderModel.OrderState.OrderState
import org.nikosoft.oanda.api.ApiModel.OrderModel.OrderTriggerCondition.OrderTriggerCondition
import org.nikosoft.oanda.api.ApiModel.OrderModel.OrderType.OrderType
import org.nikosoft.oanda.api.ApiModel.OrderModel.TimeInForce.TimeInForce
import org.nikosoft.oanda.api.ApiModel.OrderModel._
import org.nikosoft.oanda.api.ApiModel.TradeModel._
import org.nikosoft.oanda.api.ApiModel.PositionModel._
import org.nikosoft.oanda.api.ApiModel.PricingModel.PriceStatus.PriceStatus
import org.nikosoft.oanda.api.ApiModel.PricingModel._
import org.nikosoft.oanda.api.ApiModel.PrimitivesModel.InstrumentType.InstrumentType
import org.nikosoft.oanda.api.ApiModel.PrimitivesModel._
import org.nikosoft.oanda.api.ApiModel.TradeModel.TradeState.TradeState
import org.nikosoft.oanda.api.ApiModel.TransactionModel.FundingReason.FundingReason
import org.nikosoft.oanda.api.ApiModel.TransactionModel.LimitOrderReason.LimitOrderReason
import org.nikosoft.oanda.api.ApiModel.TransactionModel.MarketIfTouchedOrderReason.MarketIfTouchedOrderReason
import org.nikosoft.oanda.api.ApiModel.TransactionModel.MarketOrderMarginCloseoutReason.MarketOrderMarginCloseoutReason
import org.nikosoft.oanda.api.ApiModel.TransactionModel.MarketOrderReason.MarketOrderReason
import org.nikosoft.oanda.api.ApiModel.TransactionModel.OrderCancelReason.OrderCancelReason
import org.nikosoft.oanda.api.ApiModel.TransactionModel.OrderFillReason.OrderFillReason
import org.nikosoft.oanda.api.ApiModel.TransactionModel.StopLossOrderReason.StopLossOrderReason
import org.nikosoft.oanda.api.ApiModel.TransactionModel.StopOrderReason.StopOrderReason
import org.nikosoft.oanda.api.ApiModel.TransactionModel.TakeProfitOrderReason.TakeProfitOrderReason
import org.nikosoft.oanda.api.ApiModel.TransactionModel.TrailingStopLossOrderReason.TrailingStopLossOrderReason
import org.nikosoft.oanda.api.ApiModel.TransactionModel.TransactionRejectReason.TransactionRejectReason
import org.nikosoft.oanda.api.ApiModel.TransactionModel.TransactionType.TransactionType

object ApiModel {

  object TransactionModel {

    /**
     * The base Transaction specification. Specifies properties that are common between all Transaction.
     */
    abstract class Transaction(
      /** The Transaction’s Identifier. */
      id: TransactionID,
      /** The date/time when the Transaction was created. */
      time: DateTime,
      /** The ID of the user that initiated the creation of the Transaction. */
      userID: Int,
      /** The ID of the Account the Transaction was created for. */
      accountID: AccountID,
      /** The ID of the "batch" that the Transaction belongs to. Transactions in the same batch are applied to the Account simultaneously. */
      batchID: TransactionID,
      /** The Request ID of the Account Control Request which generated the transaction (only provided for Transactions created by a Client request) */
      requestID: RequestID,
      /** The Type of the Transaction. */
      `type`: TransactionType
    )
  
    /**
     * A CreateTransaction represents the creation of an Account.
     */
    case class CreateTransaction(
      /** The Transaction’s Identifier. */
      id: TransactionID,
      /** The date/time when the Transaction was created. */
      time: DateTime,
      /** The ID of the user that initiated the creation of the Transaction. */
      userID: Int,
      /** The ID of the Account the Transaction was created for. */
      accountID: AccountID,
      /** The ID of the "batch" that the Transaction belongs to. Transactions in the same batch are applied to the Account simultaneously. */
      batchID: TransactionID,
      /** The Request ID of the Account Control Request which generated the transaction (only provided for Transactions created by a Client request) */
      requestID: RequestID,
      /** The Type of the Transaction. Always set to "CREATE" in a CreateTransaction. */
       `type`: TransactionType = TransactionType.CREATE,
      /** The ID of the Division that the Account is in */
      divisionID: Int,
      /** The ID of the Site that the Account was created at */
      siteID: Int,
      /** The ID of the user that the Account was created for */
      accountUserID: Int,
      /** The number of the Account within the site/division/user */
      accountNumber: Int,
      /** The home currency of the Account */
      homeCurrency: Currency
    ) extends Transaction(id, time, userID, accountID, batchID, requestID, `type`)
  
    /**
     * A CloseTransaction represents the closing of an Account.
     */
    case class CloseTransaction(
      /** The Transaction’s Identifier. */
      id: TransactionID,
      /** The date/time when the Transaction was created. */
      time: DateTime,
      /** The ID of the user that initiated the creation of the Transaction. */
      userID: Int,
      /** The ID of the Account the Transaction was created for. */
      accountID: AccountID,
      /** The ID of the "batch" that the Transaction belongs to. Transactions in the same batch are applied to the Account simultaneously. */
      batchID: TransactionID,
      /** The Request ID of the Account Control Request which generated the transaction (only provided for Transactions created by a Client request) */
      requestID: RequestID
      /** The Type of the Transaction. Always set to "CLOSE" in a CloseTransaction. */,
      `type`: TransactionType = TransactionType.CLOSE
    ) extends Transaction(id, time, userID, accountID, batchID, requestID, `type`)
  
    /**
     * A ReopenTransaction represents the re-opening of a closed Account.
     */
    case class ReopenTransaction(
      /** The Transaction’s Identifier. */
      id: TransactionID,
      /** The date/time when the Transaction was created. */
      time: DateTime,
      /** The ID of the user that initiated the creation of the Transaction. */
      userID: Int,
      /** The ID of the Account the Transaction was created for. */
      accountID: AccountID,
      /** The ID of the "batch" that the Transaction belongs to. Transactions in the same batch are applied to the Account simultaneously. */
      batchID: TransactionID,
      /** The Request ID of the Account Control Request which generated the transaction (only provided for Transactions created by a Client request) */
      requestID: RequestID,
      /** The Type of the Transaction. Always set to "REOPEN" in a ReopenTransaction. */
      `type`: TransactionType = TransactionType.REOPEN
    ) extends Transaction(id, time, userID, accountID, batchID, requestID, `type`)
  
    /**
     * A ClientConfigureTransaction represents the configuration of an Account by a client.
     */
    case class ClientConfigureTransaction(
      /** The Transaction’s Identifier. */
      id: TransactionID,
      /** The date/time when the Transaction was created. */
      time: DateTime,
      /** The ID of the user that initiated the creation of the Transaction. */
      userID: Int,
      /** The ID of the Account the Transaction was created for. */
      accountID: AccountID,
      /** The ID of the "batch" that the Transaction belongs to. Transactions in the same batch are applied to the Account simultaneously. */
      batchID: TransactionID,
      /** The Request ID of the Account Control Request which generated the transaction (only provided for Transactions created by a Client request) */
      requestID: RequestID,
      /** The Type of the Transaction. Always set to "CLIENT_CONFIGURE" in a ClientConfigureTransaction. */
      `type`: TransactionType = TransactionType.CLIENT_CONFIGURE,
      /** The client-provided alias for the Account. */
      alias: String,
      /** The margin rate override for the Account. */
      marginRate: Double
    ) extends Transaction(id, time, userID, accountID, batchID, requestID, `type`)
  
    /**
     * A ClientConfigureRejectTransaction represents the reject of configuration of an Account by a client.
     */
    case class ClientConfigureRejectTransaction(
      /** The Transaction’s Identifier. */
      id: TransactionID,
      /** The date/time when the Transaction was created. */
      time: DateTime,
      /** The ID of the user that initiated the creation of the Transaction. */
      userID: Int,
      /** The ID of the Account the Transaction was created for. */
      accountID: AccountID,
      /** The ID of the "batch" that the Transaction belongs to. Transactions in the same batch are applied to the Account simultaneously. */
      batchID: TransactionID,
      /** The Request ID of the Account Control Request which generated the transaction (only provided for Transactions created by a Client request) */
      requestID: RequestID,
      /** The Type of the Transaction. Always set to "CLIENT_CONFIGURE_REJECT" in a ClientConfigureRejectTransaction. */
      `type`: TransactionType = TransactionType.CLIENT_CONFIGURE_REJECT,
      /** The client-provided alias for the Account. */
      alias: String,
      /** The margin rate override for the Account. */
      marginRate: Double,
      /** The reason that the Reject Transaction was created */
      rejectReason: TransactionRejectReason
    ) extends Transaction(id, time, userID, accountID, batchID, requestID, `type`)
  
    /**
     * A TransferFundsTransaction represents the transfer of funds in/out of an Account.
     */
    case class TransferFundsTransaction(
      /** The Transaction’s Identifier. */
      id: TransactionID,
      /** The date/time when the Transaction was created. */
      time: DateTime,
      /** The ID of the user that initiated the creation of the Transaction. */
      userID: Int,
      /** The ID of the Account the Transaction was created for. */
      accountID: AccountID,
      /** The ID of the "batch" that the Transaction belongs to. Transactions in the same batch are applied to the Account simultaneously. */
      batchID: TransactionID,
      /** The Request ID of the Account Control Request which generated the transaction (only provided for Transactions created by a Client request) */
      requestID: RequestID,
      /** The Type of the Transaction. Always set to "TRANSFER_FUNDS" in a TransferFundsTransaction. */
      `type`: TransactionType = TransactionType.TRANSFER_FUNDS,
      /** The amount to deposit/withdraw from the Account in the Account’s home currency. A positive value indicates a deposit, a negative value indicates a withdrawal. */
      amount: AccountUnits,
      /** The reason that an Account is being funded. */
      fundingReason: FundingReason,
      /** The Account’s balance after funds are transferred. */
      accountBalance: AccountUnits
    ) extends Transaction(id, time, userID, accountID, batchID, requestID, `type`)
  
    /**
     * A TransferFundsRejectTransaction represents the rejection of the transfer of funds in/out of an Account.
     */
    case class TransferFundsRejectTransaction(
      /** The Transaction’s Identifier. */
      id: TransactionID,
      /** The date/time when the Transaction was created. */
      time: DateTime,
      /** The ID of the user that initiated the creation of the Transaction. */
      userID: Int,
      /** The ID of the Account the Transaction was created for. */
      accountID: AccountID,
      /** The ID of the "batch" that the Transaction belongs to. Transactions in the same batch are applied to the Account simultaneously. */
      batchID: TransactionID,
      /** The Request ID of the Account Control Request which generated the transaction (only provided for Transactions created by a Client request) */
      requestID: RequestID,
      /** The Type of the Transaction. Always set to "TRANSFER_FUNDS_REJECT" in a TransferFundsRejectTransaction. */
      `type`: TransactionType = TransactionType.TRANSFER_FUNDS_REJECT,
      /** The amount to deposit/withdraw from the Account in the Account’s home currency. A positive value indicates a deposit, a negative value indicates a withdrawal. */
      amount: AccountUnits,
      /** The reason that an Account is being funded. */
      fundingReason: FundingReason,
      /** The reason that the Reject Transaction was created */
      rejectReason: TransactionRejectReason
    ) extends Transaction(id, time, userID, accountID, batchID, requestID, `type`)
  
    /**
     * A MarketOrderTransaction represents the creation of a Market Order in the user’s account. A Market Order is an Order that is filled immediately at the current market price. Market Orders can be specialized when they are created to accomplish a specific task: to close a Trade, to closeout a Position or to particiate in in a Margin closeout.
     */
    case class MarketOrderTransaction(
      /** The Transaction’s Identifier. */
      id: TransactionID,
      /** The date/time when the Transaction was created. */
      time: DateTime,
      /** The ID of the user that initiated the creation of the Transaction. */
      userID: Int,
      /** The ID of the Account the Transaction was created for. */
      accountID: AccountID,
      /** The ID of the "batch" that the Transaction belongs to. Transactions in the same batch are applied to the Account simultaneously. */
      batchID: TransactionID,
      /** The Request ID of the Account Control Request which generated the transaction (only provided for Transactions created by a Client request) */
      requestID: RequestID,
      /** The Type of the Transaction. Always set to "MARKET_ORDER" in a MarketOrderTransaction. */
      `type`: TransactionType = TransactionType.MARKET_ORDER,
      /** The Market Order’s Instrument. */
      instrument: InstrumentName,
      /** The quantity requested to be filled by the Market Order. A posititive number of units results in a long Order, and a negative number of units results in a short Order. */
      units: Double,
      /** The time-in-force requested for the Market Order. Restricted to FOK or IOC for a MarketOrder. */
      timeInForce: TimeInForce,
      /** The worst price that the client is willing to have the Market Order filled at. */
      priceBound: PriceValue,
      /** Specification of how Positions in the Account are modified when the Order is filled. */
      positionFill: OrderPositionFill,
      /** Details of the Trade requested to be closed, only provided when the Market Order is being used to explicitly close a Trade. */
      tradeClose: MarketOrderTradeClose,
      /** Details of the long Position requested to be closed out, only provided when a Market Order is being used to explicitly closeout a long Position. */
      longPositionCloseout: MarketOrderPositionCloseout,
      /** Details of the short Position requested to be closed out, only provided when a Market Order is being used to explicitly closeout a short Position. */
      shortPositionCloseout: MarketOrderPositionCloseout,
      /** Details of the Margin Closeout that this Market Order was created for */
      marginCloseout: MarketOrderMarginCloseout,
      /** Details of the delayed Trade close that this Market Order was created for */
      delayedTradeClose: MarketOrderDelayedTradeClose,
      /** The reason that the Market Order was created */
      reason: MarketOrderReason,
      /** Client Extensions to add to the Order (only provided if the Order is being created with client extensions). */
      clientExtensions: Option[ClientExtensions],
      /** The specification of the Take Profit Order that should be created for a Trade opened when the Order is filled (if such a Trade is created). */
      takeProfitOnFill: TakeProfitDetails,
      /** The specification of the Stop Loss Order that should be created for a Trade opened when the Order is filled (if such a Trade is created). */
      stopLossOnFill: StopLossDetails,
      /** The specification of the Trailing Stop Loss Order that should be created for a Trade that is opened when the Order is filled (if such a Trade is created). */
      trailingStopLossOnFill: TrailingStopLossDetails,
      /** Client Extensions to add to the Trade created when the Order is filled (if such a Trade is created).  Do not set, modify, delete tradeClientExtensions if your account is associated with MT4. */
      tradeclientExtensions: Option[ClientExtensions]
    ) extends Transaction(id, time, userID, accountID, batchID, requestID, `type`)
  
    /**
     * A MarketOrderRejectTransaction represents the rejection of the creation of a Market Order.
     */
    case class MarketOrderRejectTransaction(
      /** The Transaction’s Identifier. */
      id: TransactionID,
      /** The date/time when the Transaction was created. */
      time: DateTime,
      /** The ID of the user that initiated the creation of the Transaction. */
      userID: Int,
      /** The ID of the Account the Transaction was created for. */
      accountID: AccountID,
      /** The ID of the "batch" that the Transaction belongs to. Transactions in the same batch are applied to the Account simultaneously. */
      batchID: TransactionID,
      /** The Request ID of the Account Control Request which generated the transaction (only provided for Transactions created by a Client request) */
      requestID: RequestID,
      /** The Type of the Transaction. Always set to "MARKET_ORDER_REJECT" in a MarketOrderRejectTransaction. */
      `type`: TransactionType = TransactionType.MARKET_ORDER_REJECT,
      /** The Market Order’s Instrument. */
      instrument: InstrumentName,
      /** The quantity requested to be filled by the Market Order. A posititive number of units results in a long Order, and a negative number of units results in a short Order. */
      units: Double,
      /** The time-in-force requested for the Market Order. Restricted to FOK or IOC for a MarketOrder. */
      timeInForce: TimeInForce,
      /** The worst price that the client is willing to have the Market Order filled at. */
      priceBound: PriceValue,
      /** Specification of how Positions in the Account are modified when the Order is filled. */
      positionFill: OrderPositionFill,
      /** Details of the Trade requested to be closed, only provided when the Market Order is being used to explicitly close a Trade. */
      tradeClose: MarketOrderTradeClose,
      /** Details of the long Position requested to be closed out, only provided when a Market Order is being used to explicitly closeout a long Position. */
      longPositionCloseout: MarketOrderPositionCloseout,
      /** Details of the short Position requested to be closed out, only provided when a Market Order is being used to explicitly closeout a short Position. */
      shortPositionCloseout: MarketOrderPositionCloseout,
      /** Details of the Margin Closeout that this Market Order was created for */
      marginCloseout: MarketOrderMarginCloseout,
      /** Details of the delayed Trade close that this Market Order was created for */
      delayedTradeClose: MarketOrderDelayedTradeClose,
      /** The reason that the Market Order was created */
      reason: MarketOrderReason,
      /** Client Extensions to add to the Order (only provided if the Order is being created with client extensions). */
      clientExtensions: Option[ClientExtensions],
      /** The specification of the Take Profit Order that should be created for a Trade opened when the Order is filled (if such a Trade is created). */
      takeProfitOnFill: TakeProfitDetails,
      /** The specification of the Stop Loss Order that should be created for a Trade opened when the Order is filled (if such a Trade is created). */
      stopLossOnFill: StopLossDetails,
      /** The specification of the Trailing Stop Loss Order that should be created for a Trade that is opened when the Order is filled (if such a Trade is created). */
      trailingStopLossOnFill: TrailingStopLossDetails,
      /** Client Extensions to add to the Trade created when the Order is filled (if such a Trade is created).  Do not set, modify, delete tradeClientExtensions if your account is associated with MT4. */
      tradeclientExtensions: Option[ClientExtensions],
      /** The reason that the Reject Transaction was created */
      rejectReason: TransactionRejectReason
    ) extends Transaction(id, time, userID, accountID, batchID, requestID, `type`)
  
    /**
     * A LimitOrderTransaction represents the creation of a Limit Order in the user’s Account.
     */
    case class LimitOrderTransaction(
      /** The Transaction’s Identifier. */
      id: TransactionID,
      /** The date/time when the Transaction was created. */
      time: DateTime,
      /** The ID of the user that initiated the creation of the Transaction. */
      userID: Int,
      /** The ID of the Account the Transaction was created for. */
      accountID: AccountID,
      /** The ID of the "batch" that the Transaction belongs to. Transactions in the same batch are applied to the Account simultaneously. */
      batchID: TransactionID,
      /** The Request ID of the Account Control Request which generated the transaction (only provided for Transactions created by a Client request) */
      requestID: RequestID,
      /** The Type of the Transaction. Always set to "LIMIT_ORDER" in a LimitOrderTransaction. */
      `type`: TransactionType = TransactionType.LIMIT_ORDER,
      /** The Limit Order’s Instrument. */
      instrument: InstrumentName,
      /** The quantity requested to be filled by the Limit Order. A posititive number of units results in a long Order, and a negative number of units results in a short Order. */
      units: Double,
      /** The price threshold specified for the Limit Order. The Limit Order will only be filled by a market price that is equal to or better than this price. */
      price: PriceValue,
      /** The time-in-force requested for the Limit Order. */
      timeInForce: TimeInForce,
      /** The date/time when the Limit Order will be cancelled if its timeInForce is "GTD". */
      gtdTime: DateTime,
      /** Specification of how Positions in the Account are modified when the Order is filled. */
      positionFill: OrderPositionFill,
      /** Specification of what component of a price should be used for comparison when determining if the Order should be filled. */
      triggerCondition: OrderTriggerCondition,
      /** The reason that the Limit Order was initiated */
      reason: LimitOrderReason,
      /** Client Extensions to add to the Order (only provided if the Order is being created with client extensions). */
      clientExtensions: Option[ClientExtensions],
      /** The specification of the Take Profit Order that should be created for a Trade opened when the Order is filled (if such a Trade is created). */
      takeProfitOnFill: TakeProfitDetails,
      /** The specification of the Stop Loss Order that should be created for a Trade opened when the Order is filled (if such a Trade is created). */
      stopLossOnFill: StopLossDetails,
      /** The specification of the Trailing Stop Loss Order that should be created for a Trade that is opened when the Order is filled (if such a Trade is created). */
      trailingStopLossOnFill: TrailingStopLossDetails,
      /** Client Extensions to add to the Trade created when the Order is filled (if such a Trade is created).  Do not set, modify, delete tradeClientExtensions if your account is associated with MT4. */
      tradeclientExtensions: Option[ClientExtensions],
      /** The ID of the Order that this Order replaces (only provided if this Order replaces an existing Order). */
      replacesOrderID: Option[OrderID],
      /** The ID of the Transaction that cancels the replaced Order (only provided if this Order replaces an existing Order). */
      cancellingTransactionID: Option[TransactionID]
    ) extends Transaction(id, time, userID, accountID, batchID, requestID, `type`)
  
    /**
     * A LimitOrderRejectTransaction represents the rejection of the creation of a Limit Order.
     */
    case class LimitOrderRejectTransaction(
      /** The Transaction’s Identifier. */
      id: TransactionID,
      /** The date/time when the Transaction was created. */
      time: DateTime,
      /** The ID of the user that initiated the creation of the Transaction. */
      userID: Int,
      /** The ID of the Account the Transaction was created for. */
      accountID: AccountID,
      /** The ID of the "batch" that the Transaction belongs to. Transactions in the same batch are applied to the Account simultaneously. */
      batchID: TransactionID,
      /** The Request ID of the Account Control Request which generated the transaction (only provided for Transactions created by a Client request) */
      requestID: RequestID,
      /** The Type of the Transaction. Always set to "LIMIT_ORDER_REJECT" in a LimitOrderRejectTransaction. */
      `type`: TransactionType = TransactionType.LIMIT_ORDER_REJECT,
      /** The Limit Order’s Instrument. */
      instrument: InstrumentName,
      /** The quantity requested to be filled by the Limit Order. A posititive number of units results in a long Order, and a negative number of units results in a short Order. */
      units: Double,
      /** The price threshold specified for the Limit Order. The Limit Order will only be filled by a market price that is equal to or better than this price. */
      price: PriceValue,
      /** The time-in-force requested for the Limit Order. */
      timeInForce: TimeInForce,
      /** The date/time when the Limit Order will be cancelled if its timeInForce is "GTD". */
      gtdTime: DateTime,
      /** Specification of how Positions in the Account are modified when the Order is filled. */
      positionFill: OrderPositionFill,
      /** Specification of what component of a price should be used for comparison when determining if the Order should be filled. */
      triggerCondition: OrderTriggerCondition,
      /** The reason that the Limit Order was initiated */
      reason: LimitOrderReason,
      /** Client Extensions to add to the Order (only provided if the Order is being created with client extensions). */
      clientExtensions: Option[ClientExtensions],
      /** The specification of the Take Profit Order that should be created for a Trade opened when the Order is filled (if such a Trade is created). */
      takeProfitOnFill: TakeProfitDetails,
      /** The specification of the Stop Loss Order that should be created for a Trade opened when the Order is filled (if such a Trade is created). */
      stopLossOnFill: StopLossDetails,
      /** The specification of the Trailing Stop Loss Order that should be created for a Trade that is opened when the Order is filled (if such a Trade is created). */
      trailingStopLossOnFill: TrailingStopLossDetails,
      /** Client Extensions to add to the Trade created when the Order is filled (if such a Trade is created).  Do not set, modify, delete tradeClientExtensions if your account is associated with MT4. */
      tradeclientExtensions: Option[ClientExtensions],
      /** The ID of the Order that this Order was intended to replace (only provided if this Order was intended to replace an existing Order). */
      intendedReplacesOrderID: Option[OrderID],
      /** The reason that the Reject Transaction was created */
      rejectReason: TransactionRejectReason
    ) extends Transaction(id, time, userID, accountID, batchID, requestID, `type`)
  
    /**
     * A StopOrderTransaction represents the creation of a Stop Order in the user’s Account.
     */
    case class StopOrderTransaction(
      /** The Transaction’s Identifier. */
      id: TransactionID,
      /** The date/time when the Transaction was created. */
      time: DateTime,
      /** The ID of the user that initiated the creation of the Transaction. */
      userID: Int,
      /** The ID of the Account the Transaction was created for. */
      accountID: AccountID,
      /** The ID of the "batch" that the Transaction belongs to. Transactions in the same batch are applied to the Account simultaneously. */
      batchID: TransactionID,
      /** The Request ID of the Account Control Request which generated the transaction (only provided for Transactions created by a Client request) */
      requestID: RequestID,
      /** The Type of the Transaction. Always set to "STOP_ORDER" in a StopOrderTransaction. */
      `type`: TransactionType = TransactionType.STOP_ORDER,
      /** The Stop Order’s Instrument. */
      instrument: InstrumentName,
      /** The quantity requested to be filled by the Stop Order. A posititive number of units results in a long Order, and a negative number of units results in a short Order. */
      units: Double,
      /** The price threshold specified for the Stop Order. The Stop Order will only be filled by a market price that is equal to or worse than this price. */
      price: PriceValue,
      /** The worst market price that may be used to fill this Stop Order. If the market gaps and crosses through both the price and the priceBound, the Stop Order will be cancelled instead of being filled. */
      priceBound: PriceValue,
      /** The time-in-force requested for the Stop Order. */
      timeInForce: TimeInForce,
      /** The date/time when the Stop Order will be cancelled if its timeInForce is "GTD". */
      gtdTime: DateTime,
      /** Specification of how Positions in the Account are modified when the Order is filled. */
      positionFill: OrderPositionFill,
      /** Specification of what component of a price should be used for comparison when determining if the Order should be filled. */
      triggerCondition: OrderTriggerCondition,
      /** The reason that the Stop Order was initiated */
      reason: StopOrderReason,
      /** Client Extensions to add to the Order (only provided if the Order is being created with client extensions). */
      clientExtensions: Option[ClientExtensions],
      /** The specification of the Take Profit Order that should be created for a Trade opened when the Order is filled (if such a Trade is created). */
      takeProfitOnFill: TakeProfitDetails,
      /** The specification of the Stop Loss Order that should be created for a Trade opened when the Order is filled (if such a Trade is created). */
      stopLossOnFill: StopLossDetails,
      /** The specification of the Trailing Stop Loss Order that should be created for a Trade that is opened when the Order is filled (if such a Trade is created). */
      trailingStopLossOnFill: TrailingStopLossDetails,
      /** Client Extensions to add to the Trade created when the Order is filled (if such a Trade is created).  Do not set, modify, delete tradeClientExtensions if your account is associated with MT4. */
      tradeclientExtensions: Option[ClientExtensions],
      /** The ID of the Order that this Order replaces (only provided if this Order replaces an existing Order). */
      replacesOrderID: Option[OrderID],
      /** The ID of the Transaction that cancels the replaced Order (only provided if this Order replaces an existing Order). */
      cancellingTransactionID: Option[TransactionID]
    ) extends Transaction(id, time, userID, accountID, batchID, requestID, `type`)
  
    /**
     * A StopOrderRejectTransaction represents the rejection of the creation of a Stop Order.
     */
    case class StopOrderRejectTransaction(
      /** The Transaction’s Identifier. */
      id: TransactionID,
      /** The date/time when the Transaction was created. */
      time: DateTime,
      /** The ID of the user that initiated the creation of the Transaction. */
      userID: Int,
      /** The ID of the Account the Transaction was created for. */
      accountID: AccountID,
      /** The ID of the "batch" that the Transaction belongs to. Transactions in the same batch are applied to the Account simultaneously. */
      batchID: TransactionID,
      /** The Request ID of the Account Control Request which generated the transaction (only provided for Transactions created by a Client request) */
      requestID: RequestID,
      /** The Type of the Transaction. Always set to "STOP_ORDER_REJECT" in a StopOrderRejectTransaction. */
      `type`: TransactionType = TransactionType.STOP_ORDER_REJECT,
      /** The Stop Order’s Instrument. */
      instrument: InstrumentName,
      /** The quantity requested to be filled by the Stop Order. A posititive number of units results in a long Order, and a negative number of units results in a short Order. */
      units: Double,
      /** The price threshold specified for the Stop Order. The Stop Order will only be filled by a market price that is equal to or worse than this price. */
      price: PriceValue,
      /** The worst market price that may be used to fill this Stop Order. If the market gaps and crosses through both the price and the priceBound, the Stop Order will be cancelled instead of being filled. */
      priceBound: PriceValue,
      /** The time-in-force requested for the Stop Order. */
      timeInForce: TimeInForce,
      /** The date/time when the Stop Order will be cancelled if its timeInForce is "GTD". */
      gtdTime: DateTime,
      /** Specification of how Positions in the Account are modified when the Order is filled. */
      positionFill: OrderPositionFill,
      /** Specification of what component of a price should be used for comparison when determining if the Order should be filled. */
      triggerCondition: OrderTriggerCondition,
      /** The reason that the Stop Order was initiated */
      reason: StopOrderReason,
      /** Client Extensions to add to the Order (only provided if the Order is being created with client extensions). */
      clientExtensions: Option[ClientExtensions],
      /** The specification of the Take Profit Order that should be created for a Trade opened when the Order is filled (if such a Trade is created). */
      takeProfitOnFill: TakeProfitDetails,
      /** The specification of the Stop Loss Order that should be created for a Trade opened when the Order is filled (if such a Trade is created). */
      stopLossOnFill: StopLossDetails,
      /** The specification of the Trailing Stop Loss Order that should be created for a Trade that is opened when the Order is filled (if such a Trade is created). */
      trailingStopLossOnFill: TrailingStopLossDetails,
      /** Client Extensions to add to the Trade created when the Order is filled (if such a Trade is created).  Do not set, modify, delete tradeClientExtensions if your account is associated with MT4. */
      tradeclientExtensions: Option[ClientExtensions],
      /** The ID of the Order that this Order was intended to replace (only provided if this Order was intended to replace an existing Order). */
      intendedReplacesOrderID: Option[OrderID],
      /** The reason that the Reject Transaction was created */
      rejectReason: TransactionRejectReason
    ) extends Transaction(id, time, userID, accountID, batchID, requestID, `type`)
  
    /**
     * A MarketIfTouchedOrderTransaction represents the creation of a MarketIfTouched Order in the user’s Account.
     */
    case class MarketIfTouchedOrderTransaction(
      /** The Transaction’s Identifier. */
      id: TransactionID,
      /** The date/time when the Transaction was created. */
      time: DateTime,
      /** The ID of the user that initiated the creation of the Transaction. */
      userID: Int,
      /** The ID of the Account the Transaction was created for. */
      accountID: AccountID,
      /** The ID of the "batch" that the Transaction belongs to. Transactions in the same batch are applied to the Account simultaneously. */
      batchID: TransactionID,
      /** The Request ID of the Account Control Request which generated the transaction (only provided for Transactions created by a Client request) */
      requestID: RequestID,
      /** The Type of the Transaction. Always set to "MARKET_IF_TOUCHED_ORDER" in a MarketIfTouchedOrderTransaction. */
      `type`: TransactionType = TransactionType.MARKET_IF_TOUCHED_ORDER,
      /** The MarketIfTouched Order’s Instrument. */
      instrument: InstrumentName,
      /** The quantity requested to be filled by the MarketIfTouched Order. A posititive number of units results in a long Order, and a negative number of units results in a short Order. */
      units: Double,
      /** The price threshold specified for the MarketIfTouched Order. The MarketIfTouched Order will only be filled by a market price that crosses this price from the direction of the market price at the time when the Order was created (the initialMarketPrice). Depending on the value of the Order’s price and initialMarketPrice, the MarketIfTouchedOrder will behave like a Limit or a Stop Order. */
      price: PriceValue,
      /** The worst market price that may be used to fill this MarketIfTouched Order. */
      priceBound: PriceValue,
      /** The time-in-force requested for the MarketIfTouched Order. Restricted to "GTC", "GFD" and "GTD" for MarketIfTouched Orders. */
      timeInForce: TimeInForce,
      /** The date/time when the MarketIfTouched Order will be cancelled if its timeInForce is "GTD". */
      gtdTime: DateTime,
      /** Specification of how Positions in the Account are modified when the Order is filled. */
      positionFill: OrderPositionFill,
      /** Specification of what component of a price should be used for comparison when determining if the Order should be filled. */
      triggerCondition: OrderTriggerCondition,
      /** The reason that the Market-if-touched Order was initiated */
      reason: MarketIfTouchedOrderReason,
      /** Client Extensions to add to the Order (only provided if the Order is being created with client extensions). */
      clientExtensions: Option[ClientExtensions],
      /** The specification of the Take Profit Order that should be created for a Trade opened when the Order is filled (if such a Trade is created). */
      takeProfitOnFill: TakeProfitDetails,
      /** The specification of the Stop Loss Order that should be created for a Trade opened when the Order is filled (if such a Trade is created). */
      stopLossOnFill: StopLossDetails,
      /** The specification of the Trailing Stop Loss Order that should be created for a Trade that is opened when the Order is filled (if such a Trade is created). */
      trailingStopLossOnFill: TrailingStopLossDetails,
      /** Client Extensions to add to the Trade created when the Order is filled (if such a Trade is created).  Do not set, modify, delete tradeClientExtensions if your account is associated with MT4. */
      tradeclientExtensions: Option[ClientExtensions],
      /** The ID of the Order that this Order replaces (only provided if this Order replaces an existing Order). */
      replacesOrderID: Option[OrderID],
      /** The ID of the Transaction that cancels the replaced Order (only provided if this Order replaces an existing Order). */
      cancellingTransactionID: Option[TransactionID]
    ) extends Transaction(id, time, userID, accountID, batchID, requestID, `type`)
  
    /**
     * A MarketIfTouchedOrderRejectTransaction represents the rejection of the creation of a MarketIfTouched Order.
     */
    case class MarketIfTouchedOrderRejectTransaction(
      /** The Transaction’s Identifier. */
      id: TransactionID,
      /** The date/time when the Transaction was created. */
      time: DateTime,
      /** The ID of the user that initiated the creation of the Transaction. */
      userID: Int,
      /** The ID of the Account the Transaction was created for. */
      accountID: AccountID,
      /** The ID of the "batch" that the Transaction belongs to. Transactions in the same batch are applied to the Account simultaneously. */
      batchID: TransactionID,
      /** The Request ID of the Account Control Request which generated the transaction (only provided for Transactions created by a Client request) */
      requestID: RequestID,
      /** The Type of the Transaction. Always set to "MARKET_IF_TOUCHED_ORDER_REJECT" in a MarketIfTouchedOrderRejectTransaction. */
      `type`: TransactionType = TransactionType.MARKET_IF_TOUCHED_ORDER_REJECT,
      /** The MarketIfTouched Order’s Instrument. */
      instrument: InstrumentName,
      /** The quantity requested to be filled by the MarketIfTouched Order. A posititive number of units results in a long Order, and a negative number of units results in a short Order. */
      units: Double,
      /** The price threshold specified for the MarketIfTouched Order. The MarketIfTouched Order will only be filled by a market price that crosses this price from the direction of the market price at the time when the Order was created (the initialMarketPrice). Depending on the value of the Order’s price and initialMarketPrice, the MarketIfTouchedOrder will behave like a Limit or a Stop Order. */
      price: PriceValue,
      /** The worst market price that may be used to fill this MarketIfTouched Order. */
      priceBound: PriceValue,
      /** The time-in-force requested for the MarketIfTouched Order. Restricted to "GTC", "GFD" and "GTD" for MarketIfTouched Orders. */
      timeInForce: TimeInForce,
      /** The date/time when the MarketIfTouched Order will be cancelled if its timeInForce is "GTD". */
      gtdTime: DateTime,
      /** Specification of how Positions in the Account are modified when the Order is filled. */
      positionFill: OrderPositionFill,
      /** Specification of what component of a price should be used for comparison when determining if the Order should be filled. */
      triggerCondition: OrderTriggerCondition,
      /** The reason that the Market-if-touched Order was initiated */
      reason: MarketIfTouchedOrderReason,
      /** Client Extensions to add to the Order (only provided if the Order is being created with client extensions). */
      clientExtensions: Option[ClientExtensions],
      /** The specification of the Take Profit Order that should be created for a Trade opened when the Order is filled (if such a Trade is created). */
      takeProfitOnFill: TakeProfitDetails,
      /** The specification of the Stop Loss Order that should be created for a Trade opened when the Order is filled (if such a Trade is created). */
      stopLossOnFill: StopLossDetails,
      /** The specification of the Trailing Stop Loss Order that should be created for a Trade that is opened when the Order is filled (if such a Trade is created). */
      trailingStopLossOnFill: TrailingStopLossDetails,
      /** Client Extensions to add to the Trade created when the Order is filled (if such a Trade is created).  Do not set, modify, delete tradeClientExtensions if your account is associated with MT4. */
      tradeclientExtensions: Option[ClientExtensions],
      /** The ID of the Order that this Order was intended to replace (only provided if this Order was intended to replace an existing Order). */
      intendedReplacesOrderID: Option[OrderID],
      /** The reason that the Reject Transaction was created */
      rejectReason: TransactionRejectReason
    ) extends Transaction(id, time, userID, accountID, batchID, requestID, `type`)
  
    /**
     * A TakeProfitOrderTransaction represents the creation of a TakeProfit Order in the user’s Account.
     */
    case class TakeProfitOrderTransaction(
      /** The Transaction’s Identifier. */
      id: TransactionID,
      /** The date/time when the Transaction was created. */
      time: DateTime,
      /** The ID of the user that initiated the creation of the Transaction. */
      userID: Int,
      /** The ID of the Account the Transaction was created for. */
      accountID: AccountID,
      /** The ID of the "batch" that the Transaction belongs to. Transactions in the same batch are applied to the Account simultaneously. */
      batchID: TransactionID,
      /** The Request ID of the Account Control Request which generated the transaction (only provided for Transactions created by a Client request) */
      requestID: RequestID,
      /** The Type of the Transaction. Always set to "TAKE_PROFIT_ORDER" in a TakeProfitOrderTransaction. */
      `type`: TransactionType = TransactionType.TAKE_PROFIT_ORDER,
      /** The ID of the Trade to close when the price threshold is breached. */
      tradeID: TradeID,
      /** The client ID of the Trade to be closed when the price threshold is breached. */
      clientTradeID: ClientID,
      /** The price threshold specified for the TakeProfit Order. The associated Trade will be closed by a market price that is equal to or better than this threshold. */
      price: PriceValue,
      /** The time-in-force requested for the TakeProfit Order. Restricted to "GTC", "GFD" and "GTD" for TakeProfit Orders. */
      timeInForce: TimeInForce,
      /** The date/time when the TakeProfit Order will be cancelled if its timeInForce is "GTD". */
      gtdTime: DateTime,
      /** Specification of what component of a price should be used for comparison when determining if the Order should be filled. */
      triggerCondition: OrderTriggerCondition,
      /** The reason that the Take Profit Order was initiated */
      reason: TakeProfitOrderReason,
      /** Client Extensions to add to the Order (only provided if the Order is being created with client extensions). */
      clientExtensions: Option[ClientExtensions],
      /** The ID of the OrderFill Transaction that caused this Order to be created (only provided if this Order was created automatically when another Order was filled). */
      orderFillTransactionID: TransactionID,
      /** The ID of the Order that this Order replaces (only provided if this Order replaces an existing Order). */
      replacesOrderID: Option[OrderID],
      /** The ID of the Transaction that cancels the replaced Order (only provided if this Order replaces an existing Order). */
      cancellingTransactionID: Option[TransactionID]
    ) extends Transaction(id, time, userID, accountID, batchID, requestID, `type`)
  
    /**
     * A TakeProfitOrderRejectTransaction represents the rejection of the creation of a TakeProfit Order.
     */
    case class TakeProfitOrderRejectTransaction(
      /** The Transaction’s Identifier. */
      id: TransactionID,
      /** The date/time when the Transaction was created. */
      time: DateTime,
      /** The ID of the user that initiated the creation of the Transaction. */
      userID: Int,
      /** The ID of the Account the Transaction was created for. */
      accountID: AccountID,
      /** The ID of the "batch" that the Transaction belongs to. Transactions in the same batch are applied to the Account simultaneously. */
      batchID: TransactionID,
      /** The Request ID of the Account Control Request which generated the transaction (only provided for Transactions created by a Client request) */
      requestID: RequestID,
      /** The Type of the Transaction. Always set to "TAKE_PROFIT_ORDER_REJECT" in a TakeProfitOrderRejectTransaction. */
      `type`: TransactionType = TransactionType.TAKE_PROFIT_ORDER_REJECT,
      /** The ID of the Trade to close when the price threshold is breached. */
      tradeID: TradeID,
      /** The client ID of the Trade to be closed when the price threshold is breached. */
      clientTradeID: ClientID,
      /** The price threshold specified for the TakeProfit Order. The associated Trade will be closed by a market price that is equal to or better than this threshold. */
      price: PriceValue,
      /** The time-in-force requested for the TakeProfit Order. Restricted to "GTC", "GFD" and "GTD" for TakeProfit Orders. */
      timeInForce: TimeInForce,
      /** The date/time when the TakeProfit Order will be cancelled if its timeInForce is "GTD". */
      gtdTime: DateTime,
      /** Specification of what component of a price should be used for comparison when determining if the Order should be filled. */
      triggerCondition: OrderTriggerCondition,
      /** The reason that the Take Profit Order was initiated */
      reason: TakeProfitOrderReason,
      /** Client Extensions to add to the Order (only provided if the Order is being created with client extensions). */
      clientExtensions: Option[ClientExtensions],
      /** The ID of the OrderFill Transaction that caused this Order to be created (only provided if this Order was created automatically when another Order was filled). */
      orderFillTransactionID: TransactionID,
      /** The ID of the Order that this Order was intended to replace (only provided if this Order was intended to replace an existing Order). */
      intendedReplacesOrderID: Option[OrderID],
      /** The reason that the Reject Transaction was created */
      rejectReason: TransactionRejectReason
    ) extends Transaction(id, time, userID, accountID, batchID, requestID, `type`)
  
    /**
     * A StopLossOrderTransaction represents the creation of a StopLoss Order in the user’s Account.
     */
    case class StopLossOrderTransaction(
      /** The Transaction’s Identifier. */
      id: TransactionID,
      /** The date/time when the Transaction was created. */
      time: DateTime,
      /** The ID of the user that initiated the creation of the Transaction. */
      userID: Int,
      /** The ID of the Account the Transaction was created for. */
      accountID: AccountID,
      /** The ID of the "batch" that the Transaction belongs to. Transactions in the same batch are applied to the Account simultaneously. */
      batchID: TransactionID,
      /** The Request ID of the Account Control Request which generated the transaction (only provided for Transactions created by a Client request) */
      requestID: RequestID,
      /** The Type of the Transaction. Always set to "STOP_LOSS_ORDER" in a StopLossOrderTransaction. */
      `type`: TransactionType = TransactionType.STOP_LOSS_ORDER,
      /** The ID of the Trade to close when the price threshold is breached. */
      tradeID: TradeID,
      /** The client ID of the Trade to be closed when the price threshold is breached. */
      clientTradeID: ClientID,
      /** The price threshold specified for the StopLoss Order. The associated Trade will be closed by a market price that is equal to or worse than this threshold. */
      price: PriceValue,
      /** The time-in-force requested for the StopLoss Order. Restricted to "GTC", "GFD" and "GTD" for StopLoss Orders. */
      timeInForce: TimeInForce,
      /** The date/time when the StopLoss Order will be cancelled if its timeInForce is "GTD". */
      gtdTime: DateTime,
      /** Specification of what component of a price should be used for comparison when determining if the Order should be filled. */
      triggerCondition: OrderTriggerCondition,
      /** The reason that the Stop Loss Order was initiated */
      reason: StopLossOrderReason,
      /** Client Extensions to add to the Order (only provided if the Order is being created with client extensions). */
      clientExtensions: Option[ClientExtensions],
      /** The ID of the OrderFill Transaction that caused this Order to be created (only provided if this Order was created automatically when another Order was filled). */
      orderFillTransactionID: TransactionID,
      /** The ID of the Order that this Order replaces (only provided if this Order replaces an existing Order). */
      replacesOrderID: Option[OrderID],
      /** The ID of the Transaction that cancels the replaced Order (only provided if this Order replaces an existing Order). */
      cancellingTransactionID: Option[TransactionID]
    ) extends Transaction(id, time, userID, accountID, batchID, requestID, `type`)
  
    /**
     * A StopLossOrderRejectTransaction represents the rejection of the creation of a StopLoss Order.
     */
    case class StopLossOrderRejectTransaction(
      /** The Transaction’s Identifier. */
      id: TransactionID,
      /** The date/time when the Transaction was created. */
      time: DateTime,
      /** The ID of the user that initiated the creation of the Transaction. */
      userID: Int,
      /** The ID of the Account the Transaction was created for. */
      accountID: AccountID,
      /** The ID of the "batch" that the Transaction belongs to. Transactions in the same batch are applied to the Account simultaneously. */
      batchID: TransactionID,
      /** The Request ID of the Account Control Request which generated the transaction (only provided for Transactions created by a Client request) */
      requestID: RequestID,
      /** The Type of the Transaction. Always set to "STOP_LOSS_ORDER_REJECT" in a StopLossOrderRejectTransaction. */
      `type`: TransactionType = TransactionType.STOP_LOSS_ORDER_REJECT,
      /** The ID of the Trade to close when the price threshold is breached. */
      tradeID: TradeID,
      /** The client ID of the Trade to be closed when the price threshold is breached. */
      clientTradeID: ClientID,
      /** The price threshold specified for the StopLoss Order. The associated Trade will be closed by a market price that is equal to or worse than this threshold. */
      price: PriceValue,
      /** The time-in-force requested for the StopLoss Order. Restricted to "GTC", "GFD" and "GTD" for StopLoss Orders. */
      timeInForce: TimeInForce,
      /** The date/time when the StopLoss Order will be cancelled if its timeInForce is "GTD". */
      gtdTime: DateTime,
      /** Specification of what component of a price should be used for comparison when determining if the Order should be filled. */
      triggerCondition: OrderTriggerCondition,
      /** The reason that the Stop Loss Order was initiated */
      reason: StopLossOrderReason,
      /** Client Extensions to add to the Order (only provided if the Order is being created with client extensions). */
      clientExtensions: Option[ClientExtensions],
      /** The ID of the OrderFill Transaction that caused this Order to be created (only provided if this Order was created automatically when another Order was filled). */
      orderFillTransactionID: TransactionID,
      /** The ID of the Order that this Order was intended to replace (only provided if this Order was intended to replace an existing Order). */
      intendedReplacesOrderID: Option[OrderID],
      /** The reason that the Reject Transaction was created */
      rejectReason: TransactionRejectReason
    ) extends Transaction(id, time, userID, accountID, batchID, requestID, `type`)
  
    /**
     * A TrailingStopLossOrderTransaction represents the creation of a TrailingStopLoss Order in the user’s Account.
     */
    case class TrailingStopLossOrderTransaction(
      /** The Transaction’s Identifier. */
      id: TransactionID,
      /** The date/time when the Transaction was created. */
      time: DateTime,
      /** The ID of the user that initiated the creation of the Transaction. */
      userID: Int,
      /** The ID of the Account the Transaction was created for. */
      accountID: AccountID,
      /** The ID of the "batch" that the Transaction belongs to. Transactions in the same batch are applied to the Account simultaneously. */
      batchID: TransactionID,
      /** The Request ID of the Account Control Request which generated the transaction (only provided for Transactions created by a Client request) */
      requestID: RequestID,
      /** The Type of the Transaction. Always set to "TRAILING_STOP_LOSS_ORDER" in a TrailingStopLossOrderTransaction. */
      `type`: TransactionType = TransactionType.TRAILING_STOP_LOSS_ORDER,
      /** The ID of the Trade to close when the price threshold is breached. */
      tradeID: TradeID,
      /** The client ID of the Trade to be closed when the price threshold is breached. */
      clientTradeID: ClientID,
      /** The price distance specified for the TrailingStopLoss Order. */
      distance: PriceValue,
      /** The time-in-force requested for the TrailingStopLoss Order. Restricted to "GTC", "GFD" and "GTD" for TrailingStopLoss Orders. */
      timeInForce: TimeInForce,
      /** The date/time when the StopLoss Order will be cancelled if its timeInForce is "GTD". */
      gtdTime: DateTime,
      /** Specification of what component of a price should be used for comparison when determining if the Order should be filled. */
      triggerCondition: OrderTriggerCondition,
      /** The reason that the Trailing Stop Loss Order was initiated */
      reason: TrailingStopLossOrderReason,
      /** Client Extensions to add to the Order (only provided if the Order is being created with client extensions). */
      clientExtensions: Option[ClientExtensions],
      /** The ID of the OrderFill Transaction that caused this Order to be created (only provided if this Order was created automatically when another Order was filled). */
      orderFillTransactionID: TransactionID,
      /** The ID of the Order that this Order replaces (only provided if this Order replaces an existing Order). */
      replacesOrderID: Option[OrderID],
      /** The ID of the Transaction that cancels the replaced Order (only provided if this Order replaces an existing Order). */
      cancellingTransactionID: Option[TransactionID]
    ) extends Transaction(id, time, userID, accountID, batchID, requestID, `type`)

    /**
     * A TrailingStopLossOrderRejectTransaction represents the rejection of the creation of a TrailingStopLoss Order.
     */
    case class TrailingStopLossOrderRejectTransaction(
      /** The Transaction’s Identifier. */
      id: TransactionID,
      /** The date/time when the Transaction was created. */
      time: DateTime,
      /** The ID of the user that initiated the creation of the Transaction. */
      userID: Int,
      /** The ID of the Account the Transaction was created for. */
      accountID: AccountID,
      /** The ID of the "batch" that the Transaction belongs to. Transactions in the same batch are applied to the Account simultaneously. */
      batchID: TransactionID,
      /** The Request ID of the Account Control Request which generated the transaction (only provided for Transactions created by a Client request) */
      requestID: RequestID,
      /** The Type of the Transaction. Always set to "TRAILING_STOP_LOSS_ORDER_REJECT" in a TrailingStopLossOrderRejectTransaction. */
      `type`: TransactionType = TransactionType.TRAILING_STOP_LOSS_ORDER_REJECT,
      /** The ID of the Trade to close when the price threshold is breached. */
      tradeID: TradeID,
      /** The client ID of the Trade to be closed when the price threshold is breached. */
      clientTradeID: ClientID,
      /** The price distance specified for the TrailingStopLoss Order. */
      distance: PriceValue,
      /** The time-in-force requested for the TrailingStopLoss Order. Restricted to "GTC", "GFD" and "GTD" for TrailingStopLoss Orders. */
      timeInForce: TimeInForce,
      /** The date/time when the StopLoss Order will be cancelled if its timeInForce is "GTD". */
      gtdTime: DateTime,
      /** Specification of what component of a price should be used for comparison when determining if the Order should be filled. */
      triggerCondition: OrderTriggerCondition,
      /** The reason that the Trailing Stop Loss Order was initiated */
      reason: TrailingStopLossOrderReason,
      /** Client Extensions to add to the Order (only provided if the Order is being created with client extensions). */
      clientExtensions: Option[ClientExtensions],
      /** The ID of the OrderFill Transaction that caused this Order to be created (only provided if this Order was created automatically when another Order was filled). */
      orderFillTransactionID: TransactionID,
      /** The ID of the Order that this Order was intended to replace (only provided if this Order was intended to replace an existing Order). */
      intendedReplacesOrderID: Option[OrderID],
      /** The reason that the Reject Transaction was created */
      rejectReason: TransactionRejectReason
    ) extends Transaction(id, time, userID, accountID, batchID, requestID, `type`)
  
    /**
     * An OrderFillTransaction represents the filling of an Order in the client’s Account.
     */
    case class OrderFillTransaction(
      /** The Transaction’s Identifier. */
      id: TransactionID,
      /** The date/time when the Transaction was created. */
      time: DateTime,
      /** The ID of the user that initiated the creation of the Transaction. */
      userID: Int,
      /** The ID of the Account the Transaction was created for. */
      accountID: AccountID,
      /** The ID of the "batch" that the Transaction belongs to. Transactions in the same batch are applied to the Account simultaneously. */
      batchID: TransactionID,
      /** The Request ID of the Account Control Request which generated the transaction (only provided for Transactions created by a Client request) */
      requestID: RequestID,
      /** The Type of the Transaction. Always set to "ORDER_FILL" for an OrderFillTransaction. */
      `type`: TransactionType = TransactionType.ORDER_FILL,
      /** The ID of the Order filled. */
      orderID: OrderID,
      /** The client Order ID of the Order filled (only provided if the client has assigned one). */
      clientOrderID: ClientID,
      /** The name of the filled Order’s instrument. */
      instrument: InstrumentName,
      /** The number of units filled by the Order. */
      units: Double,
      /** The average market price that the Order was filled at. */
      price: PriceValue,
      /** The reason that an Order was filled */
      reason: OrderFillReason,
      /** The profit or loss incurred when the Order was filled. */
      pl: AccountUnits,
      /** The financing paid or collected when the Order was filled. */
      financing: AccountUnits,
      /** The Account’s balance after the Order was filled. */
      accountBalance: AccountUnits,
      /** The Trade that was opened when the Order was filled (only provided if filling the Order resulted in a new Trade). */
      tradeOpened: TradeOpen,
      /** The Trades that were closed when the Order was filled (only provided if filling the Order resulted in a closing open Trades). */
      tradesClosed: Seq[TradeReduce],
      /** The Trade that was reduced when the Order was filled (only provided if filling the Order resulted in reducing an open Trade). */
      tradeReduced: TradeReduce
    ) extends Transaction(id, time, userID, accountID, batchID, requestID, `type`)
  
    /**
     * An OrderCancelTransaction represents the cancellation of an Order in the client’s Account.
     */
    case class OrderCancelTransaction(
      /** The Transaction’s Identifier. */
      id: TransactionID,
      /** The date/time when the Transaction was created. */
      time: DateTime,
      /** The ID of the user that initiated the creation of the Transaction. */
      userID: Int,
      /** The ID of the Account the Transaction was created for. */
      accountID: AccountID,
      /** The ID of the "batch" that the Transaction belongs to. Transactions in the same batch are applied to the Account simultaneously. */
      batchID: TransactionID,
      /** The Request ID of the Account Control Request which generated the transaction (only provided for Transactions created by a Client request) */
      requestID: RequestID,
      /** The Type of the Transaction. Always set to "ORDER_CANCEL" for an OrderCancelTransaction. */
      `type`: TransactionType = TransactionType.ORDER_CANCEL,
      /** The ID of the Order cancelled */
      orderID: OrderID,
      /** The client ID of the Order cancelled (only provided if the Order has a client Order ID). */
      clientOrderID: OrderID,
      /** The reason that the Order was cancelled. */
      reason: OrderCancelReason,
      /** The ID of the Order that replaced this Order (only provided if this Order was cancelled for replacement). */
      replacedByOrderID: Option[OrderID]
    ) extends Transaction(id, time, userID, accountID, batchID, requestID, `type`)
  
    /**
     * An OrderCancelRejectTransaction represents the rejection of the cancellation of an Order in the client’s Account.
     */
    case class OrderCancelRejectTransaction(
      /** The Transaction’s Identifier. */
      id: TransactionID,
      /** The date/time when the Transaction was created. */
      time: DateTime,
      /** The ID of the user that initiated the creation of the Transaction. */
      userID: Int,
      /** The ID of the Account the Transaction was created for. */
      accountID: AccountID,
      /** The ID of the "batch" that the Transaction belongs to. Transactions in the same batch are applied to the Account simultaneously. */
      batchID: TransactionID,
      /** The Request ID of the Account Control Request which generated the transaction (only provided for Transactions created by a Client request) */
      requestID: RequestID,
      /** The Type of the Transaction. Always set to "ORDER_CANCEL_REJECT" for an OrderCancelRejectTransaction. */
      `type`: TransactionType = TransactionType.ORDER_CANCEL_REJECT,
      /** The ID of the Order intended to be cancelled */
      orderID: OrderID,
      /** The client ID of the Order intended to be cancelled (only provided if the Order has a client Order ID). */
      clientOrderID: OrderID,
      /** The reason that the Order was to be cancelled. */
      reason: OrderCancelReason,
      /** The reason that the Reject Transaction was created */
      rejectReason: TransactionRejectReason
    ) extends Transaction(id, time, userID, accountID, batchID, requestID, `type`)
  
    /**
     * A OrderClientExtensionsModifyTransaction represents the modification of an Order’s Client Extensions.
     */
    case class OrderClientExtensionsModifyTransaction(
      /** The Transaction’s Identifier. */
      id: TransactionID,
      /** The date/time when the Transaction was created. */
      time: DateTime,
      /** The ID of the user that initiated the creation of the Transaction. */
      userID: Int,
      /** The ID of the Account the Transaction was created for. */
      accountID: AccountID,
      /** The ID of the "batch" that the Transaction belongs to. Transactions in the same batch are applied to the Account simultaneously. */
      batchID: TransactionID,
      /** The Request ID of the Account Control Request which generated the transaction (only provided for Transactions created by a Client request) */
      requestID: RequestID,
      /** The Type of the Transaction. Always set to "ORDER_CLIENT_EXTENSIONS_MODIFY" for a OrderClienteExtensionsModifyTransaction. */
      `type`: TransactionType = TransactionType.ORDER_CLIENT_EXTENSIONS_MODIFY,
      /** The ID of the Order who’s client extensions are to be modified. */
      orderID: OrderID,
      /** The original Client ID of the Order who’s client extensions are to be modified. */
      clientOrderID: ClientID,
      /** The new Client Extensions for the Order. */
      clientExtensionsModify: ClientExtensions,
      /** The new Client Extensions for the Order’s Trade on fill. */
      tradeClientExtensionsModify: ClientExtensions
    ) extends Transaction(id, time, userID, accountID, batchID, requestID, `type`)
  
    /**
     * A OrderClientExtensionsModifyRejectTransaction represents the rejection of the modification of an Order’s Client Extensions.
     */
    case class OrderClientExtensionsModifyRejectTransaction(
      /** The Transaction’s Identifier. */
      id: TransactionID,
      /** The date/time when the Transaction was created. */
      time: DateTime,
      /** The ID of the user that initiated the creation of the Transaction. */
      userID: Int,
      /** The ID of the Account the Transaction was created for. */
      accountID: AccountID,
      /** The ID of the "batch" that the Transaction belongs to. Transactions in the same batch are applied to the Account simultaneously. */
      batchID: TransactionID,
      /** The Request ID of the Account Control Request which generated the transaction (only provided for Transactions created by a Client request) */
      requestID: RequestID,
      /** The Type of the Transaction. Always set to "ORDER_CLIENT_EXTENSIONS_MODIFY_REJECT" for a OrderClientExtensionsModifyRejectTransaction. */
      `type`: TransactionType = TransactionType.ORDER_CLIENT_EXTENSIONS_MODIFY_REJECT,
      /** The ID of the Order who’s client extensions are to be modified. */
      orderID: OrderID,
      /** The original Client ID of the Order who’s client extensions are to be modified. */
      clientOrderID: ClientID,
      /** The new Client Extensions for the Order. */
      clientExtensionsModify: ClientExtensions,
      /** The new Client Extensions for the Order’s Trade on fill. */
      tradeClientExtensionsModify: ClientExtensions,
      /** The reason that the Reject Transaction was created */
      rejectReason: TransactionRejectReason
    ) extends Transaction(id, time, userID, accountID, batchID, requestID, `type`)
  
    /**
     * A TradeClientExtensionsModifyTransaction represents the modification of a Trade’s Client Extensions.
     */
    case class TradeClientExtensionsModifyTransaction(
      /** The Transaction’s Identifier. */
      id: TransactionID,
      /** The date/time when the Transaction was created. */
      time: DateTime,
      /** The ID of the user that initiated the creation of the Transaction. */
      userID: Int,
      /** The ID of the Account the Transaction was created for. */
      accountID: AccountID,
      /** The ID of the "batch" that the Transaction belongs to. Transactions in the same batch are applied to the Account simultaneously. */
      batchID: TransactionID,
      /** The Request ID of the Account Control Request which generated the transaction (only provided for Transactions created by a Client request) */
      requestID: RequestID,
      /** The Type of the Transaction. Always set to "TRADE_CLIENT_EXTENSIONS_MODIFY" for a TradeClientExtensionsModifyTransaction. */
      `type`: TransactionType = TransactionType.TRADE_CLIENT_EXTENSIONS_MODIFY,
      /** The ID of the Trade who’s client extensions are to be modified. */
      tradeID: TradeID,
      /** The original Client ID of the Trade who’s client extensions are to be modified. */
      clientTradeID: ClientID,
      /** The new Client Extensions for the Trade. */
      tradeClientExtensionsModify: ClientExtensions
    ) extends Transaction(id, time, userID, accountID, batchID, requestID, `type`)
  
    /**
     * A TradeClientExtensionsModifyRejectTransaction represents the rejection of the modification of a Trade’s Client Extensions.
     */
    case class TradeClientExtensionsModifyRejectTransaction(
      /** The Transaction’s Identifier. */
      id: TransactionID,
      /** The date/time when the Transaction was created. */
      time: DateTime,
      /** The ID of the user that initiated the creation of the Transaction. */
      userID: Int,
      /** The ID of the Account the Transaction was created for. */
      accountID: AccountID,
      /** The ID of the "batch" that the Transaction belongs to. Transactions in the same batch are applied to the Account simultaneously. */
      batchID: TransactionID,
      /** The Request ID of the Account Control Request which generated the transaction (only provided for Transactions created by a Client request) */
      requestID: RequestID,
      /** The Type of the Transaction. Always set to "TRADE_CLIENT_EXTENSIONS_MODIFY_REJECT" for a TradeClientExtensionsModifyRejectTransaction. */
      `type`: TransactionType = TransactionType.TRADE_CLIENT_EXTENSIONS_MODIFY_REJECT,
      /** The ID of the Trade who’s client extensions are to be modified. */
      tradeID: TradeID,
      /** The original Client ID of the Trade who’s client extensions are to be modified. */
      clientTradeID: ClientID,
      /** The new Client Extensions for the Trade. */
      tradeClientExtensionsModify: ClientExtensions,
      /** The reason that the Reject Transaction was created */
      rejectReason: TransactionRejectReason
    ) extends Transaction(id, time, userID, accountID, batchID, requestID, `type`)
  
    /**
     * A MarginCallEnterTransaction is created when an Account enters the margin call state.
     */
    case class MarginCallEnterTransaction(
      /** The Transaction’s Identifier. */
      id: TransactionID,
      /** The date/time when the Transaction was created. */
      time: DateTime,
      /** The ID of the user that initiated the creation of the Transaction. */
      userID: Int,
      /** The ID of the Account the Transaction was created for. */
      accountID: AccountID,
      /** The ID of the "batch" that the Transaction belongs to. Transactions in the same batch are applied to the Account simultaneously. */
      batchID: TransactionID,
      /** The Request ID of the Account Control Request which generated the transaction (only provided for Transactions created by a Client request) */
      requestID: RequestID,
      /** The Type of the Transaction. Always set to "MARGIN_CALL_ENTER" for an MarginCallEnterTransaction. */
      `type`: TransactionType = TransactionType.MARGIN_CALL_ENTER
    ) extends Transaction(id, time, userID, accountID, batchID, requestID, `type`)
  
    /**
     * A MarginCallExtendTransaction is created when the margin call state for an Account has been extended.
     */
    case class MarginCallExtendTransaction(
      /** The Transaction’s Identifier. */
      id: TransactionID,
      /** The date/time when the Transaction was created. */
      time: DateTime,
      /** The ID of the user that initiated the creation of the Transaction. */
      userID: Int,
      /** The ID of the Account the Transaction was created for. */
      accountID: AccountID,
      /** The ID of the "batch" that the Transaction belongs to. Transactions in the same batch are applied to the Account simultaneously. */
      batchID: TransactionID,
      /** The Request ID of the Account Control Request which generated the transaction (only provided for Transactions created by a Client request) */
      requestID: RequestID,
      /** The Type of the Transaction. Always set to "MARGIN_CALL_EXTEND" for an MarginCallExtendTransaction. */
      `type`: TransactionType = TransactionType.MARGIN_CALL_EXTEND,
      /** The number of the extensions to the Account’s current margin call that have been applied. This value will be set to 1 for the first MarginCallExtend Transaction */
      extensionNumber: Int
    ) extends Transaction(id, time, userID, accountID, batchID, requestID, `type`)
  
    /**
     * A MarginCallExitnterTransaction is created when an Account leaves the margin call state.
     */
    case class MarginCallExitTransaction(
      /** The Transaction’s Identifier. */
      id: TransactionID,
      /** The date/time when the Transaction was created. */
      time: DateTime,
      /** The ID of the user that initiated the creation of the Transaction. */
      userID: Int,
      /** The ID of the Account the Transaction was created for. */
      accountID: AccountID,
      /** The ID of the "batch" that the Transaction belongs to. Transactions in the same batch are applied to the Account simultaneously. */
      batchID: TransactionID,
      /** The Request ID of the Account Control Request which generated the transaction (only provided for Transactions created by a Client request) */
      requestID: RequestID,
      /** The Type of the Transaction. Always set to "MARGIN_CALL_EXIT" for an MarginCallExitTransaction. */
      `type`: TransactionType = TransactionType.MARGIN_CALL_EXIT
    ) extends Transaction(id, time, userID, accountID, batchID, requestID, `type`)
  
    /**
     * A DelayedTradeClosure Transaction is created administratively to indicate open trades that should have been closed but weren’t because the open trades’ instruments were untradeable at the time. Open trades listed in this transaction will be closed once their respective instruments become tradeable.
     */
    case class DelayedTradeClosureTransaction(
      /** The Transaction’s Identifier. */
      id: TransactionID,
      /** The date/time when the Transaction was created. */
      time: DateTime,
      /** The ID of the user that initiated the creation of the Transaction. */
      userID: Int,
      /** The ID of the Account the Transaction was created for. */
      accountID: AccountID,
      /** The ID of the "batch" that the Transaction belongs to. Transactions in the same batch are applied to the Account simultaneously. */
      batchID: TransactionID,
      /** The Request ID of the Account Control Request which generated the transaction (only provided for Transactions created by a Client request) */
      requestID: RequestID,
      /** The Type of the Transaction. Always set to "DELAYED_TRADE_CLOSURE" for an DelayedTradeClosureTransaction. */
      `type`: TransactionType = TransactionType.DELAYED_TRADE_CLOSURE,
      /** The reason for the delayed trade closure */
      reason: MarketOrderReason,
      /** List of Trade ID’s identifying the open trades that will be closed when their respective instruments become tradeable */
      tradeIDs: TradeID
    ) extends Transaction(id, time, userID, accountID, batchID, requestID, `type`)
  
    /**
     * A DailyFinancingTransaction represents the daily payment/collection of financing for an Account.
     */
    case class DailyFinancingTransaction(
      /** The Transaction’s Identifier. */
      id: TransactionID,
      /** The date/time when the Transaction was created. */
      time: DateTime,
      /** The ID of the user that initiated the creation of the Transaction. */
      userID: Int,
      /** The ID of the Account the Transaction was created for. */
      accountID: AccountID,
      /** The ID of the "batch" that the Transaction belongs to. Transactions in the same batch are applied to the Account simultaneously. */
      batchID: TransactionID,
      /** The Request ID of the Account Control Request which generated the transaction (only provided for Transactions created by a Client request) */
      requestID: RequestID,
      /** The Type of the Transaction. Always set to "DAILY_FINANCING" for a DailyFinancingTransaction. */
      `type`: TransactionType = TransactionType.DAILY_FINANCING,
      /** The amount of financing paid/collected for the Account. */
      financing: AccountUnits,
      /** The Account’s balance after daily financing. */
      accountBalance: AccountUnits,
      /** The account financing mode at the time of the daily financing. */
      accountFinancingMode: AccountFinancingMode,
      /** The financing paid/collected for each Position in the Account. */
      positionFinancings: Seq[PositionFinancing]
    ) extends Transaction(id, time, userID, accountID, batchID, requestID, `type`)
  
    /**
     * A ResetResettablePLTransaction represents the resetting of the Account’s resettable PL counters.
     */
    case class ResetResettablePLTransaction(
      /** The Transaction’s Identifier. */
      id: TransactionID,
      /** The date/time when the Transaction was created. */
      time: DateTime,
      /** The ID of the user that initiated the creation of the Transaction. */
      userID: Int,
      /** The ID of the Account the Transaction was created for. */
      accountID: AccountID,
      /** The ID of the "batch" that the Transaction belongs to. Transactions in the same batch are applied to the Account simultaneously. */
      batchID: TransactionID,
      /** The Request ID of the Account Control Request which generated the transaction (only provided for Transactions created by a Client request) */
      requestID: RequestID,
      /** The Type of the Transaction. Always set to "RESET_RESETTABLE_PL" for a ResetResettablePLTransaction. */
      `type`: TransactionType = TransactionType.RESET_RESETTABLE_PL
    ) extends Transaction(id, time, userID, accountID, batchID, requestID, `type`)

    /**
     * The unique Transaction identifier within each Account.
     * Format: String representation of the numerical OANDA-assigned TransactionID
     * Example: 1523
     */
    case class TransactionID(value: String) extends AnyVal
    /**
     * The possible types of a Transaction
     */
    object TransactionType extends Enumeration {
      type TransactionType = Value
    
      /** Account Create Transaction */
      val CREATE = Value
    
      /** Market if Touched Order Reject Transaction */
      val MARKET_IF_TOUCHED_ORDER_REJECT = Value
    
      /** Order Client Extensions Modify Reject Transaction */
      val ORDER_CLIENT_EXTENSIONS_MODIFY_REJECT = Value
    
      /** Limit Order Reject Transaction */
      val LIMIT_ORDER_REJECT = Value
    
      /** Take Profit Order Reject Transaction */
      val TAKE_PROFIT_ORDER_REJECT = Value
    
      /** Limit Order Transaction */
      val LIMIT_ORDER = Value
    
      /** Trade Client Extensions Modify Transaction */
      val TRADE_CLIENT_EXTENSIONS_MODIFY = Value
    
      /** Trade Client Extensions Modify Reject Transaction */
      val TRADE_CLIENT_EXTENSIONS_MODIFY_REJECT = Value
    
      /** Transfer Funds Reject Transaction */
      val TRANSFER_FUNDS_REJECT = Value
    
      /** Reset Resettable PL Transaction */
      val RESET_RESETTABLE_PL = Value
    
      /** Order Cancel Transaction */
      val ORDER_CANCEL = Value
    
      /** Stop Loss Order Reject Transaction */
      val STOP_LOSS_ORDER_REJECT = Value
    
      /** Client Configuration Transaction */
      val CLIENT_CONFIGURE = Value
    
      /** Margin Call Extend Transaction */
      val MARGIN_CALL_EXTEND = Value
    
      /** Take Profit Order Transaction */
      val TAKE_PROFIT_ORDER = Value
    
      /** Order Client Extensions Modify Transaction */
      val ORDER_CLIENT_EXTENSIONS_MODIFY = Value
    
      /** Daily Financing Transaction */
      val DAILY_FINANCING = Value
    
      /** Order Fill Transaction */
      val ORDER_FILL = Value
    
      /** Account Close Transaction */
      val CLOSE = Value
    
      /** Delayed Trade Closure Transaction */
      val DELAYED_TRADE_CLOSURE = Value
    
      /** Market Order Transaction */
      val MARKET_ORDER = Value
    
      /** Client Configuration Reject Transaction */
      val CLIENT_CONFIGURE_REJECT = Value
    
      /** Margin Call Enter Transaction */
      val MARGIN_CALL_ENTER = Value
    
      /** Order Cancel Reject Transaction */
      val ORDER_CANCEL_REJECT = Value
    
      /** Market Order Reject Transaction */
      val MARKET_ORDER_REJECT = Value
    
      /** Margin Call Exit Transaction */
      val MARGIN_CALL_EXIT = Value
    
      /** Trailing Stop Loss Order Reject Transaction */
      val TRAILING_STOP_LOSS_ORDER_REJECT = Value
    
      /** Stop Loss Order Transaction */
      val STOP_LOSS_ORDER = Value
    
      /** Stop Order Reject Transaction */
      val STOP_ORDER_REJECT = Value
    
      /** Account Reopen Transaction */
      val REOPEN = Value
    
      /** Market if Touched Order Transaction */
      val MARKET_IF_TOUCHED_ORDER = Value
    
      /** Transfer Funds Transaction */
      val TRANSFER_FUNDS = Value
    
      /** Stop Order Transaction */
      val STOP_ORDER = Value
    
      /** Trailing Stop Loss Order Transaction */
      val TRAILING_STOP_LOSS_ORDER = Value
    }

    /**
     * The reason that an Account is being funded.
     */
    object FundingReason extends Enumeration {
      type FundingReason = Value
    
      /** Funds are being transfered between two Accounts. */
      val ACCOUNT_TRANSFER = Value
    
      /** The client has initiated a funds transfer */
      val CLIENT_FUNDING = Value
    
      /** Funds are being transfered as part of a Site migration */
      val SITE_MIGRATION = Value
    
      /** Funds are being transfered as part of a Division migration */
      val DIVISION_MIGRATION = Value
    
      /** Funds are being transfered as part of an Account adjustment */
      val ADJUSTMENT = Value
    }

    /**
     * The reason that the Market Order was created
     */
    object MarketOrderReason extends Enumeration {
      type MarketOrderReason = Value
    
      /** The Market Order was created to close a Position at the request of a client */
      val POSITION_CLOSEOUT = Value
    
      /** The Market Order was created as part of a Margin Closeout */
      val MARGIN_CLOSEOUT = Value
    
      /** The Market Order was created to close a trade marked for delayed closure */
      val DELAYED_TRADE_CLOSE = Value
    
      /** The Market Order was created at the request of a client */
      val CLIENT_ORDER = Value
    
      /** The Market Order was created to close a Trade at the request of a client */
      val TRADE_CLOSE = Value
    }

    /**
     * The reason that the Limit Order was initiated
     */
    object LimitOrderReason extends Enumeration {
      type LimitOrderReason = Value
    
      /** The Limit Order was initiated at the request of a client */
      val CLIENT_ORDER = Value
    
      /** The Limit Order was initiated as a replacement for an existing Order */
      val REPLACEMENT = Value
    }

    /**
     * The reason that the Stop Order was initiated
     */
    object StopOrderReason extends Enumeration {
      type StopOrderReason = Value
    
      /** The Stop Order was initiated at the request of a client */
      val CLIENT_ORDER = Value
    
      /** The Stop Order was initiated as a replacement for an existing Order */
      val REPLACEMENT = Value
    }

    /**
     * The reason that the Market-if-touched Order was initiated
     */
    object MarketIfTouchedOrderReason extends Enumeration {
      type MarketIfTouchedOrderReason = Value
    
      /** The Market-if-touched Order was initiated at the request of a client */
      val CLIENT_ORDER = Value
    
      /** The Market-if-touched Order was initiated as a replacement for an existing Order */
      val REPLACEMENT = Value
    }

    /**
     * The reason that the Take Profit Order was initiated
     */
    object TakeProfitOrderReason extends Enumeration {
      type TakeProfitOrderReason = Value
    
      /** The Take Profit Order was initiated at the request of a client */
      val CLIENT_ORDER = Value
    
      /** The Take Profit Order was initiated as a replacement for an existing Order */
      val REPLACEMENT = Value
    
      /** The Take Profit Order was initiated automatically when an Order was filled that opened a new Trade requiring a Take Profit Order. */
      val ON_FILL = Value
    }

    /**
     * The reason that the Stop Loss Order was initiated
     */
    object StopLossOrderReason extends Enumeration {
      type StopLossOrderReason = Value
    
      /** The Stop Loss Order was initiated at the request of a client */
      val CLIENT_ORDER = Value
    
      /** The Stop Loss Order was initiated as a replacement for an existing Order */
      val REPLACEMENT = Value
    
      /** The Stop Loss Order was initiated automatically when an Order was filled that opened a new Trade requiring a Stop Loss Order. */
      val ON_FILL = Value
    }

    /**
     * The reason that the Trailing Stop Loss Order was initiated
     */
    object TrailingStopLossOrderReason extends Enumeration {
      type TrailingStopLossOrderReason = Value
    
      /** The Trailing Stop Loss Order was initiated at the request of a client */
      val CLIENT_ORDER = Value
    
      /** The Trailing Stop Loss Order was initiated as a replacement for an existing Order */
      val REPLACEMENT = Value
    
      /** The Trailing Stop Loss Order was initiated automatically when an Order was filled that opened a new Trade requiring a Trailing Stop Loss Order. */
      val ON_FILL = Value
    }

    /**
     * The reason that an Order was filled
     */
    object OrderFillReason extends Enumeration {
      type OrderFillReason = Value
    
      /** The Order filled was a Limit Order */
      val LIMIT_ORDER = Value
    
      /** The Order filled was a Market Order used to explicitly close a Position */
      val MARKET_ORDER_POSITION_CLOSEOUT = Value
    
      /** The Order filled was a Take Profit Order */
      val TAKE_PROFIT_ORDER = Value
    
      /** The Order filled was a Market Order used for a delayed Trade close */
      val MARKET_ORDER_DELAYED_TRADE_CLOSE = Value
    
      /** The Order filled was a Market Order */
      val MARKET_ORDER = Value
    
      /** The Order filled was a Market Order used to explicitly close a Trade */
      val MARKET_ORDER_TRADE_CLOSE = Value
    
      /** The Order filled was a Market Order used for a Margin Closeout */
      val MARKET_ORDER_MARGIN_CLOSEOUT = Value
    
      /** The Order filled was a Stop Loss Order */
      val STOP_LOSS_ORDER = Value
    
      /** The Order filled was a Market-if-touched Order */
      val MARKET_IF_TOUCHED_ORDER = Value
    
      /** The Order filled was a Stop Order */
      val STOP_ORDER = Value
    
      /** The Order filled was a Trailing Stop Loss Order */
      val TRAILING_STOP_LOSS_ORDER = Value
    }

    /**
     * The reason that an Order was cancelled.
     */
    object OrderCancelReason extends Enumeration {
      type OrderCancelReason = Value
    
      /** Filling the Order wasn’t possible because the Account had insufficient margin. */
      val INSUFFICIENT_MARGIN = Value
    
      /** Filling the Order would have violated the Order’s price bound. */
      val BOUNDS_VIOLATION = Value
    
      /** Filling the Order would have resulted in the creation of a Stop Loss Order with a GTD time in the past. */
      val STOP_LOSS_ON_FILL_GTD_TIMESTAMP_IN_PAST = Value
    
      /** Filling the Order would have resulted in the creation of a Trailing Stop Loss Order with a GTD time in the past. */
      val TRAILING_STOP_LOSS_ON_FILL_GTD_TIMESTAMP_IN_PAST = Value
    
      /** The Order was cancelled explicitly at the request of the client. */
      val CLIENT_REQUEST = Value
    
      /** Filling the Order would have resulted in the creation of a Take Profit Order with a client Order ID that is already in use. */
      val TAKE_PROFIT_ON_FILL_CLIENT_ORDER_ID_ALREADY_EXISTS = Value
    
      /** Filling the Order wasn’t possible because enough liquidity available. */
      val INSUFFICIENT_LIQUIDITY = Value
    
      /** Filling the Order would have resulted in exceeding the number of pending Orders allowed for the Account. */
      val PENDING_ORDERS_ALLOWED_EXCEEDED = Value
    
      /** Filling the Order would have resulted in a a FIFO violation. */
      val FIFO_VIOLATION = Value
    
      /** Filling the Order would have resulted in the creation of a Stop Loss Order with a client Order ID that is already in use. */
      val STOP_LOSS_ON_FILL_CLIENT_ORDER_ID_ALREADY_EXISTS = Value
    
      /** Filling the Order would result in the creation of a new Open Trade with a client Trade ID already in use. */
      val CLIENT_TRADE_ID_ALREADY_EXISTS = Value
    
      /** The Order was cancelled because at the time of filling the account was locked. */
      val ACCOUNT_LOCKED = Value
    
      /** Filling the Order would result in the creation of a Stop Loss Order that would have been filled immediately, closing the new Trade at a loss. */
      val STOP_LOSS_ON_FILL_LOSS = Value
    
      /** Filling the Order would have resulted in the Account’s maximum position size limit being exceeded for the Order’s instrument. */
      val POSITION_SIZE_EXCEEDED = Value
    
      /** The Order is linked to an open Trade that was closed. */
      val LINKED_TRADE_CLOSED = Value
    
      /** Filling the Order would cause the maximum open trades allowed for the Account to be exceeded. */
      val OPEN_TRADES_ALLOWED_EXCEEDED = Value
    
      /** Filling the Order would result in the creation of a Take Profit Order that would have been filled immediately, closing the new Trade at a loss. */
      val TAKE_PROFIT_ON_FILL_LOSS = Value
    
      /** Filling the Order was not possible because the Account is locked for filling Orders. */
      val ACCOUNT_ORDER_FILL_LOCKED = Value
    
      /** The Order cancelled because it is being migrated to another account. */
      val MIGRATION = Value
    
      /** Filling the Order would have resulted in the creation of a Trailing Stop Loss Order with a client Order ID that is already in use. */
      val TRAILING_STOP_LOSS_ON_FILL_CLIENT_ORDER_ID_ALREADY_EXISTS = Value
    
      /** The time in force specified for this order has passed. */
      val TIME_IN_FORCE_EXPIRED = Value
    
      /** The Order was cancelled because at the time of filling, an unexpected internal server error occurred. */
      val INTERNAL_SERVER_ERROR = Value
    
      /** The order was to be filled, however the account is configured to not allow new positions to be created. */
      val ACCOUNT_NEW_POSITIONS_LOCKED = Value
    
      /** Filling the Order wasn’t possible because it required the creation of a dependent Order and the Account is locked for Order creation. */
      val ACCOUNT_ORDER_CREATION_LOCKED = Value
    
      /** The Order was cancelled for replacement at the request of the client. */
      val CLIENT_REQUEST_REPLACED = Value
    
      /** Closing out a position wasn’t fully possible. */
      val POSITION_CLOSEOUT_FAILED = Value
    
      /** Filling the Order wasn’t possible because the Order’s instrument was halted. */
      val MARKET_HALTED = Value
    
      /** Filling the Order would have resulted in the creation of a Take Profit Order with a GTD time in the past. */
      val TAKE_PROFIT_ON_FILL_GTD_TIMESTAMP_IN_PAST = Value
    
      /** Filling the Order would result in the creation of a Take Profit Loss Order that would close the new Trade at a loss when filled. */
      val LOSING_TAKE_PROFIT = Value
    }

    /**
     * A client-provided identifier, used by clients to refer to their Orders or Trades with an identifier that they have provided.
     * Example: my_order_id
     */
    case class ClientID(value: String) extends AnyVal

    /**
     * A client-provided tag that can contain any data and may be assigned to their Orders or Trades. Tags are typically used to associate groups of Trades and/or Orders together.
     * Example: client_tag_1
     */
    case class ClientTag(value: String) extends AnyVal

    /**
     * A client-provided comment that can contain any data and may be assigned to their Orders or Trades. Comments are typically used to provide extra context or meaning to an Order or Trade.
     * Example: This is a client comment
     */
    case class ClientComment(value: String) extends AnyVal
  
    /**
     * A ClientExtensions object allows a client to attach a clientID, tag and comment to Orders and Trades in their Account. Do not set, modify, or delete this field if your account is associated with MT4.
     */
    case class ClientExtensions(
      /** The Client ID of the Order/Trade */
      id: ClientID,
      /** A tag associated with the Order/Trade */
      tag: ClientTag,
      /** A comment associated with the Order/Trade */
      comment: Option[ClientComment]
    )
  
    /**
     * TakeProfitDetails specifies the details of a Take Profit Order to be created on behalf of a client. This may happen when an Order is filled that opens a Trade requiring a Take Profit, or when a Trade’s dependent Take Profit Order is modified directly through the Trade.
     */
    case class TakeProfitDetails(
      /** The price that the Take Profit Order will be triggered at. */
      price: PriceValue,
      /** The time in force for the created Take Profit Order. This may only be GTC, GTD or GFD. */
      timeInForce: TimeInForce,
      /** The date when the Take Profit Order will be cancelled on if timeInForce is GTD. */
      gtdTime: DateTime,
      /** The Client Extensions to add to the Take Profit Order when created. */
      clientExtensions: Option[ClientExtensions]
    )
  
    /**
     * StopLossDetails specifies the details of a Stop Loss Order to be created on behalf of a client. This may happen when an Order is filled that opens a Trade requiring a Stop Loss, or when a Trade’s dependent Stop Loss Order is modified directly through the Trade.
     */
    case class StopLossDetails(
      /** The price that the Stop Loss Order will be triggered at. */
      price: PriceValue,
      /** The time in force for the created Stop Loss Order. This may only be GTC, GTD or GFD. */
      timeInForce: TimeInForce,
      /** The date when the Stop Loss Order will be cancelled on if timeInForce is GTD. */
      gtdTime: DateTime,
      /** The Client Extensions to add to the Stop Loss Order when created. */
      clientExtensions: Option[ClientExtensions]
    )
  
    /**
     * TrailingStopLossDetails specifies the details of a Trailing Stop Loss Order to be created on behalf of a client. This may happen when an Order is filled that opens a Trade requiring a Trailing Stop Loss, or when a Trade’s dependent Trailing Stop Loss Order is modified directly through the Trade.
     */
    case class TrailingStopLossDetails(
      /** The distance (in price units) from the Trade’s fill price that the Trailing Stop Loss Order will be triggered at. */
      distance: PriceValue,
      /** The time in force for the created Trailing Stop Loss Order. This may only be GTC, GTD or GFD. */
      timeInForce: TimeInForce,
      /** The date when the Trailing Stop Loss Order will be cancelled on if timeInForce is GTD. */
      gtdTime: DateTime,
      /** The Client Extensions to add to the Trailing Stop Loss Order when created. */
      clientExtensions: Option[ClientExtensions]
    )
  
    /**
     * A TradeOpen object represents a Trade for an instrument that was opened in an Account. It is found embedded in Transactions that affect the position of an instrument in the Account, specifically the OrderFill Transaction.
     */
    case class TradeOpen(
      /** The ID of the Trade that was opened */
      tradeID: TradeID,
      /** The number of units opened by the Trade */
      units: Double,
      /** The client extensions for the newly opened Trade */
      clientExtensions: Option[ClientExtensions]
    )
  
    /**
     * A TradeReduce object represents a Trade for an instrument that was reduced (either partially or fully) in an Account. It is found embedded in Transactions that affect the position of an instrument in the account, specifically the OrderFill Transaction.
     */
    case class TradeReduce(
      /** The ID of the Trade that was reduced or closed */
      tradeID: TradeID,
      /** The number of units that the Trade was reduced by */
      units: Double,
      /** The PL realized when reducing the Trade */
      realizedPL: AccountUnits,
      /** The financing paid/collected when reducing the Trade */
      financing: AccountUnits
    )
  
    /**
     * A MarketOrderTradeClose specifies the extensions to a Market Order that has been created specifically to close a Trade.
     */
    case class MarketOrderTradeClose(
      /** The ID of the Trade requested to be closed */
      tradeID: TradeID,
      /** The client ID of the Trade requested to be closed */
      clientTradeID: String,
      /** Indication of how much of the Trade to close. Either "ALL", or a DecimalNumber reflection a partial close of the Trade. */
      units: String
    )
  
    /**
     * Details for the Market Order extensions specific to a Market Order placed that is part of a Market Order Margin Closeout in a client’s account
     */
    case class MarketOrderMarginCloseout(
      /** The reason the Market Order was created to perform a margin closeout */
      reason: MarketOrderMarginCloseoutReason
    )
    /**
     * The reason that the Market Order was created to perform a margin closeout
     */
    object MarketOrderMarginCloseoutReason extends Enumeration {
      type MarketOrderMarginCloseoutReason = Value
    
      /** Trade closures resulted from violating OANDA’s margin policy */
      val MARGIN_CHECK_VIOLATION = Value
    
      /** Trade closures came from a margin closeout event resulting from regulatory conditions placed on the Account’s margin call */
      val REGULATORY_MARGIN_CALL_VIOLATION = Value
    }
  
    /**
     * Details for the Market Order extensions specific to a Market Order placed with the intent of fully closing a specific open trade that should have already been closed but wasn’t due to halted market conditions
     */
    case class MarketOrderDelayedTradeClose(
      /** The ID of the Trade being closed */
      tradeID: TradeID,
      /** The Client ID of the Trade being closed */
      clientTradeID: TradeID,
      /** The Transaction ID of the DelayedTradeClosure transaction to which this Delayed Trade Close belongs to */
      sourceTransactionID: TransactionID
    )
  
    /**
     * A MarketOrderPositionCloseout specifies the extensions to a Market Order when it has been created to closeout a specific Position.
     */
    case class MarketOrderPositionCloseout(
      /** The instrument of the Position being closed out. */
      instrument: InstrumentName,
      /** Indication of how much of the Position to close. Either "ALL", or a DecimalNumber reflection a partial close of the Trade. The DecimalNumber must always be positive, and represent a number that doesn’t exceed the absolute size of the Position. */
      units: String
    )
  
    /**
     * A VWAP Receipt provides a record of how the price for an Order fill is constructed. If the Order is filled with multiple buckets in a depth of market, each bucket will be represented with a VWAP Receipt.
     */
    case class VWAPReceipt(
      /** The number of units filled */
      units: Double,
      /** The price at which the units were filled */
      price: PriceValue
    )
  
    /**
     * A LiquidityRegenerationSchedule indicates how liquidity that is used when filling an Order for an instrument is regenerated following the fill. A liquidity regeneration schedule will be in effect until the timestamp of its final step, but may be replaced by a schedule created for an Order of the same instrument that is filled while it is still in effect.
     */
    case class LiquidityRegenerationSchedule(
      /** The steps in the Liquidity Regeneration Schedule */
      steps: Seq[LiquidityRegenerationScheduleStep]
    )
  
    /**
     * A liquidity regeneration schedule Step indicates the amount of bid and ask liquidity that is used by the Account at a certain time. These amounts will only change at the timestamp of the following step.
     */
    case class LiquidityRegenerationScheduleStep(
      /** The timestamp of the schedule step. */
      timestamp: DateTime,
      /** The amount of bid liquidity used at this step in the schedule. */
      bidLiquidityUsed: Double,
      /** The amount of ask liquidity used at this step in the schedule. */
      askLiquidityUsed: Double
    )
  
    /**
     * OpenTradeFinancing is used to pay/collect daily financing charge for an open Trade within an Account
     */
    case class OpenTradeFinancing(
      /** The ID of the Trade that financing is being paid/collected for. */
      tradeID: TradeID,
      /** The amount of financing paid/collected for the Trade. */
      financing: AccountUnits
    )
  
    /**
     * OpenTradeFinancing is used to pay/collect daily financing charge for a Position within an Account
     */
    case class PositionFinancing(
      /** The instrument of the Position that financing is being paid/collected for. */
      instrumentID: InstrumentName,
      /** The amount of financing paid/collected for the Position. */
      financing: AccountUnits,
      /** The financing paid/collecte for each open Trade within the Position. */
      openTradeFinancings: Seq[OpenTradeFinancing]
    )

    /**
     * The request identifier. Used by administrators to refer to a client’s request.
     */
    case class RequestID(value: String) extends AnyVal

    /**
     * The reason that a Transaction was rejected.
     */
    object TransactionRejectReason extends Enumeration {
      type TransactionRejectReason = Value
    
      /** The Take Profit on fill specified contains a price with more precision than is allowed by the Order’s instrument */
      val TAKE_PROFIT_ON_FILL_PRICE_PRECISION_EXCEEDED = Value
    
      /** Multiple Orders on fill share the same client Order ID */
      val ORDERS_ON_FILL_DUPLICATE_CLIENT_ORDER_IDS = Value
    
      /** The OrderFillPositionAction field has not been specified */
      val ORDER_PARTIAL_FILL_OPTION_MISSING = Value
    
      /** The Take Profit on fill specifies a GTD TimeInForce but does not provide a GTD timestamp */
      val TAKE_PROFIT_ON_FILL_GTD_TIMESTAMP_MISSING = Value
    
      /** The Position requested to be closed out does not exist */
      val CLOSEOUT_POSITION_DOESNT_EXIST = Value
    
      /** The client Order ID specified is invalid */
      val CLIENT_ORDER_ID_INVALID = Value
    
      /** The markup group ID provided is invalid */
      val MARKUP_GROUP_ID_INVALID = Value
    
      /** The Stop Loss on fill specifies an invalid price */
      val STOP_LOSS_ON_FILL_PRICE_INVALID = Value
    
      /** The Take Profit on fill specifies an invalid TimeInForce */
      val TAKE_PROFIT_ON_FILL_TIME_IN_FORCE_INVALID = Value
    
      /** The TimeInForce is GTD but no GTD timestamp is provided */
      val TIME_IN_FORCE_GTD_TIMESTAMP_MISSING = Value
    
      /** The price has not been specified */
      val PRICE_MISSING = Value
    
      /** The Stop Loss on fill specifies a GTD timestamp that is in the past */
      val STOP_LOSS_ON_FILL_GTD_TIMESTAMP_IN_PAST = Value
    
      /** The Take Profit on fill client Order ID specified is invalid */
      val TAKE_PROFIT_ON_FILL_CLIENT_ORDER_ID_INVALID = Value
    
      /** The price distance does not meet the minimum allowed amount */
      val PRICE_DISTANCE_MINIMUM_NOT_MET = Value
    
      /** Order units have not been not specified */
      val UNITS_MISSING = Value
    
      /** A Take Profit Order for the specified Trade already exists */
      val TAKE_PROFIT_ORDER_ALREADY_EXISTS = Value
    
      /** The Trailing Stop Loss on fill GTD timestamp is in the past */
      val TRAILING_STOP_LOSS_ON_FILL_GTD_TIMESTAMP_IN_PAST = Value
    
      /** A client attempted to create either a Trailing Stop Loss order or an order with a Trailing Stop Loss On Fill specified, which may not yet be supported. */
      val TRAILING_STOP_LOSS_ORDERS_NOT_SUPPORTED = Value
    
      /** Order units specified are invalid */
      val UNITS_INVALID = Value
    
      /** The client Trade ID specified is invalid */
      val CLIENT_TRADE_ID_INVALID = Value
    
      /** The request to closeout a Position could not be fully satisfied */
      val CLOSEOUT_POSITION_REJECT = Value
    
      /** No configuration parameters provided */
      val CLIENT_CONFIGURE_DATA_MISSING = Value
    
      /** The Account is locked for Order cancellation */
      val ACCOUNT_ORDER_CANCEL_LOCKED = Value
    
      /** Neither the Order ID nor client Order ID are specified */
      val ORDER_ID_UNSPECIFIED = Value
    
      /** The request to close a Trade partially did not specify the number of units to close */
      val CLOSE_TRADE_PARTIAL_UNITS_MISSING = Value
    
      /** The Account is not active */
      val ACCOUNT_NOT_ACTIVE = Value
    
      /** The Take Profit on fill specified does not provide a TimeInForce */
      val TAKE_PROFIT_ON_FILL_TIME_IN_FORCE_MISSING = Value
    
      /** The Stop Loss on fill client Order tag specified is invalid */
      val STOP_LOSS_ON_FILL_CLIENT_ORDER_TAG_INVALID = Value
    
      /** Creating the Order would result in the maximum number of allowed pending Orders being exceeded */
      val PENDING_ORDERS_ALLOWED_EXCEEDED = Value
    
      /** The margin rate provided is invalid */
      val MARGIN_RATE_INVALID = Value
    
      /** The TriggerCondition field has not been specified */
      val TRIGGER_CONDITION_MISSING = Value
    
      /** The Take Profit on fill specified contains an invalid price */
      val TAKE_PROFIT_ON_FILL_PRICE_INVALID = Value
    
      /** The Trade ID and client Trade ID specified do not identify the same Trade */
      val TRADE_IDENTIFIER_INCONSISTENCY = Value
    
      /** The Stop Loss on fill specified does not provide a TimeInForce */
      val STOP_LOSS_ON_FILL_TIME_IN_FORCE_MISSING = Value
    
      /** The PositionAggregationMode provided is not supported/valid. */
      val POSITION_AGGREGATION_MODE_INVALID = Value
    
      /** The Account is locked for withdrawals */
      val ACCOUNT_WITHDRAWAL_LOCKED = Value
    
      /** The Trailing Stop Loss on fill price distance does not meet the minimum allowed amount */
      val TRAILING_STOP_LOSS_ON_FILL_PRICE_DISTANCE_MINIMUM_NOT_MET = Value
    
      /** The Stop Loss on fill specified does not provide a TriggerCondition */
      val STOP_LOSS_ON_FILL_TRIGGER_CONDITION_MISSING = Value
    
      /** The Trailing Stop Loss on fill client Order tag specified is invalid */
      val TRAILING_STOP_LOSS_ON_FILL_CLIENT_ORDER_TAG_INVALID = Value
    
      /** The Trailing Stop Loss on fill client Order ID specified is invalid */
      val TRAILING_STOP_LOSS_ON_FILL_CLIENT_ORDER_ID_INVALID = Value
    
      /** The Stop Loss on fill specifies an invalid TriggerCondition */
      val STOP_LOSS_ON_FILL_TRIGGER_CONDITION_INVALID = Value
    
      /** The Trailing Stop Loss on fill TimeInForce is specified as GTD but no GTD timestamp is provided */
      val TRAILING_STOP_LOSS_ON_FILL_GTD_TIMESTAMP_MISSING = Value
    
      /** The OrderFillPositionAction specified is invalid */
      val ORDER_FILL_POSITION_ACTION_INVALID = Value
    
      /** The client Trade ID specifed is already assigned to another open Trade */
      val CLIENT_TRADE_ID_ALREADY_EXISTS = Value
    
      /** The Account is locked */
      val ACCOUNT_LOCKED = Value
    
      /** The OrderFillPositionAction field has not been specified */
      val ORDER_FILL_POSITION_ACTION_MISSING = Value
    
      /** The Order ID and client Order ID specified do not identify the same Order */
      val ORDER_IDENTIFIER_INCONSISTENCY = Value
    
      /** The Tailing Stop Loss on fill specifies an invalid TriggerCondition */
      val TRAILING_STOP_LOSS_ON_FILL_TRIGGER_CONDITION_INVALID = Value
    
      /** The request to partially closeout a Position did not specify the number of units to close. */
      val CLOSEOUT_POSITION_PARTIAL_UNITS_MISSING = Value
    
      /** The price distance exceeds that maximum allowed amount */
      val PRICE_DISTANCE_MAXIMUM_EXCEEDED = Value
    
      /** The instrument specified is unknown */
      val INSTRUMENT_UNKNOWN = Value
    
      /** The TimeInForce is GTD but the GTD timestamp is in the past */
      val TIME_IN_FORCE_GTD_TIMESTAMP_IN_PAST = Value
    
      /** The Trade specified does not exist */
      val TRADE_DOESNT_EXIST = Value
    
      /** The units specified exceeds the maximum number of units allowed */
      val UNITS_LIMIT_EXCEEDED = Value
    
      /** Funding amount has not been specified */
      val AMOUNT_MISSING = Value
    
      /** The request to close a Trade does not specify a full or partial close */
      val CLOSE_TRADE_TYPE_MISSING = Value
    
      /** The Trailing Stop Loss on fill price distance exceeds the maximum allowed amount */
      val TRAILING_STOP_LOSS_ON_FILL_PRICE_DISTANCE_MAXIMUM_EXCEEDED = Value
    
      /** Neither Order nor Trade on Fill client extensions were provided for modification */
      val CLIENT_EXTENSIONS_DATA_MISSING = Value
    
      /** The Account is locked for deposits */
      val ACCOUNT_DEPOSIT_LOCKED = Value
    
      /** The Trailing Stop Loss on fill specified does not provide a distance */
      val TRAILING_STOP_LOSS_ON_FILL_PRICE_DISTANCE_MISSING = Value
    
      /** A Stop Loss Order for the specified Trade already exists */
      val STOP_LOSS_ORDER_ALREADY_EXISTS = Value
    
      /** The Take Profit on fill specified does not provide a price */
      val TAKE_PROFIT_ON_FILL_PRICE_MISSING = Value
    
      /** The Stop Loss on fill specifies an invalid TimeInForce */
      val STOP_LOSS_ON_FILL_TIME_IN_FORCE_INVALID = Value
    
      /** The Account is locked for configuration */
      val ACCOUNT_CONFIGURATION_LOCKED = Value
    
      /** The units specified is less than the minimum number of units required */
      val UNITS_MIMIMUM_NOT_MET = Value
    
      /** The Stop Loss on fill specified does not provide a price */
      val STOP_LOSS_ON_FILL_PRICE_MISSING = Value
    
      /** The Take Profit on fill specifies an invalid TriggerCondition */
      val TAKE_PROFIT_ON_FILL_TRIGGER_CONDITION_INVALID = Value
    
      /** The Order to be replaced has a different type than the replacing Order. */
      val REPLACING_ORDER_INVALID = Value
    
      /** The Account does not have sufficient balance to complete the funding request */
      val INSUFFICIENT_FUNDS = Value
    
      /** Funding reason has not been specified */
      val FUNDING_REASON_MISSING = Value
    
      /** The Trailing Stop Loss on fill specified does not provide a TimeInForce */
      val TRAILING_STOP_LOSS_ON_FILL_TIME_IN_FORCE_MISSING = Value
    
      /** The client Order ID specified is already assigned to another pending Order */
      val CLIENT_ORDER_ID_ALREADY_EXISTS = Value
    
      /** The system was unable to determine the current price for the Order’s instrument */
      val INSTRUMENT_PRICE_UNKNOWN = Value
    
      /** The price bound specified is invalid */
      val PRICE_BOUND_INVALID = Value
    
      /** The Take Profit on fill specified does not provide a TriggerCondition */
      val TAKE_PROFIT_ON_FILL_TRIGGER_CONDITION_MISSING = Value
    
      /** The TriggerCondition specified is invalid */
      val TRIGGER_CONDITION_INVALID = Value
    
      /** The Trailing Stop Loss on fill specified does not provide a TriggerCondition */
      val TRAILING_STOP_LOSS_ON_FILL_TRIGGER_CONDITION_MISSING = Value
    
      /** The Trailing Stop Loss on fill distance contains more precision than is allowed by the instrument */
      val TRAILING_STOP_LOSS_ON_FILL_PRICE_DISTANCE_PRECISION_EXCEEDED = Value
    
      /** A Trailing Stop Loss Order for the specified Trade already exists */
      val TRAILING_STOP_LOSS_ORDER_ALREADY_EXISTS = Value
    
      /** The price distance specified contains more precision than is allowed for the instrument */
      val PRICE_DISTANCE_PRECISION_EXCEEDED = Value
    
      /** The Order does not support Trade on fill client extensions because it cannot create a new Trade */
      val TRADE_ON_FILL_CLIENT_EXTENSIONS_NOT_SUPPORTED = Value
    
      /** The Stop Loss on fill client Order comment specified is invalid */
      val STOP_LOSS_ON_FILL_CLIENT_ORDER_COMMENT_INVALID = Value
    
      /** The Stop Loss on fill specifies a price with more precision than is allowed by the Order’s instrument */
      val STOP_LOSS_ON_FILL_PRICE_PRECISION_EXCEEDED = Value
    
      /** The TimeInForce field has not been specified */
      val TIME_IN_FORCE_MISSING = Value
    
      /** The price specified contains more precision than is allowed for the instrument */
      val PRICE_PRECISION_EXCEEDED = Value
    
      /** The Trailing Stop Loss on fill client Order comment specified is invalid */
      val TRAILING_STOP_LOSS_ON_FILL_CLIENT_ORDER_COMMENT_INVALID = Value
    
      /** The price specifed is invalid */
      val PRICE_INVALID = Value
    
      /** The price distance specifed is invalid */
      val PRICE_DISTANCE_INVALID = Value
    
      /** The units specified contain more precision than is allowed for the Order’s instrument */
      val UNITS_PRECISION_EXCEEDED = Value
    
      /** The OrderFillPositionAction specified is invalid. */
      val ORDER_PARTIAL_FILL_OPTION_INVALID = Value
    
      /** An unexpected internal server error has occurred */
      val INTERNAL_SERVER_ERROR = Value
    
      /** The Take Profit on fill client Order tag specified is invalid */
      val TAKE_PROFIT_ON_FILL_CLIENT_ORDER_TAG_INVALID = Value
    
      /** When attempting to reissue an order (currently only a MarketIfTouched) that was immediately partially filled, it is not possible to create a correct pending Order. */
      val INVALID_REISSUE_IMMEDIATE_PARTIAL_FILL = Value
    
      /** The Stop Loss on fill client Order ID specified is invalid */
      val STOP_LOSS_ON_FILL_CLIENT_ORDER_ID_INVALID = Value
    
      /** The client Order tag specified is invalid */
      val CLIENT_ORDER_TAG_INVALID = Value
    
      /** The request to closeout a Position was specified incompletely */
      val CLOSEOUT_POSITION_INCOMPLETE_SPECIFICATION = Value
    
      /** The replacing Order refers to a different Trade than the Order that is being replaced. */
      val REPLACING_TRADE_ID_INVALID = Value
    
      /** The Account is locked for Order creation */
      val ACCOUNT_ORDER_CREATION_LOCKED = Value
    
      /** The price distance has not been specified */
      val PRICE_DISTANCE_MISSING = Value
    
      /** Funding is not possible because the requested transfer amount is invalid */
      val AMOUNT_INVALID = Value
    
      /** The Stop Loss on fill specifies a GTD TimeInForce but does not provide a GTD timestamp */
      val STOP_LOSS_ON_FILL_GTD_TIMESTAMP_MISSING = Value
    
      /** Order instrument has not been specified */
      val INSTRUMENT_MISSING = Value
    
      /** The instrument specified is not tradeable by the Account */
      val INSTRUMENT_NOT_TRADEABLE = Value
    
      /** No configuration parameters provided */
      val ADMIN_CONFIGURE_DATA_MISSING = Value
    
      /** The Order specified does not exist */
      val ORDER_DOESNT_EXIST = Value
    
      /** The margin rate provided would cause the Account to enter a margin call state. */
      val MARGIN_RATE_WOULD_TRIGGER_MARGIN_CALL = Value
    
      /** The price bound specified contains more precision than is allowed for the Order’s instrument */
      val PRICE_BOUND_PRECISION_EXCEEDED = Value
    
      /** A partial Position closeout request specifies a number of units that exceeds the current Position */
      val CLOSEOUT_POSITION_UNITS_EXCEED_POSITION_SIZE = Value
    
      /** The client Trade comment is invalid */
      val CLIENT_TRADE_COMMENT_INVALID = Value
    
      /** The request to partially close a Trade specifies a number of units that exceeds the current size of the given Trade */
      val CLOSE_TRADE_UNITS_EXCEED_TRADE_SIZE = Value
    
      /** The Trailing Stop Loss on fill specifies an invalid TimeInForce */
      val TRAILING_STOP_LOSS_ON_FILL_TIME_IN_FORCE_INVALID = Value
    
      /** The Trailing Stop Loss on fill distance is invalid */
      val TRAILING_STOP_LOSS_ON_FILL_PRICE_DISTANCE_INVALID = Value
    
      /** The account alias string provided is invalid */
      val ALIAS_INVALID = Value
    
      /** The Take Profit on fill client Order comment specified is invalid */
      val TAKE_PROFIT_ON_FILL_CLIENT_ORDER_COMMENT_INVALID = Value
    
      /** The client Trade tag specified is invalid */
      val CLIENT_TRADE_TAG_INVALID = Value
    
      /** The margin rate provided would cause an immediate margin closeout */
      val MARGIN_RATE_WOULD_TRIGGER_CLOSEOUT = Value
    
      /** The Take Profit on fill specifies a GTD timestamp that is in the past */
      val TAKE_PROFIT_ON_FILL_GTD_TIMESTAMP_IN_PAST = Value
    
      /** The TimeInForce specified is invalid */
      val TIME_IN_FORCE_INVALID = Value
    
      /** Neither the Trade ID nor client Trade ID are specified */
      val TRADE_ID_UNSPECIFIED = Value
    
      /** The client Order comment specified is invalid */
      val CLIENT_ORDER_COMMENT_INVALID = Value
    }

    /**
     * A filter that can be used when fetching Transactions
     */
    object TransactionFilter extends Enumeration {
      type TransactionFilter = Value
    
      /** Account Create Transaction */
      val CREATE = Value
    
      /** Market if Touched Order Reject Transaction */
      val MARKET_IF_TOUCHED_ORDER_REJECT = Value
    
      /** Order Client Extensions Modify Reject Transaction */
      val ORDER_CLIENT_EXTENSIONS_MODIFY_REJECT = Value
    
      /** Limit Order Reject Transaction */
      val LIMIT_ORDER_REJECT = Value
    
      /** Take Profit Order Reject Transaction */
      val TAKE_PROFIT_ORDER_REJECT = Value
    
      /** Limit Order Transaction */
      val LIMIT_ORDER = Value
    
      /** Trade Client Extensions Modify Transaction */
      val TRADE_CLIENT_EXTENSIONS_MODIFY = Value
    
      /** Trade Client Extensions Modify Reject Transaction */
      val TRADE_CLIENT_EXTENSIONS_MODIFY_REJECT = Value
    
      /** Administrative Transactions */
      val ADMIN = Value
    
      /** Transfer Funds Reject Transaction */
      val TRANSFER_FUNDS_REJECT = Value
    
      /** Reset Resettable PL Transaction */
      val RESET_RESETTABLE_PL = Value
    
      /** Order Cancel Transaction */
      val ORDER_CANCEL = Value
    
      /** Funding-related Transactions */
      val FUNDING = Value
    
      /** Stop Loss Order Reject Transaction */
      val STOP_LOSS_ORDER_REJECT = Value
    
      /** Client Configuration Transaction */
      val CLIENT_CONFIGURE = Value
    
      /** One Cancels All Order Trigger Transaction */
      val ONE_CANCELS_ALL_ORDER_TRIGGERED = Value
    
      /** Margin Call Extend Transaction */
      val MARGIN_CALL_EXTEND = Value
    
      /** Take Profit Order Transaction */
      val TAKE_PROFIT_ORDER = Value
    
      /** Order Client Extensions Modify Transaction */
      val ORDER_CLIENT_EXTENSIONS_MODIFY = Value
    
      /** Daily Financing Transaction */
      val DAILY_FINANCING = Value
    
      /** Order Fill Transaction */
      val ORDER_FILL = Value
    
      /** Account Close Transaction */
      val CLOSE = Value
    
      /** Delayed Trade Closure Transaction */
      val DELAYED_TRADE_CLOSURE = Value
    
      /** Market Order Transaction */
      val MARKET_ORDER = Value
    
      /** Client Configuration Reject Transaction */
      val CLIENT_CONFIGURE_REJECT = Value
    
      /** Margin Call Enter Transaction */
      val MARGIN_CALL_ENTER = Value
    
      /** One Cancels All Order Transaction */
      val ONE_CANCELS_ALL_ORDER = Value
    
      /** Order Cancel Reject Transaction */
      val ORDER_CANCEL_REJECT = Value
    
      /** Market Order Reject Transaction */
      val MARKET_ORDER_REJECT = Value
    
      /** Margin Call Exit Transaction */
      val MARGIN_CALL_EXIT = Value
    
      /** Trailing Stop Loss Order Reject Transaction */
      val TRAILING_STOP_LOSS_ORDER_REJECT = Value
    
      /** Stop Loss Order Transaction */
      val STOP_LOSS_ORDER = Value
    
      /** Stop Order Reject Transaction */
      val STOP_ORDER_REJECT = Value
    
      /** One Cancels All Order Reject Transaction */
      val ONE_CANCELS_ALL_ORDER_REJECT = Value
    
      /** Account Reopen Transaction */
      val REOPEN = Value
    
      /** Market if Touched Order Transaction */
      val MARKET_IF_TOUCHED_ORDER = Value
    
      /** Transfer Funds Transaction */
      val TRANSFER_FUNDS = Value
    
      /** Order-related Transactions. These are the Transactions that create, cancel, fill or trigger Orders */
      val ORDER = Value
    
      /** Stop Order Transaction */
      val STOP_ORDER = Value
    
      /** Trailing Stop Loss Order Transaction */
      val TRAILING_STOP_LOSS_ORDER = Value
    }
  
    /**
     * A TransactionHeartbeat object is injected into the Transaction stream to ensure that the HTTP connection remains active.
     */
    case class TransactionHeartbeat(
      /** The string "HEARTBEAT" */
      `type`: String = "HEARTBEAT",
      /** The ID of the most recent Transaction created for the Account */
      lastTransactionID: TransactionID,
      /** The date/time when the TransactionHeartbeat was created. */
      time: DateTime
    )
  }
       
  object AccountModel {
    /**
     * The string representation of an Account Identifier.
     * Format: "-"-delimited string with format "{siteID}-{divisionID}-{userID}-{accountNumber}"
     * Example: 001-011-5838423-001
     */
    case class AccountID(value: String) extends AnyVal
  
    /**
     * The full details of a client’s Account. This includes full open Trade, open Position and pending Order representation.
     */
    case class Account(
      /** The Account’s identifier */
      id: AccountID,
      /** Client-assigned alias for the Account. Only provided if the Account has an alias set */
      alias: String,
      /** The home currency of the Account */
      currency: Currency,
      /** The current balance of the Account. Represented in the Account’s home currency. */
      balance: AccountUnits,
      /** ID of the user that created the Account. */
      createdByUserID: Int,
      /** The date/time when the Account was created. */
      createdTime: DateTime,
      /** The total profit/loss realized over the lifetime of the Account. Represented in the Account’s home currency. */
      pl: AccountUnits,
      /** The total realized profit/loss for the Account since it was last reset by the client. Represented in the Account’s home currency. */
      resettablePL: AccountUnits,
      /** The date/time that the Account’s resettablePL was last reset. */
      resettabledPLTime: Option[DateTime],
      /** Client-provided margin rate override for the Account. The effective margin rate of the Account is the lesser of this value and the OANDA margin rate for the Account’s division. This value is only provided if a margin rate override exists for the Account. */
      marginRate: Double,
      /** The date/time when the Account entered a margin call state. Only provided if the Account is in a margin call. */
      marginCallEnterTime: Option[DateTime],
      /** The number of times that the Account’s current margin call was extended. */
      marginCallExtensionCount: Option[Int],
      /** The date/time of the Account’s last margin call extension. */
      lastMarginCallExtensionTime: Option[DateTime],
      /** The number of Trades currently open in the Account. */
      openTradeCount: Int,
      /** The number of Positions currently open in the Account. */
      openPositionCount: Int,
      /** The number of Orders currently pending in the Account. */
      pendingOrderCount: Int,
      /** Flag indicating that the Account has hedging enabled. */
      hedgingEnabled: Boolean,
      /** The total unrealized profit/loss for all Trades currently open in the Account. Represented in the Account’s home currency. */
      unrealizedPL: AccountUnits,
      /** The net asset value of the Account. Equal to Account balance + unrealizedPL. Represented in the Account’s home currency. */
      NAV: AccountUnits,
      /** Margin currently used for the Account. Represented in the Account’s home currency. */
      marginUsed: AccountUnits,
      /** Margin available for Account. Represented in the Account’s home currency. */
      marginAvailable: AccountUnits,
      /** The value of the Account’s open positions represented in the Account’s home currency. */
      positionValue: AccountUnits,
      /** The Account’s margin closeout unrealized PL. */
      marginCloseoutUnrealizedPL: AccountUnits,
      /** The Account’s margin closeout NAV. */
      marginCloseoutNAV: AccountUnits,
      /** The Account’s margin closeout margin used. */
      marginCloseoutMarginUsed: AccountUnits,
      /** The Account’s margin closeout percentage. When this value is 1.0 or above the Account is in a margin closeout situation. */
      marginCloseoutPercent: Double,
      /** The value of the Account’s open positions as used for margin closeout calculations represented in the Account’s home currency. */
      marginCloseoutPositionValue: Double,
      /** The current WithdrawalLimit for the account which will be zero or a positive value indicating how much can be withdrawn from the account. */
      withdrawalLimit: AccountUnits,
      /** The Account’s margin call margin used. */
      marginCallMarginUsed: AccountUnits,
      /** The Account’s margin call percentage. When this value is 1.0 or above the Account is in a margin call situation. */
      marginCallPercent: Double,
      /** The ID of the last Transaction created for the Account. */
      lastTransactionID: TransactionID,
      /** The details of the Trades currently open in the Account. */
      trades: Seq[TradeSummary],
      /** The details all Account Positions. */
      positions: Seq[Position],
      /** The details of the Orders currently pending in the Account. */
      orders: Seq[Order]
    )
  
    /**
     * An AccountState Object is used to represent an Account’s current price-dependent state. Price-dependent Account state is dependent on OANDA’s current Prices, and includes things like unrealized PL, NAV and Trailing Stop Loss Order state.
     */
    case class AccountChangesState(
      /** The total unrealized profit/loss for all Trades currently open in the Account. Represented in the Account’s home currency. */
      unrealizedPL: AccountUnits,
      /** The net asset value of the Account. Equal to Account balance + unrealizedPL. Represented in the Account’s home currency. */
      NAV: AccountUnits,
      /** Margin currently used for the Account. Represented in the Account’s home currency. */
      marginUsed: AccountUnits,
      /** Margin available for Account. Represented in the Account’s home currency. */
      marginAvailable: AccountUnits,
      /** The value of the Account’s open positions represented in the Account’s home currency. */
      positionValue: AccountUnits,
      /** The Account’s margin closeout unrealized PL. */
      marginCloseoutUnrealizedPL: AccountUnits,
      /** The Account’s margin closeout NAV. */
      marginCloseoutNAV: AccountUnits,
      /** The Account’s margin closeout margin used. */
      marginCloseoutMarginUsed: AccountUnits,
      /** The Account’s margin closeout percentage. When this value is 1.0 or above the Account is in a margin closeout situation. */
      marginCloseoutPercent: Double,
      /** The value of the Account’s open positions as used for margin closeout calculations represented in the Account’s home currency. */
      marginCloseoutPositionValue: Double,
      /** The current WithdrawalLimit for the account which will be zero or a positive value indicating how much can be withdrawn from the account. */
      withdrawalLimit: AccountUnits,
      /** The Account’s margin call margin used. */
      marginCallMarginUsed: AccountUnits,
      /** The Account’s margin call percentage. When this value is 1.0 or above the Account is in a margin call situation. */
      marginCallPercent: Double,
      /** The price-dependent state of each pending Order in the Account. */
      orders: Seq[DynamicOrderState],
      /** The price-dependent state for each open Trade in the Account. */
      trades: Seq[CalculatedTradeState],
      /** The price-dependent state for each open Position in the Account. */
      positions: Seq[CalculatedPositionState]
    )
  
    /**
     * Properties related to an Account.
     */
    case class AccountProperties(
      /** The Account’s identifier */
      id: AccountID,
      /** The Account’s associated MT4 Account ID. This field will not be present if the Account is not an MT4 account. */
      mt4AccountID: Option[Int],
      /** The Account’s tags */
      tags: Seq[String]
    )
  
    /**
     * A summary representation of a client’s Account. The AccountSummary does not provide to full specification of pending Orders, open Trades and Positions.
     */
    case class AccountSummary(
      /** The Account’s identifier */
      id: AccountID,
      /** Client-assigned alias for the Account. Only provided if the Account has an alias set */
      alias: String,
      /** The home currency of the Account */
      currency: Currency,
      /** The current balance of the Account. Represented in the Account’s home currency. */
      balance: AccountUnits,
      /** ID of the user that created the Account. */
      createdByUserID: Int,
      /** The date/time when the Account was created. */
      createdTime: DateTime,
      /** The total profit/loss realized over the lifetime of the Account. Represented in the Account’s home currency. */
      pl: AccountUnits,
      /** The total realized profit/loss for the Account since it was last reset by the client. Represented in the Account’s home currency. */
      resettablePL: AccountUnits,
      /** The date/time that the Account’s resettablePL was last reset. */
      resettabledPLTime: Option[DateTime],
      /** Client-provided margin rate override for the Account. The effective margin rate of the Account is the lesser of this value and the OANDA margin rate for the Account’s division. This value is only provided if a margin rate override exists for the Account. */
      marginRate: Double,
      /** The date/time when the Account entered a margin call state. Only provided if the Account is in a margin call. */
      marginCallEnterTime: Option[DateTime],
      /** The number of times that the Account’s current margin call was extended. */
      marginCallExtensionCount: Option[Int],
      /** The date/time of the Account’s last margin call extension. */
      lastMarginCallExtensionTime: Option[DateTime],
      /** The number of Trades currently open in the Account. */
      openTradeCount: Int,
      /** The number of Positions currently open in the Account. */
      openPositionCount: Int,
      /** The number of Orders currently pending in the Account. */
      pendingOrderCount: Int,
      /** Flag indicating that the Account has hedging enabled. */
      hedgingEnabled: Boolean,
      /** The total unrealized profit/loss for all Trades currently open in the Account. Represented in the Account’s home currency. */
      unrealizedPL: AccountUnits,
      /** The net asset value of the Account. Equal to Account balance + unrealizedPL. Represented in the Account’s home currency. */
      NAV: AccountUnits,
      /** Margin currently used for the Account. Represented in the Account’s home currency. */
      marginUsed: AccountUnits,
      /** Margin available for Account. Represented in the Account’s home currency. */
      marginAvailable: AccountUnits,
      /** The value of the Account’s open positions represented in the Account’s home currency. */
      positionValue: AccountUnits,
      /** The Account’s margin closeout unrealized PL. */
      marginCloseoutUnrealizedPL: AccountUnits,
      /** The Account’s margin closeout NAV. */
      marginCloseoutNAV: AccountUnits,
      /** The Account’s margin closeout margin used. */
      marginCloseoutMarginUsed: AccountUnits,
      /** The Account’s margin closeout percentage. When this value is 1.0 or above the Account is in a margin closeout situation. */
      marginCloseoutPercent: Double,
      /** The value of the Account’s open positions as used for margin closeout calculations represented in the Account’s home currency. */
      marginCloseoutPositionValue: Double,
      /** The current WithdrawalLimit for the account which will be zero or a positive value indicating how much can be withdrawn from the account. */
      withdrawalLimit: AccountUnits,
      /** The Account’s margin call margin used. */
      marginCallMarginUsed: AccountUnits,
      /** The Account’s margin call percentage. When this value is 1.0 or above the Account is in a margin call situation. */
      marginCallPercent: Double,
      /** The ID of the last Transaction created for the Account. */
      lastTransactionID: TransactionID
    )
  
    /**
     * An AccountChanges Object is used to represent the changes to an Account’s Orders, Trades and Positions since a specified Account TransactionID in the past.
     */
    case class AccountChanges(
      /** The Orders created. These Orders may have been filled, cancelled or triggered in the same period. */
      ordersCreated: Seq[Order],
      /** The Orders cancelled. */
      ordersCancelled: Seq[Order],
      /** The Orders filled. */
      ordersFilled: Seq[Order],
      /** The Orders triggered. */
      ordersTriggered: Seq[Order],
      /** The Trades opened. */
      tradesOpened: Seq[TradeSummary],
      /** The Trades reduced. */
      tradesReduced: Seq[TradeSummary],
      /** The Trades closed. */
      tradesClosed: Seq[TradeSummary],
      /** The Positions changed. */
      positions: Seq[Position],
      /** The Transactions that have been generated. */
      transactions: Seq[Transaction]
    )
    /**
     * The financing mode of an Account
     */
    object AccountFinancingMode extends Enumeration {
      type AccountFinancingMode = Value
    
      /** No financing is paid/charged for open Trades in the Account */
      val NO_FINANCING = Value
    
      /** Second-by-second financing is paid/charged for open Trades in the Account, both daily and when the the Trade is closed */
      val SECOND_BY_SECOND = Value
    
      /** A full day’s worth of financing is paid/charged for open Trades in the Account daily at 5pm New York time */
      val DAILY = Value
    }
    /**
     * The way that position values for an Account are calculated and aggregated.
     */
    object PositionAggregationMode extends Enumeration {
      type PositionAggregationMode = Value
    
      /** The Position value or margin for each side (long and short) of the Position are computed independently and added together. */
      val ABSOLUTE_SUM = Value
    
      /** The Position value or margin for each side (long and short) of the Position are computed independently. The Position value or margin chosen is the maximal absolute value of the two. */
      val MAXIMAL_SIDE = Value
    
      /** The units for each side (long and short) of the Position are netted together and the resulting value (long or short) is used to compute the Position value or margin. */
      val NET_SUM = Value
    }
  }
       
  object InstrumentModel {
    /**
     * The granularity of a candlestick
     */
    object CandlestickGranularity extends Enumeration {
      type CandlestickGranularity = Value
    
      /** 30 second candlesticks, minute alignment */
      val S30 = Value
    
      /** 2 hour candlesticks, day alignment */
      val H2 = Value
    
      /** 15 second candlesticks, minute alignment */
      val S15 = Value
    
      /** 2 minute candlesticks, hour alignment */
      val M2 = Value
    
      /** 1 hour candlesticks, hour alignment */
      val H1 = Value
    
      /** 6 hour candlesticks, day alignment */
      val H6 = Value
    
      /** 10 second candlesticks, minute alignment */
      val S10 = Value
    
      /** 1 month candlesticks, aligned to first day of the month */
      val M = Value
    
      /** 5 minute candlesticks, hour alignment */
      val M5 = Value
    
      /** 4 hour candlesticks, day alignment */
      val H4 = Value
    
      /** 4 minute candlesticks, hour alignment */
      val M4 = Value
    
      /** 8 hour candlesticks, day alignment */
      val H8 = Value
    
      /** 10 minute candlesticks, hour alignment */
      val M10 = Value
    
      /** 15 minute candlesticks, hour alignment */
      val M15 = Value
    
      /** 12 hour candlesticks, day alignment */
      val H12 = Value
    
      /** 5 second candlesticks, minute alignment */
      val S5 = Value
    
      /** 1 week candlesticks, aligned to start of week */
      val W = Value
    
      /** 3 hour candlesticks, day alignment */
      val H3 = Value
    
      /** 1 day candlesticks, day alignment */
      val D = Value
    
      /** 30 minute candlesticks, hour alignment */
      val M30 = Value
    
      /** 1 minute candlesticks, minute alignment */
      val M1 = Value
    }
    /**
     * The day of the week to use for candlestick granularities with weekly alignment.
     */
    object WeeklyAlignment extends Enumeration {
      type WeeklyAlignment = Value
    
      /** Wednesday */
      val Wednesday = Value
    
      /** Monday */
      val Monday = Value
    
      /** Saturday */
      val Saturday = Value
    
      /** Thursday */
      val Thursday = Value
    
      /** Tuesday */
      val Tuesday = Value
    
      /** Friday */
      val Friday = Value
    
      /** Sunday */
      val Sunday = Value
    }
  
    /**
     * The Candlestick representation
     */
    case class Candlestick(
      /** The start time of the candlestick */
      time: DateTime,
      /** The candlestick data based on bids. Only provided if bid-based candles were requested. */
      bid: Option[CandlestickData],
      /** The candlestick data based on asks. Only provided if ask-based candles were requested. */
      ask: Option[CandlestickData],
      /** The candlestick data based on midpoints. Only provided if midpoint-based candles were requested. */
      mid: Option[CandlestickData],
      /** The number of prices created during the time-range represented by the candlestick. */
      volume: Int,
      /** A flag indicating if the candlestick is complete. A complete candlestick is one whose ending time is not in the future. */
      complete: Boolean
    )
  
    /**
     * The price data (open, high, low, close) for the Candlestick representation.
     */
    case class CandlestickData(
      /** The first (open) price in the time-range represented by the candlestick. */
      o: PriceValue,
      /** The highest price in the time-range represented by the candlestick. */
      h: PriceValue,
      /** The lowest price in the time-range represented by the candlestick. */
      l: PriceValue,
      /** The last (closing) price in the time-range represented by the candlestick. */
      c: PriceValue
    )
  }
       
  object OrderModel {
  
    /**
     * The base Order definition specifies the properties that are common to all Orders.
     */
    abstract class Order(
      /** The Order’s identifier, unique within the Order’s Account. */
      id: OrderID,
      /** The time when the Order was created. */
      createTime: DateTime,
      /** The current state of the Order. */
      state: OrderState,
      /** The client extensions of the Order. Do not set, modify, or delete clientExtensions if your account is associated with MT4. */
      clientExtensions: Option[ClientExtensions],
      /** The type of the Order.*/
      `type`: OrderType
    )
  
    /**
     * A MarketOrder is an order that is filled immediately upon creation using the current market price.
     */
    case class MarketOrder(
      /** The Order’s identifier, unique within the Order’s Account. */
      id: OrderID,
      /** The time when the Order was created. */
      createTime: DateTime,
      /** The current state of the Order. */
      state: OrderState,
      /** The client extensions of the Order. Do not set, modify, or delete clientExtensions if your account is associated with MT4. */
      clientExtensions: Option[ClientExtensions],
      /** The type of the Order. Always set to "MARKET" for Market Orders. */
      `type`: OrderType = OrderType.MARKET,
      /** The Market Order’s Instrument. */
      instrument: InstrumentName,
      /** The quantity requested to be filled by the Market Order. A posititive number of units results in a long Order, and a negative number of units results in a short Order. */
      units: Double,
      /** The time-in-force requested for the Market Order. Restricted to FOK or IOC for a MarketOrder. */
      timeInForce: TimeInForce,
      /** The worst price that the client is willing to have the Market Order filled at. */
      priceBound: Option[PriceValue],
      /** Specification of how Positions in the Account are modified when the Order is filled. */
      positionFill: OrderPositionFill,
      /** Details of the Trade requested to be closed, only provided when the Market Order is being used to explicitly close a Trade. */
      tradeClose: Option[MarketOrderTradeClose],
      /** Details of the long Position requested to be closed out, only provided when a Market Order is being used to explicitly closeout a long Position. */
      longPositionCloseout: Option[MarketOrderPositionCloseout],
      /** Details of the short Position requested to be closed out, only provided when a Market Order is being used to explicitly closeout a short Position. */
      shortPositionCloseout: Option[MarketOrderPositionCloseout],
      /** Details of the Margin Closeout that this Market Order was created for */
      marginCloseout: Option[MarketOrderMarginCloseout],
      /** Details of the delayed Trade close that this Market Order was created for */
      delayedTradeClose: Option[MarketOrderDelayedTradeClose],
      /** TakeProfitDetails specifies the details of a Take Profit Order to be created on behalf of a client. This may happen when an Order is filled that opens a Trade requiring a Take Profit, or when a Trade’s dependent Take Profit Order is modified directly through the Trade. */
      takeProfitOnFill: Option[TakeProfitDetails],
      /** StopLossDetails specifies the details of a Stop Loss Order to be created on behalf of a client. This may happen when an Order is filled that opens a Trade requiring a Stop Loss, or when a Trade’s dependent Stop Loss Order is modified directly through the Trade. */
      stopLossOnFill: Option[StopLossDetails],
      /** TrailingStopLossDetails specifies the details of a Trailing Stop Loss Order to be created on behalf of a client. This may happen when an Order is filled that opens a Trade requiring a Trailing Stop Loss, or when a Trade’s dependent Trailing Stop Loss Order is modified directly through the Trade. */
      trailingStopLossOnFill: Option[TrailingStopLossDetails],
      /** Client Extensions to add to the Trade created when the Order is filled (if such a Trade is created). Do not set, modify, or delete tradeClientExtensions if your account is associated with MT4. */
      tradeclientExtensions: Option[ClientExtensions],
      /** ID of the Transaction that filled this Order (only provided when the Order’s state is FILLED) */
      fillingTransactionID: Option[TransactionID],
      /** Date/time when the Order was filled (only provided when the Order’s state is FILLED) */
      filledTime: Option[DateTime],
      /** Trade ID of Trade opened when the Order was filled (only provided when the Order’s state is FILLED and a Trade was opened as a result of the fill) */
      tradeOpenedID: Option[TradeID],
      /** Trade ID of Trade reduced when the Order was filled (only provided when the Order’s state is FILLED and a Trade was reduced as a result of the fill) */
      tradeReducedID: Option[TradeID],
      /** Trade IDs of Trades closed when the Order was filled (only provided when the Order’s state is FILLED and one or more Trades were closed as a result of the fill) */
      tradeClosedIDs: Seq[TradeID],
      /** ID of the Transaction that cancelled the Order (only provided when the Order’s state is CANCELLED) */
      cancellingTransactionID: Option[TransactionID],
      /** Date/time when the Order was cancelled (only provided when the state of the Order is CANCELLED) */
      cancelledTime: Option[DateTime]
    ) extends Order(id, createTime, state, clientExtensions, `type`)

    /**
     * A LimitOrder is an order that is created with a price threshold, and will only be filled by a price that is equal to or better than the threshold.
     */
    case class LimitOrder(
      /** The Order’s identifier, unique within the Order’s Account. */
      id: OrderID,
      /** The time when the Order was created. */
      createTime: DateTime,
      /** The current state of the Order. */
      state: OrderState,
      /** The client extensions of the Order. Do not set, modify, or delete clientExtensions if your account is associated with MT4. */
      clientExtensions: Option[ClientExtensions],
      /** The type of the Order. Always set to "LIMIT" for Limit Orders. */
      `type`: OrderType = OrderType.LIMIT,
      /** The Limit Order’s Instrument. */
      instrument: InstrumentName,
      /** The quantity requested to be filled by the Limit Order. A posititive number of units results in a long Order, and a negative number of units results in a short Order. */
      units: Double,
      /** The price threshold specified for the Limit Order. The Limit Order will only be filled by a market price that is equal to or better than this price. */
      price: PriceValue,
      /** The time-in-force requested for the Limit Order. */
      timeInForce: TimeInForce,
      /** The date/time when the Limit Order will be cancelled if its timeInForce is "GTD". */
      gtdTime: Option[DateTime],
      /** Specification of how Positions in the Account are modified when the Order is filled. */
      positionFill: OrderPositionFill,
      /** Specification of what component of a price should be used for comparison when determining if the Order should be filled. */
      triggerCondition: OrderTriggerCondition,
      /** TakeProfitDetails specifies the details of a Take Profit Order to be created on behalf of a client. This may happen when an Order is filled that opens a Trade requiring a Take Profit, or when a Trade’s dependent Take Profit Order is modified directly through the Trade. */
      takeProfitOnFill: Option[TakeProfitDetails],
      /** StopLossDetails specifies the details of a Stop Loss Order to be created on behalf of a client. This may happen when an Order is filled that opens a Trade requiring a Stop Loss, or when a Trade’s dependent Stop Loss Order is modified directly through the Trade. */
      stopLossOnFill: Option[StopLossDetails],
      /** TrailingStopLossDetails specifies the details of a Trailing Stop Loss Order to be created on behalf of a client. This may happen when an Order is filled that opens a Trade requiring a Trailing Stop Loss, or when a Trade’s dependent Trailing Stop Loss Order is modified directly through the Trade. */
      trailingStopLossOnFill: Option[TrailingStopLossDetails],
      /** Client Extensions to add to the Trade created when the Order is filled (if such a Trade is created). Do not set, modify, or delete tradeClientExtensions if your account is associated with MT4. */
      tradeclientExtensions: Option[ClientExtensions],
      /** ID of the Transaction that filled this Order (only provided when the Order’s state is FILLED) */
      fillingTransactionID: Option[TransactionID],
      /** Date/time when the Order was filled (only provided when the Order’s state is FILLED) */
      filledTime: Option[DateTime],
      /** Trade ID of Trade opened when the Order was filled (only provided when the Order’s state is FILLED and a Trade was opened as a result of the fill) */
      tradeOpenedID: Option[TradeID],
      /** Trade ID of Trade reduced when the Order was filled (only provided when the Order’s state is FILLED and a Trade was reduced as a result of the fill) */
      tradeReducedID: Option[TradeID],
      /** Trade IDs of Trades closed when the Order was filled (only provided when the Order’s state is FILLED and one or more Trades were closed as a result of the fill) */
      tradeClosedIDs: Seq[TradeID],
      /** ID of the Transaction that cancelled the Order (only provided when the Order’s state is CANCELLED) */
      cancellingTransactionID: Option[TransactionID],
      /** Date/time when the Order was cancelled (only provided when the state of the Order is CANCELLED) */
      cancelledTime: Option[DateTime],
      /** The ID of the Order that was replaced by this Order (only provided if this Order was created as part of a cancel/replace). */
      replacesOrderID: Option[OrderID],
      /** The ID of the Order that replaced this Order (only provided if this Order was cancelled as part of a cancel/replace). */
      replacedByOrderID: Option[OrderID]
    ) extends Order(id, createTime, state, clientExtensions, `type`)

    /**
     * A StopOrder is an order that is created with a price threshold, and will only be filled by a price that is equal to or worse than the threshold.
     */
    case class StopOrder(
      /** The Order’s identifier, unique within the Order’s Account. */
      id: OrderID,
      /** The time when the Order was created. */
      createTime: DateTime,
      /** The current state of the Order. */
      state: OrderState,
      /** The client extensions of the Order. Do not set, modify, or delete clientExtensions if your account is associated with MT4. */
      clientExtensions: Option[ClientExtensions],
      /** The type of the Order. Always set to "STOP" for Stop Orders. */
      `type`: OrderType = OrderType.STOP,
      /** The Stop Order’s Instrument. */
      instrument: InstrumentName,
      /** The quantity requested to be filled by the Stop Order. A posititive number of units results in a long Order, and a negative number of units results in a short Order. */
      units: Double,
      /** The price threshold specified for the Stop Order. The Stop Order will only be filled by a market price that is equal to or worse than this price. */
      price: PriceValue,
      /** The worst market price that may be used to fill this Stop Order. If the market gaps and crosses through both the price and the priceBound, the Stop Order will be cancelled instead of being filled. */
      priceBound: Option[PriceValue],
      /** The time-in-force requested for the Stop Order. */
      timeInForce: TimeInForce,
      /** The date/time when the Stop Order will be cancelled if its timeInForce is "GTD". */
      gtdTime: Option[DateTime],
      /** Specification of how Positions in the Account are modified when the Order is filled. */
      positionFill: OrderPositionFill,
      /** Specification of what component of a price should be used for comparison when determining if the Order should be filled. */
      triggerCondition: OrderTriggerCondition,
      /** TakeProfitDetails specifies the details of a Take Profit Order to be created on behalf of a client. This may happen when an Order is filled that opens a Trade requiring a Take Profit, or when a Trade’s dependent Take Profit Order is modified directly through the Trade. */
      takeProfitOnFill: TakeProfitDetails,
      /** StopLossDetails specifies the details of a Stop Loss Order to be created on behalf of a client. This may happen when an Order is filled that opens a Trade requiring a Stop Loss, or when a Trade’s dependent Stop Loss Order is modified directly through the Trade. */
      stopLossOnFill: StopLossDetails,
      /** TrailingStopLossDetails specifies the details of a Trailing Stop Loss Order to be created on behalf of a client. This may happen when an Order is filled that opens a Trade requiring a Trailing Stop Loss, or when a Trade’s dependent Trailing Stop Loss Order is modified directly through the Trade. */
      trailingStopLossOnFill: TrailingStopLossDetails,
      /** Client Extensions to add to the Trade created when the Order is filled (if such a Trade is created). Do not set, modify, or delete tradeClientExtensions if your account is associated with MT4. */
      tradeclientExtensions: Option[ClientExtensions],
      /** ID of the Transaction that filled this Order (only provided when the Order’s state is FILLED) */
      fillingTransactionID: Option[TransactionID],
      /** Date/time when the Order was filled (only provided when the Order’s state is FILLED) */
      filledTime: Option[DateTime],
      /** Trade ID of Trade opened when the Order was filled (only provided when the Order’s state is FILLED and a Trade was opened as a result of the fill) */
      tradeOpenedID: Option[TradeID],
      /** Trade ID of Trade reduced when the Order was filled (only provided when the Order’s state is FILLED and a Trade was reduced as a result of the fill) */
      tradeReducedID: Option[TradeID],
      /** Trade IDs of Trades closed when the Order was filled (only provided when the Order’s state is FILLED and one or more Trades were closed as a result of the fill) */
      tradeClosedIDs: Seq[TradeID],
      /** ID of the Transaction that cancelled the Order (only provided when the Order’s state is CANCELLED) */
      cancellingTransactionID: Option[TransactionID],
      /** Date/time when the Order was cancelled (only provided when the state of the Order is CANCELLED) */
      cancelledTime: Option[DateTime],
      /** The ID of the Order that was replaced by this Order (only provided if this Order was created as part of a cancel/replace). */
      replacesOrderID: Option[OrderID],
      /** The ID of the Order that replaced this Order (only provided if this Order was cancelled as part of a cancel/replace). */
      replacedByOrderID: Option[OrderID]
    ) extends Order(id, createTime, state, clientExtensions, `type`)

    /**
     * A MarketIfTouchedOrder is an order that is created with a price threshold, and will only be filled by a market price that is touches or crosses the threshold.
     */
    case class MarketIfTouchedOrder(
      /** The Order’s identifier, unique within the Order’s Account. */
      id: OrderID,
      /** The time when the Order was created. */
      createTime: DateTime,
      /** The current state of the Order. */
      state: OrderState,
      /** The client extensions of the Order. Do not set, modify, or delete clientExtensions if your account is associated with MT4. */
      clientExtensions: Option[ClientExtensions],
      /** The type of the Order. Always set to "MARKET_IF_TOUCHED" for Market If Touched Orders. */
      `type`: OrderType = OrderType.MARKET_IF_TOUCHED,
      /** The MarketIfTouched Order’s Instrument. */
      instrument: InstrumentName,
      /** The quantity requested to be filled by the MarketIfTouched Order. A posititive number of units results in a long Order, and a negative number of units results in a short Order. */
      units: Double,
      /** The price threshold specified for the MarketIfTouched Order. The MarketIfTouched Order will only be filled by a market price that crosses this price from the direction of the market price at the time when the Order was created (the initialMarketPrice). Depending on the value of the Order’s price and initialMarketPrice, the MarketIfTouchedOrder will behave like a Limit or a Stop Order. */
      price: PriceValue,
      /** The worst market price that may be used to fill this MarketIfTouched Order. */
      priceBound: Option[PriceValue],
      /** The time-in-force requested for the MarketIfTouched Order. Restricted to "GTC", "GFD" and "GTD" for MarketIfTouched Orders. */
      timeInForce: TimeInForce,
      /** The date/time when the MarketIfTouched Order will be cancelled if its timeInForce is "GTD". */
      gtdTime: Option[DateTime],
      /** Specification of how Positions in the Account are modified when the Order is filled. */
      positionFill: OrderPositionFill,
      /** Specification of what component of a price should be used for comparison when determining if the Order should be filled. */
      triggerCondition: OrderTriggerCondition,
      /** The Market price at the time when the MarketIfTouched Order was created. */
      initialMarketPrice: PriceValue,
      /** TakeProfitDetails specifies the details of a Take Profit Order to be created on behalf of a client. This may happen when an Order is filled that opens a Trade requiring a Take Profit, or when a Trade’s dependent Take Profit Order is modified directly through the Trade. */
      takeProfitOnFill: TakeProfitDetails,
      /** StopLossDetails specifies the details of a Stop Loss Order to be created on behalf of a client. This may happen when an Order is filled that opens a Trade requiring a Stop Loss, or when a Trade’s dependent Stop Loss Order is modified directly through the Trade. */
      stopLossOnFill: StopLossDetails,
      /** TrailingStopLossDetails specifies the details of a Trailing Stop Loss Order to be created on behalf of a client. This may happen when an Order is filled that opens a Trade requiring a Trailing Stop Loss, or when a Trade’s dependent Trailing Stop Loss Order is modified directly through the Trade. */
      trailingStopLossOnFill: TrailingStopLossDetails,
      /** Client Extensions to add to the Trade created when the Order is filled (if such a Trade is created). Do not set, modify, or delete tradeClientExtensions if your account is associated with MT4. */
      tradeclientExtensions: Option[ClientExtensions],
      /** ID of the Transaction that filled this Order (only provided when the Order’s state is FILLED) */
      fillingTransactionID: Option[TransactionID],
      /** Date/time when the Order was filled (only provided when the Order’s state is FILLED) */
      filledTime: Option[DateTime],
      /** Trade ID of Trade opened when the Order was filled (only provided when the Order’s state is FILLED and a Trade was opened as a result of the fill) */
      tradeOpenedID: Option[TradeID],
      /** Trade ID of Trade reduced when the Order was filled (only provided when the Order’s state is FILLED and a Trade was reduced as a result of the fill) */
      tradeReducedID: Option[TradeID],
      /** Trade IDs of Trades closed when the Order was filled (only provided when the Order’s state is FILLED and one or more Trades were closed as a result of the fill) */
      tradeClosedIDs: Seq[TradeID],
      /** ID of the Transaction that cancelled the Order (only provided when the Order’s state is CANCELLED) */
      cancellingTransactionID: Option[TransactionID],
      /** Date/time when the Order was cancelled (only provided when the state of the Order is CANCELLED) */
      cancelledTime: Option[DateTime],
      /** The ID of the Order that was replaced by this Order (only provided if this Order was created as part of a cancel/replace). */
      replacesOrderID: Option[OrderID],
      /** The ID of the Order that replaced this Order (only provided if this Order was cancelled as part of a cancel/replace). */
      replacedByOrderID: Option[OrderID]
    ) extends Order(id, createTime, state, clientExtensions, `type`)

    /**
     * A TakeProfitOrder is an order that is linked to an open Trade and created with a price threshold. The Order will be filled (closing the Trade) by the first price that is equal to or better than the threshold. A TakeProfitOrder cannot be used to open a new Position.
     */
    case class TakeProfitOrder(
      /** The Order’s identifier, unique within the Order’s Account. */
      id: OrderID,
      /** The time when the Order was created. */
      createTime: DateTime,
      /** The current state of the Order. */
      state: OrderState,
      /** The client extensions of the Order. Do not set, modify, or delete clientExtensions if your account is associated with MT4. */
      clientExtensions: Option[ClientExtensions],
      /** The type of the Order. Always set to "TAKE_PROFIT" for Take Profit Orders. */
      `type`: OrderType = OrderType.TAKE_PROFIT,
      /** The ID of the Trade to close when the price threshold is breached. */
      tradeID: TradeID,
      /** The client ID of the Trade to be closed when the price threshold is breached. */
      clientTradeID: Option[ClientID],
      /** The price threshold specified for the TakeProfit Order. The associated Trade will be closed by a market price that is equal to or better than this threshold. */
      price: PriceValue,
      /** The time-in-force requested for the TakeProfit Order. Restricted to "GTC", "GFD" and "GTD" for TakeProfit Orders. */
      timeInForce: TimeInForce,
      /** The date/time when the TakeProfit Order will be cancelled if its timeInForce is "GTD". */
      gtdTime: Option[DateTime],
      /** Specification of what component of a price should be used for comparison when determining if the Order should be filled. */
      triggerCondition: OrderTriggerCondition,
      /** ID of the Transaction that filled this Order (only provided when the Order’s state is FILLED) */
      fillingTransactionID: Option[TransactionID],
      /** Date/time when the Order was filled (only provided when the Order’s state is FILLED) */
      filledTime: Option[DateTime],
      /** Trade ID of Trade opened when the Order was filled (only provided when the Order’s state is FILLED and a Trade was opened as a result of the fill) */
      tradeOpenedID: Option[TradeID],
      /** Trade ID of Trade reduced when the Order was filled (only provided when the Order’s state is FILLED and a Trade was reduced as a result of the fill) */
      tradeReducedID: Option[TradeID],
      /** Trade IDs of Trades closed when the Order was filled (only provided when the Order’s state is FILLED and one or more Trades were closed as a result of the fill) */
      tradeClosedIDs: Seq[TradeID],
      /** ID of the Transaction that cancelled the Order (only provided when the Order’s state is CANCELLED) */
      cancellingTransactionID: Option[TransactionID],
      /** Date/time when the Order was cancelled (only provided when the state of the Order is CANCELLED) */
      cancelledTime: Option[DateTime],
      /** The ID of the Order that was replaced by this Order (only provided if this Order was created as part of a cancel/replace). */
      replacesOrderID: Option[OrderID],
      /** The ID of the Order that replaced this Order (only provided if this Order was cancelled as part of a cancel/replace). */
      replacedByOrderID: Option[OrderID]
    ) extends Order(id, createTime, state, clientExtensions, `type`)

    /**
     * A StopLossOrder is an order that is linked to an open Trade and created with a price threshold. The Order will be filled (closing the Trade) by the first price that is equal to or worse than the threshold. A StopLossOrder cannot be used to open a new Position.
     */
    case class StopLossOrder(
      /** The Order’s identifier, unique within the Order’s Account. */
      id: OrderID,
      /** The time when the Order was created. */
      createTime: DateTime,
      /** The current state of the Order. */
      state: OrderState,
      /** The client extensions of the Order. Do not set, modify, or delete clientExtensions if your account is associated with MT4. */
      clientExtensions: Option[ClientExtensions],
      /** The type of the Order. Always set to "STOP_LOSS" for Stop Loss Orders. */
      `type`: OrderType = OrderType.STOP_LOSS,
      /** The ID of the Trade to close when the price threshold is breached. */
      tradeID: TradeID,
      /** The client ID of the Trade to be closed when the price threshold is breached. */
      clientTradeID: Option[ClientID],
      /** The price threshold specified for the StopLoss Order. The associated Trade will be closed by a market price that is equal to or worse than this threshold. */
      price: PriceValue,
      /** The time-in-force requested for the StopLoss Order. Restricted to "GTC", "GFD" and "GTD" for StopLoss Orders. */
      timeInForce: TimeInForce,
      /** The date/time when the StopLoss Order will be cancelled if its timeInForce is "GTD". */
      gtdTime: Option[DateTime],
      /** Specification of what component of a price should be used for comparison when determining if the Order should be filled. */
      triggerCondition: OrderTriggerCondition,
      /** ID of the Transaction that filled this Order (only provided when the Order’s state is FILLED) */
      fillingTransactionID: Option[TransactionID],
      /** Date/time when the Order was filled (only provided when the Order’s state is FILLED) */
      filledTime: Option[DateTime],
      /** Trade ID of Trade opened when the Order was filled (only provided when the Order’s state is FILLED and a Trade was opened as a result of the fill) */
      tradeOpenedID: Option[TradeID],
      /** Trade ID of Trade reduced when the Order was filled (only provided when the Order’s state is FILLED and a Trade was reduced as a result of the fill) */
      tradeReducedID: Option[TradeID],
      /** Trade IDs of Trades closed when the Order was filled (only provided when the Order’s state is FILLED and one or more Trades were closed as a result of the fill) */
      tradeClosedIDs: Seq[TradeID],
      /** ID of the Transaction that cancelled the Order (only provided when the Order’s state is CANCELLED) */
      cancellingTransactionID: Option[TransactionID],
      /** Date/time when the Order was cancelled (only provided when the state of the Order is CANCELLED) */
      cancelledTime: Option[DateTime],
      /** The ID of the Order that was replaced by this Order (only provided if this Order was created as part of a cancel/replace). */
      replacesOrderID: Option[OrderID],
      /** The ID of the Order that replaced this Order (only provided if this Order was cancelled as part of a cancel/replace). */
      replacedByOrderID: Option[OrderID]
    ) extends Order(id, createTime, state, clientExtensions, `type`)

    /**
     * A TrailingStopLossOrder is an order that is linked to an open Trade and created with a price distance. The price distance is used to calculate a trailing stop value for the order that is in the losing direction from the market price at the time of the order’s creation. The trailing stop value will follow the market price as it moves in the winning direction, and the order will filled (closing the Trade) by the first price that is equal to or worse than the trailing stop value. A TrailingStopLossOrder cannot be used to open a new Position.
     */
    case class TrailingStopLossOrder(
      /** The Order’s identifier, unique within the Order’s Account. */
      id: OrderID,
      /** The time when the Order was created. */
      createTime: DateTime,
      /** The current state of the Order. */
      state: OrderState,
      /** The client extensions of the Order. Do not set, modify, or delete clientExtensions if your account is associated with MT4. */
      clientExtensions: Option[ClientExtensions],
      /** The type of the Order. Always set to "TRAILING_STOP_LOSS" for Trailing Stop Loss Orders. */
      `type`: OrderType = OrderType.TRAILING_STOP_LOSS,
      /** The ID of the Trade to close when the price threshold is breached. */
      tradeID: TradeID,
      /** The client ID of the Trade to be closed when the price threshold is breached. */
      clientTradeID: Option[ClientID],
      /** The price distance specified for the TrailingStopLoss Order. */
      distance: PriceValue,
      /** The time-in-force requested for the TrailingStopLoss Order. Restricted to "GTC", "GFD" and "GTD" for TrailingStopLoss Orders. */
      timeInForce: TimeInForce,
      /** The date/time when the StopLoss Order will be cancelled if its timeInForce is "GTD". */
      gtdTime: Option[DateTime],
      /** Specification of what component of a price should be used for comparison when determining if the Order should be filled. */
      triggerCondition: OrderTriggerCondition,
      /** The trigger price for the Trailing Stop Loss Order. The trailing stop value will trail (follow) the market price by the TSL order’s configured "distance" as the market price moves in the winning direction. If the market price moves to a level that is equal to or worse than the trailing stop value, the order will be filled and the Trade will be closed. */
      trailingStopValue: PriceValue,
      /** ID of the Transaction that filled this Order (only provided when the Order’s state is FILLED) */
      fillingTransactionID: Option[TransactionID],
      /** Date/time when the Order was filled (only provided when the Order’s state is FILLED) */
      filledTime: Option[DateTime],
      /** Trade ID of Trade opened when the Order was filled (only provided when the Order’s state is FILLED and a Trade was opened as a result of the fill) */
      tradeOpenedID: Option[TradeID],
      /** Trade ID of Trade reduced when the Order was filled (only provided when the Order’s state is FILLED and a Trade was reduced as a result of the fill) */
      tradeReducedID: Option[TradeID],
      /** Trade IDs of Trades closed when the Order was filled (only provided when the Order’s state is FILLED and one or more Trades were closed as a result of the fill) */
      tradeClosedIDs: Seq[TradeID],
      /** ID of the Transaction that cancelled the Order (only provided when the Order’s state is CANCELLED) */
      cancellingTransactionID: Option[TransactionID],
      /** Date/time when the Order was cancelled (only provided when the state of the Order is CANCELLED) */
      cancelledTime: Option[DateTime],
      /** The ID of the Order that was replaced by this Order (only provided if this Order was created as part of a cancel/replace). */
      replacesOrderID: Option[OrderID],
      /** The ID of the Order that replaced this Order (only provided if this Order was cancelled as part of a cancel/replace). */
      replacedByOrderID: Option[OrderID]
    ) extends Order(id, createTime, state, clientExtensions, `type`)

    abstract class OrderRequest()

    /**
     * A MarketOrderRequest specifies the parameters that may be set when creating a Market Order.
     */
    case class MarketOrderRequest(
      /** The type of the Order to Create. Must be set to "MARKET" when creating a Market Order. */
      `type`: OrderType = OrderType.MARKET,
      /** The Market Order’s Instrument. */
      instrument: InstrumentName,
      /** The quantity requested to be filled by the Market Order. A posititive number of units results in a long Order, and a negative number of units results in a short Order. */
      units: Double,
      /** The time-in-force requested for the Market Order. Restricted to FOK or IOC for a MarketOrder. */
      timeInForce: TimeInForce,
      /** The worst price that the client is willing to have the Market Order filled at. */
      priceBound: Option[PriceValue],
      /** Specification of how Positions in the Account are modified when the Order is filled. */
      positionFill: OrderPositionFill,
      /** The client extensions to add to the Order. Do not set, modify, or delete clientExtensions if your account is associated with MT4. */
      clientExtensions: Option[ClientExtensions],
      /** TakeProfitDetails specifies the details of a Take Profit Order to be created on behalf of a client. This may happen when an Order is filled that opens a Trade requiring a Take Profit, or when a Trade’s dependent Take Profit Order is modified directly through the Trade. */
      takeProfitOnFill: TakeProfitDetails,
      /** StopLossDetails specifies the details of a Stop Loss Order to be created on behalf of a client. This may happen when an Order is filled that opens a Trade requiring a Stop Loss, or when a Trade’s dependent Stop Loss Order is modified directly through the Trade. */
      stopLossOnFill: StopLossDetails,
      /** TrailingStopLossDetails specifies the details of a Trailing Stop Loss Order to be created on behalf of a client. This may happen when an Order is filled that opens a Trade requiring a Trailing Stop Loss, or when a Trade’s dependent Trailing Stop Loss Order is modified directly through the Trade. */
      trailingStopLossOnFill: TrailingStopLossDetails,
      /** Client Extensions to add to the Trade created when the Order is filled (if such a Trade is created). Do not set, modify, or delete tradeClientExtensions if your account is associated with MT4. */
      tradeclientExtensions: Option[ClientExtensions]
    ) extends OrderRequest
  
    /**
     * A LimitOrderRequest specifies the parameters that may be set when creating a Limit Order.
     */
    case class LimitOrderRequest(
      /** The type of the Order to Create. Must be set to "LIMIT" when creating a Market Order. */
      `type`: OrderType = OrderType.LIMIT,
      /** The Limit Order’s Instrument. */
      instrument: InstrumentName,
      /** The quantity requested to be filled by the Limit Order. A posititive number of units results in a long Order, and a negative number of units results in a short Order. */
      units: Double,
      /** The price threshold specified for the Limit Order. The Limit Order will only be filled by a market price that is equal to or better than this price. */
      price: PriceValue,
      /** The time-in-force requested for the Limit Order. */
      timeInForce: TimeInForce = TimeInForce.GTC,
      /** The date/time when the Limit Order will be cancelled if its timeInForce is "GTD". */
      gtdTime: Option[DateTime] = None,
      /** Specification of how Positions in the Account are modified when the Order is filled. */
      positionFill: OrderPositionFill = OrderPositionFill.DEFAULT,
      /** Specification of what component of a price should be used for comparison when determining if the Order should be filled. */
      triggerCondition: OrderTriggerCondition = OrderTriggerCondition.DEFAULT,
      /** The client extensions to add to the Order. Do not set, modify, or delete clientExtensions if your account is associated with MT4. */
      clientExtensions: Option[ClientExtensions] = None,
      /** TakeProfitDetails specifies the details of a Take Profit Order to be created on behalf of a client. This may happen when an Order is filled that opens a Trade requiring a Take Profit, or when a Trade’s dependent Take Profit Order is modified directly through the Trade. */
      takeProfitOnFill: Option[TakeProfitDetails] = None,
      /** StopLossDetails specifies the details of a Stop Loss Order to be created on behalf of a client. This may happen when an Order is filled that opens a Trade requiring a Stop Loss, or when a Trade’s dependent Stop Loss Order is modified directly through the Trade. */
      stopLossOnFill: Option[StopLossDetails] = None,
      /** TrailingStopLossDetails specifies the details of a Trailing Stop Loss Order to be created on behalf of a client. This may happen when an Order is filled that opens a Trade requiring a Trailing Stop Loss, or when a Trade’s dependent Trailing Stop Loss Order is modified directly through the Trade. */
      trailingStopLossOnFill: Option[TrailingStopLossDetails] = None,
      /** Client Extensions to add to the Trade created when the Order is filled (if such a Trade is created). Do not set, modify, or delete tradeClientExtensions if your account is associated with MT4. */
      tradeclientExtensions: Option[ClientExtensions] = None
    ) extends OrderRequest
  
    /**
     * A StopOrderRequest specifies the parameters that may be set when creating a Stop Order.
     */
    case class StopOrderRequest(
      /** The type of the Order to Create. Must be set to "STOP" when creating a Stop Order. */
      `type`: OrderType = OrderType.STOP,
      /** The Stop Order’s Instrument. */
      instrument: InstrumentName,
      /** The quantity requested to be filled by the Stop Order. A posititive number of units results in a long Order, and a negative number of units results in a short Order. */
      units: Double,
      /** The price threshold specified for the Stop Order. The Stop Order will only be filled by a market price that is equal to or worse than this price. */
      price: PriceValue,
      /** The worst market price that may be used to fill this Stop Order. If the market gaps and crosses through both the price and the priceBound, the Stop Order will be cancelled instead of being filled. */
      priceBound: Option[PriceValue],
      /** The time-in-force requested for the Stop Order. */
      timeInForce: TimeInForce,
      /** The date/time when the Stop Order will be cancelled if its timeInForce is "GTD". */
      gtdTime: Option[DateTime],
      /** Specification of how Positions in the Account are modified when the Order is filled. */
      positionFill: OrderPositionFill = OrderPositionFill.DEFAULT,
      /** Specification of what component of a price should be used for comparison when determining if the Order should be filled. */
      triggerCondition: OrderTriggerCondition = OrderTriggerCondition.DEFAULT,
      /** The client extensions to add to the Order. Do not set, modify, or delete clientExtensions if your account is associated with MT4. */
      clientExtensions: Option[ClientExtensions],
      /** TakeProfitDetails specifies the details of a Take Profit Order to be created on behalf of a client. This may happen when an Order is filled that opens a Trade requiring a Take Profit, or when a Trade’s dependent Take Profit Order is modified directly through the Trade. */
      takeProfitOnFill: Option[TakeProfitDetails],
      /** StopLossDetails specifies the details of a Stop Loss Order to be created on behalf of a client. This may happen when an Order is filled that opens a Trade requiring a Stop Loss, or when a Trade’s dependent Stop Loss Order is modified directly through the Trade. */
      stopLossOnFill: Option[StopLossDetails],
      /** TrailingStopLossDetails specifies the details of a Trailing Stop Loss Order to be created on behalf of a client. This may happen when an Order is filled that opens a Trade requiring a Trailing Stop Loss, or when a Trade’s dependent Trailing Stop Loss Order is modified directly through the Trade. */
      trailingStopLossOnFill: Option[TrailingStopLossDetails],
      /** Client Extensions to add to the Trade created when the Order is filled (if such a Trade is created). Do not set, modify, or delete tradeClientExtensions if your account is associated with MT4. */
      tradeclientExtensions: Option[ClientExtensions]
    ) extends OrderRequest
  
    /**
     * A MarketIfTouchedOrderRequest specifies the parameters that may be set when creating a Market-if-Touched Order.
     */
    case class MarketIfTouchedOrderRequest(
      /** The type of the Order to Create. Must be set to "MARKET_IF_TOUCHED" when creating a Market If Touched Order. */
      `type`: OrderType = OrderType.MARKET_IF_TOUCHED,
      /** The MarketIfTouched Order’s Instrument. */
      instrument: InstrumentName,
      /** The quantity requested to be filled by the MarketIfTouched Order. A posititive number of units results in a long Order, and a negative number of units results in a short Order. */
      units: Double,
      /** The price threshold specified for the MarketIfTouched Order. The MarketIfTouched Order will only be filled by a market price that crosses this price from the direction of the market price at the time when the Order was created (the initialMarketPrice). Depending on the value of the Order’s price and initialMarketPrice, the MarketIfTouchedOrder will behave like a Limit or a Stop Order. */
      price: PriceValue,
      /** The worst market price that may be used to fill this MarketIfTouched Order. */
      priceBound: Option[PriceValue],
      /** The time-in-force requested for the MarketIfTouched Order. Restricted to "GTC", "GFD" and "GTD" for MarketIfTouched Orders. */
      timeInForce: TimeInForce,
      /** The date/time when the MarketIfTouched Order will be cancelled if its timeInForce is "GTD". */
      gtdTime: Option[DateTime],
      /** Specification of how Positions in the Account are modified when the Order is filled. */
      positionFill: OrderPositionFill,
      /** Specification of what component of a price should be used for comparison when determining if the Order should be filled. */
      triggerCondition: OrderTriggerCondition,
      /** The client extensions to add to the Order. Do not set, modify, or delete clientExtensions if your account is associated with MT4. */
      clientExtensions: Option[ClientExtensions],
      /** TakeProfitDetails specifies the details of a Take Profit Order to be created on behalf of a client. This may happen when an Order is filled that opens a Trade requiring a Take Profit, or when a Trade’s dependent Take Profit Order is modified directly through the Trade. */
      takeProfitOnFill: TakeProfitDetails,
      /** StopLossDetails specifies the details of a Stop Loss Order to be created on behalf of a client. This may happen when an Order is filled that opens a Trade requiring a Stop Loss, or when a Trade’s dependent Stop Loss Order is modified directly through the Trade. */
      stopLossOnFill: StopLossDetails,
      /** TrailingStopLossDetails specifies the details of a Trailing Stop Loss Order to be created on behalf of a client. This may happen when an Order is filled that opens a Trade requiring a Trailing Stop Loss, or when a Trade’s dependent Trailing Stop Loss Order is modified directly through the Trade. */
      trailingStopLossOnFill: TrailingStopLossDetails,
      /** Client Extensions to add to the Trade created when the Order is filled (if such a Trade is created). Do not set, modify, or delete tradeClientExtensions if your account is associated with MT4. */
      tradeclientExtensions: Option[ClientExtensions]
    ) extends OrderRequest
  
    /**
     * A TakeProfitOrderRequest specifies the parameters that may be set when creating a Take Profit Order.
     */
    case class TakeProfitOrderRequest(
      /** The type of the Order to Create. Must be set to "TAKE_PROFIT" when creating a Take Profit Order. */
      `type`: OrderType = OrderType.TAKE_PROFIT,
      /** The ID of the Trade to close when the price threshold is breached. */
      tradeID: TradeID,
      /** The client ID of the Trade to be closed when the price threshold is breached. */
      clientTradeID: Option[ClientID],
      /** The price threshold specified for the TakeProfit Order. The associated Trade will be closed by a market price that is equal to or better than this threshold. */
      price: PriceValue,
      /** The time-in-force requested for the TakeProfit Order. Restricted to "GTC", "GFD" and "GTD" for TakeProfit Orders. */
      timeInForce: TimeInForce,
      /** The date/time when the TakeProfit Order will be cancelled if its timeInForce is "GTD". */
      gtdTime: Option[DateTime],
      /** Specification of what component of a price should be used for comparison when determining if the Order should be filled. */
      triggerCondition: OrderTriggerCondition,
      /** The client extensions to add to the Order. Do not set, modify, or delete clientExtensions if your account is associated with MT4. */
      clientExtensions: Option[ClientExtensions]
    ) extends OrderRequest
  
    /**
     * A StopLossOrderRequest specifies the parameters that may be set when creating a Stop Loss Order.
     */
    case class StopLossOrderRequest(
      /** The type of the Order to Create. Must be set to "STOP_LOSS" when creating a Stop Loss Order. */
      `type`: OrderType = OrderType.STOP_LOSS,
      /** The ID of the Trade to close when the price threshold is breached. */
      tradeID: TradeID,
      /** The client ID of the Trade to be closed when the price threshold is breached. */
      clientTradeID: Option[ClientID],
      /** The price threshold specified for the StopLoss Order. The associated Trade will be closed by a market price that is equal to or worse than this threshold. */
      price: PriceValue,
      /** The time-in-force requested for the StopLoss Order. Restricted to "GTC", "GFD" and "GTD" for StopLoss Orders. */
      timeInForce: TimeInForce,
      /** The date/time when the StopLoss Order will be cancelled if its timeInForce is "GTD". */
      gtdTime: Option[DateTime],
      /** Specification of what component of a price should be used for comparison when determining if the Order should be filled. */
      triggerCondition: OrderTriggerCondition,
      /** The client extensions to add to the Order. Do not set, modify, or delete clientExtensions if your account is associated with MT4. */
      clientExtensions: Option[ClientExtensions]
    ) extends OrderRequest
  
    /**
     * A TrailingStopLossOrderRequest specifies the parameters that may be set when creating a Trailing Stop Loss Order.
     */
    case class TrailingStopLossOrderRequest(
      /** The type of the Order to Create. Must be set to "TRAILING_STOP_LOSS" when creating a Trailng Stop Loss Order. */
      `type`: OrderType = OrderType.TRAILING_STOP_LOSS,
      /** The ID of the Trade to close when the price threshold is breached. */
      tradeID: TradeID,
      /** The client ID of the Trade to be closed when the price threshold is breached. */
      clientTradeID: Option[ClientID],
      /** The price distance specified for the TrailingStopLoss Order. */
      distance: PriceValue,
      /** The time-in-force requested for the TrailingStopLoss Order. Restricted to "GTC", "GFD" and "GTD" for TrailingStopLoss Orders. */
      timeInForce: TimeInForce,
      /** The date/time when the StopLoss Order will be cancelled if its timeInForce is "GTD". */
      gtdTime: Option[DateTime],
      /** Specification of what component of a price should be used for comparison when determining if the Order should be filled. */
      triggerCondition: OrderTriggerCondition,
      /** The client extensions to add to the Order. Do not set, modify, or delete clientExtensions if your account is associated with MT4. */
      clientExtensions: Option[ClientExtensions]
    ) extends OrderRequest
    /**
     * The Order’s identifier, unique within the Order’s Account.
     * Format: The string representation of the OANDA-assigned OrderID. OANDA-assigned OrderIDs are positive integers, and are derived from the TransactionID of the Transaction that created the Order.
     * Example: 1523
     */
    case class OrderID(value: String) extends AnyVal

    /**
     * The type of the Order.
     */
    object OrderType extends Enumeration {
      type OrderType = Value
    
      /** A Limit Order */
      val LIMIT = Value
    
      /** A Take Profit Order */
      val TAKE_PROFIT = Value
    
      /** A Stop Order */
      val STOP = Value
    
      /** A Stop Loss Order */
      val STOP_LOSS = Value
    
      /** A Trailing Stop Loss Order */
      val TRAILING_STOP_LOSS = Value
    
      /** A Market Order */
      val MARKET = Value
    
      /** A Market-if-touched Order */
      val MARKET_IF_TOUCHED = Value
    }

    /**
     * The current state of the Order.
     */
    object OrderState extends Enumeration {
      type OrderState = Value
    
      /** The Order is currently pending execution */
      val PENDING = Value
    
      /** The Order has been filled */
      val FILLED = Value
    
      /** The Order has been triggered */
      val TRIGGERED = Value
    
      /** The Order has been cancelled */
      val CANCELLED = Value
    }
  
    /**
     * An OrderIdentifier is used to refer to an Order, and contains both the OrderID and the ClientOrderID.
     */
    case class OrderIdentifier(
      /** The OANDA-assigned Order ID */
      orderID: OrderID,
      /** The client-provided client Order ID */
      clientOrderID: ClientID
    )

    /**
     * The specification of an Order as referred to by clients
     * Format: Either the Order’s OANDA-assigned OrderID or the Order’s client-provided ClientID prefixed by the "@" symbol
     * Example: 1523
     */
    case class OrderSpecifier(value: String) extends AnyVal

    /**
     * The time-in-force of an Order. TimeInForce describes how long an Order should remain pending before being automatically cancelled by the execution system.
     */
    object TimeInForce extends Enumeration {
      type TimeInForce = Value
    
      /** The Order must be immediately "Filled Or Killed" */
      val FOK = Value
    
      /** The Order must be "Immediatedly paritally filled Or Cancelled" */
      val IOC = Value
    
      /** The Order is "Good unTil Cancelled" */
      val GTC = Value
    
      /** The Order is "Good unTil Date" and will be cancelled at the provided time */
      val GTD = Value
    
      /** The Order is "Good For Day" and will be cancelled at 5pm New York time */
      val GFD = Value
    }

    /**
     * Specification of how Positions in the Account are modified when the Order is filled.
     */
    object OrderPositionFill extends Enumeration {
      type OrderPositionFill = Value
    
      /** When the Order is filled, only allow Positions to be opened or extended. */
      val OPEN_ONLY = Value
    
      /** When the Order is filled, always fully reduce an existing Position before opening a new Position. */
      val REDUCE_FIRST = Value
    
      /** When the Order is filled, only reduce an existing Position. */
      val REDUCE_ONLY = Value
    
      /** When the Order is filled, use REDUCE_FIRST behaviour for non-client hedging Accounts, and OPEN_ONLY behaviour for client hedging Accounts. */
      val DEFAULT = Value
    }

    /**
     * Specification of what component of a price should be used for comparison when determining if the Order should be filled.
     */
    object OrderTriggerCondition extends Enumeration {
      type OrderTriggerCondition = Value
    
      /** Trigger an Order by comparing its price to the bid regardless of whether it is long or short. */
      val BID = Value
    
      /** Trigger an Order by comparing its price to the ask regardless of whether it is long or short. */
      val ASK = Value
    
      /** Trigger an Order by comparing its price to the midpoint regardless of whether it is long or short. */
      val MID = Value
    
      /** Trigger an Order the "natural" way: compare its price to the ask for long Orders and bid for short Orders. */
      val DEFAULT = Value
    
      /** Trigger an Order the opposite of the "natural" way: compare its price the bid for long Orders and ask for short Orders. */
      val INVERSE = Value
    }
  
    /**
     * The dynamic state of an Order. This is only relevant to TrailingStopLoss Orders, as no other Order type has dynamic state.
     */
    case class DynamicOrderState(
      /** The Order’s ID. */
      id: OrderID,
      /** The Order’s calculated trailing stop value. */
      trailingStopValue: PriceValue,
      /** The distance between the Trailing Stop Loss Order’s trailingStopValue and the current Market Price. This represents the distance (in price units) of the Order from a triggering price. If the distance could not be determined, this value will not be set. */
      triggerDistance: PriceValue,
      /** True if an exact trigger distance could be calculated. If false, it means the provided trigger distance is a best estimate. If the distance could not be determined, this value will not be set. */
      isTriggerDistanceExact: Boolean
    )
  }
       
  object TradeModel {
    /**
     * The Trade’s identifier, unique within the Trade’s Account.
     * Format: The string representation of the OANDA-assigned TradeID. OANDA-assigned TradeIDs are positive integers, and are derived from the TransactionID of the Transaction that opened the Trade.
     * Example: 1523
     */
    case class TradeID(value: String) extends AnyVal
    /**
     * The current state of the Trade.
     */
    object TradeState extends Enumeration {
      type TradeState = Value
    
      /** The Trade is currently open */
      val OPEN = Value
    
      /** The Trade has been fully closed */
      val CLOSED = Value
    
      /** The Trade will be closed as soon as the trade’s instrument becomes tradeable */
      val CLOSE_WHEN_TRADEABLE = Value
    }

    /**
     * The identification of a Trade as referred to by clients
     * Format: Either the Trade’s OANDA-assigned TradeID or the Trade’s client-provided ClientID prefixed by the "@" symbol
     * Example: @my_trade_id
     */
    case class TradeSpecifier(value: String) extends AnyVal
  
    /**
     * The specification of a Trade within an Account. This includes the full representation of the Trade’s dependent Orders in addition to the IDs of those Orders.
     */
    case class Trade(
      /** The Trade’s identifier, unique within the Trade’s Account. */
      id: TradeID,
      /** The Trade’s Instrument. */
      instrument: InstrumentName,
      /** The execution price of the Trade. */
      price: PriceValue,
      /** The date/time when the Trade was opened. */
      openTime: DateTime,
      /** The current state of the Trade. */
      state: TradeState,
      /** The initial size of the Trade. Negative values indicate a short Trade, and positive values indicate a long Trade. */
      initialUnits: Double,
      /** The number of units currently open for the Trade. This value is reduced to 0.0 as the Trade is closed. */
      currentUnits: Double,
      /** The total profit/loss realized on the closed portion of the Trade. */
      realizedPL: AccountUnits,
      /** The unrealized profit/loss on the open portion of the Trade. */
      unrealizedPL: Option[AccountUnits],
      /** The average closing price of the Trade. Only present if the Trade has been closed or reduced at least once. */
      averageClosePrice: PriceValue,
      /** The IDs of the Transactions that have closed portions of this Trade. */
      closingTransactionIDs: Seq[TransactionID],
      /** The financing paid/collected for this Trade. */
      financing: AccountUnits,
      /** The date/time when the Trade was fully closed. Only provided for Trades whose state is CLOSED. */
      closeTime: DateTime,
      /** The client extensions of the Trade. */
      clientExtensions: Option[ClientExtensions],
      /** Full representation of the Trade’s Take Profit Order, only provided if such an Order exists. */
      takeProfitOrder: Option[TakeProfitOrder],
      /** Full representation of the Trade’s Stop Loss Order, only provided if such an Order exists. */
      stopLossOrder: Option[StopLossOrder],
      /** Full representation of the Trade’s Trailing Stop Loss Order, only provided if such an Order exists. */
      trailingStopLossOrder: Option[TrailingStopLossOrder]
    )
  
    /**
     * The summary of a Trade within an Account. This representation does not provide the full details of the Trade’s dependent Orders.
     */
    case class TradeSummary(
      /** The Trade’s identifier, unique within the Trade’s Account. */
      id: TradeID,
      /** The Trade’s Instrument. */
      instrument: InstrumentName,
      /** The execution price of the Trade. */
      price: PriceValue,
      /** The date/time when the Trade was opened. */
      openTime: DateTime,
      /** The current state of the Trade. */
      state: TradeState,
      /** The initial size of the Trade. Negative values indicate a short Trade, and positive values indicate a long Trade. */
      initialUnits: Double,
      /** The number of units currently open for the Trade. This value is reduced to 0.0 as the Trade is closed. */
      currentUnits: Double,
      /** The total profit/loss realized on the closed portion of the Trade. */
      realizedPL: AccountUnits,
      /** The unrealized profit/loss on the open portion of the Trade. */
      unrealizedPL: AccountUnits,
      /** The average closing price of the Trade. Only present if the Trade has been closed or reduced at least once. */
      averageClosePrice: PriceValue,
      /** The IDs of the Transactions that have closed portions of this Trade. */
      closingTransactionIDs: Seq[TransactionID],
      /** The financing paid/collected for this Trade. */
      financing: AccountUnits,
      /** The date/time when the Trade was fully closed. Only provided for Trades whose state is CLOSED. */
      closeTime: DateTime,
      /** The client extensions of the Trade. */
      clientExtensions: Option[ClientExtensions],
      /** ID of the Trade’s Take Profit Order, only provided if such an Order exists. */
      takeProfitOrderID: OrderID,
      /** ID of the Trade’s Stop Loss Order, only provided if such an Order exists. */
      stopLossOrderID: OrderID,
      /** ID of the Trade’s Trailing Stop Loss Order, only provided if such an Order exists. */
      trailingStopLossOrderID: OrderID
    )
  
    /**
     * The dynamic (calculated) state of an open Trade
     */
    case class CalculatedTradeState(
      /** The Trade’s ID. */
      id: TradeID,
      /** The Trade’s unrealized profit/loss. */
      unrealizedPL: AccountUnits
    )
  }
       
  object PositionModel {
    /**
     * The specification of a Position within an Account.
     */
    case class Position(
      /** The Position’s Instrument. */
      instrument: InstrumentName,
      /** Profit/loss realized by the Position over the lifetime of the Account. */
      pl: AccountUnits,
      /** The unrealized profit/loss of all open Trades that contribute to this Position. */
      unrealizedPL: AccountUnits,
      /** Profit/loss realized by the Position since the Account’s resettablePL was last reset by the client. */
      resettablePL: AccountUnits,
      /** The details of the long side of the Position. */
      long: PositionSide,
      /** The details of the short side of the Position. */
      short: PositionSide
    )
  
    /**
     * The representation of a Position for a single direction (long or short).
     */
    case class PositionSide(
      /** Number of units in the position (negative value indicates short position, positive indicates long position). */
      units: Double,
      /** Volume-weighted average of the underlying Trade open prices for the Position. */
      averagePrice: Option[PriceValue],
      /** List of the open Trade IDs which contribute to the open Position. */
      tradeIDs: Seq[TradeID],
      /** Profit/loss realized by the PositionSide over the lifetime of the Account. */
      pl: AccountUnits,
      /** The unrealized profit/loss of all open Trades that contribute to this PositionSide. */
      unrealizedPL: AccountUnits,
      /** Profit/loss realized by the PositionSide since the Account’s resettablePL was last reset by the client. */
      resettablePL: AccountUnits
    )
  
    /**
     * The dynamic (calculated) state of a Position
     */
    case class CalculatedPositionState(
      /** The Position’s Instrument. */
      instrument: InstrumentName,
      /** The Position’s net unrealized profit/loss */
      netUnrealizedPL: AccountUnits,
      /** The unrealized profit/loss of the Position’s long open Trades */
      longUnrealizedPL: AccountUnits,
      /** The unrealized profit/loss of the Position’s short open Trades */
      shortUnrealizedPL: AccountUnits
    )
  }
       
  object PricingModel {
    /**
     * The specification of an Account-specific Price.
     */
    case class Price(
      /** The string "PRICE". Used to identify the a Price object when found in a stream. */
      `type`: String = "PRICE",
      /** The Price’s Instrument. */
      instrument: InstrumentName,
      /** The date/time when the Price was created */
      time: DateTime,
      /** The status of the Price.<b>Deprecated</b>: Will be removed in a future API update. */
      status: PriceStatus,
      /** Flag indicating if the Price is tradeable or not */
      tradeable: Boolean,
      /** The list of prices and liquidity available on the Instrument’s bid side. It is possible for this list to be empty if there is no bid liquidity currently available for the Instrument in the Account. */
      bids: Seq[PriceBucket],
      /** The list of prices and liquidity available on the Instrument’s ask side. It is possible for this list to be empty if there is no ask liquidity currently available for the Instrument in the Account. */
      asks: Seq[PriceBucket],
      /** The closeout bid Price. This Price is used when a bid is required to closeout a Position (margin closeout or manual) yet there is no bid liquidity. The closeout bid is never used to open a new position. */
      closeoutBid: PriceValue,
      /** The closeout ask Price. This Price is used when a ask is required to closeout a Position (margin closeout or manual) yet there is no ask liquidity. The closeout ask is never used to open a new position. */
      closeoutAsk: PriceValue,
      /** The factors used to convert quantities of this price’s Instrument’s quote currency into a quantity of the Account’s home currency.<b>Deprecated</b>: Will be removed in a future API update. */
      quoteHomeConversionFactors: QuoteHomeConversionFactors,
      /** Representation of how many units of an Instrument are available to be traded by an Order depending on its postionFill option.<b>Deprecated</b>: Will be removed in a future API update. */
      unitsAvailable: UnitsAvailable
    )
    /**
     * The string representation of a Price for an Instrument.
     * Format: A decimal number encodes as a string. The amount of precision provided depends on the Price’s Instrument.
     */
    case class PriceValue(value: String) extends AnyVal
  
    /**
     * A Price Bucket represents a price available for an amount of liquidity
     */
    case class PriceBucket(
      /** The Price offered by the PriceBucket */
      price: PriceValue,
      /** The amount of liquidity offered by the PriceBucket */
      liquidity: Int
    )

    /**
     * The status of the Price.
     */
    object PriceStatus extends Enumeration {
      type PriceStatus = Value
    
      /** The Instrument’s price is tradeable. */
      val tradeable = Value
    
      /** The Instrument’s price is not tradeable. */
      val non_tradeable = Value
    
      /** The Instrument of the price is invalid or there is no valid Price for the Instrument. */
      val invalid = Value
    }
  
    /**
     * Representation of many units of an Instrument are available to be traded for both long and short Orders.
     */
    case class UnitsAvailableDetails(
      /** The units available for long Orders. */
      long: Double,
      /** The units available for short Orders. */
      short: Double
    )
  
    /**
     * Representation of how many units of an Instrument are available to be traded by an Order depending on its postionFill option.
     */
    case class UnitsAvailable(
      /** The number of units that are available to be traded using an Order with a positionFill option of "DEFAULT". For an Account with hedging enabled, this value will be the same as the "OPEN_ONLY" value. For an Account without hedging enabled, this value will be the same as the "REDUCE_FIRST" value. */
      default: UnitsAvailableDetails,
      /** The number of units that may are available to be traded with an Order with a positionFill option of "REDUCE_FIRST". */
      reduceFirst: UnitsAvailableDetails,
      /** The number of units that may are available to be traded with an Order with a positionFill option of "REDUCE_ONLY". */
      reduceOnly: UnitsAvailableDetails,
      /** The number of units that may are available to be traded with an Order with a positionFill option of "OPEN_ONLY". */
      openOnly: UnitsAvailableDetails
    )
  
    /**
     * QuoteHomeConversionFactors represents the factors that can be used used to convert quantities of a Price’s Instrument’s quote currency into the Account’s home currency.
     */
    case class QuoteHomeConversionFactors(
      /** The factor used to convert a positive amount of the Price’s Instrument’s quote currency into a positive amount of the Account’s home currency. Conversion is performed by multiplying the quote units by the conversion factor. */
      positiveUnits: Double,
      /** The factor used to convert a negative amount of the Price’s Instrument’s quote currency into a negative amount of the Account’s home currency. Conversion is performed by multiplying the quote units by the conversion factor. */
      negativeUnits: Double
    )
  
    /**
     * A PricingHeartbeat object is injected into the Pricing stream to ensure that the HTTP connection remains active.
     */
    case class PricingHeartbeat(
      /** The string "HEARTBEAT" */
      `type`: String = "HEARTBEAT",
      /** The date/time when the Heartbeat was created. */
      time: DateTime
    )
  }
       
  object PrimitivesModel {
    /**
     * The string representation of a decimal number.
     * Format: A decimal number encoded as a string. The amount of precision provided depends on what the number represents.
     */
    case class DecimalNumber(value: String) extends AnyVal
    /**
     * The string representation of a quantity of an Account’s home currency.
     * Format: A decimal number encoded as a string. The amount of precision provided depends on the Account’s home currency.
     */
    case class AccountUnits(value: String) extends AnyVal
    /**
     * Currency name identifier. Used by clients to refer to currencies.
     * Format: A string containing an ISO 4217 currency (
     */
    case class Currency(value: String) extends AnyVal
    /**
     * Instrument name identifier. Used by clients to refer to an Instrument.
     * Format: A string containing the base currency and quote currency delimited by a "_".
     */
    case class InstrumentName(value: String) extends AnyVal
    /**
     * The type of an Instrument.
     */
    object InstrumentType extends Enumeration {
      type InstrumentType = Value
    
      /** Currency */
      val CURRENCY = Value
    
      /** Contract For Difference */
      val CFD = Value
    
      /** Metal */
      val METAL = Value
    }
  
    /**
     * Full specification of an Instrument.
     */
    case class Instrument(
      /** The name of the Instrument */
      name: InstrumentName,
      /** The type of the Instrument */
      `type`: InstrumentType,
      /** The display name of the Instrument */
      displayName: String,
      /** The location of the "pip" for this instrument. The decimal position of the pip in this Instrument’s price can be found at 10 ^ pipLocation (e.g. -4 pipLocation results in a decimal pip position of 10 ^ -4 = 0.0001). */
      pipLocation: Int,
      /** The number of decimal places that should be used to display prices for this instrument. (e.g. a displayPrecision of 5 would result in a price of "1" being displayed as "1.00000") */
      displayPrecision: Int,
      /** The amount of decimal places that may be provided when specifying the number of units traded for this instrument. */
      tradeUnitsPrecision: Int,
      /** The smallest number of units allowed to be traded for this instrument. */
      minimumTradeSize: Double,
      /** The maximum trailing stop distance allowed for a trailing stop loss created for this instrument. Specified in price units. */
      maximumTrailingStopDistance: Double,
      /** The minimum trailing stop distance allowed for a trailing stop loss created for this instrument. Specified in price units. */
      minimumTrailingStopDistance: Double,
      /** The maximum position size allowed for this instrument. Specified in units. */
      maximumPositionSize: Double,
      /** The maximum units allowed for an Order placed for this instrument. Specified in units. */
      maximumOrderUnits: Double,
      /** The margin rate for this instrument. */
      marginRate: Double
    )
    /**
     * A date and time value using either RFC3339 or UNIX time representation.
     * Format: The RFC 3339 representation is a string conforming to
     */
    case class DateTime(value: String) extends AnyVal {
      def toJodaDateTime = time.DateTime.parse(value)
    }
    /**
     * DateTime header
     */
    object AcceptDatetimeFormat extends Enumeration {
      type AcceptDatetimeFormat = Value
    
      /** If "UNIX" is specified DateTime fields will be specified or returned in the "12345678.000000123" format. */
      val UNIX = Value
    
      /** If "RFC3339" is specified DateTime will be specified or returned in "YYYY-MM-DDTHH:MM:SS.nnnnnnnnnZ" format. */
      val RFC3339 = Value
    }
  }
       
}
