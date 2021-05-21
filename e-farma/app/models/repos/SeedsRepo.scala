package models.repos

import com.google.inject.Inject
import models.definitions.SeedsTableDef
import models.entities.Seeds
import play.api.db.slick.DatabaseConfigProvider
import slick.jdbc.JdbcProfile
import slick.jdbc.MySQLProfile.api._

import scala.concurrent.{ExecutionContext, Future}

class SeedsRepo @Inject()(protected val dbConfigProvider: DatabaseConfigProvider )(implicit executionContext: ExecutionContext) {

  var seedsList = TableQuery[SeedsTableDef]
  private val dbConfig = dbConfigProvider.get[JdbcProfile]

  def add(seedItem: Seeds): Future[String] = {
    dbConfig.db
      .run(seedsList += seedItem)
      .map(res => "SeedsItem successfully added")
      .recover {
        case ex: Exception => {
          printf(ex.getMessage())
          ex.getMessage
        }
      }
  }

  def delete(id: Long): Future[Int] = {
    dbConfig.db.run(seedsList.filter(_.id === id).delete)
  }

  def update(seedItem: Seeds): Future[Int] = {
    dbConfig.db
      .run(seedsList.filter(_.id === seedItem.id)
        .map(x => (x.name, x.quantity, x.weight, x.price, x.description))
        .update(seedItem.name, seedItem.quantity, seedItem.weight, seedItem.price, seedItem.description)
      )
  }

  def get(id: Long): Future[Option[Seeds]] = {
    dbConfig.db.run(seedsList.filter(_.id === id).result.headOption)
  }

  def listAll: Future[Seq[Seeds]] = {
    dbConfig.db.run(seedsList.result)
  }
}
