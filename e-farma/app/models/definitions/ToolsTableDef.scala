package models.definitions

import models.entities.Tools
import slick.jdbc.MySQLProfile.api._

class ToolsTableDef(tag: Tag) extends Table[Tools](tag, "plants") {

  def id = column[Long]("id", O.PrimaryKey, O.AutoInc)
  def name = column[String]("name")
  def quantity = column[Int]("quantity")
  def price = column[Double]("price")
  def description = column[String]("description")

  override def * = (id, name, quantity, price, description) <> ((Tools.apply _).tupled, Tools.unapply)
}