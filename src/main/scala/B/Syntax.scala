package B

// implementation from TaPL p.34

sealed trait Syntax

sealed trait Term extends Syntax

sealed trait Value extends Syntax

final case class IfElse(cond: Term, thenBlock: Term, elseBlock: Term) extends Term

case object True extends Term with Value

case object False extends Term with Value

