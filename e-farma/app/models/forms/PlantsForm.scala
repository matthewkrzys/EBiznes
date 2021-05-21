package models.forms

import play.api.data.Form
import play.api.data.Forms.{mapping, nonEmptyText, number, of}
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
}

case class PlantsFormData(name: String, quantity: Int, species: String, price: Double, description: String)


