package us.martink.solver;

import us.martink.solver.model.Atom;
import us.martink.solver.model.Input;
import us.martink.solver.model.Output;
import us.martink.solver.model.Rule;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

/**
 * Created by tadas on 2016-11-13.
 */
public class FCSolver {

    public static Output solve (Input input){
        Output resOutput = new Output();
        StringBuilder result = resOutput.getResult();

        if(input.getFacts().contains(input.getGoal())){
            resOutput.setPreSolved(true);
            return resOutput;
        }

        List<Atom> facts = new ArrayList<>(input.getFacts());
        boolean noMoreFactsCanBeApplied = false;
        int iterationCount = 0;

        while (!facts.contains(input.getGoal()) && !noMoreFactsCanBeApplied){
            result.append(++iterationCount);
            result.append(" iteracija\n");
            int ruleIndex = 0;
            noMoreFactsCanBeApplied = true;
            for (Rule rule : input.getRules()) {
                appendRuleInfo(rule, ++ruleIndex, result);
                if(rule.isUsed()){
                    result.append("netaikoma, nes taikyta anksčiau (flag1)\n");
                } else if (rule.isRedundant()) {
                    result.append("netaikoma, nes konsekventas jau yra faktų aibėje (flag2)\n");
                } else if (facts.contains(rule.getRightHandSide())) {
                    rule.setRedundant(true);
                    result.append("netaikoma ir pažymima flag2, nes konsekventas jau yra faktų aibėje\n");
                } else if (facts.containsAll(rule.getLeftHandSide())) {
                    rule.setUsed(true);
                    noMoreFactsCanBeApplied = false;
                    facts.add(rule.getRightHandSide());
                    resOutput.addResultPath(rule.getName());
                    result.append("taikoma ir pažymima flag1\n");
                    //break??
                } else {
                    result.append("netaikoma, nes trūksta ");
                    result.append(appendMissingPremisesInfo(rule, facts));
                    result.append("\n");
                }
            }
        }

        if(facts.contains(input.getGoal())){
            resOutput.setSolved(true);
        }

        return resOutput;
    }

    private static String appendMissingPremisesInfo(Rule rule, List<Atom> facts) {
        StringJoiner info = new StringJoiner(", ");
        for (Atom premise : rule.getLeftHandSide()) {
            if (!facts.contains(premise)) {
                info.add(premise.getName());
            }
        }
        return info.toString();
    }

    private static void appendRuleInfo(Rule rule, int step, StringBuilder result) {
        result.append("  ");
        result.append(step);
        result.append(". ");
        result.append(rule.toString());
        result.append(' ');
    }
}
