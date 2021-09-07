package services

import models.entities.Seeds
import models.repos.SeedsRepo
import org.mockito.Mockito._
import org.scalatest.FunSuite
import org.scalatestplus.mockito.MockitoSugar
import play.api.mvc._
import service.SeedsService

import scala.concurrent.Future

class SeedsServiceTests extends FunSuite with Results with MockitoSugar {

  val seedElements = Seeds(1l, "name", 1, "species", 1.1, "description")

  test ("test listAllItems service") {

    val repo = mock[SeedsRepo]
    val request = Future.successful(Seq(seedElements))
    doReturn(request).when(repo).listAll
    val service = new SeedsService(repo)
    val allList = service.listAllItems
    assert(request == allList)
  }

  test ("test getById service") {

    val repo = mock[SeedsRepo]
    val request = Future.successful(Seq(seedElements))
    doReturn(request).when(repo).get(1)
    val service = new SeedsService(repo)
    val item = service.getItem(1)

    assert(request == item)

  }

  test ("test add service") {

    val repo = mock[SeedsRepo]
    val request = Future.successful(Seq(seedElements))
    doReturn(request).when(repo).add(seedElements)
    val service = new SeedsService(repo)
    val addItem = service.addItem(seedElements)

    assert(request == addItem)

  }

  test ("test update service") {

    val repo = mock[SeedsRepo]
    val request = Future.successful(Seq(seedElements))
    doReturn(request).when(repo).update(seedElements)
    val service = new SeedsService(repo)
    val updateItem = service.updateItem(seedElements)

    assert(request == updateItem)

  }

  test ("test delete service") {

    val repo = mock[SeedsRepo]
    val request = Future.successful(Seq(seedElements))
    doReturn(request).when(repo).delete(1)
    val service = new SeedsService(repo)
    val deleteItem = service.deleteItem(1)

    assert(request == deleteItem)

  }
}