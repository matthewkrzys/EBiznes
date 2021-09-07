package controllers

import com.mohiva.play.silhouette.api.Authenticator.Implicits._
import com.mohiva.play.silhouette.api._
import com.mohiva.play.silhouette.api.services.AuthenticatorResult
import models.User
import play.api.mvc._

import scala.concurrent.{ExecutionContext, Future}

abstract class AbstractAuthController(scc: DefaultSilhouetteControllerComponents)(implicit ex: ExecutionContext) extends SilhouetteController(scc) {

  protected def authenticateUser(user: User)(implicit request: RequestHeader): Future[AuthenticatorResult] = {
//    println("poszlo")
    authenticatorService.create(user.loginInfo)
      .flatMap { authenticator =>
//        println("aut "+ authenticator)
        authenticatorService.init(authenticator).flatMap { v =>
//          println("v  " + v)
          authenticatorService.embed(v, Ok("Authenticated"))
        }
      }
  }
}
