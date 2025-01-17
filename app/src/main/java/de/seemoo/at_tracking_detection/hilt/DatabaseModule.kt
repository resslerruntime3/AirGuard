package de.seemoo.at_tracking_detection.hilt

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import de.seemoo.at_tracking_detection.database.AppDatabase
import de.seemoo.at_tracking_detection.database.daos.BeaconDao
import de.seemoo.at_tracking_detection.database.daos.DeviceDao
import de.seemoo.at_tracking_detection.database.daos.FeedbackDao
import de.seemoo.at_tracking_detection.database.daos.NotificationDao
import de.seemoo.at_tracking_detection.database.repository.BeaconRepository
import de.seemoo.at_tracking_detection.database.repository.DeviceRepository
import de.seemoo.at_tracking_detection.database.repository.FeedbackRepository
import de.seemoo.at_tracking_detection.database.repository.NotificationRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, "attd_db")
            .allowMainThreadQueries().build()
    }

    @Provides
    fun provideBeaconDao(database: AppDatabase): BeaconDao {
        return database.beaconDao()
    }

    @Provides
    fun provideDeviceDao(database: AppDatabase): DeviceDao {
        return database.deviceDao()
    }

    @Provides
    fun provideNotificationDao(database: AppDatabase): NotificationDao {
        return database.notificationDao()
    }

    @Provides
    fun provideFeedbackDao(database: AppDatabase): FeedbackDao {
        return database.feedbackDao()
    }

    @Provides
    fun provideBeaconRepository(beaconDao: BeaconDao): BeaconRepository {
        return BeaconRepository(beaconDao)
    }

    @Provides
    fun provideDeviceRepository(deviceDao: DeviceDao): DeviceRepository {
        return DeviceRepository(deviceDao)
    }

    @Provides
    fun provideNotificationRepository(notificationDao: NotificationDao): NotificationRepository {
        return NotificationRepository(notificationDao)
    }

    @Provides
    fun providesFeedbackRepository(feedbackDao: FeedbackDao): FeedbackRepository {
        return FeedbackRepository(feedbackDao)
    }
}