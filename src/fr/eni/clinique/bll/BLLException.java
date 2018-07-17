package fr.eni.clinique.bll;

@SuppressWarnings("serial")
public class BLLException extends Exception {
		public BLLException() {
			super();
		}
		
		public BLLException(String message) {
			super(message);
		}
		
		public BLLException(String message, Throwable exception) {
			super(message, exception);
		}

		@Override
		public String getMessage() {
			
			return "BLL - " + super.getMessage();
		}
		
		
	}

