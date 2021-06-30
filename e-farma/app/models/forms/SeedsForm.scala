package models.forms

import play.api.data.Form
import play.api.data.Forms.{longNumber, mapping, nonEmptyText, number, of}
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

  val updateForm = Form(
    mapping(
      "id" -> longNumber,
      "name" -> nonEmptyText,
      "quantity" -> number,
      "weight" -> nonEmptyText,
      "price" -> of(doubleFormat),
      "descriptions" -> nonEmptyText
    )(SeedsUpdateFormData.apply)(SeedsUpdateFormData.unapply)
  )
}

case class SeedsFormData(name: String, quantity: Int, weight: String, price: Double, description: String)
case class SeedsUpdateFormData(id: Long, name: String, quantity: Int, weight: String, price: Double, description: String)


