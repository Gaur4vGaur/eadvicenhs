package model

import play.api.libs.json.Json

case class Links(href: String, rel: String)

case class Organisation(links: List[Links], name: String, odsCode: String, recordClass: String)

case class Organisations(organisations: List[Organisation])

object Links {
  implicit val formats = Json.format[Links]
}

object Organisation {
  implicit val formats = Json.format[Organisation]
}

object Organisations {
  implicit val formats = Json.format[Organisations]
}

