package controllers

import javax.inject._
import models.entities.Vegetables
import play.api.mvc._
import play.api.libs.json._
import models.forms.{VegetablesForm, VegetablesUpdateFormData}
import service.VegetablesService

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

@Singleton
class VegetablesController @Inject()(cc: MessagesControllerComponents, vegetablesService: VegetablesService) extends MessagesAbstractController(cc) {

  def getAll(): Action[AnyContent] = Action.async { implicit request: Request[AnyContent] =>
    vegetablesService.listAllItems map ( items =>
      Ok(Json.toJson(items))
      )
  }

  def getAllVegetablesView(): Action[AnyContent] = Action.async { implicit request: Request[AnyContent] =>
    val vegetables = vegetablesService.listAllItems
    vegetables.map(vegetables => Ok(views.html.vegetables.vegetables(vegetables)))
  }

  def getById(id: Long): Action[AnyContent] = Action.async { implicit request: Request[AnyContent] =>
    vegetablesService.getItem(id) map { item =>
      Ok(Json.toJson(item))
    }
  }

  def getByIdVegetableView(id: Long): Action[AnyContent] = Action.async { implicit request: Request[AnyContent] =>
    val vegetable = vegetablesService.getItem(id)
    vegetable.map(vegetable => vegetable match {
      case Some(v) => Ok(views.html.vegetables.vegetable(v))
      case None => Redirect(routes.VegetablesController.getAll())
    })
  }

  def add(): Action[AnyContent] = Action.async { implicit request: Request[AnyContent] =>
    VegetablesForm.form.bindFromRequest().fold(
      errorForm => {
        errorForm.errors.foreach(println)
        Future.successful(BadRequest("Error!"))
      },
      data => {
        val newHoneyItem = Vegetables(0, data.name, data.quantity, data.weight, data.price)
        vegetablesService.addItem(newHoneyItem).map(_ => Redirect(routes.VegetablesController.getAll()))
      })
  }

  def addVegetableView: Action[AnyContent] = Action.async { implicit request =>

    VegetablesForm.form.bindFromRequest.fold(
      errorForm => {
        Future.successful(
          BadRequest(views.html.vegetables.vegetableadd(errorForm))
        )
      },
      vegetable => {
        vegetablesService.addItem(Vegetables(0, vegetable.name, vegetable.quantity, vegetable.weight, vegetable.price)).map { _ =>
          Redirect(routes.VegetablesController.add())
        }
      }
    )

  }

  def updateVegetable: Action[AnyContent] = Action.async { implicit request: Request[AnyContent] =>
    VegetablesForm.updateForm.bindFromRequest().fold(
      // if any error in submitted data
      errorForm => {
        errorForm.errors.foreach(println)
        Future.successful(BadRequest("Error!"))
      },
      data => {
        val honeyItem = Vegetables(data.id, data.name, data.quantity, data.weight, data.price)
        vegetablesService.updateItem(honeyItem).map(_ => Redirect(routes.VegetablesController.getAll()))
      })
  }

  def updateVegetableView(id: Long): Action[AnyContent] = Action.async { implicit request: MessagesRequest[AnyContent] =>
    val vegetable = vegetablesService.getItem(id)
    vegetable.map(vegetable => {
      val vegetableForm = VegetablesForm.updateForm.fill(VegetablesUpdateFormData(vegetable.get.id, vegetable.get.name, vegetable.get.quantity, vegetable.get.weight, vegetable.get.price))
      Ok(views.html.vegetables.vegetableupdate(vegetableForm))
    })
  }


  def delete(id: Long): Action[AnyContent] = Action.async { implicit request: Request[AnyContent] =>
    vegetablesService.deleteItem(id) map { res =>
      Redirect(routes.HoneyController.getAll())
    }
  }
}
