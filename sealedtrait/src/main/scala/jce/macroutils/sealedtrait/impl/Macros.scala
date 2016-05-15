package jce.macroutils.sealedtrait.impl

import scala.reflect.macros.blackbox


private[sealedtrait] object Macros {

  def valuesImpl[T: c.WeakTypeTag](c: blackbox.Context): c.Expr[Set[T]] = {
    import c.universe._
    weakTypeOf[T].typeSymbol match {
      case s: Symbol if s.isClass && s.asClass.isSealed =>
        buildValues(c)(getValues(c)(s.asClass))
      case _ => c.abort(c.enclosingPosition, "can only get values of a sealed trait or class")
    }
  }

  private def getValues(c: blackbox.Context)(s: c.universe.ClassSymbol): List[c.universe.Symbol] =
    s.knownDirectSubclasses.toList.filter(_.isModuleClass)

  private def buildValues[T: c.WeakTypeTag](c: blackbox.Context)(modules: List[c.universe.Symbol]): c.Expr[Set[T]] = {
    import c.universe._
    c.Expr[Set[T]](Apply(
      Select(
        reify(Set).tree,
        TermName("apply")
      ),
      modules.map(child => Ident(child.asClass.module))
    ))
  }
}
