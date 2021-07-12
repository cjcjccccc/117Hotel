package utils;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
/*
 *  封装接口数据格式返回给页面
 * @author cjc
 */
public class DataGridViewResult {

    private Integer code = 0;
    private String msg = "";
    private Long count;
    private Object data;

    public DataGridViewResult(Long count, Object data) {
        this.count = count;
        this.data = data;
    }

    public DataGridViewResult(Object data) {
        this.data = data;
    }
}
