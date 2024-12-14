package Exceptions;

public class StudentRetakeLimitException extends Exception{
	private int currentRetakeCount;

    public StudentRetakeLimitException(String message, int currentRetakeCount) {
        super(message);
        this.currentRetakeCount = currentRetakeCount;
    }

    public int getCurrentRetakeCount() {
        return currentRetakeCount;
    }
}
