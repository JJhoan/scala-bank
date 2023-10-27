package com.bank.shared.domain

import scala.util.Random

object StringMother {
  private val minimumChars = 1
  private val maximumChars = 10
  
  def random( numChars: Int ): String = Random.alphanumeric take numChars mkString ""
  
  def random: String = random( IntMother.randomBetween( minimumChars, maximumChars ) )
}
