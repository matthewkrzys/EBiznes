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
        "name"-> nonEmptyText,
      "surname"-> nonEmptyText,
      "email"-> nonEmptyText,
      "telephone"-> nonEmptyText,
      "city"-> nonEmptyText,
      "street"-> nonEmptyText,
      "buildingNumber"-> nonEmptyText,
      "apartmentNumber"-> nonEmptyText
    )(StatusOrderFormData.apply)(StatusOrderFormData.unapply)
  )
}

case class StatusOrderFormData(userId: Int, productId: Int, tableName: String, quantity: Int,
                               name: String, surname: String, email:String, telephone: String,
                               city: String, street: String, buildingNumber: String, apartmentNumber: String)
