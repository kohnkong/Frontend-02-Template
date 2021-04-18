package io.kohnkong.spring02;

import lombok.*;
import lombok.experimental.Accessors;

/**
 * @Description: TODO
 * @author: kongqf
 * @date: 2021年04月18日 21:09
 */
@Data
@Builder
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class OrderInfo {
    private Integer orderInfo;
    private String name;
}
