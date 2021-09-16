package controllers

import controllers.request.Common
import javax.inject._
import models.entities.Seeds
import models.forms.{SeedsForm, SeedsUpdateFormData}
import play.api.libs.json._
import play.api.mvc._
import service.SeedsService

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

@Singleton
class SeedsController @Inject()(cc: MessagesControllerComponents, seedsService: SeedsService, common: Common) extends MessagesAbstractController(cc) {

  def getAll(): Action[AnyContent] = Action.async { implicit request: Request[AnyContent] =>
    if (common.checkAuth(request.headers.toMap("Cookie").toString())) {
      seedsService.listAllItems map (items =>
        Ok(Json.toJson(items))
        )
    }
    else {

      Future {
        Forbidden(common.ERROR_MESSAGE)
      }
    }
  }

  def getAllSeedsView(): Action[AnyContent] = Action.async { implicit request: Request[AnyContent] =>
    val seeds = seedsService.listAllItems
    seeds.map(seeds => Ok(views.html.seeds.seeds(seeds)))
  }

  def getById(id: Long): Action[AnyContent] = Action.async { implicit request: Request[AnyContent] =>
    if (common.checkAuth(request.headers.toMap("Cookie").toString())) {
      seedsService.getItem(id) map { item =>
        Ok(Json.toJson(item))
      }
    }
    else {

      Future {
        Forbidden(common.ERROR_MESSAGE)
      }
    }
  }

  def getByIdSeedView(id: Long): Action[AnyContent] = Action.async { implicit request: Request[AnyContent] =>
    val seed = seedsService.getItem(id)
    seed.map(seed => seed match {
      case Some(s) => Ok(views.html.seeds.seed(s))
      case None => Redirect(routes.SeedsController.getAll())
    })
  }

  def add(): Action[AnyContent] = Action.async { implicit request: Request[AnyContent] =>
    if (common.checkAuth(request.headers.toMap("Cookie").toString())) {
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
    else {

      Future {
        Forbidden(common.ERROR_MESSAGE)
      }
    }
  }

  def addSeedView: Action[AnyContent] = Action.async { implicit request =>

    SeedsForm.form.bindFromRequest.fold(
      errorForm => {
        Future.successful(
          BadRequest(views.html.seeds.seedadd(errorForm))
        )
      },
      seed => {
        seedsService.addItem(Seeds(0, seed.name, seed.quantity, seed.weight, seed.price, seed.description)).map { _ =>
          Redirect(routes.SeedsController.add())
        }
      }
    )

  }

  def updateSeed: Action[AnyContent] = Action.async { implicit request: Request[AnyContent] =>
    if (common.checkAuth(request.headers.toMap("Cookie").toString())) {
      SeedsForm.updateForm.bindFromRequest().fold(
        // if any error in submitted data
        errorForm => {
          errorForm.errors.foreach(println)
          Future.successful(BadRequest("Error!"))
        },
        data => {
          val seedItem = Seeds(data.id, data.name, data.quantity, data.weight, data.price, data.description)
          seedsService.updateItem(seedItem).map(_ => Redirect(routes.SeedsController.getAll()))
        })
    }
    else {

      Future {
        Forbidden(common.ERROR_MESSAGE)
      }
    }
  }

  def updateSeedView(id: Long): Action[AnyContent] = Action.async { implicit request: MessagesRequest[AnyContent] =>
    val seed = seedsService.getItem(id)
    seed.map(seed => {
      val seedForm = SeedsForm.updateForm.fill(SeedsUpdateFormData(seed.get.id, seed.get.name, seed.get.quantity, seed.get.weight, seed.get.price, seed.get.description))
      Ok(views.html.seeds.seedupdate(seedForm))
    })
  }

  def delete(id: Long): Action[AnyContent] = Action.async { implicit request: Request[AnyContent] =>
    if (common.checkAuth(request.headers.toMap("Cookie").toString())) {
      seedsService.deleteItem(id) map { res =>
        Redirect(routes.SeedsController.getAll())
      }
    }
    else {

      Future {
        Forbidden(common.ERROR_MESSAGE)
      }
    }
  }
}
