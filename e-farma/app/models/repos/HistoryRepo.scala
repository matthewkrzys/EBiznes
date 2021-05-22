package models.repos

import com.google.inject.Inject
import models.definitions.HistoryTableDef
import models.entities.{History, Users}
import play.api.db.slick.DatabaseConfigProvider
import slick.jdbc.JdbcProfile
import slick.jdbc.MySQLProfile.api._

import scala.concurrent.{ExecutionContext, Future}

class HistoryRepo @Inject()(protected val dbConfigProvider: DatabaseConfigProvider )(implicit executionContext: ExecutionContext) {

  var historyList = TableQuery[HistoryTableDef]
  private val dbConfig = dbConfigProvider.get[JdbcProfile]

  def add(historyItem: History): Future[String] = {
    dbConfig.db
      .run(historyList += historyItem)
      .map(res => "HistoryItem successfully added")
      .recover {
        case ex: Exception => {
          printf(ex.getMessage())
          ex.getMessage
        }
      }
  }

  def delete(id: Long): Future[Int] = {
    val num = id.toString
    val q = sql"delete from History where id=$num".as[String]
    val affectedRowsCount: Future[Vector[String]] = dbConfig.db.run(q.transactionally)
    return affectedRowsCount.map(s=>s.length)
  }

  def update(historyItem: History): Future[Int] = {
    dbConfig.db
      .run(historyList.filter(_.id === historyItem.id)
        .map(x => (x.product, x.description, x.users))
        .update(historyItem.product, historyItem.description, historyItem.users)
      )
  }

  def get(id: Long): Future[Option[History]] = {
    dbConfig.db.run(historyList.filter(_.id === id).result.headOption)
  }

  def listAll: Future[Seq[History]] = {
    dbConfig.db.run(historyList.result)
  }

  def listUserItems(users: Long): Future[Seq[History]] = {
    dbConfig.db.run(historyList.filter(_.users === users).result)
  }

  def deleteUserHistoryItems(userId: Long): Future[Int] = {
    val num = userId.toString
    val q = sql"delete from History where users=$num".as[String]
    val affectedRowsCount: Future[Vector[String]] = dbConfig.db.run(q.transactionally)
    return affectedRowsCount.map(s=>s.length)
  }
}
