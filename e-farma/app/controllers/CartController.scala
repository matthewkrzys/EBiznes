package controllers

import controllers.request.Common
import javax.inject.{Inject, Singleton}
import models.entities.CartItem
import play.api.mvc.{Action, _}
import models.forms.{CartFrom, DeleteForm}
import play.api.libs.json.Json
import service.CartService

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

@Singleton
class CartController @Inject()(cc: MessagesControllerComponents, cartService: CartService, common: Common) extends MessagesAbstractController(cc) {

  def addProductToCart(): Action[AnyContent] = Action.async { implicit request: Request[AnyContent] =>
    if (common.checkAuth(request.headers.toMap("Cookie").toString())) {
      CartFrom.form.bindFromRequest.fold(
        formWithErrors => {
          println(" no problem")
          Future{BadRequest(formWithErrors.errors.toString)}
        },
        formData => {
          val newCartElement = CartItem(formData.userId, formData.productId, formData.productName, formData.tableName, formData.quantity)
          Future{Ok(cartService.addElementToCart(newCartElement))}
        }
      )
    }
    else {
      Future {
        Forbidden("Wrong Auth")
      }
    }
  }

  def delete(): Action[AnyContent] = Action.async { implicit request: Request[AnyContent] =>
    if (common.checkAuth(request.headers.toMap("Cookie").toString())) {
      DeleteForm.form.bindFromRequest.fold(
        formWithErrors => {
          Future{BadRequest(formWithErrors.errors.toString)}
        },
        formData => {
          val newCartElement = CartItem(formData.userId, formData.productId, formData.productName, formData.tableName, formData.quantity)
          Future{Ok(cartService.deleteElement(newCartElement))}
        }
      )
    }
    else
      Future {
        Forbidden("Wrong Auth")
      }
  }

  def getCartStatus(id: Int): Action[AnyContent] = Action.async { implicit request: Request[AnyContent] =>
    if (common.checkAuth(request.headers.toMap("Cookie").toString())) {
      cartService.getCart(id) map (items =>
        Ok(Json.toJson(items))
        )
    }
    else {
      Future {
        Forbidden("Wrong Auth")
      }
    }
  }

  def getStatusOrder(userId: Int): Action[AnyContent] = Action.async { implicit request: Request[AnyContent] =>
    if (common.checkAuth(request.headers.toMap("Cookie").toString())) {
      cartService.getCart(userId) map (items =>
        Ok(Json.toJson(items))
        )
    }
    else {
      Future {
        Forbidden("Wrong Auth")
      }
    }
  }

  def getBuy(userId: Int): Action[AnyContent] = Action.async { implicit request: Request[AnyContent] =>
    if (common.checkAuth(request.headers.toMap("Cookie").toString())) {
      Future {
        Ok(cartService.getBuy(userId))
      }
    }
    else {
      Future {
        Forbidden("Wrong Auth")
      }
    }
  }
}
