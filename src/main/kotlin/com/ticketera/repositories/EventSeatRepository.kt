package com.ticketera.repositories

import com.ticketera.model.EventSeat
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface EventSeatRepository : CrudRepository<EventSeat, UUID>