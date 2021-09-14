package controllers

import com.mohiva.play.silhouette.api.actions.SecuredRequest
import com.mohiva.play.silhouette.api.exceptions.ProviderException
import com.mohiva.play.silhouette.api.util.{Credentials, PasswordInfo}
import com.mohiva.play.silhouette.impl.exceptions.IdentityNotFoundException
import controllers.request.{Common, SignInRequest}
import javax.inject.{Inject, Singleton}
import models.repository.PasswordInfoRepository
import play.api.mvc._
import play.filters.csrf.CSRF.Token
import play.filters.csrf.{CSRF, CSRFAddToken}

import scala.concurrent.{ExecutionContext, Future}
import scala.util.{Failure, Success}

@Singleton
class SignInController @Inject()(scc: DefaultSilhouetteControllerComponents, addToken: CSRFAddToken,
                                 passwordInfoRepository: PasswordInfoRepository, common: Common)(implicit ex: ExecutionContext) extends AbstractAuthController(scc) {

  def signIn: Action[AnyContent] = addToken(unsecuredAction.async { implicit request: Request[AnyContent] =>
    val json = request.body.asJson.get
    val Token(name, value) = CSRF.getToken.get
    val signInRequest = json.as[SignInRequest]
    val credentials = Credentials(signInRequest.email, signInRequest.password)

    credentialsProvider.authenticate(credentials)
      .flatMap { loginInfo =>
        userRepository.retrieve(loginInfo).flatMap {
          case Some(user) =>
            var password: String = ""
            passwordInfoRepository.find(loginInfo).onComplete {
              case Success(value) => password = value.get.password
              case Failure(e) => e.printStackTrace
            }
            authenticateUser(user)
              .map(_.withCookies(Cookie(name, value, httpOnly = false))
                .withCookies(Cookie("Authorization", common.md5(signInRequest.email+user.loginInfo.providerKey), httpOnly = false))
                .withCookies(Cookie("Id", user.id.toString, httpOnly = false)))
          case None => Future.failed(new IdentityNotFoundException("Couldn't find user"))
        }
      }
      .recover {
        case _: ProviderException =>
          Forbidden("Wrong credentials")
            .discardingCookies(DiscardingCookie(name = "PLAY_SESSION"))
      }
  })

  def signOut: Action[AnyContent] = securedAction.async { implicit request: SecuredRequest[EnvType, AnyContent] =>
    authenticatorService.discard(request.authenticator, Ok("Logged out"))
      .map(_.discardingCookies(
        DiscardingCookie(name = "csrfToken"),
        DiscardingCookie(name = "PLAY_SESSION"),
        DiscardingCookie(name = "OAuth2State")
      ))
  }
}
