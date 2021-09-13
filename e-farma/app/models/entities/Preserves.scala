package models.entities

import play.api.libs.json.Json

case class Preserves(id: Long, name: String, quantity: Int, weight: Double, price: Double, description: String)

object Preserves {
  implicit val preservesFormat = Json.format[Preserves]
}