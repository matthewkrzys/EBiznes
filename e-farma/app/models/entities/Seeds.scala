package models.entities

import play.api.libs.json.Json

case class Seeds(id: Long, name: String, quantity: Int, weight: Double, price: Double, description: String)

object Seeds {
  implicit val seedsFormat = Json.format[Seeds]
}