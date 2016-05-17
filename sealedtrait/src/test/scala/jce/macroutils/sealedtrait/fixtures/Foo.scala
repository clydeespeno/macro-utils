package jce.macroutils.sealedtrait.fixtures

import jce.macroutils.sealedtrait

sealed trait Foo

object Bar extends Foo
object Baz extends Foo

object Foo {
  val values: Set[Foo] = sealedtrait.values[Foo]
}
