package eu.lbriot.shared_impl

import rx_binding.JSFunctionKeysTrait

class JSFunctionKeys(
                      private val key0: FunctionKeys.Value,
                      private val params0: Map[String,String] = Map()
                    ) extends JSFunctionKeysTrait(key0.toString,params0)

object JSFunctionKeys{
  implicit def datatoString(t:JSFunctionKeys):String={
    t.toString
  }
}