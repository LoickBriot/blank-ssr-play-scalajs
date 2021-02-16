package eu.lbriot.server

import com.typesafe.config.{Config, ConfigFactory}


object ConfigUtils {

  val config : Config = ConfigFactory.load().resolve()

  val prod_version: String = config.getString("deployement.prod_version")

}
