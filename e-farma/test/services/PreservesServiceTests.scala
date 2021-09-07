package services

import models.entities.Preserves
import models.repos.PreservesRepo
import org.mockito.Mockito._
import org.scalatest.FunSuite
import org.scalatestplus.mockito.MockitoSugar
import play.api.mvc._
import service.PreservesService

import scala.concurrent.Future

class PreservesServiceTests extends FunSuite with Results with MockitoSugar {

  val preserveElements = Preserves(1l, "name", 1, "species", 1.1, "description")

  test ("test listAllItems service") {

    val repo = mock[PreservesRepo]
    val request = Future.successful(Seq(preserveElements))
    doReturn(request).when(repo).listAll
    val service = new PreservesService(repo)
    val allList = service.listAllItems
    assert(request == allList)
  }

  test ("test getById service") {

    val repo = mock[PreservesRepo]
    val request = Future.successful(Seq(preserveElements))
    doReturn(request).when(repo).get(1)
    val service = new PreservesService(repo)
    val item = service.getItem(1)

    assert(request == item)

  }

  test ("test add service") {

    val repo = mock[PreservesRepo]
    val request = Future.successful(Seq(preserveElements))
    doReturn(request).when(repo).add(preserveElements)
    val service = new PreservesService(repo)
    val addItem = service.addItem(preserveElements)

    assert(request == addItem)

  }

  test ("test update service") {

    val repo = mock[PreservesRepo]
    val request = Future.successful(Seq(preserveElements))
    doReturn(request).when(repo).update(preserveElements)
    val service = new PreservesService(repo)
    val updateItem = service.updateItem(preserveElements)

    assert(request == updateItem)

  }

  test ("test delete service") {

    val repo = mock[PreservesRepo]
    val request = Future.successful(Seq(preserveElements))
    doReturn(request).when(repo).delete(1)
    val service = new PreservesService(repo)
    val deleteItem = service.deleteItem(1)

    assert(request == deleteItem)

  }
}