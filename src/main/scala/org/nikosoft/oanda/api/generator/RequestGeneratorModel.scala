package org.nikosoft.oanda.api.generator

/**
  * Created by Nikolai Cherkezishvili on 20/06/2017
  */
object RequestGeneratorModel {

  case class RequestBody
  case class Request(method: String, path: String, description: String, )

}
