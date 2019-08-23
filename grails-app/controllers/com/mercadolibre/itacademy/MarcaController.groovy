package com.mercadolibre.itacademy

import grails.rest.RestfulController
import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class MarcaController extends RestfulController<Marca> {

    MarcaService marcaService

    MarcaController() {
        super(Marca)
    }

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    static responseFormats = ['json']

    @Override
    def index() {
        def marcaId = params.marcaId
        if (marcaId != null) {
            respond Articulo.where {
                Marca.id == marcaId
            }.list()
        } else {
            respond Marca.list()
        }

    }

    def show(Long id) {
        respond marcaService.get(id)
    }

    def create() {
        respond new Marca(params)
    }

    def save(Marca marca) {
        if (marca == null) {
            notFound()
            return
        }

        try {
            marcaService.save(marca)
        } catch (ValidationException e) {
            respond marca.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'marca.label', default: 'Marca'), marca.id])
                redirect marca
            }
            '*' { respond marca, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond marcaService.get(id)
    }

    def update(Marca marca) {
        if (marca == null) {
            notFound()
            return
        }

        try {
            marcaService.save(marca)
        } catch (ValidationException e) {
            respond marca.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'marca.label', default: 'Marca'), marca.id])
                redirect marca
            }
            '*'{ respond marca, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        marcaService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'marca.label', default: 'Marca'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'marca.label', default: 'Marca'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
