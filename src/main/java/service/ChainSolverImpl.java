package service;

import com.sun.xml.ws.developer.SchemaValidation;
import solver.BCSolver;
import solver.FCSolver;
import solver.Unsolvable;
import solver.model.Input;
import solver.model.Output;
import solver.model.SolverResult;

import javax.jws.WebService;

@WebService(endpointInterface = "service.ChainSolver") //, name = "ChainSolver", serviceName = "ChainSolverService", targetNamespace = "http://chainsolver"
@SchemaValidation
public class ChainSolverImpl implements ChainSolver {

    @Override
    public SolverResult solveBackwardChaining(Input input) throws Unsolvable {
        Output output = BCSolver.solve(input);
        return mapSolverResult(output);
    }

    @Override
    public SolverResult solveForwardChaining(Input input) {
        Output output = FCSolver.solve(input);
        return mapSolverResult(output);
    }

    private SolverResult mapSolverResult(Output output) {
        SolverResult result = new SolverResult();
        result.setResult(output.getResultText());
        result.setSolverWorkflow(output.getResult().toString());
        result.setResultPath(output.getResultPath().toString());
        return result;
    }
}
