package com.ticketera.repositories

import com.ticketera.model.EventSector
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface EventSectorRepository : CrudRepository<EventSector, UUID>