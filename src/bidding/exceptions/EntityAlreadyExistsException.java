package bidding.exceptions;

public class EntityAlreadyExistsException extends Exception {

    public EntityAlreadyExistsException(String type, String name) {
        super(type + ": " + name + " already exists.");
    }
}
