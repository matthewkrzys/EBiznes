package service

import com.google.inject.Inject
import models.entities.Flowers
import models.repos.FlowersRepo

import scala.concurrent.Future

class FlowersService @Inject()(items: FlowersRepo) {

  def addItem(item: Flowers): Future[String] = {
    items.add(item)
  }

  def deleteItem(id: Long): Future[Int] = {
    items.delete(id)
  }

  def updateItem(item: Flowers): Future[Int] = {
    items.update(item)
  }

  def getItem(id: Long): Future[Option[Flowers]] = {
    items.get(id)
  }

  def listAllItems: Future[Seq[Flowers]] = {
    items.listAll
  }
}