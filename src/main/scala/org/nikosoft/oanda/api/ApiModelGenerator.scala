package org.nikosoft.oanda.api

import com.gargoylesoftware.htmlunit.WebClient
import com.gargoylesoftware.htmlunit.html.{HtmlDivision, HtmlElement, HtmlPage, HtmlSpan}

import scala.collection.JavaConverters._

object ApiModelGenerator extends App {

  case class JsonObjectField(description: String, name: String, `type`: String)

  val client = new WebClient()
  client.getOptions.setJavaScriptEnabled(false)
  val htmlPage = client.getPage[HtmlPage]("http://developer.oanda.com/rest-live-v20/transaction-df/")

  val apiSection = htmlPage.getElementById("content-api-section")

  val endpointHeaders = apiSection.getByXPath[HtmlDivision](".//div[@class='endpoint_header']").asScala.toList
  val definitionBody = apiSection.getByXPath[HtmlDivision](".//div[contains(@class, 'definition_body')]").asScala.toList

  (endpointHeaders, definitionBody).zipped.map { case (headerElt, definitionElt) =>
//    println(header)

    val title = headerElt.getFirstByXPath[HtmlSpan](".//span[@class='method']").asText()
    val definition = headerElt.getFirstByXPath[HtmlSpan](".//span[@class='definition']").asText()

    val parameterTableOption = Option(definitionElt.getFirstByXPath[HtmlElement]("./table[@class='parameter_table']"))
    val jsonSchemaOption = Option(definitionElt.getFirstByXPath[HtmlElement]("./pre[@class='json_schema']"))

    println(title)
    println(definition)
    (parameterTableOption, jsonSchemaOption) match {
      case (Some(parameterTableElt), None) =>
        val parameterTable = parameterTableElt.asXml()
        parameterTable.lines.toList.init.tail
        println(parameterTable)

      case (None, Some(jsonSchema)) => println(jsonSchema.asXml())
      case _ => throw new RuntimeException("woiejfaiuerhf")
    }

    println("-------------")
  }


}
