package services

import models.entities.Fruits
import models.repos.FruitsRepo
import org.mockito.Mockito._
import org.scalatest.FunSuite
import org.scalatestplus.mockito.MockitoSugar
import play.api.mvc._
import service.FruitsService

import scala.concurrent.Future

class FruitsServiceTests extends FunSuite with Results with MockitoSugar {

  private val fruitElements = Fruits(1l, "name", 1, "weight", 1.1)

  test ("test listAllItems service") {

    val repo = mock[FruitsRepo]
    val request = Future.successful(Seq(fruitElements))
    doReturn(request).when(repo).listAll
    val service = new FruitsService(repo)
    val allList = service.listAllItems
    assert(request == allList)
  }

  test ("test getById service") {

    val repo = mock[FruitsRepo]
    val request = Future.successful(Seq(fruitElements))
    doReturn(request).when(repo).get(1)
    val service = new FruitsService(repo)
    val item = service.getItem(1)

    assert(request == item)

  }

  test ("test add service") {

    val repo = mock[FruitsRepo]
    val request = Future.successful(Seq(fruitElements))
    doReturn(request).when(repo).add(fruitElements)
    val service = new FruitsService(repo)
    val addItem = service.addItem(fruitElements)

    assert(request == addItem)

  }

  test ("test update service") {

    val repo = mock[FruitsRepo]
    val request = Future.successful(Seq(fruitElements))
    doReturn(request).when(repo).update(fruitElements)
    val service = new FruitsService(repo)
    val updateItem = service.updateItem(fruitElements)

    assert(request == updateItem)

  }

  test ("test delete service") {

    val repo = mock[FruitsRepo]
    val request = Future.successful(Seq(fruitElements))
    doReturn(request).when(repo).delete(1)
    val service = new FruitsService(repo)
    val deleteItem = service.deleteItem(1)

    assert(request == deleteItem)

  }
}