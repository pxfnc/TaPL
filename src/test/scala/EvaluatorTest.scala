import org.scalatest.{Matchers, WordSpec}

class EvaluatorTest extends WordSpec with Matchers {

  "evalOneStep" when {
    "apply to True" should {
      "be evaluating to None" in assert(Evaluator.evalOneStep(True).isEmpty)
    }
    "apply to False" should {
      "be evaluating None" in assert(Evaluator.evalOneStep(False).isEmpty)
    }
    "apply to IfElse(True, t1, t2)" should {
      "be evaluating to Some(\"E-IFTRUE\", t1)" in {
        Evaluator.evalOneStep(IfElse(True, True, True)) shouldEqual Some("E-IFTRUE", True)
        Evaluator.evalOneStep(IfElse(True, True, False)) shouldEqual Some("E-IFTRUE", True)
        Evaluator.evalOneStep(IfElse(True, False, True)) shouldEqual Some("E-IFTRUE", False)
        Evaluator.evalOneStep(IfElse(True, False, False)) shouldEqual Some("E-IFTRUE", False)
      }
    }
    "apply to IfElse(False, t1, t2)" should {
      "be evaluating to Some(\"E-IFFALSE\", t2)" in {
        Evaluator.evalOneStep(IfElse(False, True, True)) shouldEqual Some("E-IFFALSE", True)
        Evaluator.evalOneStep(IfElse(False, True, False)) shouldEqual Some("E-IFFALSE", False)
        Evaluator.evalOneStep(IfElse(False, False, True)) shouldEqual Some("E-IFFALSE", True)
        Evaluator.evalOneStep(IfElse(False, False, False)) shouldEqual Some("E-IFFALSE", False)
      }
    }
    "apply to IfElse(t1, t2, t3)" when {
      "evaluateOneStep(t1) returns (somerule, t1')" should {
        "IfElse(t1', t2, t3)" in {
          val case1 = IfElse(True, True, True)
          val case2 = IfElse(False, False, False)
          Evaluator.evalOneStep(IfElse(case1, True, False)) shouldEqual Some("E-IF", IfElse(True, True, False))
          Evaluator.evalOneStep(IfElse(case2, True, False)) shouldEqual Some("E-IF", IfElse(False, True, False))
        }
      }
    }
  }
}
