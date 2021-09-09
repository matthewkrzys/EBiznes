package models.entities

import play.api.libs.json.Json

case class StatusOrderItem(userId: Int, productId: Int, tableName: String, quantity: Int, address: String)

object StatusOrderItem {
  implicit val statusOrderItemFormat = Json.format[StatusOrderItem]
}