package models.forms

import play.api.data.Form
import play.api.data.Forms.{mapping, nonEmptyText, number, of}
import play.api.data.format.Formats.doubleFormat

object FruitsForm {
  val form = Form(
    mapping(
      "name" -> nonEmptyText,
      "quantity" -> number,
      "weight" -> nonEmptyText,
      "price" -> of(doubleFormat)
    )(FruitsFormData.apply)(FruitsFormData.unapply)
  )
}

case class FruitsFormData(name: String, quantity: Int, weight: String, price: Double)

