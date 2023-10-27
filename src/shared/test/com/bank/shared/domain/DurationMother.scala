package com.bank.shared.domain

import scala.concurrent.duration._

object DurationMother {
  def random: Duration = IntMother.randomUnsigned().seconds
}
