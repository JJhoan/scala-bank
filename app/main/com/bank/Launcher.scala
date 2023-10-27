package com.bank

import com.bank.backoffice.api.BackofficeApiApp
import com.bank.backoffice.consumer.BackofficeConsumerApp
import com.bank.mooc.api.MoocApiApp
import com.bank.mooc.consumer.MoocConsumerApp

object Launcher {
  private val MoocApiArgument       = "mooc-api"
  private val MoocConsumersArgument = "mooc-consumers"

  private val BackofficeApiArgument       = "backoffice-api"
  private val BackofficeConsumersArgument = "backoffice-consumers"

  def main(args: Array[String]): Unit = {
    if (args.length != 1) {
      throw new RuntimeException("You need to specify with app to start")
    }

    args(0) match {
      case MoocApiArgument       => MoocApiApp.start()
      case MoocConsumersArgument => MoocConsumerApp.start()

      case BackofficeApiArgument       => BackofficeApiApp.start()
      case BackofficeConsumersArgument => BackofficeConsumerApp.start()

      case _ => println("Application doesn't exist!")
    }
  }
}
