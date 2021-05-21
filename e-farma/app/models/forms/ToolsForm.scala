package models.forms

import play.api.data.Form
import play.api.data.Forms.{mapping, nonEmptyText, number, of}
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
}

case class ToolsFormData(name: String, quantity: Int, price: Double, description: String)


