package com.test.application.local_data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.test.application.local_data.company_entity.CompanyDao
import com.test.application.local_data.company_entity.CompanyEntity
import com.test.application.local_data.company_entity.CustomerMarkParametersEntity
import com.test.application.local_data.company_entity.LoyaltyLevelEntity
import com.test.application.local_data.company_entity.MobileAppDashboardEntity

@Database(
    version = 1,
    exportSchema = false,
    entities = [
        CompanyEntity::class,
        CustomerMarkParametersEntity::class,
        LoyaltyLevelEntity::class,
        MobileAppDashboardEntity::class
    ]
)

abstract class LocalDatabase : RoomDatabase() {
    abstract fun companyDao(): CompanyDao
}