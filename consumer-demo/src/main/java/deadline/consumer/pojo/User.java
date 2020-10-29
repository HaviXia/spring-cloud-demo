package deadline.consumer.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;


// 或者使用 @Data 不需要 getter/setter/toString

public class User implements Serializable {

    private static final long serialVersionUID = 1L;


    private Long id;

    // 用户名
    private String userName;

    // 密码
    private String password;

    // 年龄
    private Integer age;

    // 性别，1男性，2女性
    private Integer sex;

    // 出生日期
    private Date birthday;

    // 备注
    private String note;

    // 创建时间
    private Date created;

    // 更新时间
    private Date updated;


}