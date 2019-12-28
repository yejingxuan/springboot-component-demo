package com.yjx.demo.es.model;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NetInfo implements Serializable {

    private static final long serialVersionUID = -2328898705526379173L;

    private String title;

    private String content;

    private String date;
}
