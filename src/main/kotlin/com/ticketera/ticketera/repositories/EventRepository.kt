package com.ticketera.ticketera.repositories

import com.ticketera.ticketera.model.Event
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface EventRepository : CrudRepository<Event, UUID>