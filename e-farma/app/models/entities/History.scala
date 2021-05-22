package models.entities

import play.api.libs.json.Json

case class History(id: Long, product: String, description: String, users: Long)

object History {
  implicit val historyForm = Json.format[History]
}
