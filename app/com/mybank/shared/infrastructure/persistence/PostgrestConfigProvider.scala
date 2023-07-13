package com.mybank.shared.infrastructure.persistence

import slick.jdbc.PostgresProfile.api._

trait PostgrestConfigProvider {
  
  val db = Database.forConfig( "mydb" )
  //def get[ P <: BasicProfile ]: DatabaseConfig[ P ]
  
}




