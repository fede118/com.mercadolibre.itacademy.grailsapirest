package com.mercadolibre.itacademy

import grails.rest.Resource

@Resource(uri="/marcas")
class Marca {

    String name

    static hasMany = [articulos: Articulo]

    static constraints = {
        name blank: false, nullable: false
    }
}