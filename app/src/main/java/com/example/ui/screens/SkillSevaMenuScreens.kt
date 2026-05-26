package com.example.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.AppDatabase
import com.example.data.Booking
import com.example.data.BookingRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

enum class Screen {
  LanguageSelection,
  Login,
  Dashboard,
  ServiceDetails,
  Schedule,
  BookingSummary,
  TrackService,
  AdminDashboard,
  AboutPOTS,
  AboutUs,
  POTSDocument,
  Profile
}

enum class Language {
  English,
  Marathi
}

class SkillSevaViewModel(application: Application) : AndroidViewModel(application) {
  private val repository: BookingRepository

  init {
    val database = AppDatabase.getDatabase(application)
    repository = BookingRepository(database.bookingDao())
    viewModelScope.launch {
      repository.prepopulateIfEmpty()
    }
  }

  // Live list stream of database bookings
  val bookings: StateFlow<List<Booking>> = repository.allBookings
    .stateIn(
      scope = viewModelScope,
      started = SharingStarted.WhileSubscribed(5000),
      initialValue = emptyList()
    )

  // Current Screen state
  private val _currentScreen = MutableStateFlow(Screen.LanguageSelection)
  val currentScreen: StateFlow<Screen> = _currentScreen.asStateFlow()

  // Dynamic system backstack
  private val backstack = mutableListOf<Screen>()

  fun navigateTo(screen: Screen) {
    backstack.add(_currentScreen.value)
    _currentScreen.value = screen
  }

  fun navigateBack() {
    if (backstack.isNotEmpty()) {
      _currentScreen.value = backstack.removeAt(backstack.size - 1)
    } else {
      _currentScreen.value = Screen.LanguageSelection
    }
  }

  // Selected language logic
  private val _selectedLanguage = MutableStateFlow(Language.English)
  val selectedLanguage: StateFlow<Language> = _selectedLanguage.asStateFlow()

  fun selectLanguage(language: Language) {
    _selectedLanguage.value = language
    navigateTo(Screen.Login)
  }

  // OTP Login logic States
  private val _phoneNumber = MutableStateFlow("")
  val phoneNumber: StateFlow<String> = _phoneNumber.asStateFlow()

  private val _otpCode = MutableStateFlow("")
  val otpCode: StateFlow<String> = _otpCode.asStateFlow()

  private val _isOtpSent = MutableStateFlow(false)
  val isOtpSent: StateFlow<Boolean> = _isOtpSent.asStateFlow()

  fun setPhoneNumber(num: String) {
    val digitsOnly = num.filter { it.isDigit() }
    if (digitsOnly.length <= 10) {
      _phoneNumber.value = digitsOnly
    }
  }

  fun setOtpCode(code: String) {
    val digitsOnly = code.filter { it.isDigit() }
    if (digitsOnly.length <= 6) {
      _otpCode.value = digitsOnly
    }
  }

  fun requestOtp() {
    if (_phoneNumber.value.length == 10) {
      _isOtpSent.value = true
    }
  }

  fun verifyOtp() {
    // Generous demo logic: allows any code to authenticate
    navigateTo(Screen.Dashboard)
  }

  fun skipToDashboard() {
    navigateTo(Screen.Dashboard)
  }

  fun resetLoginState() {
    _phoneNumber.value = ""
    _otpCode.value = ""
    _isOtpSent.value = false
  }

  // Chosen Service Card logic
  private val _selectedService = MutableStateFlow("Electrician")
  val selectedService: StateFlow<String> = _selectedService.asStateFlow()

  fun selectService(service: String) {
    _selectedService.value = service
    navigateTo(Screen.ServiceDetails)
  }

  // Horizontal schedule choices
  private val _selectedDate = MutableStateFlow("Mon, 12 Oct")
  val selectedDate: StateFlow<String> = _selectedDate.asStateFlow()

  private val _selectedTimeSlot = MutableStateFlow("10:00 AM")
  val selectedTimeSlot: StateFlow<String> = _selectedTimeSlot.asStateFlow()

  private val _paymentMethod = MutableStateFlow("online") // "online" or "cash"
  val paymentMethod: StateFlow<String> = _paymentMethod.asStateFlow()

  fun selectDate(date: String) {
    _selectedDate.value = date
  }

  fun selectTimeSlot(slot: String) {
    _selectedTimeSlot.value = slot
  }

  fun selectPaymentMethod(method: String) {
    _paymentMethod.value = method
  }

  // Placement variables
  private val _currentActiveBooking = MutableStateFlow<Booking?>(null)
  val currentActiveBooking: StateFlow<Booking?> = _currentActiveBooking.asStateFlow()

  fun confirmAndPlaceBooking() {
    viewModelScope.launch {
      val newBooking = Booking(
        customerName = "Rahul Deshmukh",
        customerPhone = _phoneNumber.value.ifEmpty { "9876543210" },
        customerAddress = "Plot 42, Viman Nagar, Pune, Maharashtra - 411014",
        serviceType = _selectedService.value,
        scheduledDate = _selectedDate.value,
        scheduledTime = _selectedTimeSlot.value,
        amount = 278.0,
        status = "Pending",
        traineeName = "Amit Kulkarni",
        traineePhotoUrl = "https://lh3.googleusercontent.com/aida-public/AB6AXuCID21nwQCR2ZmHIlC7GwD6Jzf-ZBSyCXGrVkP5QLI9OH3XiKO6VannECMPouQ__QbQ5yb17EcQTTu6awiXW5oF3cEUauN36DRCSIfpr1BpUur2SbcvXwp4npFBjcQZQWx0hMKKMupd1M6HKGU8Gn7E31Gekb4V2ylDx4-tyJdlpMxbzPb9rhunLvSYQaQdEva_2azuGg3y-EDvDz5yfEe0xJQWma5Z9vDtCW3kKXRk5N-c815JtanH9o1MyeIlQg1TxWsDRd8HOfc-"
      )
      repository.insert(newBooking)
      _currentActiveBooking.value = newBooking
      _currentScreen.value = Screen.TrackService
    }
  }

  fun updateBookingStatus(booking: Booking, newStatus: String) {
    viewModelScope.launch {
      repository.update(booking.copy(status = newStatus))
    }
  }

  // Simulated live helper messaging chat
  private val _chatMessages = MutableStateFlow<List<Pair<String, Boolean>>>(listOf(
    "Hello! I am on my way to your location." to false,
    "Please make sure the work area is clear." to false
  ))
  val chatMessages: StateFlow<List<Pair<String, Boolean>>> = _chatMessages.asStateFlow()

  fun sendChatMessage(message: String) {
    if (message.isNotBlank()) {
      val currentList = _chatMessages.value.toMutableList()
      currentList.add(message to true)
      _chatMessages.value = currentList

      // Realist feedback automation
      viewModelScope.launch {
        kotlinx.coroutines.delay(1200)
        val answers = listOf(
          "Got it! Reaching soon.",
          "Awesome. On my way.",
          "Perfect, I am near Viman Nagar.",
          "Okay, thank you for the info!"
        )
        val replyList = _chatMessages.value.toMutableList()
        replyList.add(answers.random() to false)
        _chatMessages.value = replyList
      }
    }
  }
}
