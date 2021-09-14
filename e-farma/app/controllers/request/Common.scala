package controllers.request

import java.security.MessageDigest

import javax.inject.Inject
import models.repository.UserRepository
import org.apache.commons.codec.binary.Hex

import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.Success


class Common  @Inject()(userRepository: UserRepository) {

  def checkAuth(value: String): Boolean = {
    var isAuth = false;
    val cookieArray = value.split(";")
    val authElement = cookieArray
      .filter(e=> e.contains("Authorization"))
      .map(e => e.split("="))
    println(" auth element "+ authElement(0)(1))
    userRepository.getAll
      .map(Success(_))
      .map( users=> users.value.foreach(u => {
        if((md5(u.email+u.loginInfo.providerKey)).equals(authElement(0)(1).trim)) {
          println(" To tu jest")
          isAuth = true
          return isAuth
        }
        value
      })
    )
    Thread.sleep(2000)
    println(" co mi zwraca " + isAuth)
    isAuth
  }

  def md5(value: String): String = {
    val sha256 = MessageDigest.getInstance("SHA-256")
    Hex.encodeHexString(sha256.digest(value.getBytes("UTF-8")))

  }
}
