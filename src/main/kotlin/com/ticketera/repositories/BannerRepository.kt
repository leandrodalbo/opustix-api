package com.ticketera.repositories

import com.ticketera.model.Banner
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface BannerRepository : CrudRepository<Banner, UUID>