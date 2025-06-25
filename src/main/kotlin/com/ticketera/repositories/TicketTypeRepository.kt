package com.ticketera.repositories

import com.ticketera.model.TicketType
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface TicketTypeRepository : CrudRepository<TicketType, UUID> {
    fun deleteByEventId(eventId: UUID)
    fun findAllByEventId(eventId: UUID): List<TicketType>
}