package eu.lbriot.client

import html_binding.mount
import org.scalajs.dom

import scala.scalajs.js.annotation.JSExportTopLevel
import akka.actor.{ActorSystem, Props}
import communication.ServerCommunicationActorTrait
import eu.lbriot.client.components.ApplicationComponent
import eu.lbriot.client.components.ApplicationComponent.update_main_page
import eu.lbriot.shared_impl._
import org.scalajs.dom.html.TextArea
import org.scalajs.dom.{Event, document}



// TODO Clean js libs files in public

object JSApplicationController extends ServerCommunicationActorTrait[ServerClientMessage](
  "api",
  new ServerClientMessageSerializor(),
  true,
  true
) {


  override protected def on_received_message(message:ServerClientMessage) : Option[ServerClientMessage] = {

    message match {
      case Ping() => {
        println("Ping")
        document.getElementById("text_area1").asInstanceOf[TextArea].value += "\nPing"
        None
      }
      case Pong() => {
        println("Pong")
        document.getElementById("text_area2").asInstanceOf[TextArea].value += "\nPong"
        None
      }
      case _ => throw new Exception("")
    }

  }

  //              _                            _       _
  //    ___ _ __ | |_ _ __ _   _   _ __   ___ (_)_ __ | |_
  //   / _ \ '_ \| __| '__| | | | | '_ \ / _ \| | '_ \| __|
  //  |  __/ | | | |_| |  | |_| | | |_) | (_) | | | | | |_
  //   \___|_| |_|\__|_|   \__, | | .__/ \___/|_|_| |_|\__|
  //                       |___/  |_|

  @JSExportTopLevel("JSApplicationController")
  def main(elementsStr: String): Unit = {}




  //   _   _ _____ __  __ _                                   _   _
  //  | | | |_   _|  \/  | |      _ __ ___   ___  _   _ _ __ | |_(_)_ __   __ _
  //  | |_| | | | | |\/| | |     | '_ ` _ \ / _ \| | | | '_ \| __| | '_ \ / _` |
  //  |  _  | | | | |  | | |___  | | | | | | (_) | |_| | | | | |_| | | | | (_| |
  //  |_| |_| |_| |_|  |_|_____| |_| |_| |_|\___/ \__,_|_| |_|\__|_|_| |_|\__, |
  //                                                                      |___/



  //   _   _ _____ __  __ _                                   _   _
  //  | | | |_   _|  \/  | |      _ __ ___   ___  _   _ _ __ | |_(_)_ __   __ _
  //  | |_| | | | | |\/| | |     | '_ ` _ \ / _ \| | | | '_ \| __| | '_ \ / _` |
  //  |  _  | | | | |  | | |___  | | | | | | (_) | |_| | | | | |_| | | | | (_| |
  //  |_| |_| |_| |_|  |_|_____| |_| |_| |_|\___/ \__,_|_| |_|\__|_|_| |_|\__, |
  //                                                                      |___/


  dom.window.onload = (e:Event)=> {

    mount(
      org.scalajs.dom.document.body,
      ApplicationComponent.view()
    )

    ApplicationComponent.load_after_view()

    ApplicationComponent.on_init_action()
  }




}
