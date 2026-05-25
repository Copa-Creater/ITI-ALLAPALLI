package com.example.data

import android.content.Context
import androidx.room.Database
import androidx.room.Dao
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Entity(tableName = "bookings")
data class Booking(
  @PrimaryKey(autoGenerate = true) val id: Int = 0,
  val customerName: String,
  val customerPhone: String,
  val customerAddress: String,
  val serviceType: String,
  val scheduledDate: String,
  val scheduledTime: String,
  val amount: Double,
  val status: String, // "Pending", "In Progress", "Completed"
  val traineeName: String,
  val traineePhotoUrl: String,
  val createdAt: Long = System.currentTimeMillis()
)

@Dao
interface BookingDao {
  @Query("SELECT * FROM bookings ORDER BY createdAt DESC")
  fun getAllBookings(): Flow<List<Booking>>

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insertBooking(booking: Booking)

  @Update
  suspend fun updateBooking(booking: Booking)

  @Query("DELETE FROM bookings WHERE id = :id")
  suspend fun deleteBookingById(id: Int)

  @Query("SELECT COUNT(*) FROM bookings")
  suspend fun getCount(): Int
}

@Database(entities = [Booking::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
  abstract fun bookingDao(): BookingDao

  companion object {
    @Volatile
    private var INSTANCE: AppDatabase? = null

    fun getDatabase(context: Context): AppDatabase {
      return INSTANCE ?: synchronized(this) {
        val instance = Room.databaseBuilder(
          context.applicationContext,
          AppDatabase::class.java,
          "skillseva_database"
        )
        .fallbackToDestructiveMigration()
        .build()
        INSTANCE = instance
        instance
      }
    }
  }
}
