package eu.lbriot.shared_impl

import upickle.default.{ readwriter, ReadWriter => RW}

object FunctionKeys extends Enumeration {
  val AJAX_PING,
  AJAX_PONG,
  WS_PING,
  WS_PONG = Value
  implicit val rw: RW[FunctionKeys.Value] = readwriter[String].bimap(_.toString, FunctionKeys.withName)
}
