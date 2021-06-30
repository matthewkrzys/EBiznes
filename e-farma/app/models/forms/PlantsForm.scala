package models.forms

import play.api.data.Form
import play.api.data.Forms.{longNumber, mapping, nonEmptyText, number, of}
import play.api.data.format.Formats.doubleFormat

object PlantsForm {
  val form = Form(
    mapping(
      "name" -> nonEmptyText,
      "quantity" -> number,
      "species" -> nonEmptyText,
      "price" -> of(doubleFormat),
      "descriptions" -> nonEmptyText
    )(PlantsFormData.apply)(PlantsFormData.unapply)
  )

  val updateForm = Form(
    mapping(
      "id" -> longNumber,
      "name" -> nonEmptyText,
      "quantity" -> number,
      "species" -> nonEmptyText,
      "price" -> of(doubleFormat),
      "descriptions" -> nonEmptyText
    )(PlantsUpdateFormData.apply)(PlantsUpdateFormData.unapply)
  )
}

case class PlantsFormData(name: String, quantity: Int, species: String, price: Double, description: String)
case class PlantsUpdateFormData(id: Long, name: String, quantity: Int, species: String, price: Double, description: String)


