package models.repos

import com.google.inject.Inject
import models.definitions.PreservesTableDef
import models.entities.Preserves
import play.api.db.slick.DatabaseConfigProvider
import slick.jdbc.JdbcProfile
import slick.jdbc.MySQLProfile.api._

import scala.concurrent.{ExecutionContext, Future}

class PreservesRepo @Inject()(protected val dbConfigProvider: DatabaseConfigProvider )(implicit executionContext: ExecutionContext) {

  var preservesList = TableQuery[PreservesTableDef]
  private val dbConfig = dbConfigProvider.get[JdbcProfile]

  def add(preserveItem: Preserves): Future[String] = {
    dbConfig.db
      .run(preservesList += preserveItem)
      .map(res => "PreserveItem successfully added")
      .recover {
        case ex: Exception => {
          printf(ex.getMessage())
          ex.getMessage
        }
      }
  }

  def delete(id: Long): Future[Int] = {
    dbConfig.db.run(preservesList.filter(_.id === id).delete)
  }

  def update(preserveItem: Preserves): Future[Int] = {
    dbConfig.db
      .run(preservesList.filter(_.id === preserveItem.id)
        .map(x => (x.name, x.quantity, x.weight, x.price, x.description))
        .update(preserveItem.name, preserveItem.quantity, preserveItem.weight, preserveItem.price, preserveItem.description)
      )
  }

  def get(id: Long): Future[Option[Preserves]] = {
    dbConfig.db.run(preservesList.filter(_.id === id).result.headOption)
  }

  def listAll: Future[Seq[Preserves]] = {
    dbConfig.db.run(preservesList.result)
  }
}
