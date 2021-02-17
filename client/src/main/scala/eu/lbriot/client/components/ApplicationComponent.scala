package eu.lbriot.client.components

import components.{ApplicationComponentTrait, JSComponentTrait, JSPageComponentTrait}
import eu.lbriot.client.JSApplicationController
import eu.lbriot.shared_impl.utils.I18nText
import eu.lbriot.client.components.pages._
import eu.lbriot.shared.i18n.Language
import eu.lbriot.shared_impl.SharedHTMLComponent
import rx_binding.Var
import html_binding.mount._
import org.scalajs.dom
import org.scalajs.dom.{Event, document}
import org.scalajs.dom.raw.{HTMLAnchorElement, HTMLLinkElement, HTMLSelectElement, HTMLSpanElement, UIEvent}

import scala.collection.mutable.ListBuffer
import scala.xml.Node

object ApplicationComponent extends ApplicationComponentTrait[I18nText] {

  //   _
  //  | | __ _ _ __   __ _ _   _  __ _  __ _  ___
  //  | |/ _` | '_ \ / _` | | | |/ _` |/ _` |/ _ \
  //  | | (_| | | | | (_| | |_| | (_| | (_| |  __/
  //  |_|\__,_|_| |_|\__, |\__,_|\__,_|\__, |\___|
  //                 |___/             |___/


  override val default_language : Language.Value = Language.FR

  override val active_languages: Seq[Language.Value] = {
    Seq(Language.FR,Language.EN)
  }




  //                                                                             _
  //   _ __   __ _  __ _  ___    ___ ___  _ __ ___  _ __   ___  _ __   ___ _ __ | |_ ___
  //  | '_ \ / _` |/ _` |/ _ \  / __/ _ \| '_ ` _ \| '_ \ / _ \| '_ \ / _ \ '_ \| __/ __|
  //  | |_) | (_| | (_| |  __/ | (__ (_) | | | | | | |_) | (_) | | | |  __/ | | | |_\__ \
  //  | .__/ \__,_|\__, |\___|  \___\___/|_| |_| |_| .__/ \___/|_| |_|\___|_| |_|\__|___/
  //  |_|          |___/                           |_|

  val page1_rx : Var[Page1Component] = Var()
  val page2_rx : Var[Page2Component] = Var()
  val page3_rx : Var[Page3Component] = Var()


  def load_empty_page(maybe_page_rx:Var[_<:JSPageComponentTrait]) : Unit = {
    maybe_page_rx match {
      case page_rx if page_rx==page1_rx => {
        page1_rx:= Page1Component()
      }
      case page_rx if page_rx==page2_rx => {
        page2_rx := Page2Component()
      }
      case page_rx if page_rx==page3_rx => {
        page3_rx := Page3Component()
      }
      case _ =>
    }
  }




  //                                            _   _
  //   _ __   __ _  __ _  ___   _ __ ___  _   _| |_(_)_ __   __ _
  //  | '_ \ / _` |/ _` |/ _ \ | '__/ _ \| | | | __| | '_ \ / _` |
  //  | |_) | (_| | (_| |  __/ | | | (_) | |_| | |_| | | | | (_| |
  //  | .__/ \__,_|\__, |\___| |_|  \___/ \__,_|\__|_|_| |_|\__, |
  //  |_|          |___/                                    |___/


  override val maybe_active_page_rx: Var[Var[_<:JSPageComponentTrait]] = Var(page1_rx)

  override val pages_routing_mapping : Map[String,Var[_<:JSPageComponentTrait]] = Map(
    "page1" -> page1_rx,
    "page2" -> page2_rx,
    "page3" -> page3_rx,
  )





  // TODO Send Env Variable list to the client side
  lazy val hmtl = new SharedHTMLComponent(
    current_language_rx,
    JSApplicationController.env_variable_data
  )


  //   _       _ _                _   _
  //  (_)_ __ (_) |_    __ _  ___| |_(_) ___  _ __  ___
  //  | | '_ \| | __|  / _` |/ __| __| |/ _ \| '_ \/ __|
  //  | | | | | | |_  | (_| | (__| |_| | (_) | | | \__ \
  //  |_|_| |_|_|\__|  \__,_|\___|\__|_|\___/|_| |_|___/
  //

  def on_init_action_impl(): Unit = {
    router_init()

    // Initialize HTML views
    hmtl.initialize()


    // Load all views once
    val a = maybe_active_page_rx.getValue()
    pages_routing_mapping.foreach{case(k,v)=>
      update_main_page(v,false)
    }
    update_main_page(a,false)


    // Add link actions on buttons
    hmtl.seq.foreach{case(id,page)=>
      try {
        val link_html = document.getElementById(id).asInstanceOf[HTMLAnchorElement]
        println(s"${id} -> ${link_html}")
        link_html.onclick = { (e: Event) =>
          e.preventDefault();
          update_main_page(pages_routing_mapping(page), true)
        }
      } catch {
        case e : Throwable=> e.printStackTrace()
      }
    }


    // Add text translation events
    document.getElementById("lang").asInstanceOf[HTMLSelectElement].onchange ={
      (e:Event)=> {
        ApplicationComponent.current_language_rx.update(_ =>
          Language.withName(e.target.asInstanceOf[HTMLSelectElement].value)
        )
        ApplicationComponent.refresh_url(true)}


        I18nText.seq.distinct.foreach{elt=>
          try {

            val html_elt = document.getElementById(elt.id)

            if(html_elt!=null) {
              println(html_elt)
              html_elt.asInstanceOf[HTMLSpanElement].innerText = {
                Language.withName(e.target.asInstanceOf[HTMLSelectElement].value) match {
                  case Language.FR => elt.fr
                  case Language.EN => elt.en
                  case _ => throw new Exception("")
                }
              }
            }
          } catch {
            case e : Throwable=> e.printStackTrace()
          }
        }

    }

  }

  def on_set_visible_action_impl(): Unit = {}

  def on_set_hidden_action_impl(): Unit = {}


  protected val children_components : ListBuffer[Var[_<:JSComponentTrait]] = {
    ListBuffer(
      page1_rx,
      page2_rx,
      page3_rx,
    )
  }




  //         _
  //  __   ___) _____      __
  //  \ \ / / |/ _ \ \ /\ / /
  //   \ V /| |  __/\ V  V /
  //    \_/ |_|\___| \_/\_/
  //

  protected override def view_impl(): Node = {

    // The Main Application Component
    <div style="overflow-x: hidden;overflow-y: hidden;">
      <div>
        <div>
          {page1_rx.map { _.view() }}
          {page2_rx.map { _.view() }}
          {page3_rx.map { _.view() }}
        </div>
      </div>
    </div>
  }


}
