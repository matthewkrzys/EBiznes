package models.forms

import play.api.data.Form
import play.api.data.Forms.{longNumber, mapping, nonEmptyText, number}

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

  val updateForm = Form(
    mapping(
      "id" -> longNumber,
      "name" -> nonEmptyText,
      "surname" -> nonEmptyText,
      "password" -> nonEmptyText,
      "email" -> nonEmptyText,
      "telephone" -> nonEmptyText,
      "address" -> nonEmptyText
    )(UsersUpdateFormData.apply)(UsersUpdateFormData.unapply)
  )
}

case class UsersFormData(name: String, surname: String, password: String, email: String, telephone: String, address: String)
case class UsersUpdateFormData(id: Long, name: String, surname: String, password: String, email: String, telephone: String, address: String)

