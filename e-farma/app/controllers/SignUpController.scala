package controllers

import com.mohiva.play.silhouette.api._
import com.mohiva.play.silhouette.impl.providers._
import controllers.request.SignUpRequest
import javax.inject.Inject
import models.User
import models.entities.Users
import models.repos.UsersRepo
import play.api.libs.json.Json
import play.api.mvc.{Action, AnyContent, Request}

import scala.concurrent.{ExecutionContext, Future}
import scala.language.postfixOps

class SignUpController @Inject()(components: DefaultSilhouetteControllerComponents, usersRepo: UsersRepo)(implicit ex: ExecutionContext) extends SilhouetteController(components) {

  def signUp: Action[AnyContent] = unsecuredAction.async { implicit request: Request[AnyContent] =>
    val json = request.body.asJson.get
    println("jest")
    println(json)
    val signUpRequest = json.as[SignUpRequest]
    val loginInfo = LoginInfo(CredentialsProvider.ID, signUpRequest.email)

    val response = userRepository.retrieve(loginInfo).flatMap {
      case Some(_) =>
        Future.successful(Forbidden("User already exists"))
      case None =>
        val authInfo = passwordHasherRegistry.current.hash(signUpRequest.password)
        userRepository.create(
          CredentialsProvider.ID,
          signUpRequest.email,
          signUpRequest.email
        ).flatMap { user =>
          println(user)
          authInfoRepository.add(loginInfo, authInfo)
            .map(_ => user)
        }.flatMap { user =>
          authTokenRepository.create(user.id)
            .map(_ => user)
        }.flatMap { user =>
          usersRepo.add(
            Users(user.id, signUpRequest.name,
              signUpRequest.surname, signUpRequest.password, signUpRequest.email, signUpRequest.telephone, signUpRequest.city,
              signUpRequest.street, signUpRequest.buildingNumber, signUpRequest.apartmentNumber))
        }.map { user =>
          Json.toJson(user)
        }.map { json =>
          Created(json)
        }
    }
    response
  }
}
