package services

import models.entities.Flowers
import models.repos.FlowersRepo
import org.mockito.Mockito._
import org.scalatest.FunSuite
import org.scalatestplus.mockito.MockitoSugar
import play.api.mvc._
import service.FlowersService

import scala.concurrent.Future

class FlowersServiceTests extends FunSuite with Results with MockitoSugar {

  val flowerElements = Flowers(1l, "name", 1, "species", 1.1, "description")

  test ("test listAllItems service") {

    val repo = mock[FlowersRepo]
    val request = Future.successful(Seq(flowerElements))
    doReturn(request).when(repo).listAll
    val service = new FlowersService(repo)
    val allList = service.listAllItems
    assert(request == allList)
  }

  test ("test getById service") {

    val repo = mock[FlowersRepo]
    val request = Future.successful(Seq(flowerElements))
    doReturn(request).when(repo).get(1)
    val service = new FlowersService(repo)
    val item = service.getItem(1)

    assert(request == item)

  }

  test ("test add service") {

    val repo = mock[FlowersRepo]
    val request = Future.successful(Seq(flowerElements))
    doReturn(request).when(repo).add(flowerElements)
    val service = new FlowersService(repo)
    val addItem = service.addItem(flowerElements)

    assert(request == addItem)

  }

  test ("test update service") {

    val repo = mock[FlowersRepo]
    val request = Future.successful(Seq(flowerElements))
    doReturn(request).when(repo).update(flowerElements)
    val service = new FlowersService(repo)
    val updateItem = service.updateItem(flowerElements)

    assert(request == updateItem)

  }

  test ("test delete service") {

    val repo = mock[FlowersRepo]
    val request = Future.successful(Seq(flowerElements))
    doReturn(request).when(repo).delete(1)
    val service = new FlowersService(repo)
    val deleteItem = service.deleteItem(1)

    assert(request == deleteItem)

  }
}