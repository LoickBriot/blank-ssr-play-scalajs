package eu.lbriot.shared_impl

import eu.lbriot.shared.i18n.Languages
import eu.lbriot.shared_impl.utils._
import rx_binding.Var

case class SharedHTMLComponent(
                          env_variable_data: EnvVariableData
                        )(implicit val current_language_rx:Var[Languages.Value], val isJS:Boolean) extends I18nHandler{

  //    __ _              _                                                     _
  //   / _(_)_  _____  __| |    ___ ___  _ __ ___  _ __   ___  _ __   ___ _ __ | |_ ___
  //  | |_| \ \/ / _ \/ _` |   / __/ _ \| '_ ` _ \| '_ \ / _ \| '_ \ / _ \ '_ \| __/ __|
  //  |  _| |>  <  __/ (_| |  | (__ (_) | | | | | | |_) | (_) | | | |  __/ | | | |_\__ \
  //  |_| |_/_/\_\___|\__,_|_______\___/|_| |_| |_| .__/ \___/|_| |_|\___|_| |_|\__|___/
  //                      |_____|                 |_|


  val env_variable_data_input_html : scala.xml.Elem = {
    <input
    id={HtmlIDHandler.env_variable_data_input}
    type="hidden"
    value={s"${upickle.default.write(env_variable_data)}"
    }></input>
  }




  lazy val header_elt_html : scala.xml.Elem = {

    <nav id={HtmlIDHandler.header}
         class="navbar navbar-light fixed-top navbar-expand-xl"
         style="background-color: #EEBEDB;z-index:10">
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
          <select id="lang" onchange={OnChangeLanguage()}>
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
    <footer id={HtmlIDHandler.footer}
    style="position:fixed;bottom: 0;width: 100%;height: 40px;line-height: 40px; background-color: #F9F4F6;text-align:center">
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
    <div id={HtmlIDHandler.page1}>
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
    <div id={HtmlIDHandler.page2}>
      <div style="margin-top:100px">
        {page2_i18n.html()}
        <textarea id="text_area1" style="width:800px;height:400px"></textarea>
      </div>
    </div>
  }




  lazy val page3_html : scala.xml.Elem = {
    <div id={HtmlIDHandler.page3}>
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



  def redirection_link(page:String, maybeClass:Option[String],xml:scala.xml.Elem) : scala.xml.Elem = {
    maybeClass match {
      case Some(class_val)=> {
        <a class={class_val} href={page} onclick={URLClickNoRedirect(page)}>
          {xml}
        </a>
      }
      case visible => {
        <a href={page} onclick={URLClickNoRedirect(page)}>
          {xml}
        </a>
      }
    }
  }
}
