package com.ticketera.repositories

import com.ticketera.model.EventSector
import com.ticketera.model.TicketType
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface EventSectorRepository : CrudRepository<EventSector, UUID> {
    fun deleteByEventId(eventId: UUID)
    fun findAllByEventId(eventId: UUID): List<EventSector>
}