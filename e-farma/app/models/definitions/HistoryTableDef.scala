package models.definitions

import models.entities.History
import slick.jdbc.MySQLProfile.api._

class HistoryTableDef(tag: Tag) extends Table[History](tag, "history") {

  def id = column[Long]("id", O.PrimaryKey, O.AutoInc)
  def product = column[String]("product")
  def description = column[String]("description")
  def users = column[Long]("users")

  override def * = (id, product, description, users) <> ((History.apply _).tupled, History.unapply)
}