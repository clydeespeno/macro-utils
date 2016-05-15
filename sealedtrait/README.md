## Sealed Trait Macros
Contains macro helpers useful for sealed traits

## Sealed Trait Values
Get instance values (objects) of a sealed trait

```scala
import jce.macroutils.sealedtrait

sealed trait Foo

object Bar extends Foo
object Baz extends Foo

object Foo {
  val values: Set[Foo] = sealedtrait.values[Foo]
}
```
