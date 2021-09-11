package service

import com.google.inject.Inject
import models.entities.Users
import models.repos.UsersRepo

import scala.concurrent.Future

class UsersService @Inject()(items: UsersRepo) {

  def addItem(item: Users): Future[String] = {
    items.add(item)
  }

  def deleteItem(id: Long): Future[Int] = {
    items.delete(id)
  }

  def updateItem(item: Users): Future[Int] = {
    items.update(item)
  }

  def getItem(id: Long): Future[Option[Users]] = {
    items.get(id)
  }

  def getItemByName(name: String): Future[Option[Users]] = {
    items.get(name)
  }

  def listAllItems: Future[Seq[Users]] = {
    items.listAll
  }
}