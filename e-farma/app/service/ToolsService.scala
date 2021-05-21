package service

import com.google.inject.Inject
import models.entities.Tools
import models.repos.ToolsRepo

import scala.concurrent.Future

class ToolsService @Inject()(items: ToolsRepo) {

  def addItem(item: Tools): Future[String] = {
    items.add(item)
  }

  def deleteItem(id: Long): Future[Int] = {
    items.delete(id)
  }

  def updateItem(item: Tools): Future[Int] = {
    items.update(item)
  }

  def getItem(id: Long): Future[Option[Tools]] = {
    items.get(id)
  }

  def listAllItems: Future[Seq[Tools]] = {
    items.listAll
  }
}