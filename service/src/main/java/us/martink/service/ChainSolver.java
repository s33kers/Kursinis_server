package us.martink.service;

import us.martink.solver.Unsolvable;
import us.martink.solver.model.Input;
import us.martink.solver.model.SolverResult;

import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlElement;

@WebService
@SOAPBinding
public interface ChainSolver {

    SolverResult solveBackwardChaining(@XmlElement(required = true) Input input) throws Unsolvable;

    SolverResult solveForwardChaining(@XmlElement(required = true) Input input);
}
