package models.repos

import com.google.inject.Inject
import models.definitions.FlowersTableDef
import models.entities.Flowers
import play.api.db.slick.DatabaseConfigProvider
import slick.jdbc.JdbcProfile
import slick.jdbc.MySQLProfile.api._

import scala.concurrent.{ExecutionContext, Future}

class FlowersRepo @Inject()(protected val dbConfigProvider: DatabaseConfigProvider )(implicit executionContext: ExecutionContext) {

  var flowersList = TableQuery[FlowersTableDef]
  private val dbConfig = dbConfigProvider.get[JdbcProfile]

  def add(flowerItem: Flowers): Future[String] = {
    dbConfig.db
      .run(flowersList += flowerItem)
      .map(res => "FlowerItem successfully added")
      .recover {
        case ex: Exception => {
          printf(ex.getMessage())
          ex.getMessage
        }
      }
  }

  def delete(id: Long): Future[Int] = {
    dbConfig.db.run(flowersList.filter(_.id === id).delete)
  }

  def update(flowerItem: Flowers): Future[Int] = {
    dbConfig.db
      .run(flowersList.filter(_.id === flowerItem.id)
        .map(x => (x.name, x.quantity, x.species, x.price, x.description))
        .update(flowerItem.name, flowerItem.quantity, flowerItem.species, flowerItem.price, flowerItem.description)
      )
  }

  def get(id: Long): Future[Option[Flowers]] = {
    dbConfig.db.run(flowersList.filter(_.id === id).result.headOption)
  }

  def listAll: Future[Seq[Flowers]] = {
    dbConfig.db.run(flowersList.result)
  }
}
