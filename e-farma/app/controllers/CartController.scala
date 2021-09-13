package controllers

import javax.inject.{Inject, Singleton}
import models.entities.CartItem
import play.api.mvc.{Action, _}
import models.forms.{CartFrom, DeleteForm}
import play.api.libs.json.Json
import service.CartService

import scala.concurrent.ExecutionContext.Implicits.global

@Singleton
class CartController @Inject()(cc: MessagesControllerComponents, cartService: CartService) extends MessagesAbstractController(cc) {

  def addProductToCart(): Action[AnyContent] = Action { implicit request: Request[AnyContent] =>

    CartFrom.form.bindFromRequest.fold(
      formWithErrors => {
        BadRequest(formWithErrors.errors.toString)
      },
      formData => {
        val newCartElement = CartItem(formData.userId, formData.productId, formData.productName, formData.tableName, formData.quantity)
        Ok(cartService.addElementToCart(newCartElement))
      }
    )
  }

  def delete(): Action[AnyContent] = Action { implicit request: Request[AnyContent] =>
    DeleteForm.form.bindFromRequest.fold(
      formWithErrors => {
        BadRequest(formWithErrors.errors.toString)
      },
      formData => {
        val newCartElement = CartItem(formData.userId, formData.productId, formData.productName, formData.tableName, formData.quantity)
        Ok(cartService.deleteElement(newCartElement))
      }
    )
  }

  def getCartStatus(id: Int): Action[AnyContent] = Action.async { implicit request: Request[AnyContent] =>
    cartService.getCart(id) map (items =>
      Ok(Json.toJson(items))
      )
  }

  def getStatusOrder(userId: Int):  Action[AnyContent] = Action.async { implicit request: Request[AnyContent] =>
    cartService.getCart(userId) map (items =>
      Ok(Json.toJson(items))
      )
  }

  def getBuy(userId: Int) = Action {
      Ok(cartService.getBuy(userId))
  }
}
