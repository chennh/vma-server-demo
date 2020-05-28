package com.vma.app.demo.task.demo.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.vma.app.demo.task.demo.dao.IDemoDao;
import com.vma.app.demo.task.demo.service.IDemoService;
import com.vma.model.xxx.demo.domain.entity.Demo;
import org.springframework.stereotype.Service;

/**
 * IDemoService实现类
 *
 * @author: chennaihua
 * @version: 1.created by chennaihua on 2019/5/6.
 */
@Service
public class DemoServiceImpl extends ServiceImpl<IDemoDao, Demo> implements IDemoService {
}
