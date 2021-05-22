package models.definitions

import models.entities.Users
import slick.jdbc.MySQLProfile.api._

class UsersTableDef(tag: Tag) extends Table[Users](tag, "users") {

  def id = column[Long]("id", O.PrimaryKey, O.AutoInc)
  def name = column[String]("name")
  def surname = column[String]("surname")
  def password = column[String]("password")

  override def * = (id, name, surname, password) <> ((Users.apply _).tupled, Users.unapply)
}