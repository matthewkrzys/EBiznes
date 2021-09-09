package service

import com.google.inject.Inject
import models.entities.{CartItem, Flowers}

import scala.concurrent.ExecutionContext.Implicits.global

class BuyService @Inject()(flowersService: FlowersService, fruitsService: FruitsService, honeyService: HoneyService,
                           plantsService: PlantsService, preservesService: PreservesService, seedsService: SeedsService,
                           toolsService: ToolsService) {
  def modifyTables(stringToItem: Map[String, CartItem]) = {
      println(stringToItem)
      var flower: Flowers = null;
      println(flowersService.getItem(1))
      flowersService.getItem(1).map(item => flower = item.get);
      println(flower)
      val newFlower: Flowers = Flowers(flower.id, flower.name, flower.quantity - 1, flower.species, flower.price, flower.description)
      println(newFlower)
      flowersService.updateItem(newFlower)
  }

}
