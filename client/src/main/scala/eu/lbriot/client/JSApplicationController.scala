package eu.lbriot.client

import communication.ServerCommunicationActorTrait
import eu.lbriot.client.components.ApplicationComponent
import eu.lbriot.client.utils.MountHandler
import eu.lbriot.shared_impl.utils.{HtmlIDHandler, _}
import org.scalajs.dom
import org.scalajs.dom._
import org.scalajs.dom.html.{Input, TextArea}

import scala.scalajs.js.annotation.JSExportTopLevel

object JSApplicationController extends ServerCommunicationActorTrait[ServerClientMessage](
  "api",
  new ServerClientMessageSerializor(),
  true,
  true
) {




  //              _                            _       _
  //    ___ _ __ | |_ _ __ _   _   _ __   ___ (_)_ __ | |_
  //   / _ \ '_ \| __| '__| | | | | '_ \ / _ \| | '_ \| __|
  //  |  __/ | | | |_| |  | |_| | | |_) | (_) | | | | | |_
  //   \___|_| |_|\__|_|   \__, | | .__/ \___/|_|_| |_|\__|
  //                       |___/  |_|

  lazy val env_variable_data : EnvVariableData = {
    upickle.default.read[EnvVariableData](
      document
        .getElementById(
          HtmlIDHandler.ENV_VARIABLE_DATA_INPUT
        )
        .asInstanceOf[Input]
        .value
    )
  }

  @JSExportTopLevel("JSApplicationController")
  def main(args:String): Unit = {
    println(env_variable_data)
  }




  //   _   _ _____ __  __ _                                   _   _
  //  | | | |_   _|  \/  | |      _ __ ___   ___  _   _ _ __ | |_(_)_ __   __ _
  //  | |_| | | | | |\/| | |     | '_ ` _ \ / _ \| | | | '_ \| __| | '_ \ / _` |
  //  |  _  | | | | |  | | |___  | | | | | | (_) | |_| | | | | |_| | | | | (_| |
  //  |_| |_| |_| |_|  |_|_____| |_| |_| |_|\___/ \__,_|_| |_|\__|_|_| |_|\__, |
  //                                                                      |___/


  dom.window.onload = (e:Event)=> {


    MountHandler(
      org.scalajs.dom.document.body,
      ApplicationComponent.view()
    )


    ApplicationComponent.load_after_view()

    ApplicationComponent.on_init_action()
  }






  //                            _                                            _
  //   _ __ ___ _ __ ___   ___ | |_ ___   _ __ ___   ___ ___ ___  __ _  __ _(_)_ __   __ _
  //  | '__/ _ \ '_ ` _ \ / _ \| __/ _ \ | '_ ` _ \ / _ \ __/ __|/ _` |/ _` | | '_ \ / _` |
  //  | | |  __/ | | | | | (_) | |_  __/ | | | | | |  __\__ \__ \ (_| | (_| | | | | | (_| |
  //  |_|  \___|_| |_| |_|\___/ \__\___| |_| |_| |_|\___|___/___/\__,_|\__, |_|_| |_|\__, |
  //                                                                   |___/         |___/


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


}
