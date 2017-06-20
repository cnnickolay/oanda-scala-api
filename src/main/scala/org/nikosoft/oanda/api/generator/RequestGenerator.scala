package org.nikosoft.oanda.api.generator

import com.gargoylesoftware.htmlunit.WebClient
import com.gargoylesoftware.htmlunit.html.{HtmlElement, HtmlPage}

import scala.collection.JavaConverters._
/**
  * Created by Nikolai Cherkezishvili on 20/06/2017
  */
object RequestGenerator {

  def extractOperations(url: String) = {
    val client = new WebClient()
    client.getOptions.setJavaScriptEnabled(false)
    val page = client.getPage[HtmlPage](url)

    val methods = page.getByXPath[HtmlElement]("//div[contains(@class, 'endpoint_header')]//span[contains(@class, 'method')]").asScala.toList.map(_.asText())
    val paths = page.getByXPath[HtmlElement]("//div[contains(@class, 'endpoint_header')]//span[contains(@class, 'path')]").asScala.toList.map(_.asXml())
    val bodies = page.getByXPath[HtmlElement]("//div[contains(@class, 'endpoint_body')]").asScala.toList

    paths.foreach(println)
  }

}

object Main extends App {
  RequestGenerator.extractOperations("http://developer.oanda.com/rest-live-v20/account-ep/")
}
