package Exceptions;

public class InvalidSupervisorException extends Exception{
	public InvalidSupervisorException(String message) {
		super(message);
	}
}

/*
 * usage
 * if (supervisor.getHIndex() < 3) {
            throw new InvalidSupervisorException("Supervisor's h-index is too low (<3) for research supervision.");
        }
   try {
            dean.assignResearchSupervisor(student, researcher);
        } catch (InvalidSupervisorException e) {
            System.out.println("Failed to assign supervisor: " + e.getMessage());
        }
 */
