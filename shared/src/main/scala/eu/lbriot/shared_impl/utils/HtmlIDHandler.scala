package eu.lbriot.shared_impl.utils

object HtmlIDHandler extends Enumeration {

  val ENV_VARIABLE_DATA_INPUT,
  PAGE1,
  PAGE2,
  PAGE3 = Value

  implicit def dataToString(v: Value): String = {
    v.toString
  }
}
