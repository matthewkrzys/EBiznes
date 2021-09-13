package models.entities


import play.api.libs.json.Json

case class CartItem(userId: Int, productId: Int, productName: String, tableName: String, quantity: Int)

object CartItem {
  implicit val cartItemFormat = Json.format[CartItem]
}