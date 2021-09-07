package models.entities


import play.api.libs.json.Json

case class CartItem(userId: Int, productId: Int, tableName: String, quantity: Int)

object CartItem {
  implicit val cartItemFormat = Json.format[CartItem]
}