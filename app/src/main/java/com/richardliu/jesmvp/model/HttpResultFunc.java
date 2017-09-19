package com.richardliu.jesmvp.model;


import com.richardliu.jesmvp.base.bean.JesResponse;
import com.richardliu.jesmvp.constants.Constants;

import rx.functions.Func1;

/**
 * Created by Allen on 2017/9/19.
 */

public class HttpResultFunc<T> implements Func1<JesResponse<T>,T> {
    @Override
    public T call(JesResponse<T> jesResponse) {
        if (jesResponse.getCode() != Constants.NET_CODE_SUCCESS) {
            throw new JesException(jesResponse.getMsg(),jesResponse.getCode());
        }
        return jesResponse.getResult();
    }
}
