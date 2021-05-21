package models.entities

import play.api.libs.json.Json

case class Tools(id: Long, name: String, quantity: Int, price: Double, description: String)

object Tools {
  implicit val toolsFormat = Json.format[Tools]
}