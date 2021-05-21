package models.entities

case class Fruits(id: Long, name: String, quanity: Int, weight: String, price: Double)

case class FruitsFormData(name: String, quanity: Int, weight: String, price: Double)
