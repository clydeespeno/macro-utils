package jce.macroutils.sealedtrait

package object fixtures {

  sealed trait Foo

  object Bar extends Foo
  object Baz extends Foo

}
