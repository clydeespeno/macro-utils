package jce.macroutils

import jce.macroutils.sealedtrait.impl.Macros

package object sealedtrait {
  def values[T]: Set[T] = macro Macros.valuesImpl[T]
  def enclosedValues[T]: Set[T] = macro Macros.enclosedValues[T]
}
