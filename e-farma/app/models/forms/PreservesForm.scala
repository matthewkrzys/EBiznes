package models.forms

import play.api.data.Form
import play.api.data.Forms.{longNumber, mapping, nonEmptyText, number, of}
import play.api.data.format.Formats.doubleFormat

object PreservesForm {
  val form = Form(
    mapping(
      "name" -> nonEmptyText,
      "quantity" -> number,
      "weight" -> of(doubleFormat),
      "price" -> of(doubleFormat),
      "descriptions" -> nonEmptyText
    )(PreservesFormData.apply)(PreservesFormData.unapply)
  )

  val updateForm = Form(
    mapping(
      "id" -> longNumber,
      "name" -> nonEmptyText,
      "quantity" -> number,
      "weight" -> of(doubleFormat),
      "price" -> of(doubleFormat),
      "descriptions" -> nonEmptyText
    )(PreservesUpdateFormData.apply)(PreservesUpdateFormData.unapply)
  )
}

case class PreservesFormData(name: String, quantity: Int, weight: Double, price: Double, description: String)
case class PreservesUpdateFormData(id: Long, name: String, quantity: Int, weight: Double, price: Double, description: String)


