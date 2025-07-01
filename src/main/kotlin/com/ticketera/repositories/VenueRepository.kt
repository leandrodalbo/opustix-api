package com.ticketera.repositories

import com.ticketera.model.Venue
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface VenueRepository : CrudRepository<Venue, UUID>{

    @Query("SELECT v FROM Venue v JOIN Event e ON v.id = e.venue.id WHERE e.id = :eventId")
    fun findByEventId(@Param("eventId") eventId: UUID): Venue

}