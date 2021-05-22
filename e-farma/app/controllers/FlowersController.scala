package controllers

import javax.inject._
import models.entities.Flowers
import models.forms.FlowersForm
import play.api.libs.json._
import play.api.mvc._
import service.FlowersService

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

@Singleton
class FlowersController @Inject()(cc: ControllerComponents, flowersService: FlowersService) extends AbstractController(cc) {

  def getAll(): Action[AnyContent] = Action.async { implicit request: Request[AnyContent] =>
    flowersService.listAllItems map ( items =>
      Ok(Json.toJson(items))
      //      Ok(views.html.honeys(items))
      )
  }

  def getById(id: Long): Action[AnyContent] = Action.async { implicit request: Request[AnyContent] =>
    flowersService.getItem(id) map { item =>
      Ok(Json.toJson(item))
      //      Ok(views.html.honey(item.get))
    }
  }

  def add(): Action[AnyContent] = Action.async { implicit request: Request[AnyContent] =>
    FlowersForm.form.bindFromRequest.fold(
      // if any error in submitted data
      errorForm => {
        errorForm.errors.foreach(println)
        Future.successful(BadRequest("Error!"))
      },
      data => {
        val newSeedItem = Flowers(0, data.name, data.quantity, data.species, data.price, data.description)
        flowersService.addItem(newSeedItem).map(_ => Redirect(routes.FlowersController.getAll()))
      })
  }

  def update(id: Long): Action[AnyContent] = Action.async { implicit request: Request[AnyContent] =>
    FlowersForm.form.bindFromRequest.fold(
      // if any error in submitted data
      errorForm => {
        errorForm.errors.foreach(println)
        Future.successful(BadRequest("Error!"))
      },
      data => {
        val seedItem = Flowers(id, data.name, data.quantity, data.species, data.price, data.description)
        flowersService.updateItem(seedItem).map(_ => Redirect(routes.FlowersController.getAll()))
      })
  }

  def delete(id: Long): Action[AnyContent] = Action.async { implicit request: Request[AnyContent] =>
    flowersService.deleteItem(id) map { res =>
      Redirect(routes.FlowersController.getAll())
    }
  }
}
