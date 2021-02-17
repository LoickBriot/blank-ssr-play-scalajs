package eu.lbriot.shared_impl.utils

import upickle.default.{macroRW, ReadWriter => RW}

case class EnvVariableData(
                            prod_version:String,
                            initial_url:String
                          ) {

}
object EnvVariableData{  implicit def rw: RW[EnvVariableData] = macroRW }
