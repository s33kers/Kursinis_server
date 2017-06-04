package solver.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

/**
 * Created by tadas.
 */
@XmlRootElement
public class Input {

    private List<Rule> rules = new ArrayList<>();
    private List<Atom> facts = new ArrayList<>();
    private Atom goal;

    public void addRule(Rule rule) {
        rules.add(rule);
    }

    public void addFact(Atom atom) {
        facts.add(atom);
    }

    public void setGoal(Atom goal) {
        this.goal = goal;
    }

    @XmlElementWrapper(name = "rules", required = true)
    @XmlElement(name = "rule", required = true)
    public List<Rule> getRules() {
        return rules;
    }

    @XmlElement(name = "fact", required = true)
    public List<Atom> getFacts() {
        return facts;
    }

    @XmlElement(required = true)
    public Atom getGoal() {
        return goal;
    }

    @Override
    public String toString() {
        StringJoiner sj = new StringJoiner("\n");
        sj.add("Taisyklės:");
        rules.stream().forEach(r -> sj.add(r.toString()));
        sj.add("\nFaktai: ");
        StringBuilder sb = new StringBuilder();
        facts.stream().forEach(f -> sb.append(f.toString() + " "));
        sj.add(sb.toString());
        sj.add("\nTikslas:");
        sj.add(goal.toString());
        sj.add("\n");

        return sj.toString();
    }
}

