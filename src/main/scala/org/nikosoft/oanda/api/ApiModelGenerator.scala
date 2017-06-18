package org.nikosoft.oanda.api

import com.gargoylesoftware.htmlunit.WebClient
import com.gargoylesoftware.htmlunit.html.{HtmlDivision, HtmlElement, HtmlPage, HtmlSpan}

import scala.collection.JavaConverters._

object ApiModelGenerator extends App {

  case class JsonObjectField(description: String = "", name: String = "", `type`: String = "", default: String = "")

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
      case (None, Some(jsonSchemaElt)) =>
        val jsonFields = parseJsonElementField(jsonSchemaElt.asXml())
        jsonFields.foreach(println)
      case _ => throw new RuntimeException("woiejfaiuerhf")
    }

    println("-------------")
  }

  def parseJsonElementField(jsonElement: String): Seq[JsonObjectField] = {
    val simpleTypeWithDefaultRegex = """^(\w+) : \((\w+), default=(\w+)\),?$""".r
    val simpleTypeRegex = """^(\w+) : \((\w+)\),?$""".r
    val complexTypeRegex = """^(\w+) : \(.*>(\w+)<.*\),?$""".r

    def augmentField: (String, JsonObjectField) => JsonObjectField = {
      case (line, field) if line == "#" => field
      case (line, field) if line.startsWith("#") => field.copy(description = (field.description + " " + line.drop(2)).trim)
      case (simpleTypeRegex(fieldName, fieldType), field) => field.copy(name = fieldName, `type` = fieldType)
      case (simpleTypeWithDefaultRegex(fieldName, fieldType, defaultValue), field) => field.copy(name = fieldName, `type` = fieldType, default = defaultValue)
      case (complexTypeRegex(fieldName, fieldType), field) => field.copy(name = fieldName, `type` = fieldType)
      case (_, field) => field
    }

    def initializeField: (Seq[JsonObjectField], String) => Seq[JsonObjectField] = {
      case (Nil, line) => Seq(augmentField(line, JsonObjectField()))
      case (fields @ head +: _, line) if line.startsWith("#") && !head.name.isEmpty => augmentField(line, JsonObjectField()) +: fields
      case (fields @ head +: tail, line) => augmentField(line, head) +: tail
    }

    def normalizeLines: (Seq[String], String) => Seq[String] = {
      case (_normalized, "#") => _normalized
      case (head +: tail, line) if head.startsWith("#") && line.startsWith("#") => (head + line.drop(1)) +: tail
      case (head +: tail, line) if line.endsWith(">") || head.endsWith(">") => (head + line) +: tail
      case (_normalized, line) => line +: _normalized
    }

    val normalizedLines = jsonElement.lines.toList
      .map(_.trim)
      .filterNot { line =>
        line.isEmpty || line.startsWith("{") || line.startsWith("}")
      }.init.tail
      .foldLeft(Seq.empty[String])(normalizeLines)
      .reverse

    val lines = normalizedLines
      .foldLeft(Seq.empty[JsonObjectField]) (initializeField)
      .reverse

    lines
  }

}
