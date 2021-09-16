package controllers

import controllers.request.Common
import javax.inject._
import models.entities.Tools
import models.forms.{ToolsForm, ToolsUpdateFormData}
import play.api.libs.json._
import play.api.mvc._
import service.ToolsService

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

@Singleton
class ToolsController @Inject()(cc: MessagesControllerComponents, toolsService: ToolsService, common: Common) extends MessagesAbstractController(cc) {

  def getAll(): Action[AnyContent] = Action.async { implicit request: Request[AnyContent] =>
    if (common.checkAuth(request.headers.toMap("Cookie").toString())) {
      toolsService.listAllItems map (items =>
        Ok(Json.toJson(items))
        )
    }
    else {
      Future {
        Forbidden(common.ERROR_MESSAGE)
      }
    }
  }

  def getAllToolsView(): Action[AnyContent] = Action.async { implicit request: Request[AnyContent] =>
    val tools = toolsService.listAllItems
    tools.map(tools => Ok(views.html.tools.tools(tools)))
  }

  def getById(id: Long): Action[AnyContent] = Action.async { implicit request: Request[AnyContent] =>
    if (common.checkAuth(request.headers.toMap("Cookie").toString())) {
      toolsService.getItem(id) map { item =>
        Ok(Json.toJson(item))
      }
    }
    else {
      Future {
        Forbidden(common.ERROR_MESSAGE)
      }
    }
  }

  def getByIdToolView(id: Long): Action[AnyContent] = Action.async { implicit request: Request[AnyContent] =>
    val tool = toolsService.getItem(id)
    tool.map(tool => tool match {
      case Some(t) => Ok(views.html.tools.tool(t))
      case None => Redirect(routes.ToolsController.getAll())
    })
  }

  def add(): Action[AnyContent] = Action.async { implicit request: Request[AnyContent] =>
    if (common.checkAuth(request.headers.toMap("Cookie").toString())) {
      ToolsForm.form.bindFromRequest().fold(
        errorForm => {
          errorForm.errors.foreach(println)
          Future.successful(BadRequest("Error!"))
        },
        data => {
          val newSeedItem = Tools(0, data.name, data.quantity, data.price, data.description)
          toolsService.addItem(newSeedItem).map(_ => Redirect(routes.ToolsController.getAll()))
        })
    }
    else {
      Future {
        Forbidden(common.ERROR_MESSAGE)
      }
    }
  }

  def addToolView(): Action[AnyContent] = Action.async { implicit request =>
    ToolsForm.form.bindFromRequest.fold(
      errorForm => {
        Future.successful(
          BadRequest(views.html.tools.tooladd(errorForm))
        )
      },
      tool => {
        toolsService.addItem(Tools(0, tool.name, tool.quantity, tool.price, tool.description)).map { _ =>
          Redirect(routes.ToolsController.add())
        }
      }
    )
  }


  def updateTool: Action[AnyContent] = Action.async { implicit request: Request[AnyContent] =>
    if (common.checkAuth(request.headers.toMap("Cookie").toString())) {
      ToolsForm.updateForm.bindFromRequest().fold(
        errorForm => {
          errorForm.errors.foreach(println)
          Future.successful(BadRequest("Error!"))
        },
        data => {
          val seedItem = Tools(data.id, data.name, data.quantity, data.price, data.description)
          toolsService.updateItem(seedItem).map(_ => Redirect(routes.ToolsController.getAll()))
        })
    }
    else {
      Future {
        Forbidden(common.ERROR_MESSAGE)
      }
    }
  }

  def updateToolView(id: Long): Action[AnyContent] = Action.async { implicit request: MessagesRequest[AnyContent] =>
    val tool = toolsService.getItem(id)
    tool.map(tool => {
      val toolForm = ToolsForm.updateForm.fill(ToolsUpdateFormData(tool.get.id, tool.get.name, tool.get.quantity, tool.get.price, tool.get.description))
      Ok(views.html.tools.toolupdate(toolForm))
    })
  }

  def delete(id: Long): Action[AnyContent] = Action.async { implicit request: Request[AnyContent] =>
    if (common.checkAuth(request.headers.toMap("Cookie").toString())) {
      toolsService.deleteItem(id) map { res =>
        Redirect(routes.ToolsController.getAll())
      }
    }
    else {
      Future {
        Forbidden(common.ERROR_MESSAGE)
      }
    }
  }
}
