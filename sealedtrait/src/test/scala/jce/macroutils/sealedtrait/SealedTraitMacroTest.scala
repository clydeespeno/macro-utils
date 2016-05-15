package jce.macroutils.sealedtrait

import jce.macroutils.sealedtrait.fixtures.Foo
import org.scalatest.{FlatSpec, ShouldMatchers}


class SealedTraitMacroTest extends FlatSpec with ShouldMatchers {

  "sealedtrait values macro" should "get all sealed trait values" in {
    v should have size 2
  }

  def v = values[Foo]

}
