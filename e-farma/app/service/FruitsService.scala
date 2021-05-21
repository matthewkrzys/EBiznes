package service

import com.google.inject.Inject
import models.{Fruits, FruitsList}

import scala.concurrent.Future

class FruitsService @Inject() (items: FruitsList) {

  def addItem(item: Fruits): Future[String] = {
    items.add(item)
  }

  def deleteItem(id: Long): Future[Int] = {
    items.delete(id)
  }

  def updateItem(item: Fruits): Future[Int] = {
    items.update(item)
  }

  def getItem(id: Long): Future[Option[Fruits]] = {
    items.get(id)
  }

  def listAllItems: Future[Seq[Fruits]] = {
    items.listAll
  }
}