package com.example.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first

class BookingRepository(private val bookingDao: BookingDao) {

  val allBookings: Flow<List<Booking>> = bookingDao.getAllBookings()

  suspend fun insert(booking: Booking) {
    bookingDao.insertBooking(booking)
  }

  suspend fun update(booking: Booking) {
    bookingDao.updateBooking(booking)
  }

  suspend fun deleteById(id: Int) {
    bookingDao.deleteBookingById(id)
  }

  suspend fun prepopulateIfEmpty() {
    val count = bookingDao.getCount()
    if (count == 0) {
      // Seed initial mock data from the administrative system dashboard mock list
      val seedBookings = listOf(
        Booking(
          customerName = "Rahul Ambani",
          customerPhone = "9876543210",
          customerAddress = "Plot 42, Viman Nagar, Pune, Maharashtra - 411014",
          serviceType = "Electrician",
          scheduledDate = "Today, 24 Oct",
          scheduledTime = "10:00 AM - 12:00 PM",
          amount = 4500.0,
          status = "Completed",
          traineeName = "Amit Kulkarni",
          traineePhotoUrl = "https://lh3.googleusercontent.com/aida-public/AB6AXuCID21nwQCR2ZmHIlC7GwD6Jzf-ZBSyCXGrVkP5QLI9OH3XiKO6VannECMPouQ__QbQ5yb17EcQTTu6awiXW5oF3cEUauN36DRCSIfpr1BpUur2SbcvXwp4npFBjcQZQWx0hMKKMupd1M6HKGU8Gn7E31Gekb4V2ylDx4-tyJdlpMxbzPb9rhunLvSYQaQdEva_2azuGg3y-EDvDz5yfEe0xJQWma5Z9vDtCW3kKXRk5N-c815JtanH9o1MyeIlQg1TxWsDRd8HOfc-",
          createdAt = System.currentTimeMillis() - 7200000 // 2 hours ago
        ),
        Booking(
          customerName = "Sunita Patil",
          customerPhone = "9123456780",
          customerAddress = "Street 5, Kothrud, Pune, Maharashtra - 411038",
          serviceType = "Plumber",
          scheduledDate = "Today, 24 Oct",
          scheduledTime = "02:00 PM - 04:00 PM",
          amount = 850.0,
          status = "In Progress",
          traineeName = "Vijay Shinde",
          traineePhotoUrl = "https://lh3.googleusercontent.com/aida-public/AB6AXuCOifNtXPmlfHgCBgtt1_z3NA2iccf_mLT9GPczEWAW9zjOPAGjIXZ4dnOjr6qJTi_j1D40UYGK4x8gJa_NSPRTytlKWqrF0QNeo6Vquxk3CDWycS2xxXvt0XmTw4cNcXr61lDYAoenYp17aHPKoULjyJPXgz25-3p8m3uCJ0n4VhASE-VCcdr5eUo7vOsuvI8S6-J8JeDTkONiNUcMr4gdccxVlNT7zVwLjhPFMx1crmrGzs3PwcNaLzToGa-gnXOm6k0E3zmpkj5M",
          createdAt = System.currentTimeMillis() - 3600000 // 1 hour ago
        ),
        Booking(
          customerName = "Mahesh Deshmukh",
          customerPhone = "9876112233",
          customerAddress = "Apartment 10C, Kalyani Nagar, Pune - 411006",
          serviceType = "AC Repair",
          scheduledDate = "Tomorrow, 25 Oct",
          scheduledTime = "09:00 AM - 11:00 AM",
          amount = 12200.0,
          status = "Pending",
          traineeName = "Prakash Joshi",
          traineePhotoUrl = "https://lh3.googleusercontent.com/aida-public/AB6AXuCID21nwQCR2ZmHIlC7GwD6Jzf-ZBSyCXGrVkP5QLI9OH3XiKO6VannECMPouQ__QbQ5yb17EcQTTu6awiXW5oF3cEUauN36DRCSIfpr1BpUur2SbcvXwp4npFBjcQZQWx0hMKKMupd1M6HKGU8Gn7E31Gekb4V2ylDx4-tyJdlpMxbzPb9rhunLvSYQaQdEva_2azuGg3y-EDvDz5yfEe0xJQWma5Z9vDtCW3kKXRk5N-c815JtanH9o1MyeIlQg1TxWsDRd8HOfc-",
          createdAt = System.currentTimeMillis() - 1800000 // 30 mins ago
        ),
        Booking(
          customerName = "Vijay Jadhav",
          customerPhone = "9445566778",
          customerAddress = "Sector 2, Hadapsar, Pune - 411028",
          serviceType = "Welding",
          scheduledDate = "Yesterday, 23 Oct",
          scheduledTime = "12:00 PM - 02:00 PM",
          amount = 18000.0,
          status = "Completed",
          traineeName = "Sandeep Thorat",
          traineePhotoUrl = "https://lh3.googleusercontent.com/aida-public/AB6AXuCOifNtXPmlfHgCBgtt1_z3NA2iccf_mLT9GPczEWAW9zjOPAGjIXZ4dnOjr6qJTi_j1D40UYGK4x8gJa_NSPRTytlKWqrF0QNeo6Vquxk3CDWycS2xxXvt0XmTw4cNcXr61lDYAoenYp17aHPKoULjyJPXgz25-3p8m3uCJ0n4VhASE-VCcdr5eUo7vOsuvI8S6-J8JeDTkONiNUcMr4gdccxVlNT7zVwLjhPFMx1crmrGzs3PwcNaLzToGa-gnXOm6k0E3zmpkj5M",
          createdAt = System.currentTimeMillis() - 86400000 // 1 day ago
        ),
        Booking(
          customerName = "Anita Kulkarni",
          customerPhone = "9988776655",
          customerAddress = "Model Colony, Shivajinagar, Pune - 411016",
          serviceType = "Comp Repair",
          scheduledDate = "Tomorrow, 25 Oct",
          scheduledTime = "04:00 PM - 06:00 PM",
          amount = 2400.0,
          status = "In Progress",
          traineeName = "Amit Kulkarni",
          traineePhotoUrl = "https://lh3.googleusercontent.com/aida-public/AB6AXuCID21nwQCR2ZmHIlC7GwD6Jzf-ZBSyCXGrVkP5QLI9OH3XiKO6VannECMPouQ__QbQ5yb17EcQTTu6awiXW5oF3cEUauN36DRCSIfpr1BpUur2SbcvXwp4npFBjcQZQWx0hMKKMupd1M6HKGU8Gn7E31Gekb4V2ylDx4-tyJdlpMxbzPb9rhunLvSYQaQdEva_2azuGg3y-EDvDz5yfEe0xJQWma5Z9vDtCW3kKXRk5N-c815JtanH9o1MyeIlQg1TxWsDRd8HOfc-",
          createdAt = System.currentTimeMillis() - 10000 // 10s ago
        )
      )
      for (booking in seedBookings) {
        bookingDao.insertBooking(booking)
      }
    }
  }
}
