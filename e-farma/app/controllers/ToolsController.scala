package controllers

import javax.inject._
import models.entities.Tools
import models.forms.ToolsForm
import play.api.libs.json._
import play.api.mvc._
import service.ToolsService

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

@Singleton
class ToolsController @Inject()(cc: ControllerComponents, toolsService: ToolsService) extends AbstractController(cc) {

  def getAll(): Action[AnyContent] = Action.async { implicit request: Request[AnyContent] =>
    toolsService.listAllItems map ( items =>
      Ok(Json.toJson(items))
      //      Ok(views.html.honeys(items))
      )
  }

  def getById(id: Long): Action[AnyContent] = Action.async { implicit request: Request[AnyContent] =>
    toolsService.getItem(id) map { item =>
      Ok(Json.toJson(item))
      //      Ok(views.html.honey(item.get))
    }
  }

  def add(): Action[AnyContent] = Action.async { implicit request: Request[AnyContent] =>
    ToolsForm.form.bindFromRequest().fold(
      // if any error in submitted data
      errorForm => {
        errorForm.errors.foreach(println)
        Future.successful(BadRequest("Error!"))
      },
      data => {
        val newSeedItem = Tools(0, data.name, data.quantity, data.price, data.description)
        toolsService.addItem(newSeedItem).map(_ => Redirect(routes.ToolsController.getAll()))
      })
  }

  def update(id: Long): Action[AnyContent] = Action.async { implicit request: Request[AnyContent] =>
    ToolsForm.form.bindFromRequest().fold(
      // if any error in submitted data
      errorForm => {
        errorForm.errors.foreach(println)
        Future.successful(BadRequest("Error!"))
      },
      data => {
        val seedItem = Tools(id, data.name, data.quantity, data.price, data.description)
        toolsService.updateItem(seedItem).map(_ => Redirect(routes.ToolsController.getAll()))
      })
  }

  def delete(id: Long): Action[AnyContent] = Action.async { implicit request: Request[AnyContent] =>
    toolsService.deleteItem(id) map { res =>
      Redirect(routes.ToolsController.getAll())
    }
  }
}
