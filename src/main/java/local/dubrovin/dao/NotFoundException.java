package local.dubrovin.dao;

public class NotFoundException extends DaoException {
    public NotFoundException() {
        super();
    }

    ;

    public NotFoundException(String message) {
        super(message);
    }
}
