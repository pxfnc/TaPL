// implementation from TaPL p.34

sealed trait Syntax

sealed trait Term extends Syntax

sealed trait Value extends Syntax

final case class IfElse(cond: Term, thenBlock: Term, elseBlock: Term) extends Term

case object True extends Term with Value

case object False extends Term with Value

object Evaluator {
  /**
    * evaluate Term. like small step evaluation. if cannnot evaluating term, return None
    *
    * @return Option[(String, Term)] first item indicates which evaluation rule was used second item indicates evaluated value.
    */
  def evalOneStep: Term => Option[(String, Term)] = {
    case IfElse(True, t2, t3) => Some("E-IFTRUE", t2)
    case IfElse(False, t2, t3) => Some("E-IFFALSE", t3)
    case IfElse(t1, t2, t3) => evalOneStep(t1).map(_t1 => ("E-IF", IfElse(_t1._2, t2, t3)))
    case _ => None
  }
}
