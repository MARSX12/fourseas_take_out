package com.hy.fourseas.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hy.fourseas.entity.Employee;
import com.hy.fourseas.mapper.EmployeeMapper;
import com.hy.fourseas.service.EmployeeService;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements EmployeeService {
}
