package eu.lbriot.shared_impl

import eu.lbriot.shared.i18n.Language
import eu.lbriot.shared_impl.language._
import rx_binding.Var
import scala.collection.mutable


case class SharedHTMLComponent(current_language_rx0:Var[Language.Value]) {

  implicit val current_language_rx : Var[Language.Value] = {
    current_language_rx0
  }


  def initialize() : Unit = {
    footer_elt
    header_elt
    page1
    page2
    page3
  }



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



  val page1_link_i18n = I18nText(
    id="link_page_1",
    fr = "Onglet 1",
    en = "Tab 1"
  )

  val page2_link_i18n = I18nText(
    id="link_page_2",
    fr = "Onglet 2",
    en = "Tab 2"
  )

  val page3_link_i18n = I18nText(
    id="link_page_3",
    fr = "Onglet 3",
    en = "Tab 3"
  )


  lazy val header_elt = {


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



  lazy val footer_elt = {
    <footer style="position:fixed;bottom: 0;width: 100%;height: 40px;line-height: 40px; background-color: #F9F4F6;text-align:center">
      <div class="container">
        <span><b>Maîtresse</b> Licorne</span>  - Version 1.0.8
      </div>
    </footer>
  }



  val page1_i18n : I18nText = {
    I18nText(
      id="span_page1",
      fr = "Texte 1",
      en = "Text 1"
    )
  }

  lazy val page1 = {
    <div id="page1">
      <div style="margin-top:100px">
        {page1_i18n.html()}
        <button id="ajax_ping">AJAX Ping</button>
        <button id="ws_ping">WS Ping</button>
        <button id="ajax_pong">AJAX Pong</button>
        <button id="ws_pong">WS Pong</button>
      </div>
    </div>
  }



  val page2_i18n : I18nText = {
    I18nText(
      id="span_page2",
      fr = "Texte 2",
      en = "Text 2"
    )
  }

  lazy val  page2 = {
    <div id="page2">
      <div style="margin-top:100px">
        {page2_i18n.html()}
        <textarea id="text_area1" style="width:800px;height:400px"></textarea>
      </div>
    </div>
  }


  val page3_i18n : I18nText = {
    I18nText(
      id="span_page3",
      fr = "Texte 3",
      en = "Text 3"
    )
  }

  lazy val page3 = {
    <div id="page3" >
      <div style="margin-top:100px">
        {page3_i18n.html()}
        <textarea id="text_area2" style="width:800px;height:400px"></textarea>
      </div>
    </div>
  }



}
