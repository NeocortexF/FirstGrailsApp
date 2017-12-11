package firstgrailsapp

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class AirportsController {

    AirportsService airportsService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond airportsService.list(params), model:[airportsCount: airportsService.count()]
    }

    def show(Long id) {
        respond airportsService.get(id)
    }

    def create() {
        respond new Airports(params)
    }

    def save(Airports airports) {
        if (airports == null) {
            notFound()
            return
        }

        try {
            airportsService.save(airports)
        } catch (ValidationException e) {
            respond airports.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'airports.label', default: 'Airports'), airports.id])
                redirect airports
            }
            '*' { respond airports, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond airportsService.get(id)
    }

    def update(Airports airports) {
        if (airports == null) {
            notFound()
            return
        }

        try {
            airportsService.save(airports)
        } catch (ValidationException e) {
            respond airports.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'airports.label', default: 'Airports'), airports.id])
                redirect airports
            }
            '*'{ respond airports, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        airportsService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'airports.label', default: 'Airports'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'airports.label', default: 'Airports'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
