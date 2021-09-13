package service

import com.google.inject.Inject
import models.entities.{CartItem, Flowers, Fruits, Honey, Plants, Preserves, Seeds, Tools, Vegetables}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{Failure, Success}

class BuyService @Inject()(flowersService: FlowersService, fruitsService: FruitsService, honeyService: HoneyService,
                           plantsService: PlantsService, preservesService: PreservesService, seedsService: SeedsService,
                           toolsService: ToolsService, vegetablesService: VegetablesService) {

  val errorMessage = "An error has occurred: ";

  def modifyTables(stringToItem: Map[String, CartItem]) = {
      println(stringToItem)
      stringToItem.map(item => {
         modifyCommon(item._2.tableName, item._2)
      })
  }

  def modifyCommon(tableName: String, item: CartItem): Unit = {
    tableName match {
      case "Flowers" => modifyFlowers(item)
      case "Fruit" => modifyFruits(item)
      case "Honey" => modifyHoney(item)
      case "Plants" => modifyPlants(item)
      case "Preserve" => modifyPreserves(item)
      case "Seeds" => modifySeeds(item)
      case "Tools" => modifyTools(item)
      case "Vegetables" => modifyVegetables(item)

    }
  }

  def modifyFlowers(item: CartItem) {
    var flower: Flowers = null;
    flowersService.getItem(item.productId.intValue()) onComplete {
      case Success(element) => {
        println(element)
        flower = element.get
        val newFlower: Flowers = Flowers(flower.id, flower.name, flower.quantity - item.quantity, flower.species,
          flower.price, flower.description)
        println(newFlower)
        println(flower)
        flowersService.updateItem(newFlower)
      }
      case Failure(t) => println(errorMessage + t.getMessage)
    }
  }


  def modifyFruits(item: CartItem) {
    var fruit: Fruits = null;
    fruitsService.getItem(item.productId.intValue()) onComplete {
      case Success(element) => {
        println(element)
        fruit = element.get
        val newFruit: Fruits = Fruits(fruit.id, fruit.name, fruit.quantity - item.quantity, fruit.weight, fruit.price)
        println(newFruit)
        println(fruit)
        fruitsService.updateItem(newFruit)
      }
      case Failure(t) => println(errorMessage + t.getMessage)
    }
  }


  def modifyHoney(item: CartItem) {
    var honey: Honey = null;
    honeyService.getItem(item.productId.intValue()) onComplete {
      case Success(element) => {
        println(element)
        honey = element.get
        val newHoney: Honey = Honey(honey.id, honey.name, honey.quantity - item.quantity, honey.weight, honey.price)
        println(newHoney)
        println(honey)
        honeyService.updateItem(newHoney)
      }
      case Failure(t) => println(errorMessage + t.getMessage)
    }
  }

  def modifyPlants(item: CartItem) {
    var plant: Plants = null;
    plantsService.getItem(item.productId.intValue()) onComplete {
      case Success(element) => {
        println(element)
        plant = element.get
        val newPlant: Plants = Plants(plant.id, plant.name, plant.quantity - item.quantity, plant.species, plant.price,
          plant.description)
        println(newPlant)
        println(plant)
        plantsService.updateItem(newPlant)
      }
      case Failure(t) => println(errorMessage + t.getMessage)
    }
  }

  def modifyPreserves(item: CartItem) {
    var preserve: Preserves = null;
    preservesService.getItem(item.productId.intValue()) onComplete {
      case Success(element) => {
        println(element)
        preserve = element.get
        val newPreserve: Preserves = Preserves(preserve.id, preserve.name, preserve.quantity - item.quantity,
          preserve.weight, preserve.price, preserve.description)
        println(newPreserve)
        println(preserve)
        preservesService.updateItem(newPreserve)
      }
      case Failure(t) => println(errorMessage + t.getMessage)
    }
  }

  def modifySeeds(item: CartItem) {
    var seed: Seeds = null;
    seedsService.getItem(item.productId.intValue()) onComplete {
      case Success(element) => {
        println(element)
        seed = element.get
        val newSeed: Seeds = Seeds(seed.id, seed.name, seed.quantity - item.quantity, seed.weight, seed.price,
          seed.description)
        println(newSeed)
        println(seed)
        seedsService.updateItem(newSeed)
      }
      case Failure(t) => println(errorMessage + t.getMessage)
    }
  }

  def modifyTools(item: CartItem) {
    var tool: Tools = null;
    toolsService.getItem(item.productId.intValue()) onComplete {
      case Success(element) => {
        println(element)
        tool = element.get
        val newTool: Tools = Tools(tool.id, tool.name, tool.quantity - item.quantity, tool.price, tool.description)
        println(newTool)
        println(tool)
        toolsService.updateItem(newTool)
      }
      case Failure(t) => println(errorMessage + t.getMessage)
    }
  }

  def modifyVegetables(item: CartItem) {
    var vegetable: Vegetables = null;
    vegetablesService.getItem(item.productId.intValue()) onComplete {
      case Success(element) => {
        println(element)
        vegetable = element.get
        val newVegetable: Vegetables = Vegetables(vegetable.id, vegetable.name, vegetable.quantity - item.quantity,
          vegetable.weight, vegetable.price)
        println(newVegetable)
        println(vegetable)
        vegetablesService.updateItem(newVegetable)
      }
      case Failure(t) => println(errorMessage + t.getMessage)
    }
  }
}
