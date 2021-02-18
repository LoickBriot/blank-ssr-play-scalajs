package eu.lbriot.server

import akka.actor.ActorSystem
import akka.stream.Materializer
import eu.lbriot.shared.i18n.Language
import eu.lbriot.shared_impl._
import eu.lbriot.shared_impl.utils.{EnvVariableData, Ping, Pong, ServerClientMessage, ServerClientMessageSerializor}
import javax.inject.Inject
import play.api.mvc._
import scala.concurrent.Future
import rx_binding.Var

class ApplicationController @Inject()(override val controllerComponents: ControllerComponents)
(
  implicit system: ActorSystem,
  mat: Materializer
) extends CommunicationClientActorTrait[ServerClientMessage](new ServerClientMessageSerializor())
  with BaseController {



//       _       __             _ _     _           _
//    __| | ___ / _| __ _ _   _| | |_  (_)_ __   __| | _____  _____ ___
//   / _` |/ _ \ |_ / _` | | | | | __| | | '_ \ / _` |/ _ \ \/ / _ \ __|
//  | (_| |  __/  _| (_| | |_| | | |_  | | | | | (_| |  __/>  <  __\__ \
//   \__,_|\___|_|  \__,_|\__,_|_|\__| |_|_| |_|\__,_|\___/_/\_\___|___/
//


  def default_index(
                     path:String
                   ) = Action.async { implicit request: Request[AnyContent] =>
    index_implementation(path,"FR")
  }

  def index_with_lang(
                       path:String,
                       lang:String
                     ) = Action.async { implicit request: Request[AnyContent] =>
    index_implementation(path,lang)
  }

  def index_implementation(
                            path:String,
                            lang:String
                          ) = {

    val env_variable_data = {
      new EnvVariableData(
        prod_version =  {
          if(ConfigUtils.prod_version.nonEmpty){
            ConfigUtils.prod_version
          } else {
            "development"
          }
        },
        initial_url = path,
        default_language = lang
      )
    }

    println(s"env_variable_data: ${env_variable_data}")

    val shared_html_component = new SharedHTMLComponent(
      env_variable_data
    )(Var(Language.withName(lang)), false)

    Future.successful {
      Ok(
        eu.lbriot.server.html.Application(
          shared_html_component
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
