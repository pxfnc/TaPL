package Language.B

// implementation from TaPL p.34

trait Syntax

trait Term extends Syntax

trait Value extends Syntax

case class IfElse(cond: Term, thenBlock: Term, elseBlock: Term) extends Term

case object True extends Term with Value

case object False extends Term with Value

