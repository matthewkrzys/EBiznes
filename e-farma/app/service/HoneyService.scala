package service

import com.google.inject.Inject
import models.entities.Honey
import models.repos.HoneyRepo

import scala.concurrent.Future

class HoneyService @Inject() (items: HoneyRepo) {

  def addItem(item: Honey): Future[String] = {
    items.add(item)
  }

  def deleteItem(id: Long): Future[Int] = {
    items.delete(id)
  }

  def updateItem(item: Honey): Future[Int] = {
    items.update(item)
  }

  def getItem(id: Long): Future[Option[Honey]] = {
    items.get(id)
  }

  def listAllItems: Future[Seq[Honey]] = {
    items.listAll
  }
}