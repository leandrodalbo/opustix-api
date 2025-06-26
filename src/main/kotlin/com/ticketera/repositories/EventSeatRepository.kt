package com.ticketera.repositories

import com.ticketera.model.EventSeat
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface EventSeatRepository : CrudRepository<EventSeat, UUID> {
    fun deleteByEventId(eventId: UUID)
    fun deleteByEventIdAndSectorId(eventId: UUID, sectorId: UUID)
    fun findAllByEventId(eventId: UUID): List<EventSeat>
    fun findAllByEventIdAndSectorId(eventId: UUID, sectorId: UUID): List<EventSeat>
}