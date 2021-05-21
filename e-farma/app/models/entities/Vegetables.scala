package models.entities

import play.api.libs.json.Json

case class Vegetables(id: Long, name: String, quantity: Int, weight: String, price: Double)

object Vegetables {
  implicit val vegetablesFormat = Json.format[Vegetables]
}