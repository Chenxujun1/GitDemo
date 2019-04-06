package cloud.common;

public enum ResponseEnum {
    SUCCESS(200, "成功"),
    ERROR(500, "失败");
    private int code;
    private String message;

    public void setCode(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    private ResponseEnum(int code, String message){
        this.code = code;
        this.message = message;
    }
}
