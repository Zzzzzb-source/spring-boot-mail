package com.zb.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * API返回模型
 *
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class ReturnModel implements Serializable {

	/**
	 * 手机号已经绑定或已经被用户的其他账号绑定
	 */
    public static final String PHONE_BOUND = "1011";
    /**
     * OK, 请求已成功
     */
    public static final String SUCCESS = "200";
    /**
     * Unauthorized
     */
    public static final String UNAUTHORIZED = "401";
    /**
     * Unauthorized（扫码登录）
     */
    public static final String UNAUTHORIZED_QR = "4011";
    /**
     * Unauthorized（用户已在其他地方登录）
     */
    public static final String UNAUTHORIZED_LOGIN_BY_OTHER = "4021";
    /**
     * Unauthorized for VIP
     */
    public static final String UNAUTHORIZED_VIP = "461";
    /**
     * Unauthorized for higher authority
     */
    public static final String UNAUTHORIZED_HIGHER_AUTH = "462";

    /**
     * 由于某些原因，无法绑定
     */
    public static final String UNABLE_BIND = "471";
    
    /**
     * Internal Server Error
     */
    public static final String FAIL = "500";
    
    /**
     * 406 Not Acceptable
     */
    public static final String NOT_ACCEPT = "406";
    
    /**
     * 403 Forbidden
     */
    public static final String FORBIDDEN = "403";
    
    /**
     * 423 Locked 到达次数限制
     */
    public static final String LOCKED = "423";
    
    /**
     *  400 Bad Request
     */
    public static final String BAD_REQUEST = "400";
    
    /**
     *  408 Server is busy.
     */
    public static final String SERVER_BUSY = "408";
    
    
    private static final long serialVersionUID = -1L;

    private String status;

    private String message;

    private Object data;

    public static ReturnModel ok() {
        return new ReturnModel().setStatus(ReturnModel.SUCCESS);
    }

    public static ReturnModel ok(String message) {
        return new ReturnModel().setStatus(ReturnModel.SUCCESS).setMessage(message);
    }

    public static ReturnModel okData(Object data) {
        return ReturnModel.ok().setData(data);
    }

    public static ReturnModel fail() {
        return new ReturnModel().setStatus(ReturnModel.FAIL);
    }

    public static ReturnModel fail(String message) {
        return new ReturnModel().setStatus(ReturnModel.FAIL).setMessage(message);
    }

    public static ReturnModel unauthorized() {
        return new ReturnModel().setStatus(UNAUTHORIZED);
    }

    public static ReturnModel unauthorized(String message) {
        return new ReturnModel().setStatus(UNAUTHORIZED).setMessage(message);
    }
}
