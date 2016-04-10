package ru.unc6.promeets.controller.rest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.unc6.promeets.controller.exception.NotFoundException;
import ru.unc6.promeets.controller.exception.ResponseError;
import ru.unc6.promeets.model.service.entity.BaseService;

import java.util.List;

/**
 * Created by Vladimir on 03.04.2016.
 */
public class BaseRestController<T, V> {
    public static final String NOT_FOUND_ERROR_MESSAGE = "Entity not found";
    private BaseService<T, V> service;

    public BaseRestController(BaseService<T, V> service) {
        this.service = service;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List getAll() {
        return service.getAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public T getById(@PathVariable("id") V id) {
        T entity = service.getById(id);
        checkIsNotFound(entity);
        return entity;
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public T create(@RequestBody T entity) {
        return service.save(entity);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public T updateById(@PathVariable("id") V id, @RequestBody T entity) {
        checkIsNotFoundById(id);
        return service.save(entity);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable("id") V id) {
        checkIsNotFoundById(id);
        service.delete(id);
    }

    protected void checkIsNotFoundById(V id) {
        if (service.getById(id) == null) {
            throw new NotFoundException().setResponseError(new ResponseError(NOT_FOUND_ERROR_MESSAGE));
        }
    }

    protected void checkIsNotFound(T entity) {
        if (entity == null) {
            throw new NotFoundException().setResponseError(new ResponseError(NOT_FOUND_ERROR_MESSAGE));
        }
    }
}
