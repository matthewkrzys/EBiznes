package models.entities

import play.api.libs.json._

case class Users(id: Long, name: String, surname: String, password: String, email:String, telephone: String,
                 city: String, street: String, buildingNumber: String, apartmentNumber: String)

object Users {
  implicit val userFormat = Json.format[Users]
}

