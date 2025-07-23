package com.ticketera.repositories

import com.ticketera.model.Event
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface EventRepository : CrudRepository<Event, UUID>{

    @Query("SELECT e FROM Event e LEFT JOIN FETCH e.banners")
    fun findAllWithBanners(): List<Event>
}