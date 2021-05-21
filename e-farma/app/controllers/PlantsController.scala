package controllers

import javax.inject._
import models.entities.Plants
import models.forms.PlantsForm
import play.api.libs.json._
import play.api.mvc._
import service.PlantsService

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

@Singleton
class PlantsController @Inject()(cc: ControllerComponents, plantsService: PlantsService) extends AbstractController(cc) {

  def getAll(): Action[AnyContent] = Action.async { implicit request: Request[AnyContent] =>
    plantsService.listAllItems map ( items =>
      Ok(Json.toJson(items))
      //      Ok(views.html.honeys(items))
      )
  }

  def getById(id: Long): Action[AnyContent] = Action.async { implicit request: Request[AnyContent] =>
    plantsService.getItem(id) map { item =>
      Ok(Json.toJson(item))
      //      Ok(views.html.honey(item.get))
    }
  }

  def add(): Action[AnyContent] = Action.async { implicit request: Request[AnyContent] =>
    PlantsForm.form.bindFromRequest.fold(
      // if any error in submitted data
      errorForm => {
        errorForm.errors.foreach(println)
        Future.successful(BadRequest("Error!"))
      },
      data => {
        val newSeedItem = Plants(0, data.name, data.quantity, data.species, data.price, data.description)
        plantsService.addItem(newSeedItem).map(_ => Redirect(routes.FlowersController.getAll))
      })
  }

  def update(id: Long): Action[AnyContent] = Action.async { implicit request: Request[AnyContent] =>
    PlantsForm.form.bindFromRequest.fold(
      // if any error in submitted data
      errorForm => {
        errorForm.errors.foreach(println)
        Future.successful(BadRequest("Error!"))
      },
      data => {
        val seedItem = Plants(id, data.name, data.quantity, data.species, data.price, data.description)
        plantsService.updateItem(seedItem).map(_ => Redirect(routes.FlowersController.getAll))
      })
  }

  def delete(id: Long): Action[AnyContent] = Action.async { implicit request: Request[AnyContent] =>
    plantsService.deleteItem(id) map { res =>
      Redirect(routes.FlowersController.getAll)
    }
  }
}
