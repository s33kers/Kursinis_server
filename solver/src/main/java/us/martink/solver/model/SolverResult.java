package us.martink.solver.model;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by tadas.
 */
@XmlRootElement
public class SolverResult {

    private String resultPath;
    private String result;
    private String solverWorkflow;

    public String getResultPath() {
        return resultPath;
    }

    public void setResultPath(String resultPath) {
        this.resultPath = resultPath;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getSolverWorkflow() {
        return solverWorkflow;
    }

    public void setSolverWorkflow(String solverWorkflow) {
        this.solverWorkflow = solverWorkflow;
    }
}
