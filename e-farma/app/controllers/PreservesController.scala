package controllers

import javax.inject._
import models.entities.Preserves
import models.forms.PreservesForm
import play.api.libs.json._
import play.api.mvc._
import service.PreservesService

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

@Singleton
class PreservesController @Inject()(cc: ControllerComponents, preservesService: PreservesService) extends AbstractController(cc) {

  def getAll(): Action[AnyContent] = Action.async { implicit request: Request[AnyContent] =>
    preservesService.listAllItems map ( items =>
      Ok(Json.toJson(items))
      //      Ok(views.html.honeys(items))
      )
  }

  def getById(id: Long): Action[AnyContent] = Action.async { implicit request: Request[AnyContent] =>
    preservesService.getItem(id) map { item =>
      Ok(Json.toJson(item))
      //      Ok(views.html.honey(item.get))
    }
  }

  def add(): Action[AnyContent] = Action.async { implicit request: Request[AnyContent] =>
    PreservesForm.form.bindFromRequest.fold(
      // if any error in submitted data
      errorForm => {
        errorForm.errors.foreach(println)
        Future.successful(BadRequest("Error!"))
      },
      data => {
        val newSeedItem = Preserves(0, data.name, data.quantity, data.weight, data.price, data.description)
        preservesService.addItem(newSeedItem).map(_ => Redirect(routes.PreservesController.getAll()))
      })
  }

  def update(id: Long): Action[AnyContent] = Action.async { implicit request: Request[AnyContent] =>
    PreservesForm.form.bindFromRequest.fold(
      // if any error in submitted data
      errorForm => {
        errorForm.errors.foreach(println)
        Future.successful(BadRequest("Error!"))
      },
      data => {
        val seedItem = Preserves(id, data.name, data.quantity, data.weight, data.price, data.description)
        preservesService.updateItem(seedItem).map(_ => Redirect(routes.PreservesController.getAll()))
      })
  }

  def delete(id: Long): Action[AnyContent] = Action.async { implicit request: Request[AnyContent] =>
    preservesService.deleteItem(id) map { res =>
      Redirect(routes.PreservesController.getAll())
    }
  }
}
