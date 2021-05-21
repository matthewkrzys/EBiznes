package controllers

import javax.inject._
import models.entities.Vegetables
import play.api.mvc._
import play.api.libs.json._
import models.forms.VegetablesForm
import service.VegetablesService

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

@Singleton
class VegetablesController @Inject()(cc: ControllerComponents, vegetablesForm: VegetablesService) extends AbstractController(cc) {

  def getAll(): Action[AnyContent] = Action.async { implicit request: Request[AnyContent] =>
    vegetablesForm.listAllItems map ( items =>
      Ok(Json.toJson(items))
      //      Ok(views.html.honeys(items))
      )
  }

  def getById(id: Long): Action[AnyContent] = Action.async { implicit request: Request[AnyContent] =>
    vegetablesForm.getItem(id) map { item =>
      Ok(Json.toJson(item))
      //      Ok(views.html.honey(item.get))
    }
  }

  def add(): Action[AnyContent] = Action.async { implicit request: Request[AnyContent] =>
    VegetablesForm.form.bindFromRequest.fold(
      // if any error in submitted data
      errorForm => {
        errorForm.errors.foreach(println)
        Future.successful(BadRequest("Error!"))
      },
      data => {
        val newHoneyItem = Vegetables(0, data.name, data.quantity, data.weight, data.price)
        vegetablesForm.addItem(newHoneyItem).map(_ => Redirect(routes.VegetablesController.getAll))
      })
  }

  def update(id: Long): Action[AnyContent] = Action.async { implicit request: Request[AnyContent] =>
    VegetablesForm.form.bindFromRequest.fold(
      // if any error in submitted data
      errorForm => {
        errorForm.errors.foreach(println)
        Future.successful(BadRequest("Error!"))
      },
      data => {
        val honeyItem = Vegetables(id, data.name, data.quantity, data.weight, data.price)
        vegetablesForm.updateItem(honeyItem).map(_ => Redirect(routes.HoneyController.getAll))
      })
  }

  def delete(id: Long): Action[AnyContent] = Action.async { implicit request: Request[AnyContent] =>
    vegetablesForm.deleteItem(id) map { res =>
      Redirect(routes.HoneyController.getAll)
    }
  }
}
