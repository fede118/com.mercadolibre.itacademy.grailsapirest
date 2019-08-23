package com.mercadolibre.itacademy

import grails.rest.Resource

@Resource(uri="/articulos")
class Articulo {

    String name
    String picture
    int total_items_in_this_category

    static belongsTo = [marca: Marca]

    static constraints = {
        name blank: false, nullable: false
        picture blank: false, nullable: true
        total_items_in_this_category min: 0, max: 100000
    }
}
