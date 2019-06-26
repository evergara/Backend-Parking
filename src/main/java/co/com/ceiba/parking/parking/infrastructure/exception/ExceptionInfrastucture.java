package co.com.ceiba.parking.parking.infrastructure.exception;

public class ExceptionInfrastucture {
    private String name;
    private String message;

    public ExceptionInfrastucture(String name, String message) {
        this.name = name;
        this.message = message;
    }

    public String getName() {
        return name;
    }

    public String getMessage() {
        return message;
    }
}
