package controllers

import controllers.request.Common
import javax.inject._
import models.entities.{History, Users}
import models.forms.{UsersForm, UsersUpdateFormData}
import play.api.libs.json._
import play.api.mvc._
import service.{HistoryService, UsersService}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.util.{Failure, Success}

@Singleton
class UsersController @Inject()(cc: MessagesControllerComponents, usersService: UsersService, historyService: HistoryService, common: Common) extends MessagesAbstractController(cc) {

  def getAll(): Action[AnyContent] = Action.async { implicit request: Request[AnyContent] =>
    usersService.listAllItems map ( items =>
      Ok(Json.toJson(items))
      )
  }

  def getAllUsersView(): Action[AnyContent] = Action.async { implicit request: Request[AnyContent] =>
    val users = usersService.listAllItems
    users.map(users => Ok(views.html.users.users(users)))
  }

  def getById(id: Long): Action[AnyContent] = Action.async { implicit request: Request[AnyContent] =>
    usersService.getItem(id) map { item =>
      Ok(Json.toJson(item))
    }
  }


  def getByEmail(email: String): Action[AnyContent] = Action.async { implicit request: Request[AnyContent] =>
    usersService.getItemByEmail(email) map { item =>
      Ok(Json.toJson(item))
    }
  }

  def getByIdUserView(id: Long): Action[AnyContent] = Action.async { implicit request: Request[AnyContent] =>
    val user = usersService.getItem(id)
    var history: Seq[History] = Seq[History]()
    user.map(user => user match {
      case Some(u) => Ok(views.html.users.user(history, u))
      case None => Redirect(routes.UsersController.getAll())
    })
  }

  def add(): Action[AnyContent] = Action.async { implicit request: Request[AnyContent] =>
    UsersForm.form.bindFromRequest().fold(
      // if any error in submitted data
      errorForm => {
        errorForm.errors.foreach(println)
        Future.successful(BadRequest("Error!"))
      },
      data => {
        val newUserItem = Users(0, data.name, data.surname, data.email, data.telephone, data.city, data.street, data.buildingNumber, data.apartmentNumber)
        usersService.addItem(newUserItem).map(_ => Redirect(routes.UsersController.getAll()))
      })
  }

  def addUserView: Action[AnyContent] = Action.async { implicit request =>

    UsersForm.form.bindFromRequest.fold(
      errorForm => {
        Future.successful(
          BadRequest(views.html.users.adduser(errorForm))
        )
      },
      user => {
        usersService.addItem(Users(0, user.name, user.surname, user.email, user.telephone,
          user.city, user.street, user.buildingNumber, user.apartmentNumber)).map { _ =>
          Redirect(routes.UsersController.add())
        }
      }
    )

  }

  def updateUser = Action.async { implicit request =>
    println("update")
    if (common.checkAuth(request.headers.toMap("Cookie").toString())) {
      println(UsersForm.updateForm.bindFromRequest)
      UsersForm.updateForm.bindFromRequest.fold(
        errorForm => {
          Future.successful(
            BadRequest(views.html.users.updateuser(errorForm))
          )
        },
        user => {
          println(" user " + user)
          val userItem = Users(user.id, user.name, user.surname, user.email, user.telephone,
            user.city, user.street, user.buildingNumber, user.apartmentNumber)
          usersService.updateItem(userItem).map { _ =>
            Redirect(routes.UsersController.getAll())
          }
        }
      )
    }
    else {
      Future {Forbidden("Wrong Auth")}
    }

  }

  def updateUserView(id: Long): Action[AnyContent] = Action.async { implicit request: MessagesRequest[AnyContent] =>
    val user = usersService.getItem(id)
    user.map(user => {
      val userForm = UsersForm.updateForm.fill(UsersUpdateFormData(user.get.id, user.get.name, user.get.surname,
        user.get.email, user.get.telephone, user.get.city, user.get.street, user.get.buildingNumber, user.get.apartmentNumber))
      Ok(views.html.users.updateuser(userForm))
    })
  }

  def delete(id: Long): Action[AnyContent] = Action.async { implicit request: Request[AnyContent] =>
    usersService.deleteItem(id) map { res =>
      Redirect(routes.UsersController.getAll())
    }
  }

  def getByNameUserView(name: String): Action[AnyContent] = Action.async { implicit request: Request[AnyContent] =>
    usersService.getItemByName(name) map { item =>
      Ok(Json.toJson(item))
    }
  }

}
