package gpxLib;

/**
 * Thrown if the file loaded is invalid
 */
public class InvalidFileException extends RuntimeException{
    public InvalidFileException(String message){
        super(message);
    }
}
