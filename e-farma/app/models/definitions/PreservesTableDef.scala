package models.definitions

import models.entities.Preserves
import slick.jdbc.MySQLProfile.api._

class PreservesTableDef(tag: Tag) extends Table[Preserves](tag, "preserves") {

  def id = column[Long]("id", O.PrimaryKey, O.AutoInc)
  def name = column[String]("name")
  def quantity = column[Int]("quantity")
  def weight = column[Double]("weight")
  def price = column[Double]("price")
  def description = column[String]("description")

  override def * = (id, name, quantity, weight, price, description) <> ((Preserves.apply _).tupled, Preserves.unapply)
}