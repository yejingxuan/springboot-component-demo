package com.yjx.demo.quickssm.base;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: yejingxuan
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageDto<T> {

    Integer pageNo;

    Integer pageSize;

    List<T> records;

    Long totalRecords;

    Integer totalPages;

    T condition;

}
