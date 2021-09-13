package models.entities

import play.api.libs.json.Json

case class Plants(id: Long, name: String, quantity: Int, species: Double, price: Double, description: String)

object Plants {
  implicit val plantsFormat = Json.format[Plants]
}