package com.hy.fourseas.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hy.fourseas.entity.AddressBook;
import com.hy.fourseas.mapper.AddressBookMapper;
import com.hy.fourseas.service.AddressBookService;
import org.springframework.stereotype.Service;

@Service
public class AddressBookServiceImpl extends ServiceImpl<AddressBookMapper, AddressBook> implements AddressBookService {

}
