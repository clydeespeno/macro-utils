package jce.macroutils.sealedtrait.impl

import jce.macroutils.sealedtrait.impl.Helper.ensureSealed

import scala.reflect.macros.blackbox

private[sealedtrait] object Macros {
  def valuesImpl[T: c.WeakTypeTag](c: blackbox.Context): c.Expr[Set[T]] = ValuesMacro(c)
  def enclosedValues[T: c.WeakTypeTag](c: blackbox.Context): c.Expr[Set[T]] = EnclosedMacro(c)
}

private object Helper {
  def ensureSealed(c: blackbox.Context)(typeSymbol: c.universe.Symbol): Unit =
    if (!typeSymbol.asClass.isSealed) c.abort(c.enclosingPosition, "can only get values of a sealed trait or class")
}

private object ValuesMacro {

  def apply[T: c.WeakTypeTag](c: blackbox.Context): c.Expr[Set[T]] = {
    import c.universe._

    val s = weakTypeOf[T].typeSymbol
    ensureSealed(c)(s)
    val subClasses: List[c.universe.Symbol] = s.asClass.knownDirectSubclasses.toList
    val modules = subClasses.filter(_.isModuleClass).map(x => Ident(x.asClass.module))
    c.Expr[Set[T]](q"Set[$s]($modules: _*)")
  }
}

private object EnclosedMacro {
  def apply[T: c.WeakTypeTag](c: blackbox.Context): c.Expr[Set[T]] = {
    import c.universe._

    val s = weakTypeOf[T].typeSymbol
    ensureSealed(c)(s)
    val subSymbols: List[c.universe.Symbol] = c.internal.enclosingOwner.owner.typeSignature.decls.toList
    val modules = subSymbols.filter(_.isModule).map(Ident(_))
    c.Expr[Set[T]](q"Set[$s]($modules: _*)")
  }
}
