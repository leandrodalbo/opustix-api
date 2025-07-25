package com.ticketera.repositories

import com.ticketera.model.Purchase
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.util.UUID


@Repository
interface PurchaseRepository : CrudRepository<Purchase, UUID> {

    @Query(
        """
        SELECT DISTINCT p 
        FROM Purchase p 
        JOIN FETCH p.reservations r 
        WHERE p.userInfo = :userInfo
        """
    )
    fun findPurchasesByUserInfo(@Param("userInfo") userInfo: String?): List<Purchase>

}

