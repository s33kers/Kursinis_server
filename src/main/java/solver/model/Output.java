package solver.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

/**
 * Created by tadas.
 */
public class Output {

    private StringBuilder result = new StringBuilder();
    private StringJoiner resultPath = new StringJoiner(", ");
    private boolean solved = false;
    private boolean preSolved = false;

    public StringBuilder getResult() {
        return result;
    }

    public void setResult(StringBuilder result) {
        this.result = result;
    }

    public StringJoiner getResultPath() {
        return resultPath;
    }

    public void setResultPath(StringJoiner resultPath) {
        this.resultPath = resultPath;
    }

    public void addResultPath(String result) {
        this.resultPath.add(result);
    }

    public boolean isSolved() {
        return solved;
    }

    public void setSolved(boolean solved) {
        this.solved = solved;
    }

    public boolean isPreSolved() {
        return preSolved;
    }

    public void setPreSolved(boolean preSolved) {
        this.preSolved = preSolved;
    }

    public String printResult(){
        StringBuilder sb = new StringBuilder();
        sb.append(result.toString());
        sb.append("\nAtsakymas:\n");
        if(preSolved){
            sb.append("Tikslas yra faktų aibėje.\n");
        }else if(solved){
            sb.append("Tikslas pasiektas. Sprendimo kelias [");
            sb.append(resultPath.toString());
            sb.append("]\n");
        } else {
            sb.append("Tikslas nepasiektas.\n");
        }
        return sb.toString();
    }

    public String getFinalResult() {
        return result.toString();
    }

    public String getFinalResultPath() {
        return resultPath.toString();
    }

    public String getResultText() {
        if(preSolved){
            return "Tikslas yra faktų aibėje.\n";
        } if (solved) {
            return "Tikslas pasiektas.";
        } else {
            return "Tikslas nepasiektas.\n";
        }
    }
}
