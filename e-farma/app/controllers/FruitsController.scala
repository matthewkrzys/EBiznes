package controllers

import controllers.request.Common
import javax.inject._
import models.entities.Fruits
import play.api.mvc._
import play.api.libs.json._
import models.forms.{FruitsForm, FruitsUpdateFormData}
import service.FruitsService

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

@Singleton
class FruitsController @Inject()(cc: MessagesControllerComponents, fruitsService: FruitsService, common: Common) extends MessagesAbstractController(cc) {

  def getAll(): Action[AnyContent] = Action.async { implicit request: Request[AnyContent] =>
    if (common.checkAuth(request.headers.toMap("Cookie").toString())) {
      fruitsService.listAllItems map (items =>
        Ok(Json.toJson(items))
        )
    }
    else {

      Future {
        Forbidden("Wrong Auth")
      }
    }
  }

  def getAllFruitsView(): Action[AnyContent] = Action.async { implicit request: Request[AnyContent] =>
    val fruits = fruitsService.listAllItems
    fruits.map(fruits => Ok(views.html.fruit.fruits(fruits)))
  }

  def getById(id: Long): Action[AnyContent] = Action.async { implicit request: Request[AnyContent] =>
    if (common.checkAuth(request.headers.toMap("Cookie").toString())) {
      fruitsService.getItem(id) map { item =>
        Ok(Json.toJson(item))
      }
    }
    else {

      Future {
        Forbidden("Wrong Auth")
      }
    }
  }


  def getByIdFruitView(id: Long): Action[AnyContent] = Action.async { implicit request: Request[AnyContent] =>
    val fruit = fruitsService.getItem(id)
    fruit.map(fruit => fruit match {
      case Some(f) => Ok(views.html.fruit.fruit(f))
      case None => Redirect(routes.FruitsController.getAll())
    })
  }

  def add(): Action[AnyContent] = Action.async { implicit request: Request[AnyContent] =>
    if (common.checkAuth(request.headers.toMap("Cookie").toString())) {
      FruitsForm.form.bindFromRequest.fold(
        errorForm => {
          errorForm.errors.foreach(println)
          Future.successful(BadRequest("Error!"))
        },
        data => {
          val newFruitItem = Fruits(0, data.name, data.quantity, data.weight, data.price)
          fruitsService.addItem(newFruitItem).map(_ => Redirect(routes.FruitsController.getAll()))
        })
    }
    else {

      Future {
        Forbidden("Wrong Auth")
      }
    }
  }


  def addFruitView: Action[AnyContent] = Action.async { implicit request =>

    FruitsForm.form.bindFromRequest.fold(
      errorForm => {
        Future.successful(
          BadRequest(views.html.fruit.fruitadd(errorForm))
        )
      },
      fruit => {
        fruitsService.addItem(Fruits(0, fruit.name, fruit.quantity, fruit.weight, fruit.price)).map { _ =>
          Redirect(routes.FruitsController.add())
        }
      }
    )

  }

  def updateFruit: Action[AnyContent] = Action.async { implicit request: Request[AnyContent] =>
    if (common.checkAuth(request.headers.toMap("Cookie").toString())) {
      FruitsForm.updateForm.bindFromRequest.fold(
        errorForm => {
          errorForm.errors.foreach(println)
          Future.successful(BadRequest("Error!"))
        },
        data => {
          val fruitItem = Fruits(data.id, data.name, data.quantity, data.weight, data.price)
          fruitsService.updateItem(fruitItem).map(_ => Redirect(routes.FruitsController.getAll()))
        })
    }
    else {

      Future {
        Forbidden("Wrong Auth")
      }
    }
  }

  def updateFruitView(id: Long): Action[AnyContent] = Action.async { implicit request: MessagesRequest[AnyContent] =>
    val fruit = fruitsService.getItem(id)
    fruit.map(fruit => {
      val fruitForm = FruitsForm.updateForm.fill(FruitsUpdateFormData(fruit.get.id, fruit.get.name, fruit.get.quantity, fruit.get.weight, fruit.get.price))
      Ok(views.html.fruit.fruitupdate(fruitForm))
    })
  }


  def delete(id: Long): Action[AnyContent] = Action.async { implicit request: Request[AnyContent] =>
    if (common.checkAuth(request.headers.toMap("Cookie").toString())) {
      fruitsService.deleteItem(id) map { res =>
        Redirect(routes.FruitsController.getAll())
      }
    }
    else {

      Future {
        Forbidden("Wrong Auth")
      }
    }
  }
}
