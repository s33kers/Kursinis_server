package us.martink.service;

import com.sun.xml.ws.developer.SchemaValidation;
import us.martink.solver.BCSolver;
import us.martink.solver.FCSolver;
import us.martink.solver.Unsolvable;
import us.martink.solver.model.Input;
import us.martink.solver.model.Output;
import us.martink.solver.model.SolverResult;

import javax.jws.WebService;

@WebService(endpointInterface = "us.martink.service.ChainSolver") //, name = "us.martink.service.ChainSolver", serviceName = "ChainSolverService", targetNamespace = "http://chainsolver"
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
