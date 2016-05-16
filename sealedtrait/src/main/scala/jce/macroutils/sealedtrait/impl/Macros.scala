package jce.macroutils.sealedtrait.impl

import scala.reflect.macros.blackbox


private[sealedtrait] object Macros {
  def valuesImpl[T: c.WeakTypeTag](c: blackbox.Context): c.Expr[Set[T]] = ValuesMacro(c)
}

private object ValuesMacro {

  def apply[T: c.WeakTypeTag](c: blackbox.Context): c.Expr[Set[T]] = {
    import c.universe._

    weakTypeOf[T].typeSymbol match {
      case s: Symbol if s.asClass.isSealed =>
        val modules = s.asClass.knownDirectSubclasses.toList.filter(_.isModuleClass).map(c => Ident(c.asClass.module))
        c.Expr[Set[T]](q"Set[$s]($modules: _*)")
      case _ => c.abort(c.enclosingPosition, "can only get values of a sealed trait or class")
    }
  }
}
