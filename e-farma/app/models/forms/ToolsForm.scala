package models.forms

import play.api.data.Form
import play.api.data.Forms.{longNumber, mapping, nonEmptyText, number, of}
import play.api.data.format.Formats.doubleFormat

object ToolsForm {
  val form = Form(
    mapping(
      "name" -> nonEmptyText,
      "quantity" -> number,
      "price" -> of(doubleFormat),
      "descriptions" -> nonEmptyText
    )(ToolsFormData.apply)(ToolsFormData.unapply)
  )

  val updateForm = Form(
    mapping(
      "id" -> longNumber,
      "name" -> nonEmptyText,
      "quantity" -> number,
      "price" -> of(doubleFormat),
      "descriptions" -> nonEmptyText
    )(ToolsUpdateFormData.apply)(ToolsUpdateFormData.unapply)
  )
}

case class ToolsFormData(name: String, quantity: Int, price: Double, description: String)
case class ToolsUpdateFormData(id: Long, name: String, quantity: Int, price: Double, description: String)


