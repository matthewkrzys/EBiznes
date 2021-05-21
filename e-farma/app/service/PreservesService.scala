package service

import com.google.inject.Inject
import models.entities.Preserves
import models.repos.PreservesRepo

import scala.concurrent.Future

class PreservesService @Inject()(items: PreservesRepo) {

  def addItem(item: Preserves): Future[String] = {
    items.add(item)
  }

  def deleteItem(id: Long): Future[Int] = {
    items.delete(id)
  }

  def updateItem(item: Preserves): Future[Int] = {
    items.update(item)
  }

  def getItem(id: Long): Future[Option[Preserves]] = {
    items.get(id)
  }

  def listAllItems: Future[Seq[Preserves]] = {
    items.listAll
  }
}