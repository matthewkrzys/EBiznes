package models.forms

import play.api.data.Form
import play.api.data.Forms.{mapping, nonEmptyText, number}

object CartFrom {
  val form = Form(
    mapping(
      "userId" -> number,
      "productId" -> number,
      "productName" -> nonEmptyText,
      "tableName" -> nonEmptyText,
      "quantity" -> number
    )(CartFormData.apply)(CartFormData.unapply)
  )
}

object DeleteForm {
  var form = Form(
    mapping(
      "userId" -> number,
      "productId" -> number,
      "productName" -> nonEmptyText,
      "tableName" -> nonEmptyText,
      "quantity" -> number
    )(CartFormData.apply)(CartFormData.unapply)
  )
}

case class CartFormData(userId: Int, productId: Int, productName: String, tableName: String, quantity: Int)