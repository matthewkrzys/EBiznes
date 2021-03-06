package models.entities

import play.api.libs.json.Json

case class Flowers(id: Long, name: String, quantity: Int, species: Double, price: Double, description: String)

object Flowers {
  implicit val flowersFormat = Json.format[Flowers]
}