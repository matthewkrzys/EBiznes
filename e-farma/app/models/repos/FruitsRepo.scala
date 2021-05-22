package models.repos

import com.google.inject.Inject
import models.definitions.FruitsTableDef
import models.entities.Fruits
import play.api.db.slick.DatabaseConfigProvider
import slick.jdbc.JdbcProfile
import slick.jdbc.MySQLProfile.api._
import scala.concurrent.{ExecutionContext, Future}

class FruitsRepo @Inject()( protected val dbConfigProvider: DatabaseConfigProvider )(implicit executionContext: ExecutionContext){

  var fruitsList = TableQuery[FruitsTableDef]
  private val dbConfig = dbConfigProvider.get[JdbcProfile]

  def add(fruitsItem: Fruits): Future[String] = {
    dbConfig.db
      .run(fruitsList += fruitsItem)
      .map(res => "FruitsItem successfully added")
      .recover {
        case ex: Exception => {
          printf(ex.getMessage())
          ex.getMessage
        }
      }
  }

  def delete(id: Long): Future[Int] = {
    val num = id.toString
    val q = sql"delete from Fruits where id=$num".as[String]
    val affectedRowsCount: Future[Vector[String]] = dbConfig.db.run(q.transactionally)
    return affectedRowsCount.map(s=>s.length)
  }

  def update(fruitsItem: Fruits): Future[Int] = {
    dbConfig.db
      .run(fruitsList.filter(_.id === fruitsItem.id)
        .map(x => (x.name, x.quantity, x.weight, x.price))
        .update(fruitsItem.name, fruitsItem.quantity, fruitsItem.weight, fruitsItem.price)
      )
  }

  def get(id: Long): Future[Option[Fruits]] = {
    dbConfig.db.run(fruitsList.filter(_.id === id).result.headOption)
  }

  def listAll: Future[Seq[Fruits]] = {
    dbConfig.db.run(fruitsList.result)
  }
}
