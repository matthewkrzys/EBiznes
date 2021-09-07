package services

import models.entities.{Plants}
import models.repos.{PlantsRepo}
import org.mockito.Mockito._
import org.scalatest.FunSuite
import org.scalatestplus.mockito.MockitoSugar
import play.api.mvc._
import service.{PlantsService}

import scala.concurrent.Future

class PlantsServiceTests extends FunSuite with Results with MockitoSugar {

  val plantElements = Plants(1l, "name", 1, "species", 1.1, "description")

  test ("test listAllItems service") {

    val repo = mock[PlantsRepo]
    val request = Future.successful(Seq(plantElements))
    doReturn(request).when(repo).listAll
    val service = new PlantsService(repo)
    val allList = service.listAllItems
    assert(request == allList)
  }

  test ("test getById service") {

    val repo = mock[PlantsRepo]
    val request = Future.successful(Seq(plantElements))
    doReturn(request).when(repo).get(1)
    val service = new PlantsService(repo)
    val item = service.getItem(1)

    assert(request == item)

  }

  test ("test add service") {

    val repo = mock[PlantsRepo]
    val request = Future.successful(Seq(plantElements))
    doReturn(request).when(repo).add(plantElements)
    val service = new PlantsService(repo)
    val addItem = service.addItem(plantElements)

    assert(request == addItem)

  }

  test ("test update service") {

    val repo = mock[PlantsRepo]
    val request = Future.successful(Seq(plantElements))
    doReturn(request).when(repo).update(plantElements)
    val service = new PlantsService(repo)
    val updateItem = service.updateItem(plantElements)

    assert(request == updateItem)

  }

  test ("test delete service") {

    val repo = mock[PlantsRepo]
    val request = Future.successful(Seq(plantElements))
    doReturn(request).when(repo).delete(1)
    val service = new PlantsService(repo)
    val deleteItem = service.deleteItem(1)

    assert(request == deleteItem)

  }
}