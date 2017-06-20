package org.nikosoft.oanda.api.generator

import org.nikosoft.oanda.api.generator.ApiModelGeneratorParsers.ParameterTable
import org.scalatest.{FunSuite, Matchers}

class ApiModelGeneratorParsersUnitTest extends FunSuite with Matchers {

  test("convert large (enum) field to ParameterTable") {
    val input = """
        |<table class="parameter_table">
        |  <thead>
        |    <tr>
        |      <th class="pt_25">
        |        Value
        |      </th>
        |      <th class="pt_75">
        |        Description
        |      </th>
        |    </tr>
        |  </thead>
        |  <tbody>
        |    <tr>
        |      <td>
        |        INTERNAL_SERVER_ERROR
        |      </td>
        |      <td>
        |        An unexpected internal server error has occurred
        |      </td>
        |    </tr>
        |    <tr>
        |      <td>
        |        INSTRUMENT_PRICE_UNKNOWN
        |      </td>
        |      <td>
        |        The system was unable to determine the current price for the Order’s instrument
        |      </td>
        |    </tr>
        |    <tr>
        |      <td>
        |        REPLACING_TRADE_ID_INVALID
        |      </td>
        |      <td>
        |        The replacing Order refers to a different Trade than the Order that is being replaced.
        |      </td>
        |    </tr>
        |  </tbody>
        |</table>
      """.stripMargin

    val parameterTable = ApiModelGeneratorParsers.parseParameterTable(input)
    val expectedParameterTable = ParameterTable("Value", "Description",
      Map(
        "INTERNAL_SERVER_ERROR" -> "An unexpected internal server error has occurred",
        "INSTRUMENT_PRICE_UNKNOWN" -> "The system was unable to determine the current price for the Order’s instrument",
        "REPLACING_TRADE_ID_INVALID" -> "The replacing Order refers to a different Trade than the Order that is being replaced."
      )
    )

    parameterTable shouldBe expectedParameterTable
  }

