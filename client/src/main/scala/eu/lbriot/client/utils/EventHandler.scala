package eu.lbriot.client.utils

import eu.lbriot.client.JSApplicationController
import eu.lbriot.shared_impl.FunctionKeys
import eu.lbriot.shared_impl.utils.{Ping, Pong}
import html_binding.mount
import org.scalajs.dom.Node
import rx_binding.JSFunctionKeysTrait

object EventHandler {

  def initialize():Unit={}



  mount.addJSFunction(
    FunctionKeys.AJAX_PING,
    {
      (param: JSFunctionKeysTrait) => {
        (e: Node) => {
          JSApplicationController.ajax(Ping())
          println(s"KEY: ${param.params.getOrElse("suffix","")}")
        }
      }
    }
  )



  mount.addJSFunction(
    FunctionKeys.AJAX_PONG,
    {
      (param: JSFunctionKeysTrait) => {
        (e: Node) => {
          JSApplicationController.ajax(Pong())
          println(s"KEY: ${param.params.getOrElse("suffix","")}")
        }
      }
    }
  )



  mount.addJSFunction(
    FunctionKeys.WS_PING,
    {
      (param: JSFunctionKeysTrait) => {
        (e: Node) => {
          JSApplicationController.ws(Ping())
          println(s"KEY: ${param.params.getOrElse("suffix","")}")
        }
      }
    }
  )



  mount.addJSFunction(
    FunctionKeys.WS_PONG,
    {
      (param: JSFunctionKeysTrait) => {
        (e: Node) => {
          JSApplicationController.ws(Pong())
          println(s"KEY: ${param.params.getOrElse("suffix","")}")
        }
      }
    }
  )

}
