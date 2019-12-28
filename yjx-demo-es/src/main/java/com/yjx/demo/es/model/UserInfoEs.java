package com.yjx.demo.es.model;

import java.io.Serializable;
import lombok.Data;
/*import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;*/


//@Document(indexName = "stu", type = "doc")
@Data
public class UserInfoEs implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    //@Id
    //@Field
    private String id;
    // @Field()
    private String stuId;
    // @Field
    private String stuName;
    // @Field
    private String createTime;


}
