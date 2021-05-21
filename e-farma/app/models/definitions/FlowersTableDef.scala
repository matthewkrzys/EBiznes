package models.definitions

import models.entities.Flowers
import slick.jdbc.MySQLProfile.api._

class FlowersTableDef(tag: Tag) extends Table[Flowers](tag, "flowers") {

  def id = column[Long]("id", O.PrimaryKey, O.AutoInc)
  def name = column[String]("name")
  def quantity = column[Int]("quantity")
  def species = column[String]("species")
  def price = column[Double]("price")
  def description = column[String]("description")

  override def * = (id, name, quantity, species, price, description) <> ((Flowers.apply _).tupled, Flowers.unapply)
}