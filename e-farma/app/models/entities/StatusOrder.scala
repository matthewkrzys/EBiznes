package models.entities

case class StatusOrder(userId: Int, productId: Int, productName: String, tableName: String, quantity: Int,
                       name: String, surname: String, email:String, telephone: String,
                       city: String, street: String, buildingNumber: String, apartmentNumber: String)

