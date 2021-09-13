package models.repos

import com.google.inject.Inject
import models.definitions.UsersTableDef
import models.entities.Users
import play.api.db.slick.DatabaseConfigProvider
import slick.jdbc.JdbcProfile
import slick.jdbc.MySQLProfile.api._

import scala.concurrent.{ExecutionContext, Future}

class UsersRepo @Inject()(protected val dbConfigProvider: DatabaseConfigProvider)(implicit executionContext: ExecutionContext) {


  var usersList = TableQuery[UsersTableDef]
  private val dbConfig = dbConfigProvider.get[JdbcProfile]

  def add(usersItem: Users): Future[String] = {

    dbConfig.db
      .run(usersList += usersItem)
      .map(res => "UsersItem successfully added")
      .recover {
        case ex: Exception => {
          printf(ex.getMessage())
          ex.getMessage
        }
      }
  }

  def delete(id: Long): Future[Int] = {
    val num = id.toString
    val q = sql"delete from Users where id=$num".as[String]
    val affectedRowsCount: Future[Vector[String]] = dbConfig.db.run(q.transactionally)
    affectedRowsCount.map(s => s.length)
  }

  def update(usersItem: Users): Future[Int] = {
    dbConfig.db
      .run(usersList.filter(_.id === usersItem.id)
        .map(x => (x.name, x.surname, x.telephone, x.city, x.street, x.buildingNumber, x.apartmentNumber))
        .update(usersItem.name, usersItem.surname, usersItem.telephone, usersItem.city, usersItem.street,
          usersItem.buildingNumber, usersItem.apartmentNumber)
      )
  }

  def get(id: Long): Future[Option[Users]] = {
    dbConfig.db.run(usersList.filter(_.id === id).result.headOption)
  }

  def get(email: String): Future[Option[Users]] = {
    dbConfig.db.run(usersList.filter(_.email === email).result.headOption)
  }

  def listAll: Future[Seq[Users]] = {
    dbConfig.db.run(usersList.result)
  }
}
