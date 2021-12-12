package ch.hevs.exception;

public class MovieException extends RuntimeException {

	public MovieException() {
		super();
	}

	public MovieException(String arg0) {
		super(arg0);
	}

	public MovieException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public MovieException(Throwable arg0) {
		super(arg0);
	}

}
