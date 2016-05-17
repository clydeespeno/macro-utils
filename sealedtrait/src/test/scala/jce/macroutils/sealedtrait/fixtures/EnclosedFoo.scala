package jce.macroutils.sealedtrait.fixtures

import jce.macroutils.sealedtrait

sealed trait EnclosedFoo

object EnclosedFoo {
  object Bar extends EnclosedFoo
  object Baz extends EnclosedFoo
  object Qux extends EnclosedFoo

  val values = sealedtrait.enclosedValues[EnclosedFoo]
}

object Quux extends EnclosedFoo
