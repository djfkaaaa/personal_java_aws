package com.james.api.common.query;

import com.james.api.common.component.MessengerVo;
import com.james.api.common.component.PageRequestVo;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface QueryService<T> {
    List<T> findAll();
    Optional<T> findById(Long id);
    Long count();
//    boolean existsById(Long id);
}
