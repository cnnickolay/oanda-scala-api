package org.nikosoft.oanda.api.generator

object ApiModelGeneratorRunner extends App {

  val model = ApiModelGenerator.generateScalaModel()
  println(model)

}
