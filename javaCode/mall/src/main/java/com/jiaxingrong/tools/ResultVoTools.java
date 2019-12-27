package com.jiaxingrong.tools;

import com.jiaxingrong.bean.type.ResultVo;

/**
 * @Author:luchang
 * @Date: 2019/12/26 21:22
 * @Version 1.0
 * 对返回结果进行按需封装的工具类，结合单例和建筑者
 */
public class ResultVoTools {
    private ResultVoTools() {
    }

    /**
     * 用内部类保证单例，减少每次都new一个新对象的内存空间
     */
    static class ReInner {
        private static ResultVo resultVo = new ResultVo();

        private static ResultVo getResultVoTools() {
            return resultVo;
        }
    }

    /**
     * 成功的返回结果
     *
     * @param data
     * @param msg
     * @return
     */
    public static ResultVo successRe(Object data, String msg) {
        ResultVo resultVo = ReInner.getResultVoTools();
        resultVo.setErrno(0);
        resultVo.setErrmsg(msg);
        resultVo.setData(data);
        return resultVo;
    }

    /**
     * 失败的返回结果
     *
     * @param msg
     * @return
     */
    public static ResultVo failedRe(String msg) {
        ResultVo resultVo = ReInner.getResultVoTools();
        // 605暂不确定，只是首页登陆失败的返回码
        resultVo.setErrno(605);
        resultVo.setData(null);
        resultVo.setErrmsg(msg);
        return resultVo;
    }

    public static ResultVo failedReHavingNo(int errno, String msg) {
        ResultVo resultVo = ReInner.getResultVoTools();
        resultVo.setErrno(errno);
        resultVo.setData(null);
        resultVo.setErrmsg(msg);
        return resultVo;
    }
}
