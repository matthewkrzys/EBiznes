package controllers

import com.mohiva.play.silhouette.api.services.AuthenticatorResult
import models.User
import play.api.mvc._

import scala.concurrent.{ExecutionContext, Future}

abstract class AbstractAuthController(scc: DefaultSilhouetteControllerComponents)(implicit ex: ExecutionContext) extends SilhouetteController(scc) {

  protected def authenticateUser(user: User)(implicit request: RequestHeader): Future[AuthenticatorResult] = {
    println(" aut "+ authenticatorService.create(user.loginInfo))
    authenticatorService.create(user.loginInfo)
      .flatMap { authenticator =>
        println(" auth 2 " + authenticator)
        authenticatorService.init(authenticator).flatMap { v =>
          println(" v " + v)
          authenticatorService.embed(v, Ok("Authenticated"))
        }
      }
  }
}
