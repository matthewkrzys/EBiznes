package models.forms

import play.api.data.Form
import play.api.data.Forms.{mapping, nonEmptyText, number, of}
import play.api.data.format.Formats.doubleFormat

object SeedsForm {
  val form = Form(
    mapping(
      "name" -> nonEmptyText,
      "quantity" -> number,
      "weight" -> nonEmptyText,
      "price" -> of(doubleFormat),
      "descriptions" -> nonEmptyText
    )(SeedsFormData.apply)(SeedsFormData.unapply)
  )
}

case class SeedsFormData(name: String, quantity: Int, weight: String, price: Double, description: String)


