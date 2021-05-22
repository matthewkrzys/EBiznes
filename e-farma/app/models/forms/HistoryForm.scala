package models.forms

import play.api.data.Form
import play.api.data.Forms.{mapping, nonEmptyText, of}
import play.api.data.format.Formats.longFormat

object HistoryForm {
  val form = Form(
    mapping(
      "product" -> nonEmptyText,
      "description" -> nonEmptyText,
      "users" -> of(longFormat)
    )(HistoryFormData.apply)(HistoryFormData.unapply)
  )
}

case class HistoryFormData(product: String, description: String, users: Long)

