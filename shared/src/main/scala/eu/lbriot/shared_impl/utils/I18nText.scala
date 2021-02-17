package eu.lbriot.shared_impl.utils

import rx_binding.{Rx, Var}
import eu.lbriot.shared.i18n._

import scala.collection.mutable.ArrayBuffer

case class I18nText(
                     id:String,
                     fr:String,
                     en:String
                   )  extends I18nTextTrait {

  I18nText.seq.append(this)

  override def rx()(implicit current_language_rx:Var[Language.Value]): Rx[String] = {
    for(
      lang <- current_language_rx
    ) yield {
      lang match {
        case Language.FR => fr
        case Language.EN => en
        case _ => throw new Exception("")
      }
    }
  }

  def html()(implicit current_language_rx:Var[Language.Value]) : scala.xml.Elem = {
    <span id={id}>{
      current_language_rx.getValue() match {
        case Language.FR => fr
        case Language.EN => en
        case _ => throw new Exception("")
      }
      }
    </span>
  }
}

object I18nText{
  val seq = ArrayBuffer[I18nText]()
}