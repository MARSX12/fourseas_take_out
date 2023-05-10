package com.hy.fourseas.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hy.fourseas.entity.Employee;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EmployeeMapper extends BaseMapper<Employee> {
}
