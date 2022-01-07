package eu.lbriot.client.utils

import eu.lbriot.client.JSApplicationController
import eu.lbriot.client.components.ApplicationComponent
import eu.lbriot.client.components.ApplicationComponent.{pages_routing_mapping, update_main_page}
import eu.lbriot.shared.i18n.Languages
import eu.lbriot.shared_impl.utils.{AjaxPingFunctionParam, AjaxPongFunctionParam, JSFunctionParamSerializor, OnChangeLanguage, Ping, Pong, URLClickNoRedirect, WsPingFunctionParam, WsPongFunctionParam}
import html_binding.mount
import org.scalajs.dom.html.Select
import org.scalajs.dom.{Event, MouseEvent, Node}
import org.scalajs.dom.raw.Node
import rx_binding.{JSFunctionParamSerializorTrait, JSFunctionParamTrait}

import scala.scalajs.js.annotation.JSExportTopLevel

object MountHandler extends mount{

  override val jsFunctionKeysTraitSerializor: JSFunctionParamSerializorTrait = {
    new JSFunctionParamSerializor()
  }

  @JSExportTopLevel("JSFunctionRegister")
  override def JSFunctionRegister(event:Event,param: String): Unit = {

    val js_function_keys_trait = {
      val str = {
        param
          .replace("&quot;", """"""")
          .replace("\u000A","""\n""")
      }

      jsFunctionKeysTraitSerializor.decode(str)
    }
    JSFunctionRegister(js_function_keys_trait)(event)

  }

  override def JSFunctionRegister(param: JSFunctionParamTrait): Event => Unit = {
    (e: Event) => {
      param match {
        case AjaxPingFunctionParam(str) => {
          JSApplicationController.ajax(Ping())
          println(s"KEY: ${str} - MouseX: ${e.asInstanceOf[MouseEvent].clientX}")
        }
        case AjaxPongFunctionParam(str) => {
          JSApplicationController.ajax(Pong())
          println(s"KEY: ${str} - MouseX: ${e.asInstanceOf[MouseEvent].clientX}")
        }
        case WsPingFunctionParam(str) => {
          JSApplicationController.ws(Ping())
          println(s"KEY: ${str} - MouseX: ${e.asInstanceOf[MouseEvent].clientX}")
        }
        case WsPongFunctionParam(str) => {
          JSApplicationController.ws(Pong())
          println(s"KEY: ${str} - MouseX: ${e.asInstanceOf[MouseEvent].clientX}")
        }
        case URLClickNoRedirect(page) => {
          e.asInstanceOf[Event].preventDefault();
          update_main_page(pages_routing_mapping(page), true)
        }
        case OnChangeLanguage() => {
          ApplicationComponent.current_language_rx:={
            Languages.withName(
              e.asInstanceOf[Event].target.asInstanceOf[Select].value
            )
          }
          ApplicationComponent.refresh_url(true)
        }
        case _ => {
          throw new Exception(s"Missing type ${param}")
        }
      }
    }
  }
}
