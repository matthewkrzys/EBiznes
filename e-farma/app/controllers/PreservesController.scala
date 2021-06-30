package controllers

import javax.inject._
import models.entities.Preserves
import models.forms.{PreservesForm, PreservesUpdateFormData}
import play.api.libs.json._
import play.api.mvc._
import service.PreservesService

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

@Singleton
class PreservesController @Inject()(cc: MessagesControllerComponents, preservesService: PreservesService) extends MessagesAbstractController(cc) {

  def getAll(): Action[AnyContent] = Action.async { implicit request: Request[AnyContent] =>
    preservesService.listAllItems map ( items =>
      Ok(Json.toJson(items))
      )
  }

  def getAllPreservesView(): Action[AnyContent] = Action.async { implicit request: Request[AnyContent] =>
    val preserves = preservesService.listAllItems
    preserves.map(preserves => Ok(views.html.preserves.preserves(preserves)))
  }

  def getById(id: Long): Action[AnyContent] = Action.async { implicit request: Request[AnyContent] =>
    preservesService.getItem(id) map { item =>
      Ok(Json.toJson(item))
    }
  }
  def getByIdPreserveView(id: Long): Action[AnyContent] = Action.async { implicit request: Request[AnyContent] =>
    val preserve = preservesService.getItem(id)
    preserve.map(honey => honey match {
      case Some(p) => Ok(views.html.preserves.preserve(p))
      case None => Redirect(routes.PreservesController.getAll())
    })
  }


  def add(): Action[AnyContent] = Action.async { implicit request: Request[AnyContent] =>
    PreservesForm.form.bindFromRequest().fold(
      errorForm => {
        errorForm.errors.foreach(println)
        Future.successful(BadRequest("Error!"))
      },
      data => {
        val newSeedItem = Preserves(0, data.name, data.quantity, data.weight, data.price, data.description)
        preservesService.addItem(newSeedItem).map(_ => Redirect(routes.PreservesController.getAll()))
      })
  }

  def addPreserveView: Action[AnyContent] = Action.async { implicit request =>

    PreservesForm.form.bindFromRequest.fold(
      errorForm => {
        Future.successful(
          BadRequest(views.html.preserves.preserveadd(errorForm))
        )
      },
      preserve => {
        preservesService.addItem(Preserves(0, preserve.name, preserve.quantity, preserve.weight, preserve.price, preserve.description)).map { _ =>
          Redirect(routes.PreservesController.add())
        }
      }
    )

  }

  def updatePreserve: Action[AnyContent] = Action.async { implicit request: Request[AnyContent] =>
    PreservesForm.updateForm.bindFromRequest().fold(
      errorForm => {
        errorForm.errors.foreach(println)
        Future.successful(BadRequest("Error!"))
      },
      data => {
        val seedItem = Preserves(data.id, data.name, data.quantity, data.weight, data.price, data.description)
        preservesService.updateItem(seedItem).map(_ => Redirect(routes.PreservesController.getAll()))
      })
  }

  def updatePreserveView(id: Long): Action[AnyContent] = Action.async { implicit request: MessagesRequest[AnyContent] =>
    val preserve = preservesService.getItem(id)
    preserve.map(preserve => {
      val preserveForm = PreservesForm.updateForm.fill(PreservesUpdateFormData(preserve.get.id, preserve.get.name, preserve.get.quantity, preserve.get.weight, preserve.get.price, preserve.get.description))
      Ok(views.html.preserves.preserveupdate(preserveForm))
    })
  }

  def delete(id: Long): Action[AnyContent] = Action.async { implicit request: Request[AnyContent] =>
    preservesService.deleteItem(id) map { res =>
      Redirect(routes.PreservesController.getAll())
    }
  }
}
