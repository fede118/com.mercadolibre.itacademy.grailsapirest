package com.mercadolibre.itacademy

import grails.converters.JSON

class BootStrap {

    def init = { servletContext ->
        def marca1 = new Marca(name: "Samsung").save(flush: true)
        def marca2 = new Marca(name: "Google").save(flush: true)

        marca1.addToArticulos(new Articulo(name:"S7", picture: null, total_items_in_this_category: 2)).save()
        marca1.addToArticulos(new Articulo(name:"S7 edge", picture: null, total_items_in_this_category: 5)).save()
        marca1.addToArticulos(new Articulo(name:"S8", picture: null, total_items_in_this_category: 10)).save()
        marca1.addToArticulos(new Articulo(name:"S9", picture: null, total_items_in_this_category: 200)).save()
        marca1.addToArticulos(new Articulo(name:"S10", picture: null, total_items_in_this_category: 1)).save()

        marca2.addToArticulos(new Articulo(name:"Pixel 2", picture: null, total_items_in_this_category: 5)).save()
        marca2.addToArticulos(new Articulo(name:"Pixel 2 XL", picture: null, total_items_in_this_category: 23)).save()
        marca2.addToArticulos(new Articulo(name:"Pixel 3", picture: null, total_items_in_this_category: 1)).save()
        marca2.addToArticulos(new Articulo(name:"Pixel 3 XL", picture: null, total_items_in_this_category: 10)).save()
        marca2.addToArticulos(new Articulo(name:"Pixel 3a", picture: null, total_items_in_this_category: 8)).save()

        marshaller()
    }
    def destroy = {
    }

    def marshaller () {
        JSON.registerObjectMarshaller(Marca) {
            marca -> [
                    id: marca.id,
                    name: marca.name,
                    articulos: marca.articulos.collect {
                        articulo -> [
                                id: articulo.id,
                                name: articulo.name,
                                picture: articulo.picture,
                                total_items_in_this_category: articulo.total_items_in_this_category,
                                children_categories: []
                        ]
                    }
            ]
        }

        JSON.registerObjectMarshaller(Articulo) {
            articulo -> [
                    id: articulo.id,
                    name: articulo.name,
                    picture: articulo.picture,
                    total_items_in_this_category: articulo.total_items_in_this_category,
                    children_categories: [],
                    path_from_root: []
            ]
        }
    }

}
