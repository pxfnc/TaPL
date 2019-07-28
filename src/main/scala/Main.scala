import Language.B.{Evaluator, False, IfElse, True}

object Main extends App {
  println(s"True -> ${Evaluator.evalOneStep(True)}")
  println(s"IfElse(True, True, False) -> ${Evaluator.evalOneStep(IfElse(True, True, True))}")
  println(s"IfElse(IfElse(False, False, False), True, True) -> ${Evaluator.evalOneStep(IfElse(IfElse(False, False, False), True, True))}")
}


