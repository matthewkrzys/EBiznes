package models.repos

import com.google.inject.Inject
import models.definitions.HoneyTableDef
import models.entities.Honey
import play.api.db.slick.DatabaseConfigProvider
import slick.jdbc.JdbcProfile
import slick.jdbc.MySQLProfile.api._

import scala.concurrent.{ExecutionContext, Future}

class HoneyRepo @Inject()(protected val dbConfigProvider: DatabaseConfigProvider)(implicit executionContext: ExecutionContext) {

  var honeysList = TableQuery[HoneyTableDef]
  private val dbConfig = dbConfigProvider.get[JdbcProfile]

  def add(honeysItem: Honey): Future[String] = {
    dbConfig.db
      .run(honeysList += honeysItem)
      .map(res => "HoneysItem successfully added")
      .recover {
        case ex: Exception => {
          printf(ex.getMessage())
          ex.getMessage
        }
      }
  }

  def delete(id: Long): Future[Int] = {
    val num = id.toString
    val q = sql"delete from Honeys where id=$num".as[String]
    val affectedRowsCount: Future[Vector[String]] = dbConfig.db.run(q.transactionally)
    return affectedRowsCount.map(s => s.length)
  }

  def update(honeysItem: Honey): Future[Int] = {
    dbConfig.db
      .run(honeysList.filter(_.id === honeysItem.id)
        .map(x => (x.name, x.quantity, x.weight, x.price))
        .update(honeysItem.name, honeysItem.quantity, honeysItem.weight, honeysItem.price)
      )
  }

  def get(id: Long): Future[Option[Honey]] = {
    dbConfig.db.run(honeysList.filter(_.id === id).result.headOption)
  }

  def listAll: Future[Seq[Honey]] = {
    dbConfig.db.run(honeysList.result)
  }
}
