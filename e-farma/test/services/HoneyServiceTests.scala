package services

import models.entities.Honey
import models.repos.HoneyRepo
import org.mockito.Mockito._
import org.scalatest.FunSuite
import org.scalatestplus.mockito.MockitoSugar
import play.api.mvc._
import service.HoneyService

import scala.concurrent.Future

class HoneyServiceTests extends FunSuite with Results with MockitoSugar {

  val HoneyElements = Honey(1l, "name", 1, "weight", 1.1)

  test ("test listAllItems service") {

    val repo = mock[HoneyRepo]
    val request = Future.successful(Seq(HoneyElements))
    doReturn(request).when(repo).listAll
    val service = new HoneyService(repo)
    val allList = service.listAllItems
    assert(request == allList)
  }

  test ("test getById service") {

    val repo = mock[HoneyRepo]
    val request = Future.successful(Seq(HoneyElements))
    doReturn(request).when(repo).get(1)
    val service = new HoneyService(repo)
    val item = service.getItem(1)

    assert(request == item)

  }

  test ("test add service") {

    val repo = mock[HoneyRepo]
    val request = Future.successful(Seq(HoneyElements))
    doReturn(request).when(repo).add(HoneyElements)
    val service = new HoneyService(repo)
    val addItem = service.addItem(HoneyElements)

    assert(request == addItem)

  }

  test ("test update service") {

    val repo = mock[HoneyRepo]
    val request = Future.successful(Seq(HoneyElements))
    doReturn(request).when(repo).update(HoneyElements)
    val service = new HoneyService(repo)
    val updateItem = service.updateItem(HoneyElements)

    assert(request == updateItem)

  }

  test ("test delete service") {

    val repo = mock[HoneyRepo]
    val request = Future.successful(Seq(HoneyElements))
    doReturn(request).when(repo).delete(1)
    val service = new HoneyService(repo)
    val deleteItem = service.deleteItem(1)

    assert(request == deleteItem)

  }
}