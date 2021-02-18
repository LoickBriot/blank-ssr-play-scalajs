package eu.lbriot.shared_impl

import eu.lbriot.shared.i18n.Language
import eu.lbriot.shared_impl.utils._
import rx_binding.Var
import scala.collection.mutable
import I18nHandler._

case class SharedHTMLComponent(
                          current_language_rx0:Var[Language.Value],
                          env_variable_data: EnvVariableData
                        ) {




  //   _       _ _   _       _ _          _   _
  //  (_)_ __ (_) |_(_) __ _| (_)______ _| |_(_) ___  _ __
  //  | | '_ \| | __| |/ _` | | |_  / _` | __| |/ _ \| '_ \
  //  | | | | | | |_| | (_| | | |/ / (_| | |_| | (_) | | | |
  //  |_|_| |_|_|\__|_|\__,_|_|_/___\__,_|\__|_|\___/|_| |_|
  //


  def initialize() : Unit = {
    footer_html
    header_elt_html
    page1_html
    page2_html
    page3_html
  }



  //                       _          _                   _       _     _
  //   _ __   ___  ___  __| | ___  __| | __   ____ _ _ __(_) __ _| |__ | | ___ ___
  //  | '_ \ / _ \/ _ \/ _` |/ _ \/ _` | \ \ / / _` | '__| |/ _` | '_ \| |/ _ \ __|
  //  | | | |  __/  __/ (_| |  __/ (_| |  \ V / (_| | |  | | (_| | |_) | |  __\__ \
  //  |_| |_|\___|\___|\__,_|\___|\__,_|   \_/ \__,_|_|  |_|\__,_|_.__/|_|\___|___/
  //


  implicit val current_language_rx : Var[Language.Value] = {
    current_language_rx0
  }




  //    __ _              _                                                     _
  //   / _(_)_  _____  __| |    ___ ___  _ __ ___  _ __   ___  _ __   ___ _ __ | |_ ___
  //  | |_| \ \/ / _ \/ _` |   / __/ _ \| '_ ` _ \| '_ \ / _ \| '_ \ / _ \ '_ \| __/ __|
  //  |  _| |>  <  __/ (_| |  | (__ (_) | | | | | | |_) | (_) | | | |  __/ | | | |_\__ \
  //  |_| |_/_/\_\___|\__,_|_______\___/|_| |_| |_| .__/ \___/|_| |_|\___|_| |_|\__|___/
  //                      |_____|                 |_|


  val env_variable_data_input_html : scala.xml.Elem = {
    <input
    id={HtmlIDHandler.ENV_VARIABLE_DATA_INPUT}
    type="hidden"
    value={s"${upickle.default.write(env_variable_data)}"
    }></input>
  }




  lazy val header_elt_html : scala.xml.Elem = {


    <nav class="navbar navbar-light fixed-top navbar-expand-xl" style="background-color: #EEBEDB;z-index:10">
      <a href="#" class="navbar-brand" style="margin-right:80px">
        <span><b>{"BLANK SSR"}</b> {"Play ScalaJS"}</span>
      </a>
      <button type="button" class="navbar-toggler" data-toggle="collapse" data-target="#navbarCollapse">
        <span class="navbar-toggler-icon"></span>
      </button>

      <div class="collapse navbar-collapse justify-content-between" id="navbarCollapse">
        <div class="navbar-nav">
          {redirection_link("page1",Some("nav-item nav-link"),{page1_link_i18n.html()})}
          {redirection_link("page2",Some("nav-item nav-link"),{page2_link_i18n.html()})}
          {redirection_link("page3",Some("nav-item nav-link"),{page3_link_i18n.html()})}
        </div>

        <div class="navbar-custom-menu" >
          <select id="lang">
            {
            if("FR"==current_language_rx.getValue().toString){
              <option value="FR" selected={true}>FR</option>
            } else {
              <option value="FR">FR</option>
            }
            }
            {
            if("EN"==current_language_rx.getValue().toString){
              <option value="EN" selected={true}>EN</option>
            } else {
              <option value="EN">EN</option>
            }
            }
          </select>
        </div>

      </div>
    </nav>
  }



  lazy val footer_html : scala.xml.Elem = {
    <footer style="position:fixed;bottom: 0;width: 100%;height: 40px;line-height: 40px; background-color: #F9F4F6;text-align:center">
      <div class="container">
        <span><b>Blank SSR</b> Play ScalaJS</span>  - Version {env_variable_data.prod_version}
      </div>
    </footer>
  }







  //       _                             _
  //    __| |_   _ _ __   __ _ _ __ ___ (_) ___   _ __   __ _  __ _  ___ ___
  //   / _` | | | | '_ \ / _` | '_ ` _ \| |/ __| | '_ \ / _` |/ _` |/ _ \ __|
  //  | (_| | |_| | | | | (_| | | | | | | | (__  | |_) | (_| | (_| |  __\__ \
  //   \__,_|\__, |_| |_|\__,_|_| |_| |_|_|\___| | .__/ \__,_|\__, |\___|___/
  //         |___/                               |_|          |___/



  lazy val current_page_html : scala.xml.Elem = {
    env_variable_data.initial_url match{
      case "page1" => {
        page1_html
      }
      case "page2" => {
        page2_html
      }
      case "page3" => {
        page3_html
      }
      case _ => <div></div>
    }
  }






  lazy val page1_html : scala.xml.Elem = {
    <div id={HtmlIDHandler.PAGE1}>
      <div style="margin-top:100px">


        {page1_i18n.html()}


        <button onclick={AjaxPingFunctionParam("abc")}>
          {ajax_ping_i18n.html()}
        </button>

        <button onclick={AjaxPongFunctionParam("def")}>
          {ajax_pong_i18n.html()}
        </button>

        <button onclick={WsPingFunctionParam("ghi")}>
          {ws_ping_i18n.html()}
        </button>

        <button onclick={WsPongFunctionParam("jkl")}>
          {ws_pong_i18n.html()}
        </button>

      </div>
    </div>
  }






  lazy val page2_html : scala.xml.Elem = {
    <div id={HtmlIDHandler.PAGE2}>
      <div style="margin-top:100px">
        {page2_i18n.html()}
        <textarea id="text_area1" style="width:800px;height:400px"></textarea>
      </div>
    </div>
  }




  lazy val page3_html : scala.xml.Elem = {
    <div id={HtmlIDHandler.PAGE3}>
      <div style="margin-top:100px">
        {page3_i18n.html()}
        <textarea id="text_area2" style="width:800px;height:400px"></textarea>
      </div>
    </div>
  }



  //         _   _ _                  _   _               _
  //   _   _| |_(_) |  _ __ ___   ___| |_| |__   ___   __| |___
  //  | | | | __| | | | '_ ` _ \ / _ \ __| '_ \ / _ \ / _` / __|
  //  | |_| | |_| | | | | | | | |  __/ |_| | | | (_) | (_| \__ \
  //   \__,_|\__|_|_| |_| |_| |_|\___|\__|_| |_|\___/ \__,_|___/
  //


  var j = 0
  val seq = mutable.HashMap[String,String]()
  def redirection_link(page:String, maybeClass:Option[String],xml:scala.xml.Elem) : scala.xml.Elem = {
    j+=1
    seq.put(s"redirection_${j}", page)

    maybeClass match {
      case Some(class_val)=> {
        <a class={class_val} id={s"redirection_${j}"} href={page}>
          {xml}
        </a>
      }
      case visible => {
        <a id={s"redirection_${j}"} href={page}>
          {xml}
        </a>
      }
    }
  }
}
