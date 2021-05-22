package models.repos

import com.google.inject.Inject
import models.definitions.VegetablesTableDef
import models.entities.Vegetables
import play.api.db.slick.DatabaseConfigProvider
import slick.jdbc.JdbcProfile
import slick.jdbc.MySQLProfile.api._

import scala.concurrent.{ExecutionContext, Future}

class VegetablesRepo @Inject()( protected val dbConfigProvider: DatabaseConfigProvider )(implicit executionContext: ExecutionContext){

  var vegetablesList = TableQuery[VegetablesTableDef]
  private val dbConfig = dbConfigProvider.get[JdbcProfile]

  def add(vegetablesItem: Vegetables): Future[String] = {
    dbConfig.db
      .run(vegetablesList += vegetablesItem)
      .map(res => "VegetableItem successfully added")
      .recover {
        case ex: Exception => {
          printf(ex.getMessage())
          ex.getMessage
        }
      }
  }

  def delete(id: Long): Future[Int] = {
//    dbConfig.db.run(vegetablesList.filter(_.id === id).delete)
    val num = id.toString
    val q = sql"delete from Vegetables where id=$num".as[String]
    val affectedRowsCount: Future[Vector[String]] = dbConfig.db.run(q.transactionally)
    return affectedRowsCount.map(s=>s.length)
  }

  def update(vegetablesItem: Vegetables): Future[Int] = {
    dbConfig.db
      .run(vegetablesList.filter(_.id === vegetablesItem.id)
        .map(x => (x.name, x.quantity, x.weight, x.price))
        .update(vegetablesItem.name, vegetablesItem.quantity, vegetablesItem.weight, vegetablesItem.price)
      )
  }

  def get(id: Long): Future[Option[Vegetables]] = {
    dbConfig.db.run(vegetablesList.filter(_.id === id).result.headOption)
  }

  def listAll: Future[Seq[Vegetables]] = {
    dbConfig.db.run(vegetablesList.result)
  }
}
