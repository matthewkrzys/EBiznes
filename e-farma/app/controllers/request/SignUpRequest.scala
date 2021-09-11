package controllers.request

import play.api.libs.json.{Json, OFormat}

case class SignUpRequest(name: String, surname: String, email: String, password: String, telephone: String,
                         city: String, street: String, buildingNumber: String, apartmentNumber: String)

object SignUpRequest {
  implicit val signUpRequestForm: OFormat[SignUpRequest] = Json.format[SignUpRequest]
}
