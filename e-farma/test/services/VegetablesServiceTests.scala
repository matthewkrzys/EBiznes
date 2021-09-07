package services

import models.entities.Vegetables
import models.repos.VegetablesRepo
import org.mockito.Mockito._
import org.scalatest.FunSuite
import org.scalatestplus.mockito.MockitoSugar
import play.api.mvc._
import service.VegetablesService

import scala.concurrent.Future

class VegetablesServiceTests extends FunSuite with Results with MockitoSugar {

  val vegetableElements = Vegetables(1l, "name", 1, "weight", 1.1)

  test ("test listAllItems service") {

    val repo = mock[VegetablesRepo]
    val request = Future.successful(Seq(vegetableElements))
    doReturn(request).when(repo).listAll
    val service = new VegetablesService(repo)
    val allList = service.listAllItems
    assert(request == allList)
  }

  test ("test getById service") {

    val repo = mock[VegetablesRepo]
    val request = Future.successful(Seq(vegetableElements))
    doReturn(request).when(repo).get(1)
    val service = new VegetablesService(repo)
    val item = service.getItem(1)

    assert(request == item)

  }

  test ("test add service") {

    val repo = mock[VegetablesRepo]
    val request = Future.successful(Seq(vegetableElements))
    doReturn(request).when(repo).add(vegetableElements)
    val service = new VegetablesService(repo)
    val addItem = service.addItem(vegetableElements)

    assert(request == addItem)

  }

  test ("test update service") {

    val repo = mock[VegetablesRepo]
    val request = Future.successful(Seq(vegetableElements))
    doReturn(request).when(repo).update(vegetableElements)
    val service = new VegetablesService(repo)
    val updateItem = service.updateItem(vegetableElements)

    assert(request == updateItem)

  }

  test ("test delete service") {

    val repo = mock[VegetablesRepo]
    val request = Future.successful(Seq(vegetableElements))
    doReturn(request).when(repo).delete(1)
    val service = new VegetablesService(repo)
    val deleteItem = service.deleteItem(1)

    assert(request == deleteItem)

  }
}