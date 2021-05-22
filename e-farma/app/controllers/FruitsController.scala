package controllers

import javax.inject._
import models.entities.Fruits
import play.api.mvc._
import play.api.libs.json._
import models.forms.FruitsForm
import service.FruitsService

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

@Singleton
class FruitsController @Inject()(cc: ControllerComponents, fruitsService: FruitsService) extends AbstractController(cc) {

  def getAll(): Action[AnyContent] = Action.async { implicit request: Request[AnyContent] =>
    fruitsService.listAllItems map ( items =>
      Ok(Json.toJson(items))
//      Ok(views.html.fruits(items))
      )
  }

  def getById(id: Long): Action[AnyContent] = Action.async { implicit request: Request[AnyContent] =>
    fruitsService.getItem(id) map { item =>
      Ok(Json.toJson(item))
//      Ok(views.html.fruit(item.get))
    }
  }

  def add(): Action[AnyContent] = Action.async { implicit request: Request[AnyContent] =>
    FruitsForm.form.bindFromRequest.fold(
      // if any error in submitted data
      errorForm => {
        errorForm.errors.foreach(println)
        Future.successful(BadRequest("Error!"))
      },
      data => {
        val newFruitItem = Fruits(0, data.name, data.quantity, data.weight, data.price)
        fruitsService.addItem(newFruitItem).map(_ => Redirect(routes.FruitsController.getAll()))
      })
  }

  def update(id: Long): Action[AnyContent] = Action.async { implicit request: Request[AnyContent] =>
    FruitsForm.form.bindFromRequest.fold(
      // if any error in submitted data
      errorForm => {
        errorForm.errors.foreach(println)
        Future.successful(BadRequest("Error!"))
      },
      data => {
        val fruitItem = Fruits(id, data.name, data.quantity, data.weight, data.price)
        fruitsService.updateItem(fruitItem).map(_ => Redirect(routes.FruitsController.getAll()))
      })
  }

  def delete(id: Long): Action[AnyContent] = Action.async { implicit request: Request[AnyContent] =>
    fruitsService.deleteItem(id) map { res =>
      Redirect(routes.FruitsController.getAll())
    }
  }
}
