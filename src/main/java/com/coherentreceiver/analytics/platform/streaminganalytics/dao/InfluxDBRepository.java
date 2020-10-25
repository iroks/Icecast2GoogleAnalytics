/**
 * Copyright 2017-2020 SWI Kommunikations- und Computer GmbH
 */

package com.coherentreceiver.analytics.platform.streaminganalytics.dao;

import org.influxdb.InfluxDB;
import org.influxdb.impl.InfluxDBMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class InfluxDBRepository <T, ID> implements CrudRepository<T,ID> {


    private InfluxDB influxDB;

    private InfluxDBMapper influxDBMapper;

    public InfluxDBRepository (InfluxDB influxDB){
        this.influxDB = influxDB;
        this.influxDBMapper = new InfluxDBMapper(influxDB);
    }

    @Override
    public <S extends T> S save(S s) {
//        InfluxDBMapper influxDBMapper = new InfluxDBMapper(influxDB);
        influxDBMapper.save(s);
        return s;

    }

    @Override
    public <S extends T> Iterable<S> saveAll(Iterable<S> iterable) {
        return null;
    }

    @Override
    public Optional<T> findById(ID id) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(ID id) {
        return false;
    }

    @Override
    public Iterable<T> findAll() {
        return null;
    }

    @Override
    public Iterable<T> findAllById(Iterable<ID> iterable) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(ID id) {

    }

    @Override
    public void delete(T t) {

    }

    @Override
    public void deleteAll(Iterable<? extends T> iterable) {

    }

    @Override
    public void deleteAll() {

    }
}
