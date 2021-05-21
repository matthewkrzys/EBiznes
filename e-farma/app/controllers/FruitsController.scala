package controllers

import javax.inject._
import play.api.mvc._
import play.api.libs.json._
import models.{Fruits, FruitsForm}
import play.api.data.FormError
import service.FruitsService
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future


class FruitsController @Inject()(
                                cc: ControllerComponents,
                                fruitsService: FruitsService
                              ) extends AbstractController(cc) {

  implicit val fruitsFormat = Json.format[Fruits]

  def getAll() = Action.async { implicit request: Request[AnyContent] =>
    fruitsService.listAllItems map { items =>
      Ok(Json.toJson(items))
    }
  }

  def getById(id: Long) = Action.async { implicit request: Request[AnyContent] =>
    fruitsService.getItem(id) map { item =>
      Ok(Json.toJson(item))
    }
  }

  def add() = Action.async { implicit request: Request[AnyContent] =>
    FruitsForm.form.bindFromRequest.fold(
      // if any error in submitted data
      errorForm => {
        errorForm.errors.foreach(println)
        Future.successful(BadRequest("Error!"))
      },
      data => {
        val newFruitItem = Fruits(0, data.name, data.quanity, data.weight, data.price)
        fruitsService.addItem(newFruitItem).map(_ => Redirect(routes.FruitsController.getAll))
      })
  }

  def update(id: Long) = Action.async { implicit request: Request[AnyContent] =>
    FruitsForm.form.bindFromRequest.fold(
      // if any error in submitted data
      errorForm => {
        errorForm.errors.foreach(println)
        Future.successful(BadRequest("Error!"))
      },
      data => {
        val fruitItem = Fruits(id, data.name, data.quanity, data.weight, data.price)
        fruitsService.updateItem(fruitItem).map(_ => Redirect(routes.FruitsController.getAll))
      })
  }

  def delete(id: Long) = Action.async { implicit request: Request[AnyContent] =>
    fruitsService.deleteItem(id) map { res =>
      Redirect(routes.FruitsController.getAll)
    }
  }
}
