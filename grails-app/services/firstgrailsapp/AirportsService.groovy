package firstgrailsapp

import grails.gorm.services.Service

@Service(Airports)
interface AirportsService {

    Airports get(Serializable id)

    List<Airports> list(Map args)

    Long count()

    void delete(Serializable id)

    Airports save(Airports airports)

}