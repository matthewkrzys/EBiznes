package models.definitions

import models.entities.Users
import slick.jdbc.MySQLProfile.api._

class UsersTableDef(tag: Tag) extends Table[Users](tag, "users") {

  def id = column[Long]("id", O.PrimaryKey, O.AutoInc)
  def name = column[String]("name")
  def surname = column[String]("surname")
  def email = column[String]("email")
  def telephone = column[String]("telephone")
  def city = column[String]("city")
  def street = column[String]("street")
  def buildingNumber = column[String]("buildingNumber")
  def apartmentNumber = column[String]("apartmentNumber")

  override def * = (id, name, surname, email, telephone, city, street,
    buildingNumber, apartmentNumber) <> ((Users.apply _).tupled, Users.unapply)
}