package controllers

import javax.inject._
import models.entities.Flowers
import models.forms.{FlowersForm, FlowersUpdateFormData}
import play.api.libs.json._
import play.api.mvc._
import service.FlowersService

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future


class StatusOrderController @Inject()(cc: MessagesControllerComponents, flowersService: FlowersService) extends MessagesAbstractController(cc) {


  def getAll(): Action[AnyContent] = Action.async { implicit request: Request[AnyContent] =>
    flowersService.listAllItems map ( items =>
      Ok(Json.toJson(items))
      )
  }

  def add(): Action[AnyContent] = Action.async { implicit request: Request[AnyContent] =>
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
}
