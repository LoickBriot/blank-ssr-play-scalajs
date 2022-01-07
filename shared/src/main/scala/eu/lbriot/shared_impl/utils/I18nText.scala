package eu.lbriot.shared_impl.utils

import rx_binding.{Rx, Var}
import eu.lbriot.shared.i18n._

import scala.collection.mutable.ArrayBuffer

case class I18nText(
                     fr:String,
                     en:String
                   )(implicit current_language_rx:Var[Languages.Value], isJS:Boolean)  extends I18nTextTrait {

  def html() : scala.xml.Elem = {
    if(isJS) {
      <span>
        {
        current_language_rx.map{
          case Languages.FR => fr
          case Languages.EN => en
          case _ => throw new Exception("")
        }
        }
      </span>
    } else {
      <span>
        {
        current_language_rx.getValue() match {
          case Languages.FR => fr
          case Languages.EN => en
          case _ => throw new Exception("")
        }
        }
      </span>
    }

  }
}