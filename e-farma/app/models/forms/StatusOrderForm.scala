package models.forms

import play.api.data.Form
import play.api.data.Forms.{mapping, nonEmptyText, number}

object StatusOrderForm {
  val form = Form(
    mapping(
      "userId" -> number,
      "productId" -> number,
      "tableName" -> nonEmptyText,
      "quantity" -> number,
      "adderess" -> nonEmptyText
    )(StatusOrderFormData.apply)(StatusOrderFormData.unapply)
  )
}

case class StatusOrderFormData(userId: Int, productId: Int, tableName: String, quantity: Int, address: String)
