package com.bank.shared.domain

import slick.dbio.DBIO

object Types {
  
  type TXN[+R] = DBIO[R]
  
}
