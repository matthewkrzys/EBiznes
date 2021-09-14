package controllers

import controllers.request.Common
import javax.inject._
import models.entities.History
import models.forms.HistoryForm
import play.api.libs.json._
import play.api.mvc._
import service.HistoryService

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

@Singleton
class HistoryController @Inject()(cc: ControllerComponents, historyService: HistoryService, common: Common) extends AbstractController(cc) {

  def getAll(): Action[AnyContent] = Action.async { implicit request: Request[AnyContent] =>
    historyService.listAllItems map ( items =>
      Ok(Json.toJson(items))
      )
  }

  def getAllHistoryView(): Action[AnyContent] = Action.async { implicit request: Request[AnyContent] =>
    val history = historyService.listAllItems
    history.map(history => Ok(views.html.history.history(history)))
  }

  def getById(id: Long): Action[AnyContent] = Action.async { implicit request: Request[AnyContent] =>
    historyService.getItem(id) map { item =>
      Ok(Json.toJson(item))
    }
  }

  def getByUserId(userId: Long): Action[AnyContent] = Action.async { implicit request: Request[AnyContent] =>
    historyService.getUserItems(userId) map { items =>
      Ok(Json.toJson(items))
    }
  }

  def add(): Action[AnyContent] = Action.async { implicit request: Request[AnyContent] =>
    HistoryForm.form.bindFromRequest.fold(
      // if any error in submitted data
      errorForm => {
        errorForm.errors.foreach(println)
        Future.successful(BadRequest("Error!"))
      },
      data => {
        val newHistoryItem = History(0, data.product, data.description, data.users)
        historyService.addItem(newHistoryItem).map(_ => Redirect(routes.HistoryController.getAll()))
      })
  }

  def update(id: Long): Action[AnyContent] = Action.async { implicit request: Request[AnyContent] =>
    HistoryForm.form.bindFromRequest.fold(
      // if any error in submitted data
      errorForm => {
        errorForm.errors.foreach(println)
        Future.successful(BadRequest("Error!"))
      },
      data => {
        val historyItem = History(id, data.product, data.description, data.users)
        historyService.updateItem(historyItem).map(_ => Redirect(routes.HistoryController.getAll()))
      })
  }

  def delete(id: Long): Action[AnyContent] = Action.async { implicit request: Request[AnyContent] =>
    historyService.deleteItem(id) map { res =>
      Redirect(routes.HistoryController.getAll())
    }
  }

  def deleteUserHistory(userId: Long): Action[AnyContent] = Action.async { implicit request: Request[AnyContent] =>
    historyService.deleteUserHistoryItems(userId) map { res =>
      Redirect(routes.HistoryController.getAll())
    }
  }
}
