package com.bank.mooc.account.infrastructure

import com.bank.mooc.account.domain.AccountRepository
import com.bank.mooc.account.infrastructure.dependency_injection.AccountModuleDependencyContainer
import com.bank.shared.infrastructure.integration.IntegrationTestCase

protected[account] trait AccountIntegrationTestCase extends IntegrationTestCase {
  private val container = new AccountModuleDependencyContainer
  
  protected val repository: AccountRepository = container.repository
}
