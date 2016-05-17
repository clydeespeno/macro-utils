package jce.macroutils.sealedtrait

import jce.macroutils.sealedtrait.fixtures.{EnclosedFoo, Foo}
import org.scalatest.{FlatSpec, ShouldMatchers}


class SealedTraitMacroTest extends FlatSpec with ShouldMatchers {
  "sealedtrait values macro" should "get all sealed trait values" in {
    Foo.values should have size 2
  }

  it should "get all enclosed values of a sealed trait" in {
    EnclosedFoo.values should have size 3
  }
}