  test("convert simple field to ParameterTable 2") {
    val input = """<table class="parameter_table">
                  |  <thead>
                  |    <tr>
                  |      <th class="pt_25">
                  |        Value
                  |      </th>
                  |      <th class="pt_75">
                  |        Description
                  |      </th>
                  |    </tr>
                  |  </thead>
                  |  <tbody>
                  |    <tr>
                  |      <td>
                  |        CREATE
                  |      </td>
                  |      <td>
                  |        Account Create Transaction
                  |      </td>
                  |    </tr>
                  |    <tr>
                  |      <td>
                  |        CLOSE
                  |      </td>
                  |      <td>
                  |        Account Close Transaction
                  |      </td>
                  |    </tr>
                  |    <tr>
                  |      <td>
                  |        REOPEN
                  |      </td>
                  |      <td>
                  |        Account Reopen Transaction
                  |      </td>
                  |    </tr>
                  |    <tr>
                  |      <td>
                  |        CLIENT_CONFIGURE
                  |      </td>
                  |      <td>
                  |        Client Configuration Transaction
                  |      </td>
                  |    </tr>
                  |    <tr>
                  |      <td>
                  |        CLIENT_CONFIGURE_REJECT
                  |      </td>
                  |      <td>
                  |        Client Configuration Reject Transaction
                  |      </td>
                  |    </tr>
                  |    <tr>
                  |      <td>
                  |        TRANSFER_FUNDS
                  |      </td>
                  |      <td>
                  |        Transfer Funds Transaction
                  |      </td>
                  |    </tr>
                  |    <tr>
                  |      <td>
                  |        TRANSFER_FUNDS_REJECT
                  |      </td>
                  |      <td>
                  |        Transfer Funds Reject Transaction
                  |      </td>
                  |    </tr>
                  |    <tr>
                  |      <td>
                  |        MARKET_ORDER
                  |      </td>
                  |      <td>
                  |        Market Order Transaction
                  |      </td>
                  |    </tr>
                  |    <tr>
                  |      <td>
                  |        MARKET_ORDER_REJECT
                  |      </td>
                  |      <td>
                  |        Market Order Reject Transaction
                  |      </td>
                  |    </tr>
                  |    <tr>
                  |      <td>
                  |        LIMIT_ORDER
                  |      </td>
                  |      <td>
                  |        Limit Order Transaction
                  |      </td>
                  |    </tr>
                  |    <tr>
                  |      <td>
                  |        LIMIT_ORDER_REJECT
                  |      </td>
                  |      <td>
                  |        Limit Order Reject Transaction
                  |      </td>
                  |    </tr>
                  |    <tr>
                  |      <td>
                  |        STOP_ORDER
                  |      </td>
                  |      <td>
                  |        Stop Order Transaction
                  |      </td>
                  |    </tr>
                  |    <tr>
                  |      <td>
                  |        STOP_ORDER_REJECT
                  |      </td>
                  |      <td>
                  |        Stop Order Reject Transaction
                  |      </td>
                  |    </tr>
                  |    <tr>
                  |      <td>
                  |        MARKET_IF_TOUCHED_ORDER
                  |      </td>
                  |      <td>
                  |        Market if Touched Order Transaction
                  |      </td>
                  |    </tr>
                  |    <tr>
                  |      <td>
                  |        MARKET_IF_TOUCHED_ORDER_REJECT
                  |      </td>
                  |      <td>
                  |        Market if Touched Order Reject Transaction
                  |      </td>
                  |    </tr>
                  |    <tr>
                  |      <td>
                  |        TAKE_PROFIT_ORDER
                  |      </td>
                  |      <td>
                  |        Take Profit Order Transaction
                  |      </td>
                  |    </tr>
                  |    <tr>
                  |      <td>
                  |        TAKE_PROFIT_ORDER_REJECT
                  |      </td>
                  |      <td>
                  |        Take Profit Order Reject Transaction
                  |      </td>
                  |    </tr>
                  |    <tr>
                  |      <td>
                  |        STOP_LOSS_ORDER
                  |      </td>
                  |      <td>
                  |        Stop Loss Order Transaction
                  |      </td>
                  |    </tr>
                  |    <tr>
                  |      <td>
                  |        STOP_LOSS_ORDER_REJECT
                  |      </td>
                  |      <td>
                  |        Stop Loss Order Reject Transaction
                  |      </td>
                  |    </tr>
                  |    <tr>
                  |      <td>
                  |        TRAILING_STOP_LOSS_ORDER
                  |      </td>
                  |      <td>
                  |        Trailing Stop Loss Order Transaction
                  |      </td>
                  |    </tr>
                  |    <tr>
                  |      <td>
                  |        TRAILING_STOP_LOSS_ORDER_REJECT
                  |      </td>
                  |      <td>
                  |        Trailing Stop Loss Order Reject Transaction
                  |      </td>
                  |    </tr>
                  |    <tr>
                  |      <td>
                  |        ORDER_FILL
                  |      </td>
                  |      <td>
                  |        Order Fill Transaction
                  |      </td>
                  |    </tr>
                  |    <tr>
                  |      <td>
                  |        ORDER_CANCEL
                  |      </td>
                  |      <td>
                  |        Order Cancel Transaction
                  |      </td>
                  |    </tr>
                  |    <tr>
                  |      <td>
                  |        ORDER_CANCEL_REJECT
                  |      </td>
                  |      <td>
                  |        Order Cancel Reject Transaction
                  |      </td>
                  |    </tr>
                  |    <tr>
                  |      <td>
                  |        ORDER_CLIENT_EXTENSIONS_MODIFY
                  |      </td>
                  |      <td>
                  |        Order Client Extensions Modify Transaction
                  |      </td>
                  |    </tr>
                  |    <tr>
                  |      <td>
                  |        ORDER_CLIENT_EXTENSIONS_MODIFY_REJECT
                  |      </td>
                  |      <td>
                  |        Order Client Extensions Modify Reject Transaction
                  |      </td>
                  |    </tr>
                  |    <tr>
                  |      <td>
                  |        TRADE_CLIENT_EXTENSIONS_MODIFY
                  |      </td>
                  |      <td>
                  |        Trade Client Extensions Modify Transaction
                  |      </td>
                  |    </tr>
                  |    <tr>
                  |      <td>
                  |        TRADE_CLIENT_EXTENSIONS_MODIFY_REJECT
                  |      </td>
                  |      <td>
                  |        Trade Client Extensions Modify Reject Transaction
                  |      </td>
                  |    </tr>
                  |    <tr>
                  |      <td>
                  |        MARGIN_CALL_ENTER
                  |      </td>
                  |      <td>
                  |        Margin Call Enter Transaction
                  |      </td>
                  |    </tr>
                  |    <tr>
                  |      <td>
                  |        MARGIN_CALL_EXTEND
                  |      </td>
                  |      <td>
                  |        Margin Call Extend Transaction
                  |      </td>
                  |    </tr>
                  |    <tr>
                  |      <td>
                  |        MARGIN_CALL_EXIT
                  |      </td>
                  |      <td>
                  |        Margin Call Exit Transaction
                  |      </td>
                  |    </tr>
                  |    <tr>
                  |      <td>
                  |        DELAYED_TRADE_CLOSURE
                  |      </td>
                  |      <td>
                  |        Delayed Trade Closure Transaction
                  |      </td>
                  |    </tr>
                  |    <tr>
                  |      <td>
                  |        DAILY_FINANCING
                  |      </td>
                  |      <td>
                  |        Daily Financing Transaction
                  |      </td>
                  |    </tr>
                  |    <tr>
                  |      <td>
                  |        RESET_RESETTABLE_PL
                  |      </td>
                  |      <td>
                  |        Reset Resettable PL Transaction
                  |      </td>
                  |    </tr>
                  |  </tbody>
                  |</table>""".stripMargin

    val expectedParameterTable = ParameterTable("Type", "string",
      Map(
        "Format" -> "String representation of the numerical OANDA-assigned TransactionID",
        "Example" -> "1523"
      )
    )

    val parameterTable = ApiModelGeneratorParsers.parseParameterTable(input)
    parameterTable shouldBe expectedParameterTable
  }

  test("convert simple field to ParameterTable") {
    val input = """
        |<table class="parameter_table">
        |  <tbody>
        |    <tr>
        |      <th class="pt_15">
        |        Type
        |      </th>
        |      <td class="pt_85">
        |        string
        |      </td>
        |    </tr>
        |    <tr>
        |      <th>
        |        Format
        |      </th>
        |      <td>
        |        String representation of the numerical OANDA-assigned TransactionID
        |      </td>
        |    </tr>
        |    <tr>
        |      <th>
        |        Example
        |      </th>
        |      <td>
        |        1523
        |      </td>
        |    </tr>
        |  </tbody>
        |</table>
      """.stripMargin

    val parameterTable = ApiModelGeneratorParsers.parseParameterTable(input)
    val expectedParameterTable = ParameterTable("Type", "string",
      Map(
        "Format" -> "String representation of the numerical OANDA-assigned TransactionID",
        "Example" -> "1523"
      )
    )

    parameterTable shouldBe expectedParameterTable
  }

}
