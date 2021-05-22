package models.entities

import play.api.libs.json._

case class Users(id: Long, name: String, surname: String, password: String)

object Users {
  implicit val userFormat = Json.format[Users]
}
