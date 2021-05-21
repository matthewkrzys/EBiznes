package models.definitions

import models.entities.Fruits
import slick.jdbc.MySQLProfile.api._

class FruitsTableDef(tag: Tag) extends Table[Fruits](tag, "fruits") {

  def id = column[Long]("id", O.PrimaryKey, O.AutoInc)
  def name = column[String]("name")
  def quanity = column[Int]("quanity")
  def weight = column[String]("weight")
  def price = column[Double]("price")

  override def * = (id, name, quanity, weight, price) <> (Fruits.tupled, Fruits.unapply)
}