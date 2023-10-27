package com.bank.mooc.shared.infrastructure.doobie

import com.bank.mooc.transaction.domain.TransactionDate
import doobie.Meta

import java.time.LocalDateTime
import java.util.UUID

object TypesConversions {
  implicit val uuidMeta         : Meta[ UUID ]            = Meta[ String ].imap( UUID.fromString )( _.toString )
  implicit val localDateTimeMeta: Meta[ TransactionDate ]    = Meta[ String ]
    .imap( d => TransactionDate( LocalDateTime.parse( d ) ) )( _.toString )
}
