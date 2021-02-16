package eu.lbriot.shared_impl

import upickle.default.{macroRW, ReadWriter => RW}
import eu.lbriot.shared._



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




//
//   _ __ ___   ___ ___ ___  __ _  __ _  ___ ___
//  | '_ ` _ \ / _ \ __/ __|/ _` |/ _` |/ _ \ __|
//  | | | | | |  __\__ \__ \ (_| | (_| |  __\__ \
//  |_| |_| |_|\___|___/___/\__,_|\__, |\___|___/
//                                |___/


sealed trait ServerClientMessage extends ServerClientMessageTrait
object ServerClientMessage  {
  implicit def rw: RW[ServerClientMessage] = RW.merge(
    Ping.rw,
    Pong.rw,
  )
}





case class Ping() extends ServerClientMessage
object Ping{  implicit def rw: RW[Ping] = macroRW }

case class Pong() extends ServerClientMessage
object Pong{  implicit def rw: RW[Pong] = macroRW }

