package service

import com.google.inject.Inject
import models.entities.History
import models.repos.HistoryRepo

import scala.concurrent.Future

class HistoryService @Inject()(items: HistoryRepo) {

  def getUserItems(id: Long): Future[Seq[History]] = {
    items.listUserItems(id)
  }

  def deleteUserHistoryItems(userId: Long): Future[Int] = {
    items.deleteUserHistoryItems(userId)
  }

  def addItem(item: History): Future[String] = {
    items.add(item)
  }

  def deleteItem(id: Long): Future[Int] = {
    items.delete(id)
  }

  def updateItem(item: History): Future[Int] = {
    items.update(item)
  }

  def getItem(id: Long): Future[Option[History]] = {
    items.get(id)
  }

  def listAllItems: Future[Seq[History]] = {
    items.listAll
  }
}