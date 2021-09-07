package services

import models.entities.Tools
import models.repos.ToolsRepo
import org.mockito.Mockito._
import org.scalatest.FunSuite
import org.scalatestplus.mockito.MockitoSugar
import play.api.mvc._
import service.ToolsService

import scala.concurrent.Future

class ToolsServiceTests extends FunSuite with Results with MockitoSugar {

  val toolElements = Tools(1l, "name", 1, 1.1, "description")

  test ("test listAllItems service") {

    val repo = mock[ToolsRepo]
    val request = Future.successful(Seq(toolElements))
    doReturn(request).when(repo).listAll
    val service = new ToolsService(repo)
    val allList = service.listAllItems
    assert(request == allList)
  }

  test ("test getById service") {

    val repo = mock[ToolsRepo]
    val request = Future.successful(Seq(toolElements))
    doReturn(request).when(repo).get(1)
    val service = new ToolsService(repo)
    val item = service.getItem(1)

    assert(request == item)

  }

  test ("test add service") {

    val repo = mock[ToolsRepo]
    val request = Future.successful(Seq(toolElements))
    doReturn(request).when(repo).add(toolElements)
    val service = new ToolsService(repo)
    val addItem = service.addItem(toolElements)

    assert(request == addItem)

  }

  test ("test update service") {

    val repo = mock[ToolsRepo]
    val request = Future.successful(Seq(toolElements))
    doReturn(request).when(repo).update(toolElements)
    val service = new ToolsService(repo)
    val updateItem = service.updateItem(toolElements)

    assert(request == updateItem)

  }

  test ("test delete service") {

    val repo = mock[ToolsRepo]
    val request = Future.successful(Seq(toolElements))
    doReturn(request).when(repo).delete(1)
    val service = new ToolsService(repo)
    val deleteItem = service.deleteItem(1)

    assert(request == deleteItem)

  }
}