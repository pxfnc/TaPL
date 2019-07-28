package Language.B

import org.scalatest.{Matchers, WordSpec}

import scala.util.chaining._

class EvaluatorTest extends WordSpec with Matchers {

  "EvaluatorTest" should {

    "evalOneStep" when {

      "apply to True" should {
        "be evaluating to None" in assert(Evaluator.evalOneStep(True).isEmpty)
      }
      "apply to False" should {
        "be evaluating None" in assert(Evaluator.evalOneStep(False).isEmpty)
      }
      "apply to IfElse(True, t1, t2)" should {
        "be evaluating to Some(\"E-IFTRUE\", t1)" in {
          IfElse(True, True, True) pipe Evaluator.evalOneStep shouldEqual Some("E-IFTRUE", True)
          IfElse(True, True, False) pipe Evaluator.evalOneStep shouldEqual Some("E-IFTRUE", True)
          IfElse(True, False, True) pipe Evaluator.evalOneStep shouldEqual Some("E-IFTRUE", False)
          IfElse(True, False, False) pipe Evaluator.evalOneStep shouldEqual Some("E-IFTRUE", False)
        }
      }
      "apply to IfElse(False, t1, t2)" should {
        "be evaluating to Some(\"E-IFFALSE\", t2)" in {
          IfElse(False, True, True) pipe Evaluator.evalOneStep shouldEqual Some("E-IFFALSE", True)
          IfElse(False, True, False) pipe Evaluator.evalOneStep shouldEqual Some("E-IFFALSE", False)
          IfElse(False, False, True) pipe Evaluator.evalOneStep shouldEqual Some("E-IFFALSE", True)
          IfElse(False, False, False) pipe Evaluator.evalOneStep shouldEqual Some("E-IFFALSE", False)
        }
      }
      "apply to IfElse(t1, t2, t3)" when {
        "evaluateOneStep(t1) returns (somerule, t1')" should {
          "IfElse(t1', t2, t3)" in {
            val case1 = IfElse(True, True, True)
            val case2 = IfElse(False, False, False)
            IfElse(case1, True, False) pipe Evaluator.evalOneStep shouldEqual Some("E-IF", IfElse(True, True, False))
            IfElse(case2, True, False) pipe Evaluator.evalOneStep shouldEqual Some("E-IF", IfElse(False, True, False))
          }
        }
      }
    }
  }
}
