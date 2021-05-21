package controllers

import javax.inject._
import models.entities.Honey
import play.api.mvc._
import play.api.libs.json._
import models.forms.HoneyForm
import service.HoneyService

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

@Singleton
class HoneyController @Inject()(cc: ControllerComponents, honeysService: HoneyService) extends AbstractController(cc) {

  def getAll(): Action[AnyContent] = Action.async { implicit request: Request[AnyContent] =>
    honeysService.listAllItems map ( items =>
      Ok(Json.toJson(items))
      //      Ok(views.html.honeys(items))
      )
  }

  def getById(id: Long): Action[AnyContent] = Action.async { implicit request: Request[AnyContent] =>
    honeysService.getItem(id) map { item =>
      Ok(Json.toJson(item))
      //      Ok(views.html.honey(item.get))
    }
  }

  def add(): Action[AnyContent] = Action.async { implicit request: Request[AnyContent] =>
    HoneyForm.form.bindFromRequest.fold(
      // if any error in submitted data
      errorForm => {
        errorForm.errors.foreach(println)
        Future.successful(BadRequest("Error!"))
      },
      data => {
        val newHoneyItem = Honey(0, data.name, data.quantity, data.weight, data.price)
        honeysService.addItem(newHoneyItem).map(_ => Redirect(routes.HoneyController.getAll))
      })
  }

  def update(id: Long): Action[AnyContent] = Action.async { implicit request: Request[AnyContent] =>
    HoneyForm.form.bindFromRequest.fold(
      // if any error in submitted data
      errorForm => {
        errorForm.errors.foreach(println)
        Future.successful(BadRequest("Error!"))
      },
      data => {
        val honeyItem = Honey(id, data.name, data.quantity, data.weight, data.price)
        honeysService.updateItem(honeyItem).map(_ => Redirect(routes.HoneyController.getAll))
      })
  }

  def delete(id: Long): Action[AnyContent] = Action.async { implicit request: Request[AnyContent] =>
    honeysService.deleteItem(id) map { res =>
      Redirect(routes.HoneyController.getAll)
    }
  }
}
