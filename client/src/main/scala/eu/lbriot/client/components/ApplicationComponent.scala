package eu.lbriot.client.components

import components.{ApplicationComponentTrait, FixedComponentTrait, JSComponentTrait, JSPageComponentTrait}
import eu.lbriot.client.JSApplicationController
import eu.lbriot.client.components.fixed.{FooterComponent, HeaderComponent}
import eu.lbriot.shared_impl.utils.{EnvVariableData, HtmlIDHandler, I18nText}
import eu.lbriot.client.components.pages._
import eu.lbriot.shared.i18n.{Countries, Languages}
import eu.lbriot.shared_impl.SharedHTMLComponent
import org.scalajs.dom.html.Input
import rx_binding.Var
import org.scalajs.dom.{Event, document}
import org.scalajs.dom.raw.{HTMLAnchorElement, HTMLSelectElement, HTMLSpanElement}

import scala.collection.mutable.ListBuffer
import scala.xml.Node


object ApplicationComponent extends ApplicationComponentTrait[I18nText] {

  //                        _              _                   _       _     _
  //   _ __ ___  __ _ _   _(_)_ __ ___  __| | __   ____ _ _ __(_) __ _| |__ | | ___ ___
  //  | '__/ _ \/ _` | | | | | '__/ _ \/ _` | \ \ / / _` | '__| |/ _` | '_ \| |/ _ \ __|
  //  | | |  __/ (_| | |_| | | | |  __/ (_| |  \ V / (_| | |  | | (_| | |_) | |  __\__ \
  //  |_|  \___|\__, |\__,_|_|_|  \___|\__,_|   \_/ \__,_|_|  |_|\__,_|_.__/|_|\___|___/
  //               |_|

  override val envVariableData : EnvVariableData = {
    upickle.default.read[EnvVariableData](
      document
        .getElementById(HtmlIDHandler.env_variable_data_input)
        .asInstanceOf[Input]
        .value
    )
  }

  lazy val sharedHTMLComponent : SharedHTMLComponent = {
    SharedHTMLComponent(envVariableData)(current_language_rx, true)
  }

  override val initial_url: String = HtmlIDHandler.page1


  // TODO put in shared
  override val active_languages: Seq[Languages.Value] = {
    Seq(
      Languages.FR,
      Languages.EN
    )
  }
  override val active_countries: Seq[Countries.Value] = {
    Seq(
      Countries.FR,
      Countries.GB
    )
  }

  //                                                                             _
  //   _ __   __ _  __ _  ___    ___ ___  _ __ ___  _ __   ___  _ __   ___ _ __ | |_ ___
  //  | '_ \ / _` |/ _` |/ _ \  / __/ _ \| '_ ` _ \| '_ \ / _ \| '_ \ / _ \ '_ \| __/ __|
  //  | |_) | (_| | (_| |  __/ | (__ (_) | | | | | | |_) | (_) | | | |  __/ | | | |_\__ \
  //  | .__/ \__,_|\__, |\___|  \___\___/|_| |_| |_| .__/ \___/|_| |_|\___|_| |_|\__|___/
  //  |_|          |___/                           |_|

  val header : HeaderComponent = HeaderComponent()
  val footer : FooterComponent = FooterComponent()
  val page1 : Page1Component = Page1Component()
  val page2 : Page2Component = Page2Component()
  val page3 : Page3Component = Page3Component()


  override lazy protected val children_components: ListBuffer[_<:JSComponentTrait] = {
    ListBuffer(
      header,
      page1,
      page2,
      page3,
      footer
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
          { header.view() }
          { page1.view() }
          { page2.view() }
          { page3.view() }
          { footer.view() }
        </div>
      </div>
    </div>
  }


  //   _       _ _                _   _
  //  (_)_ __ (_) |_    __ _  ___| |_(_) ___  _ __  ___
  //  | | '_ \| | __|  / _` |/ __| __| |/ _ \| '_ \/ __|
  //  | | | | | | |_  | (_| | (__| |_| | (_) | | | \__ \
  //  |_|_| |_|_|\__|  \__,_|\___|\__|_|\___/|_| |_|___/
  //

  def on_init_action_impl(): Unit = {}

  def on_set_visible_action_impl(): Unit = {}

  def on_set_hidden_action_impl(): Unit = {}



}
