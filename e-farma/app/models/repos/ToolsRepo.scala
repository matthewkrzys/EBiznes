package models.repos

import com.google.inject.Inject
import models.definitions.ToolsTableDef
import models.entities.Tools
import play.api.db.slick.DatabaseConfigProvider
import slick.jdbc.JdbcProfile
import slick.jdbc.MySQLProfile.api._

import scala.concurrent.{ExecutionContext, Future}

class ToolsRepo @Inject()(protected val dbConfigProvider: DatabaseConfigProvider )(implicit executionContext: ExecutionContext) {

  var toolsList = TableQuery[ToolsTableDef]
  private val dbConfig = dbConfigProvider.get[JdbcProfile]

  def add(toolItem: Tools): Future[String] = {
    dbConfig.db
      .run(toolsList += toolItem)
      .map(res => "ToolItem successfully added")
      .recover {
        case ex: Exception => {
          printf(ex.getMessage())
          ex.getMessage
        }
      }
  }

  def delete(id: Long): Future[Int] = {
//    dbConfig.db.run(toolsList.filter(_.id === id).delete)
    val num = id.toString
    val q = sql"delete from Tools where id=$num".as[String]
    val affectedRowsCount: Future[Vector[String]] = dbConfig.db.run(q.transactionally)
    return affectedRowsCount.map(s=>s.length)
  }

  def update(toolItem: Tools): Future[Int] = {
    dbConfig.db
      .run(toolsList.filter(_.id === toolItem.id)
        .map(x => (x.name, x.quantity, x.price, x.description))
        .update(toolItem.name, toolItem.quantity, toolItem.price, toolItem.description)
      )
  }

  def get(id: Long): Future[Option[Tools]] = {
    dbConfig.db.run(toolsList.filter(_.id === id).result.headOption)
  }

  def listAll: Future[Seq[Tools]] = {
    dbConfig.db.run(toolsList.result)
  }
}
