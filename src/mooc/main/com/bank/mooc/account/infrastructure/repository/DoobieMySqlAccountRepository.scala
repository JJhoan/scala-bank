package com.bank.mooc.account.infrastructure.repository

import cats.effect.unsafe.implicits.global
import com.bank.mooc.account.domain.{ Account, AccountId, AccountRepository }
import com.bank.shared.infrastructure.doobie.DoobieDbConnection
import doobie.implicits._

import scala.concurrent.{ ExecutionContext, Future }

final class DoobieMySqlAccountRepository( implicit db: DoobieDbConnection, ec: ExecutionContext )
  extends AccountRepository {
  
  override def all( ): Future[ Seq[ Account ] ] = {
    sql"SELECT * FROM accounts".query[ Account ].to[ Seq ].transact( db.transactor).unsafeToFuture()
  }
  
  override def save( account: Account ): Future[ Unit ] = {
    sql"INSERT INTO accounts(id, number, amount) VALUES (${ account.id }, ${ account.number }, ${ account.amount })"
      .update.run
      .transact( db.transactor )
      .unsafeToFuture( )
      .map( _ => () )
  }
  
  override def update( account: Account ): Future[ Unit ] = ???
  
  override def search( accountId: AccountId ): Future[ Option[ Account ] ] = ???
}
