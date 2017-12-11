package firstgrailsapp

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class AirportsServiceSpec extends Specification {

    AirportsService airportsService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new Airports(...).save(flush: true, failOnError: true)
        //new Airports(...).save(flush: true, failOnError: true)
        //Airports airports = new Airports(...).save(flush: true, failOnError: true)
        //new Airports(...).save(flush: true, failOnError: true)
        //new Airports(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //airports.id
    }

    void "test get"() {
        setupData()

        expect:
        airportsService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Airports> airportsList = airportsService.list(max: 2, offset: 2)

        then:
        airportsList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        airportsService.count() == 5
    }

    void "test delete"() {
        Long airportsId = setupData()

        expect:
        airportsService.count() == 5

        when:
        airportsService.delete(airportsId)
        sessionFactory.currentSession.flush()

        then:
        airportsService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        Airports airports = new Airports()
        airportsService.save(airports)

        then:
        airports.id != null
    }
}
