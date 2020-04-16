package com.yjx.demo.es.model.base;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Data
public class Page<T> {

    private Integer pageNo;

    private Integer pageSize;

    private Long totalCount;

    private List<T> records = new ArrayList<>();

}
