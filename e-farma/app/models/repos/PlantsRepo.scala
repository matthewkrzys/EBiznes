package models.repos

import com.google.inject.Inject
import models.definitions.PlantsTableDef
import models.entities.Plants
import play.api.db.slick.DatabaseConfigProvider
import slick.jdbc.JdbcProfile
import slick.jdbc.MySQLProfile.api._

import scala.concurrent.{ExecutionContext, Future}

class PlantsRepo @Inject()(protected val dbConfigProvider: DatabaseConfigProvider)(implicit executionContext: ExecutionContext) {

  var plantsList = TableQuery[PlantsTableDef]
  private val dbConfig = dbConfigProvider.get[JdbcProfile]

  def add(plantItem: Plants): Future[String] = {
    dbConfig.db
      .run(plantsList += plantItem)
      .map(res => "PlantsItem successfully added")
      .recover {
        case ex: Exception => {
          printf(ex.getMessage())
          ex.getMessage
        }
      }
  }

  def delete(id: Long): Future[Int] = {
    //    dbConfig.db.run(plantsList.filter(_.id === id).delete)
    val num = id.toString
    val q = sql"delete from Plants where id=$num".as[String]
    val affectedRowsCount: Future[Vector[String]] = dbConfig.db.run(q.transactionally)
    return affectedRowsCount.map(s => s.length)
  }

  def update(plantItem: Plants): Future[Int] = {
    dbConfig.db
      .run(plantsList.filter(_.id === plantItem.id)
        .map(x => (x.name, x.quantity, x.species, x.price, x.description))
        .update(plantItem.name, plantItem.quantity, plantItem.species, plantItem.price, plantItem.description)
      )
  }

  def get(id: Long): Future[Option[Plants]] = {
    dbConfig.db.run(plantsList.filter(_.id === id).result.headOption)
  }

  def listAll: Future[Seq[Plants]] = {
    dbConfig.db.run(plantsList.result)
  }
}
