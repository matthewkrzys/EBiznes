package service

import com.google.inject.Inject

import scala.concurrent.Future
import models.entities.{CartItem, Users}
import models.repository.UserRepository

import scala.concurrent.ExecutionContext.Implicits.global

class CartService @Inject()(userService: UsersService, buyService: BuyService, userRepository: UserRepository) {


  var listUsersProducts: Map[Int, Map[String, CartItem]] = Map()

  def deleteElement(element: CartItem): String = {
    var index: String = element.tableName+element.productId
    listUsersProducts = listUsersProducts.updated(element.userId,listUsersProducts(element.userId).-(index))
    "delete"
  }

  def getCart(userId: Int) : Future[Iterable[CartItem]] = {
    checkUserIdInListUsersProducts(userId)
    Future { listUsersProducts(userId).values }
  }

  def addElementToCart(element: CartItem): String = {

    println("dodałem ")
    if ( listUsersProducts.contains(element.userId)){
      val index: String = element.tableName+element.productId
      println(index)
      if ( listUsersProducts(element.userId).contains(index) ){
      val quantity: Int = element.quantity
      println(quantity)
        listUsersProducts = listUsersProducts.updated(element.userId,
          listUsersProducts(element.userId).updated(index,
            CartItem(element.userId, element.productId, element.productName, element.tableName, quantity)))
      }else {
        val newList = listUsersProducts(element.userId) + (index -> element)
        listUsersProducts = listUsersProducts.updated(element.userId, newList)
      }
    }
    else {
      val index: String = element.tableName+element.productId
      listUsersProducts = Map(element.userId -> Map(index->element))
    }
    "ok"
  }

  def getBuy(userId: Int): String = {
    buyService.modifyTables(listUsersProducts(userId))
    listUsersProducts = listUsersProducts.removed(userId)
    "You bought this products"
  }

  def checkUserIdInListUsersProducts(userId: Int): Boolean = {
    if (!listUsersProducts.contains(userId)){
      listUsersProducts = listUsersProducts.updated(userId, Map())
      false
    }
    true
  }

}
