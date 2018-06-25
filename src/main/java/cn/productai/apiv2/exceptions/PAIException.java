package cn.productai.apiv2.exceptions;

public class PAIException extends Exception {

    private Integer errorCode = 500;
    private String message = "PAIException";

    private Integer responseCode;
    private String requestId;

    public PAIException() {
    }

    public PAIException(Integer errorCode, String message, Integer responseCode, String requestId) {
        this.errorCode = errorCode;
        this.message = message;
        this.responseCode = responseCode;
        this.requestId = requestId;
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

    public Integer getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(Integer responseCode) {
        this.responseCode = responseCode;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }
}
