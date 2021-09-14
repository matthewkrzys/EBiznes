package controllers

import controllers.request.Common
import javax.inject._
import models.entities.Flowers
import models.forms.{FlowersForm, FlowersUpdateFormData}
import play.api.libs.json._
import play.api.mvc._
import service.FlowersService

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

@Singleton
class FlowersController @Inject()(cc: MessagesControllerComponents, flowersService: FlowersService, common: Common) extends MessagesAbstractController(cc) {

  def index() = Action {
    Ok("ok")
  }

  def getAll(): Action[AnyContent] = Action.async { implicit request: Request[AnyContent] =>
    if (common.checkAuth(request.headers.toMap("Cookie").toString())) {

      flowersService.listAllItems map (items =>
        Ok(Json.toJson(items))
        )
    }
    else {
      Future {
        Forbidden("Wrong Auth")
      }
    }
  }

  def getAllFlowersView(): Action[AnyContent] = Action.async { implicit request: Request[AnyContent] =>
    val flowers = flowersService.listAllItems
    flowers.map(flowers => Ok(views.html.flowers.flowers(flowers)))
  }

  def getById(id: Long): Action[AnyContent] = Action.async { implicit request: Request[AnyContent] =>
    if (common.checkAuth(request.headers.toMap("Cookie").toString())) {
      flowersService.getItem(id) map { item =>
        Ok(Json.toJson(item))
      }
    }
    else {
      Future {
        Forbidden("Wrong Auth")
      }
    }
  }

  def getByIdFlowerView(id: Long): Action[AnyContent] = Action.async { implicit request: Request[AnyContent] =>
    val flower = flowersService.getItem(id)
    flower.map(flower => flower match {
      case Some(f) => Ok(views.html.flowers.flower(f))
      case None => Redirect(routes.FlowersController.getAll())
    })
  }

  def add(): Action[AnyContent] = Action.async { implicit request: Request[AnyContent] =>
    if (common.checkAuth(request.headers.toMap("Cookie").toString())) {
      FlowersForm.form.bindFromRequest.fold(
        errorForm => {
          errorForm.errors.foreach(println)
          Future.successful(BadRequest("Error!"))
        },
        data => {
          val newSeedItem = Flowers(0, data.name, data.quantity, data.species, data.price, data.description)
          flowersService.addItem(newSeedItem).map(_ => Redirect(routes.FlowersController.getAll()))
        })
    }
    else {
      Future {
        Forbidden("Wrong Auth")
      }
    }
  }


  def addFlowerView(): Action[AnyContent] = Action.async { implicit request =>

    FlowersForm.form.bindFromRequest.fold(
      errorForm => {
        Future.successful(
          BadRequest(views.html.flowers.floweradd(errorForm))
        )
      },
      flower => {
        flowersService.addItem(Flowers(0, flower.name, flower.quantity, flower.species, flower.price, flower.description)).map { _ =>
          Redirect(routes.FlowersController.add())
        }
      }
    )
  }

  def updateFlower: Action[AnyContent] = Action.async { implicit request: Request[AnyContent] =>
    if (common.checkAuth(request.headers.toMap("Cookie").toString())) {
      FlowersForm.updateForm.bindFromRequest.fold(
        errorForm => {
          errorForm.errors.foreach(println)
          Future.successful(BadRequest("Error!"))
        },
        data => {
          val seedItem = Flowers(data.id, data.name, data.quantity, data.species, data.price, data.description)
          flowersService.updateItem(seedItem).map(_ => Redirect(routes.FlowersController.getAll()))
        })
    }
    else {
      Future {
        Forbidden("Wrong Auth")
      }
    }
  }

  def updateFlowerView(id: Long): Action[AnyContent] = Action.async { implicit request: MessagesRequest[AnyContent] =>
    val flower = flowersService.getItem(id)
    flower.map(flower => {
      val flowerForm = FlowersForm.updateForm.fill(FlowersUpdateFormData(flower.get.id, flower.get.name, flower.get.quantity, flower.get.species, flower.get.price, flower.get.description))
      Ok(views.html.flowers.flowerupdate(flowerForm))
    })
  }

  def delete(id: Long): Action[AnyContent] = Action.async { implicit request: Request[AnyContent] =>
    if (common.checkAuth(request.headers.toMap("Cookie").toString())) {
      flowersService.deleteItem(id) map { res =>
        Redirect(routes.FlowersController.getAll())
      }
    }
    else {
      Future {
        Forbidden("Wrong Auth")
      }
    }
  }
}
