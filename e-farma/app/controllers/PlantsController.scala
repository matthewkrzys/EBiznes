package controllers

import javax.inject._
import models.entities.Plants
import models.forms.{PlantsForm, PlantsUpdateFormData}
import play.api.libs.json._
import play.api.mvc._
import service.PlantsService

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

@Singleton
class PlantsController @Inject()(cc: MessagesControllerComponents, plantsService: PlantsService) extends MessagesAbstractController(cc) {

  def getAll(): Action[AnyContent] = Action.async { implicit request: Request[AnyContent] =>
    plantsService.listAllItems map ( items =>
      Ok(Json.toJson(items))
      )
  }

  def getAllPlantsView(): Action[AnyContent] = Action.async { implicit request: Request[AnyContent] =>
    val plants = plantsService.listAllItems
    plants.map(plants => Ok(views.html.plants.plants(plants)))
  }

  def getById(id: Long): Action[AnyContent] = Action.async { implicit request: Request[AnyContent] =>
    plantsService.getItem(id) map { item =>
      Ok(Json.toJson(item))
    }
  }

  def getByIdPlantView(id: Long): Action[AnyContent] = Action.async { implicit request: Request[AnyContent] =>
    val plant = plantsService.getItem(id)
    plant.map(plant => plant match {
      case Some(p) => Ok(views.html.plants.plant(p))
      case None => Redirect(routes.PlantsController.getAll())
    })
  }

  def add(): Action[AnyContent] = Action.async { implicit request: Request[AnyContent] =>
    PlantsForm.form.bindFromRequest.fold(
      errorForm => {
        errorForm.errors.foreach(println)
        Future.successful(BadRequest("Error!"))
      },
      data => {
        val newSeedItem = Plants(0, data.name, data.quantity, data.species, data.price, data.description)
        plantsService.addItem(newSeedItem).map(_ => Redirect(routes.FlowersController.getAll()))
      })
  }

  def addPlantView: Action[AnyContent] = Action.async { implicit request =>

    PlantsForm.form.bindFromRequest.fold(
      errorForm => {
        Future.successful(
          BadRequest(views.html.plants.plantadd(errorForm))
        )
      },
      plant => {
        plantsService.addItem(Plants(0, plant.name, plant.quantity, plant.species, plant.price, plant.description)).map { _ =>
          Redirect(routes.PlantsController.add())
        }
      }
    )

  }

  def updatePlant: Action[AnyContent] = Action.async { implicit request: Request[AnyContent] =>
    PlantsForm.updateForm.bindFromRequest.fold(
      errorForm => {
        errorForm.errors.foreach(println)
        Future.successful(BadRequest("Error!"))
      },
      data => {
        val seedItem = Plants(data.id, data.name, data.quantity, data.species, data.price, data.description)
        plantsService.updateItem(seedItem).map(_ => Redirect(routes.PlantsController.getAll()))
      })
  }

  def updatePlantView(id: Long): Action[AnyContent] = Action.async { implicit request: MessagesRequest[AnyContent] =>
    val plant = plantsService.getItem(id)
    plant.map(plant => {
      val plantForm = PlantsForm.updateForm.fill(PlantsUpdateFormData(plant.get.id, plant.get.name, plant.get.quantity, plant.get.species, plant.get.price, plant.get.description))
      Ok(views.html.plants.plantsupdate(plantForm))
    })
  }

  def delete(id: Long): Action[AnyContent] = Action.async { implicit request: Request[AnyContent] =>
    plantsService.deleteItem(id) map { res =>
      Redirect(routes.FlowersController.getAll())
    }
  }
}
