package models

import play.api.data.format.Formats._
import com.google.inject.Inject
import play.api.data.Form
import play.api.data.Forms.mapping
import play.api.data.Forms._
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.jdbc.JdbcProfile
import scala.concurrent.{ExecutionContext, Future}
import slick.jdbc.MySQLProfile.api._

case class Fruits(id: Long, name: String, quanity: Int, weight: String, price: Double)


case class FruitsFormData(name: String, quanity: Int, weight: String, price: Double)

object FruitsForm {
  val form = Form(
    mapping(
      "name" -> nonEmptyText,
      "quanity" -> number,
      "weight" -> nonEmptyText,
      "price" -> of(doubleFormat)
    )(FruitsFormData.apply)(FruitsFormData.unapply)
  )
}

class FruitsTableDef(tag: Tag) extends Table[Fruits](tag, "fruits") {

  def id = column[Long]("id", O.PrimaryKey, O.AutoInc)
  def name = column[String]("name")
  def quanity = column[Int]("quanity")
  def weight = column[String]("weight")
  def price = column[Double]("price")

  override def * = (id, name, quanity, weight, price) <> (Fruits.tupled, Fruits.unapply)
}


class FruitsList @Inject()(
                          protected val dbConfigProvider: DatabaseConfigProvider
                        )(implicit executionContext: ExecutionContext)
  extends HasDatabaseConfigProvider[JdbcProfile] {

  var fruitsList = TableQuery[FruitsTableDef]

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
    dbConfig.db.run(fruitsList.filter(_.id === id).delete)
  }

  def update(fruitsItem: Fruits): Future[Int] = {
    dbConfig.db
      .run(fruitsList.filter(_.id === fruitsItem.id)
        .map(x => (x.name, x.quanity, x.weight, x.price))
        .update(fruitsItem.name, fruitsItem.quanity, fruitsItem.weight, fruitsItem.price)
      )
  }

  def get(id: Long): Future[Option[Fruits]] = {
    dbConfig.db.run(fruitsList.filter(_.id === id).result.headOption)
  }

  def listAll: Future[Seq[Fruits]] = {
    dbConfig.db.run(fruitsList.result)
  }
}
