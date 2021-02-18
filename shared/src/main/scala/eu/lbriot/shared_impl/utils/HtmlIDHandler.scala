package eu.lbriot.shared_impl.utils

object HtmlIDHandler extends Enumeration {

  val env_variable_data_input,
  page1,
  page2,
  page3,
  header,
  footer = Value

  implicit def dataToString(v: Value): String = {
    v.toString
  }
}
