package model

import play.api.libs.json.Json


case class Assessment(symptoms: List[String], investigation: List[String], referral: String)

object Assessment {implicit val formats = Json.format[Assessment]}