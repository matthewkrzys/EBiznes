package models.entities

import play.api.libs.json.Json

case class Fruits(id: Long, name: String, quantity: Int, weight: Double, price: Double)

object Fruits {
  implicit val fruitsFormat = Json.format[Fruits]
}