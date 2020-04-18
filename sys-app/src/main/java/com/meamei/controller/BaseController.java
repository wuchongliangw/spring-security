package com.meamei.controller;

import com.meamei.baseConfig.RestResponse;
import com.meamei.baseEntity.common.Constant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author hboxs028
 * @create 2019-03-15 11:33
 **/
@Slf4j
public abstract class BaseController {

    /**
     * 默认页码
     */
    protected final static String DEFAULT_PARAM_PAGE_NUM = "1";
    /**
     * 默认分页大小
     */
    protected final static String DEFAULT_PARAM_PAGE_SIZE = "20";

    /**
     * 成功消息
     */
    public static final String SUCCESS_MESSAGE = "操作成功";

    /**
     * 参数有误
     */
    protected static final String PARAM_ERR_MESSAGE = "参数有误";

    /**
     * 返回结果
     */
    protected static final String RESULT_OK = Constant.RESULT_OK;

    /**
     * 统一处理异常抛出未知错误
     *
     * @param e 异常
     * @return json
     */
    @ExceptionHandler(RuntimeException.class)
    public
    @ResponseBody
    RestResponse<Object> unknownException(Exception e) {
        log.error("Runtime Exception:" + e.getMessage(), e);
        return RestResponse.UNKNOWN_ERROR();
    }
    
}
