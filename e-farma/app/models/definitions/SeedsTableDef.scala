package models.definitions

import models.entities.Seeds
import slick.jdbc.MySQLProfile.api._

class SeedsTableDef(tag: Tag) extends Table[Seeds](tag, "seeds") {

  def id = column[Long]("id", O.PrimaryKey, O.AutoInc)
  def name = column[String]("name")
  def quantity = column[Int]("quantity")
  def weight = column[Double]("weight")
  def price = column[Double]("price")
  def description = column[String]("description")

  override def * = (id, name, quantity, weight, price, description) <> ((Seeds.apply _).tupled, Seeds.unapply)
}