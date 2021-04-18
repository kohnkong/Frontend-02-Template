package io.kohnkong.spring02.aop2;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * @Description: TODO
 * @author: kongqf
 * @date: 2021年04月18日 09:13
 */
@Getter
@Setter
@Data
public class OperateLogBean {
    private String type;

    private String operateObj;

    private boolean result;
}
