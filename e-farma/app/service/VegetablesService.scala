package service

import com.google.inject.Inject
import models.entities.Vegetables
import models.repos.VegetablesRepo

import scala.concurrent.Future

class VegetablesService @Inject() (items: VegetablesRepo) {

  def addItem(item: Vegetables): Future[String] = {
    items.add(item)
  }

  def deleteItem(id: Long): Future[Int] = {
    items.delete(id)
  }

  def updateItem(item: Vegetables): Future[Int] = {
    items.update(item)
  }

  def getItem(id: Long): Future[Option[Vegetables]] = {
    items.get(id)
  }

  def listAllItems: Future[Seq[Vegetables]] = {
    items.listAll
  }
}