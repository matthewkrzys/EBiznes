package models.forms

import play.api.data.Form
import play.api.data.Forms.{longNumber, mapping, nonEmptyText, number, of}
import play.api.data.format.Formats.doubleFormat

object HoneyForm {
  val form = Form(
    mapping(
      "name" -> nonEmptyText,
      "quantity" -> number,
      "weight" -> nonEmptyText,
      "price" -> of(doubleFormat)
    )(HoneyFormData.apply)(HoneyFormData.unapply)
  )

  val updateForm = Form(
    mapping(
      "id" -> longNumber,
      "name" -> nonEmptyText,
      "quantity" -> number,
      "weight" -> nonEmptyText,
      "price" -> of(doubleFormat)
    )(HoneyUpdateFormData.apply)(HoneyUpdateFormData.unapply)
  )
}

case class HoneyFormData(name: String, quantity: Int, weight: String, price: Double)
case class HoneyUpdateFormData(id: Long, name: String, quantity: Int, weight: String, price: Double)

