package com.mybank.account.application

import scala.concurrent.Future

trait Rollback {
  
  def rollback( ): Future[ Unit ]
  
}
