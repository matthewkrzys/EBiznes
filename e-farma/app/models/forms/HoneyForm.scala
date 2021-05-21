package models.forms

import play.api.data.Form
import play.api.data.Forms.{mapping, nonEmptyText, number, of}
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
}

case class HoneyFormData(name: String, quantity: Int, weight: String, price: Double)

