package models.forms

import play.api.data.Form
import play.api.data.Forms.{longNumber, mapping, nonEmptyText, number, of}
import play.api.data.format.Formats.doubleFormat

object VegetablesForm {
  val form = Form(
    mapping(
      "name" -> nonEmptyText,
      "quantity" -> number,
      "weight" -> of(doubleFormat),
      "price" -> of(doubleFormat)
    )(VegetablesFormData.apply)(VegetablesFormData.unapply)
  )

  val updateForm = Form(
    mapping(
      "id" -> longNumber,
      "name" -> nonEmptyText,
      "quantity" -> number,
      "weight" -> of(doubleFormat),
      "price" -> of(doubleFormat)
    )(VegetablesUpdateFormData.apply)(VegetablesUpdateFormData.unapply)
  )
}

case class VegetablesFormData(name: String, quantity: Int, weight: Double, price: Double)
case class VegetablesUpdateFormData(id: Long, name: String, quantity: Int, weight: Double, price: Double)


