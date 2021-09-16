package controllers

import controllers.request.Common
import javax.inject._
import models.entities.Honey
import play.api.mvc._
import play.api.libs.json._
import models.forms.{HoneyForm, HoneyUpdateFormData}
import service.HoneyService

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

@Singleton
class HoneyController @Inject()(cc: MessagesControllerComponents, honeysService: HoneyService, common: Common) extends MessagesAbstractController(cc) {

  def getAll(): Action[AnyContent] = Action.async { implicit request: Request[AnyContent] =>
    if (common.checkAuth(request.headers.toMap("Cookie").toString())) {
      honeysService.listAllItems map (items =>
        Ok(Json.toJson(items))
        )
    }
    else {

      Future {
        Forbidden(common.ERROR_MESSAGE)
      }
    }
  }

  def getAllHoneysView(): Action[AnyContent] = Action.async { implicit request: Request[AnyContent] =>
    val honeys = honeysService.listAllItems
    honeys.map(honeys => Ok(views.html.honey.honeys(honeys)))
  }

  def getById(id: Long): Action[AnyContent] = Action.async { implicit request: Request[AnyContent] =>
    if (common.checkAuth(request.headers.toMap("Cookie").toString())) {
      honeysService.getItem(id) map { item =>
        Ok(Json.toJson(item))
      }
    }
    else {

      Future {
        Forbidden(common.ERROR_MESSAGE)
      }
    }
  }

  def getByIdHoneyView(id: Long): Action[AnyContent] = Action.async { implicit request: Request[AnyContent] =>
    val honey = honeysService.getItem(id)
    honey.map(honey => honey match {
      case Some(h) => Ok(views.html.honey.honey(h))
      case None => Redirect(routes.HoneyController.getAll())
    })
  }

  def add(): Action[AnyContent] = Action.async { implicit request: Request[AnyContent] =>
    if (common.checkAuth(request.headers.toMap("Cookie").toString())) {
      HoneyForm.form.bindFromRequest.fold(
        errorForm => {
          errorForm.errors.foreach(println)
          Future.successful(BadRequest("Error!"))
        },
        data => {
          val newHoneyItem = Honey(0, data.name, data.quantity, data.weight, data.price)
          honeysService.addItem(newHoneyItem).map(_ => Redirect(routes.HoneyController.getAll()))
        })
    }
    else {

      Future {
        Forbidden(common.ERROR_MESSAGE)
      }
    }
  }


  def addHoneyView(): Action[AnyContent] = Action.async { implicit request =>

    HoneyForm.form.bindFromRequest.fold(
      errorForm => {
        Future.successful(
          BadRequest(views.html.honey.honeyadd(errorForm))
        )
      },
      honey => {
        honeysService.addItem(Honey(0, honey.name, honey.quantity, honey.weight, honey.price)).map { _ =>
          Redirect(routes.HoneyController.add())
        }
      }
    )

  }

  def updateHoney: Action[AnyContent] = Action.async { implicit request: Request[AnyContent] =>
    if (common.checkAuth(request.headers.toMap("Cookie").toString())) {
      HoneyForm.updateForm.bindFromRequest.fold(
        errorForm => {
          errorForm.errors.foreach(println)
          Future.successful(BadRequest("Error!"))
        },
        data => {
          val honeyItem = Honey(data.id, data.name, data.quantity, data.weight, data.price)
          honeysService.updateItem(honeyItem).map(_ => Redirect(routes.HoneyController.getAll()))
        })
    }
    else {

      Future {
        Forbidden(common.ERROR_MESSAGE)
      }
    }
  }

  def updateHoneyView(id: Long): Action[AnyContent] = Action.async { implicit request: MessagesRequest[AnyContent] =>
    val honey = honeysService.getItem(id)
    honey.map(honey => {
      val honeyForm = HoneyForm.updateForm.fill(HoneyUpdateFormData(honey.get.id, honey.get.name, honey.get.quantity, honey.get.weight, honey.get.price))
      Ok(views.html.honey.honeyupdate(honeyForm))
    })
  }

  def delete(id: Long): Action[AnyContent] = Action.async { implicit request: Request[AnyContent] =>
    if (common.checkAuth(request.headers.toMap("Cookie").toString())) {
      honeysService.deleteItem(id) map { res =>
        Redirect(routes.HoneyController.getAll())
      }
    }
    else {

      Future {
        Forbidden(common.ERROR_MESSAGE)
      }
    }
  }
}
