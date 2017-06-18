package org.nikosoft.oanda.api.generator

import com.gargoylesoftware.htmlunit.WebClient
import com.gargoylesoftware.htmlunit.html.{HtmlDivision, HtmlElement, HtmlPage, HtmlSpan}

import scala.collection.JavaConverters._

object ApiModelGenerator {

  def generateScalaModel(): Unit = {
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
          println(parameterTable)
        case (None, Some(jsonSchemaElt)) =>
          val jsonFields = ApiModelGeneratorParsers.parseJsonElementField(jsonSchemaElt.asXml())
        //        jsonFields.foreach(println)
        case _ => throw new RuntimeException("woiejfaiuerhf")
      }

      println("-------------")
    }
  }

}