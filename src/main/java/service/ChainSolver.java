package service;

import solver.Unsolvable;
import solver.model.Input;
import solver.model.SolverResult;

import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@WebService
@SOAPBinding
public interface ChainSolver {

    SolverResult solveBackwardChaining(Input input) throws Unsolvable;

    SolverResult solveForwardChaining(Input input);
}
