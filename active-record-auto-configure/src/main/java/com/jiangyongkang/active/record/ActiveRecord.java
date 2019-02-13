package com.jiangyongkang.active.record;

import com.jiangyongkang.active.record.toolkit.BeanUtils;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@SuppressWarnings("unchecked")
public class ActiveRecord<E extends ActiveRecord> extends ActiveModel<E> {

    private Class<?> clazz;

    public ActiveRecord() {
        this.clazz = this.getClass();
    }

    protected E record() {
        return (E) this;
    }

    @Override
    public E first() {
        Map<String, Object> result = selectMapper.first(clazz);
        return (E) BeanUtils.mapToBean(result, clazz);
    }

    @Override
    public E last() {
        Map<String, Object> result = selectMapper.last(clazz);
        return (E) BeanUtils.mapToBean(result, clazz);
    }

    @Override
    public E findById(Serializable id) {
        Map<String, Object> result = selectMapper.findById(id, clazz);
        return (E) BeanUtils.mapToBean(result, clazz);
    }

    @Override
    public E findBySQL(String condition) {
        Map<String, Object> result = selectMapper.findBySQL(condition, clazz);
        return (E) BeanUtils.mapToBean(result, clazz);
    }

    @Override
    public List<E> all() {
        return where(null);
    }

    @Override
    public List<E> where(String condition) {
        List<Map<String, Object>> results = selectMapper.where(condition, clazz);
        return (List<E>) BeanUtils.mapToBean(results, clazz);
    }

    @Override
    public int count() {
        return selectMapper.count(clazz);
    }

    @Override
    public int countBySQL(String condition) {
        return selectMapper.countBySQL(condition, clazz);
    }

    @Override
    public boolean exists() {
        return false;
    }

    @Override
    public boolean delete() {
        return false;
    }

    @Override
    public boolean deleteById(Serializable id) {
        return deleteMapper.deleteById(id, clazz) == 1;
    }

    @Override
    public boolean deleteBySQL(String condition) {
        deleteMapper.deleteBySQL(condition, clazz);
        return true;
    }

    @Override
    public boolean save() {
        return insertMapper.save(this, clazz);
    }

    @Override
    public boolean update() {
        return update(BeanUtils.beanToMap(this));
    }

    @Override
    public boolean update(Map<String, Object> attributes) {
        return updateMapper.update(attributes, clazz);
    }
}
