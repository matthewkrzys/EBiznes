package models.definitions

import models.entities.Plants
import slick.jdbc.MySQLProfile.api._

class PlantsTableDef(tag: Tag) extends Table[Plants](tag, "plants") {

  def id = column[Long]("id", O.PrimaryKey, O.AutoInc)
  def name = column[String]("name")
  def quantity = column[Int]("quantity")
  def species = column[String]("species")
  def price = column[Double]("price")
  def description = column[String]("description")

  override def * = (id, name, quantity, species, price, description) <> ((Plants.apply _).tupled, Plants.unapply)
}