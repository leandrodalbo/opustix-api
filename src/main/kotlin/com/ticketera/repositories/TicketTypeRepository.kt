package com.ticketera.repositories

import com.ticketera.model.TicketType
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface TicketTypeRepository : CrudRepository<TicketType, UUID> {

    @Query(
        """
    SELECT DISTINCT tt FROM TicketType tt
    JOIN FETCH tt.event e
    LEFT JOIN FETCH tt.sectors s
    LEFT JOIN FETCH s.seats
    LEFT JOIN FETCH e.banners
    JOIN FETCH e.venue
    WHERE e.id = :eventId"""
    )
    fun findTicketTypesWithSectorsAndSeatsByEventId(eventId: UUID): List<TicketType>
}