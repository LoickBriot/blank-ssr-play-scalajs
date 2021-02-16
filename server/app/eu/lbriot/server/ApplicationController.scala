package eu.lbriot.server

import akka.actor.{ActorSystem, Props}
import akka.stream.Materializer
import eu.lbriot.shared._
import eu.lbriot.shared.i18n.Language
import eu.lbriot.shared_impl._

import javax.inject.Inject
import play.api.libs.streams.ActorFlow
import play.api.mvc._
import play.twirl.api.Html

import scala.concurrent.Future
import scala.io.Source
import play.twirl.api.Html
import rx_binding.Var

class ApplicationController @Inject()(override val controllerComponents: ControllerComponents)(implicit system: ActorSystem, mat: Materializer) extends
  CommunicationClientActorTrait[ServerClientMessage](new ServerClientMessageSerializor()) with BaseController{


  def index2(path:String) = Action.async { implicit request: Request[AnyContent] =>
    func(path,"FR")
  }

  def index(path:String, lang:String) = Action.async { implicit request: Request[AnyContent] =>
    func(path,lang)
  }

  def func(path:String, lang:String) = {

    val html_el = new SharedHTMLComponent(Var(Language.withName(lang)))
    println(s"PATH IS '${path}'")

    val html = {
      path match{
        case "page1" => {
          html_el.page1.toString()
        }
        case "page2" => {
          html_el.page2.toString()
        }
        case "page3" => {
          html_el.page3.toString()
        }
        case _ => "<div></div>"

      }
    }
    Future.successful {
      Ok(
        eu.lbriot.server.html.Application(
          ConfigUtils.prod_version,
          Html.apply(
            html
          ),
          Html.apply(
            html_el.header_elt.toString
          ),
          Html.apply(
            html_el.footer_elt.toString
          )
        )
      )
    }
  }

  //        _ _            _                                               _           _   _
  //    ___| (_) ___ _ __ | |_    ___ ___  _ __ ___  _ __ ___  _   _ _ __ (_) ___ __ _| |_(_) ___  _ __
  //   / __| | |/ _ \ '_ \| __|  / __/ _ \| '_ ` _ \| '_ ` _ \| | | | '_ \| |/ __/ _` | __| |/ _ \| '_ \
  //  | (__| | |  __/ | | | |_  | (__ (_) | | | | | | | | | | | |_| | | | | | (__ (_| | |_| | (_) | | | |
  //   \___|_|_|\___|_| |_|\__|  \___\___/|_| |_| |_|_| |_| |_|\__,_|_| |_|_|\___\__,_|\__|_|\___/|_| |_|
  //


  def on_received_message(message:ServerClientMessage) : Option[ServerClientMessage] = {
    message match {
      case Ping() => {
        println("Ping")
        Some(Pong())
      }
      case Pong() => {
        println("Pong")
        Some(Ping())
      }
      case _ => throw new Exception("")
    }
  }


}
