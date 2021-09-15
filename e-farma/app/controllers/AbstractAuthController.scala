package controllers

import com.mohiva.play.silhouette.api.services.AuthenticatorResult
import models.User
import play.api.mvc._

import scala.concurrent.{ExecutionContext, Future}

abstract class AbstractAuthController(scc: DefaultSilhouetteControllerComponents)(implicit ex: ExecutionContext) extends SilhouetteController(scc) {

  protected def authenticateUser(user: User)(implicit request: RequestHeader): Future[AuthenticatorResult] = {
    println("auth tez tu")
    authenticatorService.create(user.loginInfo)
      .flatMap { authenticator =>
        println("A " + authenticator)
        println("U " + user)
        authenticatorService.init(authenticator).flatMap { v =>
          authenticatorService.embed(v, Ok("Authenticated"))
        }
      }
  }
}
