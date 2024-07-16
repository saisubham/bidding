package bidding.exceptions;

public class EntityDoesNotExistException extends Exception {
    public EntityDoesNotExistException(String type, String name) {
        super(type + ": " + name + " does not exist.");
    }
}
