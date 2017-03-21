package service;

import solver.model.Input;
import solver.model.Output;
import solver.model.SolverResult;

import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface ChainSolver {

    SolverResult solveBackwardChaining(Input input);

    SolverResult solveForwardChaining(Input input);
}
