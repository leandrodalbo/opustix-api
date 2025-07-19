package com.ticketera.repositories

import com.ticketera.model.Event
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface EventRepository : CrudRepository<Event, UUID> {

    @Query(
        """
    SELECT 
        e.id as event_id, e.title, e.description, e.start_time, e.end_time, e.capacity,

        tt.id as ticket_id, tt.name as ticket_name, tt.price as ticket_price, tt.currency, 
        tt.sale_start, tt.sale_end, tt.quantity, tt.description as ticket_description,

        s.id as sector_id, s.name as sector_name, s.description as sector_description, s.price_addition as sector_price_addition,

        seat.id as seat_id, seat.label as seat_label, seat.seat_row_info, seat.seat_number, 
        seat.price_addition as seat_price_addition, seat.sector_id as seat_sector_id

    FROM event e
    LEFT JOIN ticket_type tt ON tt.event_id = e.id
    LEFT JOIN event_sector s ON s.event_id = e.id
    LEFT JOIN event_seat seat ON seat.event_id = e.id
    WHERE e.id = :eventId
    """,
        nativeQuery = true
    )
    fun fetchEventDetailsRaw(@Param("eventId") eventId: UUID): List<Map<String, Any>>

}