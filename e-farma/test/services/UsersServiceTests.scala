package services

import models.entities.Users
import models.repos.UsersRepo
import org.mockito.Mockito._
import org.scalatest.FunSuite
import org.scalatestplus.mockito.MockitoSugar
import play.api.mvc._
import service.UsersService

import scala.concurrent.Future

class UsersServiceTests extends FunSuite with Results with MockitoSugar {

  val userElements = Users(1l, "name", "surname", "password", "email", "telephone", "address")

  test ("test listAllItems service") {

    val repo = mock[UsersRepo]
    val request = Future.successful(Seq(userElements))
    doReturn(request).when(repo).listAll
    val service = new UsersService(repo)
    val allList = service.listAllItems
    assert(request == allList)
  }

  test ("test getById service") {

    val repo = mock[UsersRepo]
    val request = Future.successful(Seq(userElements))
    doReturn(request).when(repo).get(1)
    val service = new UsersService(repo)
    val item = service.getItem(1)

    assert(request == item)

  }

  test ("test add service") {

    val repo = mock[UsersRepo]
    val request = Future.successful(Seq(userElements))
    doReturn(request).when(repo).add(userElements)
    val service = new UsersService(repo)
    val addItem = service.addItem(userElements)

    assert(request == addItem)

  }

  test ("test update service") {

    val repo = mock[UsersRepo]
    val request = Future.successful(Seq(userElements))
    doReturn(request).when(repo).update(userElements)
    val service = new UsersService(repo)
    val updateItem = service.updateItem(userElements)

    assert(request == updateItem)

  }

  test ("test delete service") {

    val repo = mock[UsersRepo]
    val request = Future.successful(Seq(userElements))
    doReturn(request).when(repo).delete(1)
    val service = new UsersService(repo)
    val deleteItem = service.deleteItem(1)

    assert(request == deleteItem)

  }
}