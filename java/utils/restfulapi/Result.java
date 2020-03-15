package utils.restfulapi;

import java.io.Serializable;

/**
 * 返回类
 */
public class Result<T> implements Serializable {
    /**
     * 错误码
     */
    private Integer code;
    /**
     * 错误信息
     */
    private String message;
    /**
     * 数据结果
     */
    private T data;

    /**
     * 构造
     */
    public Result() {

    }

    public Result(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * 响应成功时的返回信息  data为null时
     **/
    public static <T> Result<T> success() {
        return new Result<>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage());
    }

    /**
     * 响应成功时的返回信息 data不为为null时
     **/
    public static <T> Result<T> success(T data) {
        Result<T> result = new Result<>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage());
        result.setData(data);
        return result;
    }

    /**
     * 响应信息封装
     **/
    public static <T> Result<T> response(Integer code, String message, T data) {
        Result<T> result = new Result<>(code, message);
        result.setData(data);
        return result;
    }


    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }


}
