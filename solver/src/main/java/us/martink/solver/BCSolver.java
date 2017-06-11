package us.martink.solver;

import us.martink.solver.model.Atom;
import us.martink.solver.model.Input;
import us.martink.solver.model.Output;
import us.martink.solver.model.Rule;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by tadas.
 */
public class BCSolver {


    public static Output solve(Input input) throws Unsolvable {

        BCRef result = new BCRef(input);
        if(solveRecursivly(input.getGoal(), result)){
            result.output.setSolved(true);
        }

        return result.output;
    }

    private static boolean solveRecursivly(Atom goal, BCRef result) throws Unsolvable {
        StringBuilder builder = result.output.getResult();
        builder.append(++result.step);
        appendGoalInfo(goal, builder);
        if(result.facts.contains(goal)){
            builder.append("Faktas - duotas.\n");
            return true;
        }
        if(result.derivedFacts.contains(goal)) {
            builder.append("Faktas - gautas.\n");
            return true;
        }

        List<Rule> potentialRules = result.rules.stream()
                .filter(r -> !r.isUsed() && r.getRightHandSide().equals(goal))
                .collect(Collectors.toList());

        if (potentialRules.isEmpty()) {
            throw new Unsolvable("Nėra taisyklių šiam tikslui pasiekti.\n");
        }

        for (Rule potentialRule : potentialRules) {
            potentialRule.setUsed(true);
            boolean allPremisesReachable = true;
            builder.append(appendNewRuleInfo(potentialRule));
            for (Atom atom : potentialRule.getLeftHandSide()) {
                if(!solveRecursivly(atom, result)){
                    allPremisesReachable = false;
                    break;
                }
            }
            if(allPremisesReachable){
                result.derivedFacts.add(goal);
                result.output.addResultPath(potentialRule.getName());
                builder.append(++result.step);
                appendGoalInfo(goal, builder);
                builder.append("Faktas - naujai gautas.\n");
                return true;
            }

        }

        return false;
    }

    private static void appendGoalInfo(Atom goal, StringBuilder info) {
        info.append(" Tikslas: ");
        info.append(goal.getName());
        info.append(". ");
    }

    private static String appendNewRuleInfo(Rule rule) {
        StringBuilder sb = new StringBuilder();
        sb.append("Randama ");
        sb.append(rule.toString());
        sb.append(". Nauji tikslai: ");
        sb.append(rule.leftHandSideToString());
        sb.append(".\n");
        return sb.toString();
    }

    private static class BCRef {
        private Output output;
        private Set<Atom> facts;
        private List<Rule> rules;
        private Set<Atom> derivedFacts = new HashSet<>();
        private int step = 0;

        private BCRef(Input inp) {
            rules = new ArrayList<>(inp.getRules());
            facts = new HashSet<>(inp.getFacts());
            output = new Output();
        }
    }
}
