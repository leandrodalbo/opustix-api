package com.ticketera.ticketera.repositories

import com.ticketera.ticketera.model.Venue
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface VenueRepository : CrudRepository<Venue, UUID>