package eu.lbriot.shared_impl.utils

import rx_binding.{JSFunctionParamSerializorTrait, JSFunctionParamTrait}
import upickle.default.{macroRW, ReadWriter => RW}


case class JSFunctionParamSerializor() extends JSFunctionParamSerializorTrait {
  def encode(message:JSFunctionParamTrait): String = {
    upickle.default.write(message.asInstanceOf[JSFunctionParam])
  }
  def decode(str:String): JSFunctionParamTrait = {
    upickle.default.read[JSFunctionParam](str)
  }
}


sealed trait JSFunctionParam extends JSFunctionParamTrait{
  implicit val serializor : JSFunctionParamSerializorTrait = {
    JSFunctionParamSerializor()
  }
}

object JSFunctionParam  {
  implicit def rw: RW[JSFunctionParam] = RW.merge(
    AjaxPingFunctionParam.rw,
    AjaxPongFunctionParam.rw,
    WsPingFunctionParam.rw,
    WsPongFunctionParam.rw,
    URLClickNoRedirect.rw,
    OnChangeLanguage.rw
  )

  implicit def datatoString(t:JSFunctionParam):String={
    t.toString
  }
}



case class AjaxPingFunctionParam(str:String) extends JSFunctionParam
object AjaxPingFunctionParam{  implicit def rw: RW[AjaxPingFunctionParam] = macroRW }

case class AjaxPongFunctionParam(str:String) extends JSFunctionParam
object AjaxPongFunctionParam{  implicit def rw: RW[AjaxPongFunctionParam] = macroRW }

case class WsPingFunctionParam(str:String) extends JSFunctionParam
object WsPingFunctionParam{  implicit def rw: RW[WsPingFunctionParam] = macroRW }

case class WsPongFunctionParam(str:String) extends JSFunctionParam
object WsPongFunctionParam{  implicit def rw: RW[WsPongFunctionParam] = macroRW }

case class URLClickNoRedirect(page:String) extends JSFunctionParam
object URLClickNoRedirect{  implicit def rw: RW[URLClickNoRedirect] = macroRW }

case class OnChangeLanguage() extends JSFunctionParam
object OnChangeLanguage{  implicit def rw: RW[OnChangeLanguage] = macroRW }



