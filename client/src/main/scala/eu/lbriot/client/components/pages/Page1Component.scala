package eu.lbriot.client.components.pages

import components.{ApplicationComponentTrait, JSComponentTrait, JSPageComponentTrait}
import eu.lbriot.client.JSApplicationController
import eu.lbriot.client.components.ApplicationComponent
import eu.lbriot.shared_impl.SharedHTMLComponent
import eu.lbriot.shared_impl.utils.{Ping, Pong}
import rx_binding.Var
import html_binding.mount._
import org.scalajs.dom
import org.scalajs.dom.raw.{Event, HTMLButtonElement}

import scala.collection.mutable.ListBuffer

case class Page1Component() extends JSPageComponentTrait {

  //   _     _             _   _     _
  //  | |__ | |_ _ __ ___ | | (_) __| |___
  //  | '_ \| __| '_ ` _ \| | | |/ _` / __|
  //  | | | | |_| | | | | | | | | (_| \__ \
  //  |_| |_|\__|_| |_| |_|_| |_|\__,_|___/
  //




  //                      ___
  //  __   ____ _ _ __   ( _ )    _ ____  _____
  //  \ \ / / _` | '__|  / _ \/\ | '__\ \/ / __|
  //   \ V / (_| | |    | (_>  < | |   >  <\__ \
  //    \_/ \__,_|_|     \___/\/ |_|  /_/\_\___/
  //





  //              _                                        _
  //   _   _ _ __| |  _ __   __ _ _ __ __ _ _ __ ___   ___| |_ ___ _ __ ___
  //  | | | | '__| | | '_ \ / _` | '__/ _` | '_ ` _ \ / _ \ __/ _ \ '__/ __|
  //  | |_| | |  | | | |_) | (_| | | | (_| | | | | | |  __/ |_  __/ |  \__ \
  //   \__,_|_|  |_| | .__/ \__,_|_|  \__,_|_| |_| |_|\___|\__\___|_|  |___/
  //                 |_|

  val params_data : Seq[(String,(Var[_<:Any],(String,Boolean)=>Unit))]= {
    Seq[(String,(Var[_<:Any],(String,Boolean)=>Unit))]()
  }




  //   _       _ _                _   _
  //  (_)_ __ (_) |_    __ _  ___| |_(_) ___  _ __  ___
  //  | | '_ \| | __|  / _` |/ __| __| |/ _ \| '_ \/ __|
  //  | | | | | | |_  | (_| | (__| |_| | (_) | | | \__ \
  //  |_|_| |_|_|\__|  \__,_|\___|\__|_|\___/|_| |_|___/
  //


  override val application_component: ApplicationComponentTrait[_] = ApplicationComponent


  def on_init_action_impl() : Unit = {
    dom.document.getElementById("ajax_ping").asInstanceOf[HTMLButtonElement].onclick = {(e:Event)=>
      JSApplicationController.ajax(Ping())
    }

    dom.document.getElementById("ws_ping").asInstanceOf[HTMLButtonElement].onclick = {(e:Event)=>
      JSApplicationController.ws(Ping())
    }

    dom.document.getElementById("ajax_pong").asInstanceOf[HTMLButtonElement].onclick = {(e:Event)=>
      JSApplicationController.ajax(Pong())
    }

    dom.document.getElementById("ws_pong").asInstanceOf[HTMLButtonElement].onclick = {(e:Event)=>
      JSApplicationController.ws(Pong())
    }
  }

  def on_set_visible_action_impl() : Unit = {}

  def on_set_hidden_action_impl() : Unit = {}


  protected val children_components : ListBuffer[Var[_<:JSComponentTrait]] = {
    ListBuffer()
  }



  //         _
  //  __   ___) _____      __
  //  \ \ / / |/ _ \ \ /\ / /
  //   \ V /| |  __/\ V  V /
  //    \_/ |_|\___| \_/\_/
  //
  override val maybe_ssr_id = Some("page1")

  def view_impl() : scala.xml.Node = {
    ApplicationComponent.hmtl.page1_html
  }


}
