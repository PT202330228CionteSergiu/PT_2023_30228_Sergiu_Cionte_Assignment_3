package BusinessLogic;





/**
 * @author sergi
 *exceptie in cazul in care datele introduse nu sunt corecte
 */
public class InvalidDataException extends Exception{
	/**
	 * 
	 * constructor pentru exceptie
	 * @param message
	 */
    public InvalidDataException(String message)
    {
        super(message);
    }
}

