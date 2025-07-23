package com.ticketera.repositories

import com.ticketera.model.Reservation
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface ReservationRepository : CrudRepository<Reservation, UUID> {

    @Query(
        """
        SELECT COUNT(r) FROM Reservation r 
        WHERE r.ticketType.id = :ticketTypeId AND r.status IN (0, 1)
        """
    )
    fun countActiveReservationsByTicketTypeId(ticketTypeId: UUID): Long

    @Query(
        """
    SELECT COUNT(r) FROM Reservation r 
    WHERE r.sector.id = :sectorId 
      AND r.seat.id = :seatId 
      AND r.status IN (0, 1)
    """
    )
    fun countActiveReservationsBySectorAndSeat(sectorId: UUID, seatId: UUID): Long
}
