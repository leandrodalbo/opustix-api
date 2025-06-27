package com.ticketera.repositories

import com.ticketera.model.Purchase
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface PurchaseRepository : CrudRepository<Purchase, UUID>
