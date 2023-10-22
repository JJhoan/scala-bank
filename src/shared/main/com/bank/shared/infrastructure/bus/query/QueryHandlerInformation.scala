package com.bank.shared.infrastructure.bus.query

import com.bank.shared.domain.bus.query.{ Query, QueryHandler, Response }
import org.reflections.Reflections

import java.lang.reflect.ParameterizedType
import scala.jdk.CollectionConverters.CollectionHasAsScala

final class QueryHandlerInformation {

  private val indexedQueryHandlers: Map[Class[_ <: Query], Class[_ <: QueryHandler[_ <: Query, _ <: Response]]] = {
    val reflections = new Reflections("com.bank.mooc")
    val classes: Set[Class[_ <: QueryHandler[Query, Response]]] = reflections.getSubTypesOf(classOf[QueryHandler[Query, Response]]).asScala.toSet

    classes.map(handler => {
      val paramType: ParameterizedType = handler.getGenericInterfaces.toList.head.asInstanceOf[ParameterizedType]
      val queryClass = paramType.getActualTypeArguments.toList.head.asInstanceOf[Class[Query]]
      queryClass -> handler
    }).toMap

  }

  def search(queryClass: Class[_ <: Query]): Class[_ <: QueryHandler[_ <: Query, _ <: Response]] = {
    val queryHandlerClass = indexedQueryHandlers.get(queryClass)
    if (queryHandlerClass.isEmpty) throw new NullPointerException()
    queryHandlerClass.get
  }

}


