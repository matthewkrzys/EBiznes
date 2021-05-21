package models.definitions

import models.entities.Honey
import slick.jdbc.MySQLProfile.api._

class HoneyTableDef(tag: Tag) extends Table[Honey](tag, "honeys") {

  def id = column[Long]("id", O.PrimaryKey, O.AutoInc)
  def name = column[String]("name")
  def quantity = column[Int]("quantity")
  def weight = column[String]("weight")
  def price = column[Double]("price")

  override def * = (id, name, quantity, weight, price) <> ((Honey.apply _).tupled, Honey.unapply)
}