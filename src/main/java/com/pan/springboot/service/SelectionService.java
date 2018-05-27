package com.pan.springboot.service;

import com.pan.springboot.entity.Selection;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface SelectionService {
    public List<Selection> find();
}
