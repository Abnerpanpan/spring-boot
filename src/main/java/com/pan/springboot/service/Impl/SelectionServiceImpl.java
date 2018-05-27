package com.pan.springboot.service.Impl;

import com.pan.springboot.dao.SelectionMapper;
import com.pan.springboot.entity.Selection;
import com.pan.springboot.service.SelectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SelectionServiceImpl implements SelectionService {
    @Autowired
    SelectionMapper selectionDao;
    @Override
    public List<Selection> find() {
        return selectionDao.find();
    }
}
