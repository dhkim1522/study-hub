package com.dhkim.concurrencyexample.infrastructure.aop

import org.springframework.expression.ExpressionParser
import org.springframework.expression.spel.standard.SpelExpressionParser
import org.springframework.expression.spel.support.StandardEvaluationContext

object CustomSpringELParser {
    fun getDynamicValue(parameterNames: Array<String>, args: Array<Any>, key: String): Any? {
        val parser: ExpressionParser = SpelExpressionParser()
        val context = StandardEvaluationContext()

        parameterNames.forEachIndexed { index, paramName ->
            context.setVariable(paramName, args[index])
        }

        return parser.parseExpression(key).getValue(context, Any::class.java)
    }
}