package com.ticketera.repositories

import com.ticketera.model.Reservation
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface ReservationRepository : CrudRepository<Reservation, UUID>
