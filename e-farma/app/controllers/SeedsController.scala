package controllers

import javax.inject._
import models.entities.Seeds
import models.forms.SeedsForm
import play.api.libs.json._
import play.api.mvc._
import service.SeedsService

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

@Singleton
class SeedsController @Inject()(cc: ControllerComponents, seedsService: SeedsService) extends AbstractController(cc) {

  def getAll(): Action[AnyContent] = Action.async { implicit request: Request[AnyContent] =>
    seedsService.listAllItems map ( items =>
      Ok(Json.toJson(items))
      //      Ok(views.html.honeys(items))
      )
  }

  def getById(id: Long): Action[AnyContent] = Action.async { implicit request: Request[AnyContent] =>
    seedsService.getItem(id) map { item =>
      Ok(Json.toJson(item))
      //      Ok(views.html.honey(item.get))
    }
  }

  def add(): Action[AnyContent] = Action.async { implicit request: Request[AnyContent] =>
    SeedsForm.form.bindFromRequest().fold(
      // if any error in submitted data
      errorForm => {
        errorForm.errors.foreach(println)
        Future.successful(BadRequest("Error!"))
      },
      data => {
        val newSeedItem = Seeds(0, data.name, data.quantity, data.weight, data.price, data.description)
        seedsService.addItem(newSeedItem).map(_ => Redirect(routes.SeedsController.getAll()))
      })
  }

  def update(id: Long): Action[AnyContent] = Action.async { implicit request: Request[AnyContent] =>
    SeedsForm.form.bindFromRequest().fold(
      // if any error in submitted data
      errorForm => {
        errorForm.errors.foreach(println)
        Future.successful(BadRequest("Error!"))
      },
      data => {
        val seedItem = Seeds(id, data.name, data.quantity, data.weight, data.price, data.description)
        seedsService.updateItem(seedItem).map(_ => Redirect(routes.SeedsController.getAll()))
      })
  }

  def delete(id: Long): Action[AnyContent] = Action.async { implicit request: Request[AnyContent] =>
    seedsService.deleteItem(id) map { res =>
      Redirect(routes.SeedsController.getAll())
    }
  }
}
