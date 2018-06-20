package cn.productai.apiv2.exceptions;

public class PAIException extends Exception {

    private Integer errorCode = 500;
    private String message = "PAIException";

    public PAIException() {
    }

    public PAIException(Integer errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
    }

    public PAIException(String message) {
        this.message = message;
    }

    public PAIException(Throwable cause) {
        super(cause);
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
