package models.definitions

import models.entities.Vegetables
import slick.jdbc.MySQLProfile.api._

class VegetablesTableDef(tag: Tag) extends Table[Vegetables](tag, "vegetables") {

  def id = column[Long]("id", O.PrimaryKey, O.AutoInc)
  def name = column[String]("name")
  def quantity = column[Int]("quantity")
  def weight = column[String]("weight")
  def price = column[Double]("price")

  override def * = (id, name, quantity, weight, price) <> ((Vegetables.apply _).tupled, Vegetables.unapply)
}