package solver.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import java.util.List;
import java.util.StringJoiner;

/**
 * Created by tadas.
 */
@XmlType(propOrder = {"name", "leftHandSide", "rightHandSide"})
public class Rule {

    private String name;

    private List<Atom> leftHandSide;

    private Atom rightHandSide;

    private boolean used;

    private boolean redundant;

    public Rule() {
    }

    public Rule(String name, List<Atom> leftHandSide, Atom rightHandSide) {
        this.name = name;
        this.leftHandSide = leftHandSide;
        this.rightHandSide = rightHandSide;
    }

    @XmlElement(required = true)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlElementWrapper(name = "allLeftHandSide", required = true)
    @XmlElement(required = true)
    public List<Atom> getLeftHandSide() {
        return leftHandSide;
    }

    public void setLeftHandSide(List<Atom> leftHandSide) {
        this.leftHandSide = leftHandSide;
    }

    @XmlElement(required = true)
    public Atom getRightHandSide() {
        return rightHandSide;
    }

    public void setRightHandSide(Atom rightHandSide) {
        this.rightHandSide = rightHandSide;
    }

    @XmlTransient
    public boolean isUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }

    @XmlTransient
    public boolean isRedundant() {
        return redundant;
    }

    public void setRedundant(boolean redundant) {
        this.redundant = redundant;
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append(name);
        sb.append(": ");
        sb.append(leftHandSideToString());
        sb.append(" -> ");
        sb.append(rightHandSide.toString());

        return sb.toString();
    }

    public String leftHandSideToString() {
        StringJoiner sj = new StringJoiner(",");
        leftHandSide.stream().forEach(a -> sj.add(a.toString()));
        return sj.toString();
    }
}
