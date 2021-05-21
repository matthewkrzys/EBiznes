package service

import com.google.inject.Inject
import models.entities.Plants
import models.repos.PlantsRepo

import scala.concurrent.Future

class PlantsService @Inject()(items: PlantsRepo) {

  def addItem(item: Plants): Future[String] = {
    items.add(item)
  }

  def deleteItem(id: Long): Future[Int] = {
    items.delete(id)
  }

  def updateItem(item: Plants): Future[Int] = {
    items.update(item)
  }

  def getItem(id: Long): Future[Option[Plants]] = {
    items.get(id)
  }

  def listAllItems: Future[Seq[Plants]] = {
    items.listAll
  }
}