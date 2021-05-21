package service

import com.google.inject.Inject
import models.entities.Seeds
import models.repos.SeedsRepo

import scala.concurrent.Future

class SeedsService @Inject()(items: SeedsRepo) {

  def addItem(item: Seeds): Future[String] = {
    items.add(item)
  }

  def deleteItem(id: Long): Future[Int] = {
    items.delete(id)
  }

  def updateItem(item: Seeds): Future[Int] = {
    items.update(item)
  }

  def getItem(id: Long): Future[Option[Seeds]] = {
    items.get(id)
  }

  def listAllItems: Future[Seq[Seeds]] = {
    items.listAll
  }
}