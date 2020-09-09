package com.imooc.sell.handler;

import com.imooc.sell.VO.ResultVO;
import com.imooc.sell.exception.SellException;
import com.imooc.sell.utils.ResultVOUtil;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class SellExceptionHandler {
    @ExceptionHandler(value = SellException.class)
    @ResponseBody
    public ResultVO HandlerSellException(SellException e){
        return ResultVOUtil.error(e.getCode(),e.getMessage());
    }
}
