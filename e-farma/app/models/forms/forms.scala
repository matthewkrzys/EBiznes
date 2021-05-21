package models.forms

import models.entities.FruitsFormData
import play.api.data.Form
import play.api.data.Forms.{mapping, nonEmptyText, number, of}
import play.api.data.format.Formats.doubleFormat

object FruitsForm {
  val form = Form(
    mapping(
      "name" -> nonEmptyText,
      "quanity" -> number,
      "weight" -> nonEmptyText,
      "price" -> of(doubleFormat)
    )(FruitsFormData.apply)(FruitsFormData.unapply)
  )
}
