package models.forms

import play.api.data.Form
import play.api.data.Forms.{mapping, nonEmptyText}

object UsersForm {
  val form = Form(
    mapping(
      "name" -> nonEmptyText,
      "surname" -> nonEmptyText,
      "password" -> nonEmptyText,
      "email" -> nonEmptyText,
      "telephone" -> nonEmptyText,
      "address" -> nonEmptyText
    )(UsersFormData.apply)(UsersFormData.unapply)
  )
}

case class UsersFormData(name: String, surname: String, password: String, email: String, telephone: String, address: String)

