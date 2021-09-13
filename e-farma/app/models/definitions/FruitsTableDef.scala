package models.definitions

import models.entities.Fruits
import slick.jdbc.MySQLProfile.api._

class FruitsTableDef(tag: Tag) extends Table[Fruits](tag, "fruits") {

  def id = column[Long]("id", O.PrimaryKey, O.AutoInc)
  def name = column[String]("name")
  def quantity = column[Int]("quantity")
  def weight = column[Double]("weight")
  def price = column[Double]("price")

  override def * = (id, name, quantity, weight, price) <> ((Fruits.apply _).tupled, Fruits.unapply)
}