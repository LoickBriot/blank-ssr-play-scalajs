package eu.lbriot.shared_impl.utils

import eu.lbriot.shared._
import upickle.default.{macroRW, ReadWriter => RW}



//                 _       _ _
//   ___  ___ _ __(_) __ _| (_)_______  _ __
//  / __|/ _ \ '__| |/ _` | | |_  / _ \| '__|
//  \__ \  __/ |  | | (_| | | |/ / (_) | |
//  |___/\___|_|  |_|\__,_|_|_/___\___/|_|
//


class ServerClientMessageSerializor extends ServerClientMessageSerializorTrait{

  override def encode(message: ServerClientMessageTrait): String = {
    upickle.default.write(message.asInstanceOf[ServerClientMessage])
  }

  override def decode(str: String): ServerClientMessage = {
    upickle.default.read[ServerClientMessage](str)
  }
}



//                                                            _          _   _             _ _
//   _ __ ___   ___ ___ ___  __ _  __ _  ___   ___  ___  __ _| | ___  __| | | |_ _ __ __ _(_) |_
//  | '_ ` _ \ / _ \ __/ __|/ _` |/ _` |/ _ \ / __|/ _ \/ _` | |/ _ \/ _` | | __| '__/ _` | | __|
//  | | | | | |  __\__ \__ \ (_| | (_| |  __/ \__ \  __/ (_| | |  __/ (_| | | |_| | | (_| | | |_
//  |_| |_| |_|\___|___/___/\__,_|\__, |\___| |___/\___|\__,_|_|\___|\__,_|  \__|_|  \__,_|_|\__|
//                                |___/

sealed trait ServerClientMessage extends ServerClientMessageTrait
object ServerClientMessage  {
  implicit def rw: RW[ServerClientMessage] = RW.merge(
    Ping.rw,
    Pong.rw,
  )
}




//                                             _                 _                           _        _   _
//   _ __ ___   ___ ___ ___  __ _  __ _  ___  (_)_ __ ___  _ __ | | ___ _ __ ___   ___ _ __ | |_ __ _| |_(_) ___  _ __
//  | '_ ` _ \ / _ \ __/ __|/ _` |/ _` |/ _ \ | | '_ ` _ \| '_ \| |/ _ \ '_ ` _ \ / _ \ '_ \| __/ _` | __| |/ _ \| '_ \
//  | | | | | |  __\__ \__ \ (_| | (_| |  __/ | | | | | | | |_) | |  __/ | | | | |  __/ | | | |_ (_| | |_| | (_) | | | |
//  |_| |_| |_|\___|___/___/\__,_|\__, |\___| |_|_| |_| |_| .__/|_|\___|_| |_| |_|\___|_| |_|\__\__,_|\__|_|\___/|_| |_|
//                                |___/                   |_|

case class Ping() extends ServerClientMessage
object Ping{  implicit def rw: RW[Ping] = macroRW }

case class Pong() extends ServerClientMessage
object Pong{  implicit def rw: RW[Pong] = macroRW }

