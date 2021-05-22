package controllers

import javax.inject._
import models.entities.Users
import models.forms.UsersForm
import play.api.libs.json._
import play.api.mvc._
import service.UsersService

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

@Singleton
class UsersController @Inject()(cc: ControllerComponents, usersService: UsersService) extends AbstractController(cc) {

  def getAll(): Action[AnyContent] = Action.async { implicit request: Request[AnyContent] =>
    usersService.listAllItems map ( items =>
      Ok(Json.toJson(items))
      )
  }

  def getById(id: Long): Action[AnyContent] = Action.async { implicit request: Request[AnyContent] =>
    usersService.getItem(id) map { item =>
      Ok(Json.toJson(item))
    }
  }

  def add(): Action[AnyContent] = Action.async { implicit request: Request[AnyContent] =>
    UsersForm.form.bindFromRequest().fold(
      // if any error in submitted data
      errorForm => {
        errorForm.errors.foreach(println)
        Future.successful(BadRequest("Error!"))
      },
      data => {
        val newHistoryItem = Users(0, data.name, data.surname, data.password)
        usersService.addItem(newHistoryItem).map(_ => Redirect(routes.UsersController.getAll()))
      })
  }

  def update(id: Long): Action[AnyContent] = Action.async { implicit request: Request[AnyContent] =>
    UsersForm.form.bindFromRequest().fold(
      // if any error in submitted data
      errorForm => {
        errorForm.errors.foreach(println)
        Future.successful(BadRequest("Error!"))
      },
      data => {
        val userItem = Users(id, data.name, data.surname, data.password)
        usersService.updateItem(userItem).map(_ => Redirect(routes.UsersController.getAll()))
      })
  }

  def delete(id: Long): Action[AnyContent] = Action.async { implicit request: Request[AnyContent] =>
    usersService.deleteItem(id) map { res =>
      Redirect(routes.UsersController.getAll())
    }
  }
}
