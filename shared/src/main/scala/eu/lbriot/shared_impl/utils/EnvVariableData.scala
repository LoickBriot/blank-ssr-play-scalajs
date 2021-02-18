package eu.lbriot.shared_impl.utils

import eu.lbriot.shared.EnvVariableDataTrait
import upickle.default.{macroRW, ReadWriter => RW}

case class EnvVariableData(
                            prod_version:String,
                            initial_url:String,
                            default_language: String
                          ) extends EnvVariableDataTrait

object EnvVariableData{  implicit def rw: RW[EnvVariableData] = macroRW }
