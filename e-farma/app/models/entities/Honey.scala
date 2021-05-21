package models.entities

import play.api.libs.json.Json

case class Honey(id: Long, name: String, quantity: Int, weight: String, price: Double)

object Honey {
  implicit val honeyFormat = Json.format[Honey]
}