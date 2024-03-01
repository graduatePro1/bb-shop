package com.bbshop.common;

import com.bbshop.common.constant.CodeEnum;

//抽象返回结果类：统一返回标准，状态码+携带信息
public class ResultBean {
    private String code; //状态码
    private String message;//返回信息
    private Object data; //携带数据

    //无参构造方法
    public ResultBean() {

    }

    //携带数据的构造方法
    public ResultBean(Object data) {
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    //成功状态
    public static ResultBean success(){
        ResultBean resultBean=new ResultBean();
        resultBean.setCode(CodeEnum.SUCCESS.code);
        resultBean.setMessage(CodeEnum.SUCCESS.message);
        return resultBean;
    }
    public static ResultBean success(Object data){
        ResultBean resultBean=new ResultBean(data);
        resultBean.setCode(CodeEnum.SUCCESS.code);
        resultBean.setMessage(CodeEnum.SUCCESS.message);
        return resultBean;
    }

    //系统异常500
    public static ResultBean error(){
        ResultBean resultBean=new ResultBean();
        resultBean.setCode(CodeEnum.SYSTEM_ERROR.code);
        resultBean.setMessage(CodeEnum.SYSTEM_ERROR.message);
        return resultBean;
    }

    //系统自定义故障
    public static ResultBean error(String code,String message){
        ResultBean resultBean=new ResultBean();
        resultBean.setCode(code);
        resultBean.setMessage(message);
        return resultBean;
    }

    //系统识别的其他故障
    public static ResultBean error(CodeEnum codeEnum){
        ResultBean resultBean=new ResultBean();
        resultBean.setCode(codeEnum.code);
        resultBean.setMessage(codeEnum.message);
        return resultBean;
    }


}
