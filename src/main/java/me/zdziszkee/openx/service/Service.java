package me.zdziszkee.openx.service;

import reactor.core.publisher.Mono;

public interface Service<T>{
    Mono<T> find(int id);
}
