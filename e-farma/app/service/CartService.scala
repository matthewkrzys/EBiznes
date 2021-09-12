package service

import com.google.inject.Inject

import scala.concurrent.Future
import models.entities.{CartItem, Users}

import scala.concurrent.ExecutionContext.Implicits.global

class CartService @Inject()(userService: UsersService, buyService: BuyService) {

  var listUsersProducts: Map[Int, Map[String, CartItem]] = Map()

  def deleteElement(element: CartItem): String = {
    println("delete")
    var index: String = element.tableName+element.productId
    listUsersProducts = listUsersProducts.updated(element.userId,listUsersProducts(element.userId).-(index))
    "delete"
  }

  def getCart(userId: Int) : Future[Iterable[CartItem]] = {
    if (!listUsersProducts.contains(userId)){
     listUsersProducts = listUsersProducts.updated(userId, Map())
    }
    Future { listUsersProducts(userId).values }
  }

  def addElementToCart(element: CartItem): String = {

    if ( listUsersProducts.contains(element.userId)){
      println("jest")
      var index: String = element.tableName+element.productId
      println(index)
      if ( listUsersProducts(element.userId).contains(index) ){
        println("ma taki index")
      var quantity: Int = listUsersProducts(element.userId)(index).quantity + 1
      println(quantity)
        listUsersProducts = listUsersProducts.updated(element.userId, listUsersProducts(element.userId).updated(index, CartItem(element.userId, element.productId, element.tableName, quantity)))
      }else {
        println("nie ma taki index")
        var newList = listUsersProducts(element.userId) + (index -> element)
        listUsersProducts = listUsersProducts.updated(element.userId, newList)
      }
    }
    else {
      println("nie jest")
      var index: String = element.tableName+element.productId
      listUsersProducts = Map(element.userId -> Map(index->element))
    }
    "ok"
  }

  def getStatusOrder(userId: Int) : Future[Iterable[CartItem]] = {

    var user: Future[Option[Users]] = userService.getItem(userId)

    Future { listUsersProducts(userId).values }
  }

  def getBuy(userId: Int): String = {
    buyService.modifyTables(listUsersProducts(userId))
    listUsersProducts = listUsersProducts.removed(userId)
    "You buy this products"
  }

}
