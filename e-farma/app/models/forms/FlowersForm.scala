package models.forms

import play.api.data.Form
import play.api.data.Forms.{longNumber, mapping, nonEmptyText, number, of}
import play.api.data.format.Formats.doubleFormat

object FlowersForm {
  val form = Form(
    mapping(
      "name" -> nonEmptyText,
      "quantity" -> number,
      "species" -> of(doubleFormat),
      "price" -> of(doubleFormat),
      "descriptions" -> nonEmptyText
    )(FlowersFormData.apply)(FlowersFormData.unapply)
  )

  val updateForm = Form(
    mapping(
      "id" -> longNumber,
      "name" -> nonEmptyText,
      "quantity" -> number,
      "species" -> of(doubleFormat),
      "price" -> of(doubleFormat),
      "descriptions" -> nonEmptyText
    )(FlowersUpdateFormData.apply)(FlowersUpdateFormData.unapply)
  )
}

case class FlowersFormData(name: String, quantity: Int, species: Double, price: Double, description: String)
case class FlowersUpdateFormData(id: Long, name: String, quantity: Int, species: Double, price: Double, description: String)


