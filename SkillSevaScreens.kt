package com.example.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.*
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.data.Booking
import com.example.ui.theme.*
import com.example.viewmodel.Language
import com.example.viewmodel.Screen
import com.example.viewmodel.SkillSevaViewModel

// Bilingual localization helper
fun txt(en: String, mr: String, language: Language): String {
  return if (language == Language.Marathi) mr else en
}

@Composable
fun SkillSevaAppContent(viewModel: SkillSevaViewModel) {
  val currentScreen by viewModel.currentScreen.collectAsState()
  val selectedLanguage by viewModel.selectedLanguage.collectAsState()

  Box(modifier = Modifier.fillMaxSize()) {
    when (currentScreen) {
      Screen.LanguageSelection -> LanguageSelectionScreen(viewModel)
      Screen.Login -> LoginScreen(viewModel, selectedLanguage)
      Screen.Dashboard -> DashboardScreen(viewModel, selectedLanguage)
      Screen.ServiceDetails -> ServiceDetailsScreen(viewModel, selectedLanguage)
      Screen.Schedule -> ScheduleScreen(viewModel, selectedLanguage)
      Screen.BookingSummary -> BookingSummaryScreen(viewModel, selectedLanguage)
      Screen.TrackService -> TrackServiceScreen(viewModel, selectedLanguage)
      Screen.AdminDashboard -> AdminDashboardScreen(viewModel, selectedLanguage)
      Screen.AboutPOTS -> AboutPOTSScreen(viewModel, selectedLanguage)
      Screen.AboutUs -> AboutUsScreen(viewModel, selectedLanguage)
      Screen.POTSDocument -> POTSDocumentScreen(viewModel, selectedLanguage)
      Screen.Profile -> ProfileScreen(viewModel, selectedLanguage)
    }
  }
}

// ----------------------------------------------------
// 1. Language Selection Screen
// ----------------------------------------------------
@Composable
fun LanguageSelectionScreen(viewModel: SkillSevaViewModel) {
  Scaffold(
    topBar = {
      Row(
        modifier = Modifier
          .fillMaxWidth()
          .statusBarsPadding()
          .padding(horizontal = 16.dp, vertical = 12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
          AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
              .data("https://lh3.googleusercontent.com/aida-public/AB6AXuBl_X41XujLJIfRvFnSuSRkREmOrDqEW4hMcBxRh0NB1Jw_51MS2TDAb5Brsfv9O5x0gr7URDH84ebZD_O-e2R5j9OeTjKmuILuaGlUxZO6HnFGsgQ1n4vYtyLnWFxt2xgfalwyNJCmcAWkJDUGXyR8dMJNxLUO_DgsyfPYt9HKmpUPi9PO_mp6OFwHBSx30XNtmE97j-9LAjNppQVLz70tfkNOG4XH0vqhll6ZaD8KO-8yPuqT8l0WtSD4Uk8D8PegmK5wkFq3NjCt")
              .crossfade(true)
              .build(),
            contentDescription = "Maharashtra State Emblem",
            modifier = Modifier.size(36.dp),
            contentScale = ContentScale.Fit
          )
          Spacer(modifier = Modifier.width(8.dp))
          Box(
            modifier = Modifier
              .width(1.dp)
              .height(24.dp)
              .background(MaterialTheme.colorScheme.outlineVariant)
          )
          Spacer(modifier = Modifier.width(8.dp))
          Text(
            text = "an Initiative by - GOVERNMENT ITI ALLAPALLI",
            fontSize = 11.sp,
            fontWeight = FontWeight.Bold,
            color = BrandSecondary,
            letterSpacing = 1.5.sp
          )
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
          Icon(
            imageVector = Icons.Default.VerifiedUser,
            contentDescription = "Verified Seal",
            tint = BrandPrimary,
            modifier = Modifier.size(18.dp)
          )
          Spacer(modifier = Modifier.width(4.dp))
          Text(
            text = "Trusted Service",
            fontSize = 11.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant
          )
        }
      }
    }
  ) { innerPadding ->
    Box(
      modifier = Modifier
        .fillMaxSize()
        .padding(innerPadding)
        .background(
          Brush.verticalGradient(
            colors = listOf(Color(0xFFFFF7F3), BrandBackground)
          )
        )
    ) {
      Column(
        modifier = Modifier
          .fillMaxSize()
          .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
      ) {
        // App branding logo
        AsyncImage(
          model = ImageRequest.Builder(LocalContext.current)
            .data("https://lh3.googleusercontent.com/aida-public/AB6AXuAh0UdqI1q5OoMobr2MA1h7gpKCpMqWkyBOHmaCS6Eys63aKp7kMTzoaXE8rUQ8Xj9h87qd-MuVUY8CnzuPclARdNlIg9OdBv2Pf_Eh9sQSp0VKJUT1Atc2adwkXWCpD7dmALJeRJh2DsgOWHQw7azyrgQtmqtFYgpa0mgjlNEPeJghJ1ENpbNN84Dh7CgvzMgh5JByecgpJy8Yt3kNYTSbipebFbCAVs53FzOHoBlK-0tRq1yGMgfdJgV4L6cfGSfdPZanXfNqlQwQ")
            .crossfade(true)
            .build(),
          contentDescription = "SkillSeva Logo",
          modifier = Modifier
            .size(100.dp)
            .clip(RoundedCornerShape(12.dp)),
          contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
          text = "SkillSeva",
          fontSize = 32.sp,
          fontWeight = FontWeight.ExtraBold,
          color = BrandOnSurface,
          textAlign = TextAlign.Center
        )

        Text(
          text = "ITI Skills at Your Doorstep",
          fontSize = 18.sp,
          fontWeight = FontWeight.Medium,
          color = MaterialTheme.colorScheme.onSurfaceVariant,
          textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(40.dp))

        Text(
          text = "CHOOSE YOUR LANGUAGE",
          fontSize = 13.sp,
          fontWeight = FontWeight.Bold,
          color = BrandSecondary,
          letterSpacing = 1.sp,
          modifier = Modifier.padding(bottom = 16.dp)
        )

        // Marathi option button
        Card(
          modifier = Modifier
            .fillMaxWidth()
            .clickable { viewModel.selectLanguage(Language.Marathi) }
            .testTag("marathi_button"),
          shape = RoundedCornerShape(16.dp),
          colors = CardDefaults.cardColors(containerColor = BrandSurface),
          border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant)
        ) {
          Row(
            modifier = Modifier
              .fillMaxWidth()
              .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
          ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
              Box(
                modifier = Modifier
                  .size(48.dp)
                  .clip(CircleShape)
                  .background(BrandPrimary.copy(alpha = 0.1f)),
                contentAlignment = Alignment.Center
              ) {
                Text(
                  text = "म",
                  fontSize = 22.sp,
                  fontWeight = FontWeight.Bold,
                  color = BrandPrimary
                )
              }
              Spacer(modifier = Modifier.width(16.dp))
              Column {
                Text(
                  text = "मराठी",
                  fontSize = 20.sp,
                  fontWeight = FontWeight.Bold,
                  color = BrandOnSurface
                )
                Text(
                  text = "Marathi",
                  fontSize = 12.sp,
                  color = MaterialTheme.colorScheme.onSurfaceVariant
                )
              }
            }
            Icon(
              imageVector = Icons.Default.ArrowForward,
              contentDescription = "Navigate to Marathi",
              tint = BrandPrimary
            )
          }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // English option button
        Card(
          modifier = Modifier
            .fillMaxWidth()
            .clickable { viewModel.selectLanguage(Language.English) }
            .testTag("english_button"),
          shape = RoundedCornerShape(16.dp),
          colors = CardDefaults.cardColors(containerColor = BrandSurface),
          border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant)
        ) {
          Row(
            modifier = Modifier
              .fillMaxWidth()
              .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
          ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
              Box(
                modifier = Modifier
                  .size(48.dp)
                  .clip(CircleShape)
                  .background(BrandSecondary.copy(alpha = 0.1f)),
                contentAlignment = Alignment.Center
              ) {
                Text(
                  text = "A",
                  fontSize = 22.sp,
                  fontWeight = FontWeight.Bold,
                  color = BrandSecondary
                )
              }
              Spacer(modifier = Modifier.width(16.dp))
              Column {
                Text(
                  text = "English",
                  fontSize = 20.sp,
                  fontWeight = FontWeight.Bold,
                  color = BrandOnSurface
                )
                Text(
                  text = "Standard English",
                  fontSize = 12.sp,
                  color = MaterialTheme.colorScheme.onSurfaceVariant
                )
              }
            }
            Icon(
              imageVector = Icons.Default.ArrowForward,
              contentDescription = "Navigate to English",
              tint = BrandSecondary
            )
          }
        }

        Spacer(modifier = Modifier.height(48.dp))

        // Quality badges
        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.SpaceAround
        ) {
          Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Icon(Icons.Default.Build, "Certified", tint = BrandSecondary, modifier = Modifier.size(24.dp))
            Spacer(modifier = Modifier.height(4.dp))
            Text("Certified", fontSize = 11.sp, fontWeight = FontWeight.SemiBold, color = BrandSecondary)
          }
          Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Icon(Icons.Default.Verified, "Government", tint = BrandSecondary, modifier = Modifier.size(24.dp))
            Spacer(modifier = Modifier.height(4.dp))
            Text("Government", fontSize = 11.sp, fontWeight = FontWeight.SemiBold, color = BrandSecondary)
          }
          Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Icon(Icons.Default.Security, "Secure", tint = BrandSecondary, modifier = Modifier.size(24.dp))
            Spacer(modifier = Modifier.height(4.dp))
            Text("Secure", fontSize = 11.sp, fontWeight = FontWeight.SemiBold, color = BrandSecondary)
          }
          Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Icon(Icons.Default.Bolt, "Fast Delivery", tint = BrandSecondary, modifier = Modifier.size(24.dp))
            Spacer(modifier = Modifier.height(4.dp))
            Text("Fast Delivery", fontSize = 11.sp, fontWeight = FontWeight.SemiBold, color = BrandSecondary)
          }
        }

        Spacer(modifier = Modifier.weight(1f))

        Text(
          text = "an Initiative by - GOVERNMENT ITI ALLAPALLI\nCreated By AMIT .A WASNIK (INSTRUCTOR COPA)",
          fontSize = 10.sp,
          color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.7f),
          textAlign = TextAlign.Center,
          modifier = Modifier.padding(bottom = 8.dp)
        )
      }
    }
  }
}

// ----------------------------------------------------
// 2. Login Screen
// ----------------------------------------------------
@Composable
fun LoginScreen(viewModel: SkillSevaViewModel, language: Language) {
  val phoneNumber by viewModel.phoneNumber.collectAsState()
  val otpCode by viewModel.otpCode.collectAsState()
  val isOtpSent by viewModel.isOtpSent.collectAsState()

  Box(
    modifier = Modifier
      .fillMaxSize()
      .background(
        Brush.radialGradient(
          colors = listOf(Color(0xFFFFE6D5), BrandBackground),
          radius = 1200f
        )
      )
  ) {
    Column(
      modifier = Modifier
        .fillMaxSize()
        .systemBarsPadding()
        .padding(24.dp),
      horizontalAlignment = Alignment.CenterHorizontally,
      verticalArrangement = Arrangement.Center
    ) {
      // Branding S wrench logo
      AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
          .data("https://lh3.googleusercontent.com/aida-public/AB6AXuAc364h6ckYkRfrTWZl4VYxpbg1TtnovqqIZebS2dLriS0bgZC7RJ32JM7acjYVYvXhp7UrojtuTAm0Q5crWAAMwTTHFkFPuDz0yRYtBxGF1-Xj1LNGlxvs6YU7H525Kfd9pfjdfVy1xrF57NWhwr4A19jMdpfRFXhrJ7tx300Ke79xBLnY-0BH5vycUl7uKyQz-hFn6GJ1e7ZHO9qtSD1aUsbnru3YOBml-TeXXKRVb2PdQjj3w_osB3TRvNe0sSvT_uA7ajDSQCfU")
          .crossfade(true)
          .build(),
        contentDescription = "SkillSeva S-wrench logo",
        modifier = Modifier.size(90.dp),
        contentScale = ContentScale.Fit
      )

      Spacer(modifier = Modifier.height(12.dp))

      Text(
        text = "SkillSeva",
        fontSize = 32.sp,
        fontWeight = FontWeight.ExtraBold,
        color = BrandPrimary,
        letterSpacing = (-0.5).sp
      )

      Text(
        text = "an Initiative by - GOVERNMENT ITI ALLAPALLI",
        fontSize = 11.sp,
        fontWeight = FontWeight.Bold,
        color = BrandSecondary,
        letterSpacing = 1.5.sp,
        modifier = Modifier.padding(top = 4.dp)
      )

      Text(
        text = txt("Production Oriented Scheme (POTS)", "उत्पादनभिमुख प्रशिक्षण योजना", language),
        fontSize = 13.sp,
        color = MaterialTheme.colorScheme.onSurfaceVariant,
        modifier = Modifier.padding(top = 2.dp)
      )

      Spacer(modifier = Modifier.height(32.dp))

      // Card wrapping interactive form
      Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = BrandSurface),
        elevation = CardDefaults.cardElevation(2.dp),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant)
      ) {
        Column(modifier = Modifier.padding(16.dp)) {
          if (!isOtpSent) {
            // Step 1: Mobile input
            Text(
              text = txt("Enter Mobile Number", "मोबाईल क्रमांक प्रविष्ट करा", language),
              fontSize = 14.sp,
              fontWeight = FontWeight.SemiBold,
              color = BrandOnSurfaceVariant
            )

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
              value = phoneNumber,
              onValueChange = { viewModel.setPhoneNumber(it) },
              placeholder = { Text("9876543210") },
              leadingIcon = {
                Text(
                  text = "+91 ",
                  fontWeight = FontWeight.Bold,
                  color = BrandOnSurfaceVariant,
                  modifier = Modifier.padding(start = 12.dp, end = 4.dp)
                )
              },
              keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
              modifier = Modifier
                .fillMaxWidth()
                .testTag("mobile_input"),
              shape = RoundedCornerShape(8.dp),
              singleLine = true
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
              onClick = { viewModel.requestOtp() },
              enabled = phoneNumber.length == 10,
              colors = ButtonDefaults.buttonColors(containerColor = BrandSecondary),
              shape = RoundedCornerShape(8.dp),
              modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
                .testTag("get_otp_button")
            ) {
              Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
              ) {
                Text(txt("Get OTP", "OTP मिळवा", language), fontSize = 16.sp, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.width(8.dp))
                Icon(Icons.Default.ArrowForward, contentDescription = "Get OTP")
              }
            }
          } else {
            // Step 2: OTP Verification
            Row(
              verticalAlignment = Alignment.CenterVertically,
              modifier = Modifier.clickable { viewModel.resetLoginState() }
            ) {
              Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint = BrandSecondary)
              Spacer(modifier = Modifier.width(8.dp))
              Text(
                text = txt("Verify OTP sent to +91 $phoneNumber", "OTP सत्यापित करा (+91 $phoneNumber)", language),
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                color = BrandOnSurfaceVariant
              )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Six simplified digit boxes represented as an OutlinedTextField
            OutlinedTextField(
              value = otpCode,
              onValueChange = { viewModel.setOtpCode(it) },
              placeholder = { Text(txt("Enter 4 or 6 digit OTP", "४ किंवा ६ अंकी OTP टाका", language)) },
              keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
              modifier = Modifier
                .fillMaxWidth()
                .testTag("otp_input"),
              shape = RoundedCornerShape(8.dp),
              singleLine = true
            )

            Spacer(modifier = Modifier.height(12.dp))

            Row(
              modifier = Modifier.fillMaxWidth(),
              horizontalArrangement = Arrangement.SpaceBetween,
              verticalAlignment = Alignment.CenterVertically
            ) {
              Text(
                text = txt("Resend OTP", "पुन्हा पाठवा", language),
                color = BrandSecondary,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.clickable { /* Simulate resend */ }
              )
              Text(
                text = "00:59",
                color = BrandOnSurfaceVariant,
                fontSize = 12.sp
              )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
              onClick = { viewModel.verifyOtp() },
              colors = ButtonDefaults.buttonColors(containerColor = BrandSecondary),
              shape = RoundedCornerShape(8.dp),
              modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
                .testTag("verify_proceed_button")
            ) {
              Text(txt("Verify & Proceed", "सत्यापित करा आणि पुढे जा", language), fontSize = 16.sp, fontWeight = FontWeight.Bold)
            }
          }
        }
      }

      Spacer(modifier = Modifier.height(24.dp))

      // Quick skip bypass button for evaluating the application easily!
      TextButton(
        onClick = { viewModel.skipToDashboard() },
        modifier = Modifier.testTag("skip_button")
      ) {
        Text(
          text = txt("Skip & Explore Dashboard", "थेट मुख्य डॅशबोर्डवर जा", language),
          color = BrandSecondary,
          fontWeight = FontWeight.Bold,
          fontSize = 15.sp,
          textDecoration = androidx.compose.ui.text.style.TextDecoration.Underline
        )
      }

      Spacer(modifier = Modifier.height(24.dp))

      // Alternate system portal login CTA
      Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.clickable { viewModel.navigateTo(Screen.AdminDashboard) }
      ) {
        Icon(Icons.Default.School, contentDescription = "School", tint = BrandSecondary)
        Spacer(modifier = Modifier.width(8.dp))
        Text(
          text = txt("Trainee/Trainer Portal", "प्रशिक्षणार्थी/मार्गदर्शक पोर्टल", language),
          fontSize = 14.sp,
          fontWeight = FontWeight.Bold,
          color = BrandSecondary
        )
      }

      Spacer(modifier = Modifier.height(40.dp))

      // Bottom regulatory credentials
      Row(
        modifier = Modifier.fillMaxWidth(0.9f),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
      ) {
        Row(
          verticalAlignment = Alignment.CenterVertically,
          modifier = Modifier.weight(1f)
        ) {
          AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
              .data("https://lh3.googleusercontent.com/aida-public/AB6AXuDujyRtxeRWV5uL7nayPzCpWCyV_KskcOZk8tW2duNzDHLI4qvzMJfvaJZNfwm7QU-oPWacwcq2gTwt0BtBR8thDCARKrZL3_fuSa43jPeGQnQZd-LzpoKe0onkv29ZO0LBTmg8Z2lQShLRp31ghKC9WZQ4mKDgj9AKeSuUfHPGzpOel97kQ5czmPfElNUgePo4yHpa30sKNrprheflNPtFMJjf4uptcNi0KkndjKNnLVuczBkK5J_eewwe0dMQLAGaVqsN4XbOdGgE")
              .crossfade(true)
              .build(),
            contentDescription = "Govt Crest",
            modifier = Modifier.size(32.dp)
          )
          Spacer(modifier = Modifier.width(8.dp))
          Text(text = "an Initiative by\nGOVERNMENT ITI ALLAPALLI", fontSize = 9.sp, color = BrandOnSurfaceVariant, lineHeight = 11.sp)
        }

        Box(
          modifier = Modifier
            .width(1.dp)
            .height(24.dp)
            .background(MaterialTheme.colorScheme.outlineVariant)
            .padding(horizontal = 8.dp)
        )

        Row(
          verticalAlignment = Alignment.CenterVertically,
          modifier = Modifier.weight(1f)
        ) {
          Spacer(modifier = Modifier.width(8.dp))
          AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
              .data("https://lh3.googleusercontent.com/aida-public/AB6AXuANg2mVO1_vR9gwGemFDhakbEhtx3BUPZxH6B53sCzbI6iWC-ZffyqGApmBlRypn3VTboJbpVOJ-bTWPXZlJjhMoAjDIRssfvEOXxKc-_DuvYkIQfG62N3gCzLZ8Hii2mwxP2O1h6DT958ZEaPlJrfB-QPYXgmb6GsRsarG4wezmucCPZdiIYBXd0OjmgQMQo9Uy9ieWhxKEM3IF8lYFrMG0JGEvrzkw2KAGFc9JjsyVkf_6c_gR-ys1-k88abLZkw1gW276tPnEjCh")
              .crossfade(true)
              .build(),
            contentDescription = "ITI logo",
            modifier = Modifier.size(32.dp)
          )
          Spacer(modifier = Modifier.width(8.dp))
          Text(text = "Skill India ITI", fontSize = 11.sp, color = BrandOnSurfaceVariant, lineHeight = 13.sp)
        }
      }

      Spacer(modifier = Modifier.height(16.dp))

      Text(
        text = "Created By AMIT .A WASNIK (INSTRUCTOR COPA)",
        fontSize = 10.sp,
        color = BrandOnSurfaceVariant,
        textAlign = TextAlign.Center
      )
    }
  }
}

// ----------------------------------------------------
// 3. Customer Dashboard Screen
// ----------------------------------------------------
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(viewModel: SkillSevaViewModel, language: Language) {
  var searchQuery by remember { mutableStateOf("") }
  var showNotifications by remember { mutableStateOf(false) }
  var showNotificationsDialog by remember { mutableStateOf(false) }

  Scaffold(
    topBar = {
      Row(
        modifier = Modifier
          .fillMaxWidth()
          .statusBarsPadding()
          .background(BrandSurface)
          .padding(horizontal = 16.dp, vertical = 12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
          Box(
            modifier = Modifier
              .size(40.dp)
              .clip(CircleShape)
              .border(1.dp, MaterialTheme.colorScheme.outlineVariant, CircleShape),
            contentAlignment = Alignment.Center
          ) {
            AsyncImage(
              model = ImageRequest.Builder(LocalContext.current)
                .data("https://lh3.googleusercontent.com/aida-public/AB6AXuAOJdy7XXXF9P9PDvWKqp2kbfJFBKMJvuHeFmMlwl45F4lpy7ZuC39GlcNlFuE0yMxjDw35gWXtoFmL1Giyu-ZCaT4hS4S09wjuZIy4_k0o8zy64UuSeWXZslWnbpXIdQPXuk-6IOOGa8RcVoEsliJrcBrjD8k818jtlBqd-Cv1ox20S_PASPSkb-J7FiyABAfE7KGl7ok7CbX0tAPYV3pEbyZYesOkcYz1KRs5LtApnvEYGd97bJBmEZHmLZbOhv-wZyDyXaqxkcKu")
                .crossfade(true)
                .build(),
              contentDescription = "Logo",
              modifier = Modifier.fillMaxSize(),
              contentScale = ContentScale.Fit
            )
          }
          Spacer(modifier = Modifier.width(8.dp))
          Text(text = "SkillSeva", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = BrandPrimary)
        }

        Row(verticalAlignment = Alignment.CenterVertically) {
          IconButton(
            onClick = { viewModel.navigateTo(Screen.AdminDashboard) },
            modifier = Modifier.testTag("admin_portal_button")
          ) {
            Icon(Icons.Default.AdminPanelSettings, "Admin Portal", tint = BrandPrimary)
          }

          IconButton(onClick = { viewModel.navigateTo(Screen.LanguageSelection) }) {
            Icon(Icons.Default.Translate, "Languages", tint = BrandPrimary)
          }

          IconButton(onClick = { showNotificationsDialog = true }) {
            Box {
              Icon(Icons.Default.Notifications, "Notifications", tint = BrandPrimary)
              Box(
                modifier = Modifier
                  .size(8.dp)
                  .align(Alignment.TopEnd)
                  .background(BrandPrimaryContainer, CircleShape)
              )
            }
          }
        }
      }
    },
    bottomBar = {
      // Material3 BottomNavigationBar decoration
      NavigationBar(
        containerColor = BrandSurface,
        modifier = Modifier.windowInsetsPadding(WindowInsets.navigationBars)
      ) {
        NavigationBarItem(
          selected = true,
          onClick = { /* Home page */ },
          icon = { Icon(Icons.Default.Home, "Home") },
          label = { Text(txt("Home", "गृह", language)) }
        )
        NavigationBarItem(
          selected = false,
          onClick = { viewModel.navigateTo(Screen.ServiceDetails) },
          icon = { Icon(Icons.Default.Build, "Services") },
          label = { Text(txt("Services", "सेवा", language)) }
        )
        NavigationBarItem(
          selected = false,
          onClick = { viewModel.navigateTo(Screen.AdminDashboard) },
          icon = { Icon(Icons.Default.EventNote, "Bookings") },
          label = { Text(txt("Bookings", "बुकिंग", language)) }
        )
        NavigationBarItem(
          selected = false,
          onClick = { viewModel.navigateTo(Screen.Profile) },
          icon = { Icon(Icons.Default.Person, "Profile") },
          label = { Text(txt("Profile", "प्रोफाईल", language)) }
        )
        NavigationBarItem(
          selected = false,
          onClick = { viewModel.navigateTo(Screen.LanguageSelection) },
          icon = { Icon(Icons.Default.ExitToApp, "Log Out") },
          label = { Text(txt("Log Out", "बाहेर पडा", language)) }
        )
      }
    }
  ) { innerPadding ->
    LazyColumn(
      modifier = Modifier
        .fillMaxSize()
        .padding(innerPadding)
        .background(BrandBackground)
    ) {
      // 1. Search Bar & Location
      item {
        Column(
          modifier = Modifier
            .fillMaxWidth()
            .background(BrandSurface)
            .padding(16.dp)
        ) {
          Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.clickable { /* Select another region */ }
          ) {
            Icon(Icons.Default.LocationOn, "Location", tint = BrandSecondary, modifier = Modifier.size(18.dp))
            Spacer(modifier = Modifier.width(4.dp))
            Text(
              text = "Pune, Maharashtra - 411001",
              fontSize = 14.sp,
              fontWeight = FontWeight.Bold,
              color = BrandSecondary
            )
            Icon(Icons.Default.ExpandMore, "Expand", tint = BrandSecondary, modifier = Modifier.size(18.dp))
          }

          Spacer(modifier = Modifier.height(12.dp))

          OutlinedTextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            placeholder = { Text(txt("Search for 'Electrician' or 'AC Repair'...", "उदा. इलेक्ट्रिशियन किंवा एसी दुरुस्ती शोधा...", language)) },
            leadingIcon = { Icon(Icons.Default.Search, "Search icon") },
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier
              .fillMaxWidth()
              .testTag("search_field"),
            singleLine = true
          )
        }
      }

      // 2. POTS Scheme Government Initiative Banner
      item {
        Card(
          modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
          shape = RoundedCornerShape(16.dp),
          colors = CardDefaults.cardColors(containerColor = BrandSecondary)
        ) {
          Box(modifier = Modifier.fillMaxWidth()) {
            Column(
              modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
            ) {
              Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                  .background(ColorStatusPending, RoundedCornerShape(12.dp))
                  .padding(horizontal = 8.dp, vertical = 4.dp)
              ) {
                Icon(Icons.Default.Verified, "Verify", tint = Color.White, modifier = Modifier.size(12.dp))
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                  text = txt("Government of Maharashtra Initiative", "महाराष्ट्र शासन अधिकृत उपक्रम", language),
                  color = Color.White,
                  fontSize = 10.sp,
                  fontWeight = FontWeight.Bold
                )
              }

              Spacer(modifier = Modifier.height(12.dp))

              Text(
                text = txt("About POTS Scheme", "POTS योजनेबद्दल जाणून घ्या", language),
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
              )

              Spacer(modifier = Modifier.height(6.dp))

              Text(
                text = txt(
                  "Practical On-site Training Scheme (POTS) provides high-quality services through ITI trainees. Expert-supervised work ensuring professional results.",
                  "प्रात्यक्षिक ऑन-साइट प्रशिक्षण योजना (POTS) याद्वारे थेट आयटीआय प्रशिक्षित उमेदवारांकडून तज्ज्ञांच्या देखरेखीखाली दर्जेदार घरगुती सेवा दिली जाते.",
                  language
                ),
                fontSize = 12.sp,
                color = Color.White.copy(alpha = 0.9f),
                lineHeight = 16.sp
              )

              Spacer(modifier = Modifier.height(12.dp))

              LazyRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
              ) {
                item {
                  Button(
                    onClick = { viewModel.navigateTo(Screen.AboutPOTS) },
                    colors = ButtonDefaults.buttonColors(containerColor = BrandPrimary),
                    shape = RoundedCornerShape(8.dp)
                  ) { Text(txt("What is POTS", "POTS काय आहे?", language), fontSize = 12.sp) }
                }
                item {
                  Button(
                    onClick = { viewModel.navigateTo(Screen.AboutUs) },
                    colors = ButtonDefaults.buttonColors(containerColor = BrandPrimary),
                    shape = RoundedCornerShape(8.dp)
                  ) { Text(txt("About Us", "आमच्याबद्दल", language), fontSize = 12.sp) }
                }
                item {
                  Button(
                    onClick = { viewModel.navigateTo(Screen.POTSDocument) },
                    colors = ButtonDefaults.buttonColors(containerColor = BrandPrimary),
                    shape = RoundedCornerShape(8.dp)
                  ) { Text(txt("POTS PDF", "शासन निर्णय", language), fontSize = 12.sp) }
                }
                item {
                  Button(
                    onClick = { viewModel.navigateTo(Screen.Profile) },
                    colors = ButtonDefaults.buttonColors(containerColor = BrandPrimary),
                    shape = RoundedCornerShape(8.dp)
                  ) { Text(txt("My Account", "माझे खाते", language), fontSize = 12.sp) }
                }
              }
            }
          }
        }
      }

      // 3. Special Offers section
      item {
        Column(modifier = Modifier.padding(horizontal = 16.dp)) {
          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
          ) {
            Text(
              text = txt("Special Offers", "तुमच्यासाठी खास सवलत", language),
              fontSize = 18.sp,
              fontWeight = FontWeight.Bold,
              color = BrandPrimary
            )
            Text(
              text = txt("View All", "सर्व पहा", language),
              fontSize = 12.sp,
              fontWeight = FontWeight.Bold,
              color = BrandSecondary,
              modifier = Modifier.clickable { /* action */ }
            )
          }

          Spacer(modifier = Modifier.height(10.dp))

          LazyRow(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.fillMaxWidth()
          ) {
            // Offer card 1
            item {
              Row(
                modifier = Modifier
                  .width(280.dp)
                  .background(BrandPrimary.copy(alpha = 0.08f), RoundedCornerShape(12.dp))
                  .border(1.dp, BrandPrimary.copy(alpha = 0.2f), RoundedCornerShape(12.dp))
                  .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
              ) {
                Column {
                  Text("FIRST BOOKING", fontSize = 11.sp, color = BrandPrimary, fontWeight = FontWeight.Bold)
                  Text("₹150 OFF", fontSize = 22.sp, fontWeight = FontWeight.Black, color = BrandOnSurface)
                  Text("On your first ITI service", fontSize = 11.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
                }
                Icon(Icons.Default.ConfirmationNumber, "ticket", tint = BrandPrimary, modifier = Modifier.size(44.dp))
              }
            }

            // Offer card 2
            item {
              Row(
                modifier = Modifier
                  .width(280.dp)
                  .background(BrandSecondary.copy(alpha = 0.08f), RoundedCornerShape(12.dp))
                  .border(1.dp, BrandSecondary.copy(alpha = 0.2f), RoundedCornerShape(12.dp))
                  .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
              ) {
                Column {
                  Text("COMBO DEAL", fontSize = 11.sp, color = BrandSecondary, fontWeight = FontWeight.Bold)
                  Text("20% OFF", fontSize = 22.sp, fontWeight = FontWeight.Black, color = BrandOnSurface)
                  Text("Home Maintenance Kit", fontSize = 11.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
                }
                Icon(Icons.Default.HomeRepairService, "bag", tint = BrandSecondary, modifier = Modifier.size(44.dp))
              }
            }
          }
        }
      }

      // 4. All Services Section (Interactive Grid)
      item {
        Spacer(modifier = Modifier.height(24.dp))
        Text(
          text = txt("All Services", "सर्व सेवांचे पर्याय", language),
          fontSize = 18.sp,
          fontWeight = FontWeight.Bold,
          color = BrandPrimary,
          modifier = Modifier.padding(horizontal = 16.dp)
        )
        Spacer(modifier = Modifier.height(12.dp))
      }

      // Set explicit items representation
      val servicesList = listOf(
        Triple("Electrician", txt("Electrician", "इलेक्ट्रिशियन", language), Icons.Default.Bolt),
        Triple("Wireman", txt("Wireman", "वायरमन", language), Icons.Default.ElectricBolt),
        Triple("MMV", txt("Mechanic Motor", "मेकॅनिक मोटर", language), Icons.Default.DirectionsCar),
        Triple("Fitter", txt("Fitter", "फिटर", language), Icons.Default.BuildCircle),
        Triple("Turner", txt("Turner", "टर्नर", language), Icons.Default.PrecisionManufacturing),
        Triple("Welder", txt("Welder", "वेल्डर", language), Icons.Default.Hardware),
        Triple("COPA", txt("COPA / Computer", "कोपा / संगणक", language), Icons.Default.Computer),
        Triple("Dress Making", txt("Dress Making", "ड्रेस मेकिंग", language), Icons.Default.Checkroom),
        Triple("Carpenter", txt("Carpenter", "सुतारकाम", language), Icons.Default.Handyman),
        Triple("Tractor Mech", txt("Tractor Mech", "ट्रॅक्टर मेकॅनिक", language), Icons.Default.Agriculture),
        Triple("Mason", txt("Mason", "गवंडी / बांधकाम", language), Icons.Default.Foundation),
        Triple("Plumber", txt("Plumber", "प्लंबर", language), Icons.Default.Plumbing)
      )

      item {
        val filteredList = servicesList.filter { 
          it.first.contains(searchQuery, ignoreCase = true) || 
          it.second.contains(searchQuery, ignoreCase = true) 
        }
        val chunkedList = filteredList.chunked(2)

        Column(
          modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
          verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
          if (filteredList.isEmpty()) {
            Text(txt("No services found.", "कोणतीही सेवा आढळली नाही.", language), color = BrandOnSurfaceVariant)
          }
          
          for (rowItems in chunkedList) {
            Row(
              modifier = Modifier.fillMaxWidth(),
              horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
              val first = rowItems[0]

              // First card
              Card(
                modifier = Modifier
                  .weight(1f)
                  .clickable { viewModel.selectService(first.first) }
                  .testTag("service_card_${first.first.lowercase()}"),
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = BrandSurface),
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant)
              ) {
                Column(
                  modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                  horizontalAlignment = Alignment.CenterHorizontally
                ) {
                  Box(
                    modifier = Modifier
                      .size(56.dp)
                      .background(BrandBackground, CircleShape),
                    contentAlignment = Alignment.Center
                  ) {
                    Icon(first.third, first.second, tint = BrandPrimary, modifier = Modifier.size(28.dp))
                  }
                  Spacer(modifier = Modifier.height(8.dp))
                  Text(first.second, fontSize = 14.sp, fontWeight = FontWeight.Bold, color = BrandOnSurface)
                  Spacer(modifier = Modifier.height(4.dp))
                  Text(
                    text = "ITI CERTIFIED",
                    fontSize = 9.sp,
                    fontWeight = FontWeight.Bold,
                    color = ColorCertificationGold,
                    modifier = Modifier
                      .background(ColorCertificationGold.copy(alpha = 0.1f), RoundedCornerShape(4.dp))
                      .padding(horizontal = 6.dp, vertical = 2.dp)
                  )
                }
              }

              // Second card or empty space
              if (rowItems.size > 1) {
                val second = rowItems[1]
                Card(
                  modifier = Modifier
                    .weight(1f)
                    .clickable { viewModel.selectService(second.first) }
                    .testTag("service_card_${second.first.lowercase()}"),
                  shape = RoundedCornerShape(12.dp),
                  colors = CardDefaults.cardColors(containerColor = BrandSurface),
                  border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant)
                ) {
                  Column(
                    modifier = Modifier
                      .fillMaxWidth()
                      .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                  ) {
                    Box(
                      modifier = Modifier
                        .size(56.dp)
                        .background(BrandBackground, CircleShape),
                      contentAlignment = Alignment.Center
                    ) {
                      Icon(second.third, second.second, tint = BrandPrimary, modifier = Modifier.size(28.dp))
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(second.second, fontSize = 14.sp, fontWeight = FontWeight.Bold, color = BrandOnSurface)
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                      text = "ITI CERTIFIED",
                      fontSize = 9.sp,
                      fontWeight = FontWeight.Bold,
                      color = ColorCertificationGold,
                      modifier = Modifier
                        .background(ColorCertificationGold.copy(alpha = 0.1f), RoundedCornerShape(4.dp))
                        .padding(horizontal = 6.dp, vertical = 2.dp)
                    )
                  }
                }
              } else {
                Spacer(modifier = Modifier.weight(1f))
              }
            }
          }
        }
      }

      // Featured Bento Grid Cards
      item {
        Spacer(modifier = Modifier.height(24.dp))
        Text(
          text = txt("Why Trust SkillSeva?", "SkillSeva का निवडावी?", language),
          fontSize = 18.sp,
          fontWeight = FontWeight.Bold,
          color = BrandPrimary,
          modifier = Modifier.padding(horizontal = 16.dp)
        )
        Spacer(modifier = Modifier.height(12.dp))

        Row(
          modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .height(180.dp),
          horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
          // Left Bento: image banner
          Box(
            modifier = Modifier
              .weight(1.3f)
              .clip(RoundedCornerShape(12.dp))
          ) {
            AsyncImage(
              model = ImageRequest.Builder(LocalContext.current)
                .data("https://lh3.googleusercontent.com/aida-public/AB6AXuARfNEBy4z7lCVwByqslx8Rum2-HNflx0hEfP0ilzmHMfvNXZH-boV3zluwjR2QxlFXnK817cI70ALs4ajACUZakPQZen16ivOd-XtYVOgJJmwKGdz-1h7bMj6lM9v6tbgypRzULxJWqPuVyE_rzkT8O6O102-6lXO27Hzf6fmSf2xDD6bS3xro8LqoaDza1FLjjqpj7PEpYV5kLWQcWTM_7EcXe6M4XeAN_HQITr-8GNDnkgwPF9Ahtz8lZsktMHlhSz11WlFxravs")
                .crossfade(true)
                .build(),
              contentDescription = "Safe wiring",
              modifier = Modifier.fillMaxSize(),
              contentScale = ContentScale.Crop
            )
            Box(
              modifier = Modifier
                .fillMaxSize()
                .background(Brush.verticalGradient(colors = listOf(Color.Transparent, Color.Black.copy(alpha = 0.8f))))
            )
            Column(
              modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(12.dp)
            ) {
              Text(
                text = txt("Fixed Pricing", "ठोक दर निश्चित शुल्क", language),
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp
              )
              Text(
                text = txt("Verified, transparent fees.", "कोणतेही छुपे खर्च नाहीत.", language),
                color = Color.White.copy(alpha = 0.8f),
                fontSize = 11.sp
              )
            }
          }

          // Right Bento: secure policy text card
          Card(
            modifier = Modifier.weight(1f),
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(containerColor = BrandSecondary),
          ) {
            Column(
              modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
              verticalArrangement = Arrangement.SpaceBetween
            ) {
              Icon(Icons.Default.Shield, "Safety", tint = Color.White, modifier = Modifier.size(28.dp))
              Column {
                Text(
                  text = txt("Safe & Insured", "सुरक्षित व खात्रीशीर", language),
                  color = Color.White,
                  fontWeight = FontWeight.Bold,
                  fontSize = 14.sp
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                  text = txt("Background checked trainees.", "पोलीस चौकशी केलेले प्रशिक्षणार्थी.", language),
                  color = Color.White.copy(alpha = 0.8f),
                  fontSize = 10.sp,
                  lineHeight = 12.sp
                )
              }
            }
          }
        }
        Spacer(modifier = Modifier.height(24.dp))
      }
    }
  }
}

// ----------------------------------------------------
// 4. Electrician Service Details Screen
// ----------------------------------------------------
@Composable
fun ServiceDetailsScreen(viewModel: SkillSevaViewModel, language: Language) {
  val selectedService by viewModel.selectedService.collectAsState()

  Scaffold(
    topBar = {
      Row(
        modifier = Modifier
          .fillMaxWidth()
          .statusBarsPadding()
          .background(BrandSurface)
          .padding(horizontal = 8.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
      ) {
        IconButton(
          onClick = { viewModel.navigateBack() },
          modifier = Modifier.testTag("details_back_button")
        ) {
          Icon(Icons.AutoMirrored.Filled.ArrowBack, "Back", tint = BrandPrimary)
        }
        Spacer(modifier = Modifier.width(4.dp))
        AsyncImage(
          model = ImageRequest.Builder(LocalContext.current)
            .data("https://lh3.googleusercontent.com/aida-public/AB6AXuBFgimjQO2FCiQ6HBUuVGnL9dNbZx7KrmHX9pqcSyGCk862Z6EJZP6H-sNWNIiD4yvAcwJHo9gwvKloM39mr5n5w7nd2wZTAv1c9wMA1UYklyGW1yBrOtF1VGMyrJ4_s-jZWy22RHvE_jvUY9dPN8iTQxhIRQj2NPE-R8yxlCYf_Icqfdt4qHwifMOLikrZ-OIbcxfNYSB1S0NQvddtm7E0UQ0Z0NKrwb49SWV7pISIhcWyGZkVqyU6amJCmVNmmaIKYezLRknC44Au")
            .crossfade(true)
            .build(),
          contentDescription = "Logo",
          modifier = Modifier.size(32.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
          text = "SkillSeva",
          fontSize = 18.sp,
          fontWeight = FontWeight.Bold,
          color = BrandPrimary
        )
      }
    },
    bottomBar = {
      // Bottom sticky bar for CTA
      Surface(
        tonalElevation = 8.dp,
        color = BrandSurface,
        modifier = Modifier.windowInsetsPadding(WindowInsets.navigationBars)
      ) {
        Row(
          modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
          horizontalArrangement = Arrangement.SpaceBetween,
          verticalAlignment = Alignment.CenterVertically
        ) {
          Column {
            Text(
              text = txt("Total Estimated Price", "एकूण अंदाजे शुल्क", language),
              fontSize = 11.sp,
              color = BrandOnSurfaceVariant
            )
            Text(
              text = "₹249",
              fontSize = 24.sp,
              fontWeight = FontWeight.Black,
              color = BrandOnSurface
            )
          }

          Button(
            onClick = { viewModel.navigateTo(Screen.Schedule) },
            colors = ButtonDefaults.buttonColors(containerColor = BrandPrimaryContainer),
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier
              .height(48.dp)
              .testTag("select_date_time_button")
          ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
              Text(
                text = txt("Select Date & Time", "तारीख आणि वेळ निवडा", language),
                fontSize = 15.sp,
                fontWeight = FontWeight.ExtraBold,
                color = Color.White
              )
              Spacer(modifier = Modifier.width(8.dp))
              Icon(Icons.Default.CalendarMonth, "calendar", tint = Color.White, modifier = Modifier.size(18.dp))
            }
          }
        }
      }
    }
  ) { innerPadding ->
    LazyColumn(
      modifier = Modifier
        .fillMaxSize()
        .padding(innerPadding)
        .background(BrandBackground)
    ) {
      // 1. Hero Image
      item {
        Box(
          modifier = Modifier
            .fillMaxWidth()
            .height(220.dp)
        ) {
          AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
              .data("https://lh3.googleusercontent.com/aida-public/AB6AXuBDvi0e1cRXXSWU5e6KfusaompaHJIHf5Hi5wNB3eqXqdjcBATxg86VktaeF8gw48WZhc3MSMUruR_dxhKHs4hRnisdIBpc3nYEPe9BIxWEvgoZhn1bO8uGTQ2be4M8ijbzfuBbCGqqbcMcBx8yTRFjwXWKUmbSe_iK1nQlgNIlqvXsSiOjdXjjdxynZrtb-2t62eLc-0BKlc4IUj5inAi-v8hego5aOgH4BjQgg0p4vFEqoloNA-MRuey_vBMxwn3M9CLgmd9kNxjL")
              .crossfade(true)
              .build(),
            contentDescription = "Electrician Working",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
          )
          Box(
            modifier = Modifier
              .fillMaxSize()
              .background(Brush.verticalGradient(colors = listOf(Color.Transparent, Color.Black.copy(alpha = 0.8f))))
          )
          Column(
            modifier = Modifier
              .align(Alignment.BottomStart)
              .padding(16.dp)
          ) {
            Row(
              modifier = Modifier
                .background(ColorCertificationGold, RoundedCornerShape(4.dp))
                .padding(horizontal = 6.dp, vertical = 2.dp),
              verticalAlignment = Alignment.CenterVertically
            ) {
              Icon(Icons.Default.VerifiedUser, "verified", tint = Color.White, modifier = Modifier.size(10.dp))
              Spacer(modifier = Modifier.width(4.dp))
              Text("ITI CERTIFIED", fontSize = 9.sp, fontWeight = FontWeight.Bold, color = Color.White)
            }
            Spacer(modifier = Modifier.height(4.dp))
            Text(
              text = txt("$selectedService Service", "कम्प्लीट $selectedService सर्व्हिस", language),
              fontSize = 24.sp,
              fontWeight = FontWeight.Bold,
              color = Color.White
            )
          }
        }
      }

      // 2. Pricing and Guarantee Card
      item {
        Card(
          modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
          colors = CardDefaults.cardColors(containerColor = BrandSurface),
          border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant)
        ) {
          Row(
            modifier = Modifier
              .fillMaxWidth()
              .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
          ) {
            Column(modifier = Modifier.weight(1f)) {
              Text(
                text = txt("Fixed Price Reliability", "पारदर्शक निश्चित दर", language),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = BrandPrimary
              )
              Spacer(modifier = Modifier.height(4.dp))
              Text(
                text = txt(
                  "Professional electrical maintenance and repairs by government-vetted trainees. Guaranteed safety standards for your home.",
                  "शासकीय देखरेखीखाली प्रशिक्षण घेतलेल्या आयटीआय प्रशिक्षणार्थींकडून सुरक्षित आणि अचूक काम.",
                  language
                ),
                fontSize = 12.sp,
                color = BrandOnSurfaceVariant,
                lineHeight = 16.sp
              )
              Spacer(modifier = Modifier.height(12.dp))
              Row {
                Row(verticalAlignment = Alignment.CenterVertically) {
                  Icon(Icons.Default.CheckCircle, "check", tint = ColorSuccessGreen, modifier = Modifier.size(14.dp))
                  Spacer(modifier = Modifier.width(4.dp))
                  Text("45-min Arrival", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = ColorSuccessGreen)
                }
                Spacer(modifier = Modifier.width(16.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                  Icon(Icons.Default.CheckCircle, "check", tint = ColorSuccessGreen, modifier = Modifier.size(14.dp))
                  Spacer(modifier = Modifier.width(4.dp))
                  Text("30-day Warranty", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = ColorSuccessGreen)
                }
              }
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column(
              horizontalAlignment = Alignment.CenterHorizontally,
              modifier = Modifier
                .background(BrandSurfaceContainerLow, RoundedCornerShape(8.dp))
                .padding(12.dp)
            ) {
              Text("Govt. Rate", fontSize = 10.sp, color = BrandOnSurfaceVariant)
              Text("₹249", fontSize = 22.sp, fontWeight = FontWeight.Black, color = BrandOnSurface)
              Text("/ visit", fontSize = 10.sp, color = BrandOnSurfaceVariant)
            }
          }
        }
      }

      // 3. What's Included details list
      item {
        Card(
          modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
          colors = CardDefaults.cardColors(containerColor = BrandSurface),
          border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant)
        ) {
          Column(modifier = Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
              Icon(Icons.Default.Checklist, "checklist", tint = BrandSecondary)
              Spacer(modifier = Modifier.width(8.dp))
              Text(
                text = txt("What's Included", "या सेवेमध्ये काय समाविष्ट आहे?", language),
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = BrandSecondary
              )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Generate dynamic lists based on POTS Prapatra 1 Rules
            val serviceInclusions = when (selectedService) {
              "Electrician", "Wireman" -> listOf(
                Pair(txt("Wiring & Repair", "वायरिंग आणि दुरुस्ती", language), txt("All types of electrical wiring and switchboard repairs.", "वायरिंगची कामे आणि विद्युत संच मांडणी.", language)),
                Pair(txt("Appliance Repair", "विद्युत उपकरणांची दुरुस्ती", language), txt("Repairing of fans, lights, and home appliances.", "पंखा, लाईट आणि घरातील विद्युत उपकरणांची दुरुस्ती.", language)),
                Pair(txt("Motor Repair", "मोटारची दुरुस्ती", language), txt("Rewinding and maintenance of electrical motors.", "विद्युत मोटारची दुरुस्ती आणि वाइंडिंगची कामे.", language))
              )
              "Plumber" -> listOf(
                Pair(txt("Pipe Repair", "नळ दुरुस्ती", language), txt("Fixing leakages, broken pipes, and water blockages.", "पाईपची गळती आणि नळ दुरुस्तीची कामे.", language)),
                Pair(txt("Sanitary Fittings", "स्वच्छता गृह व्यवस्था", language), txt("Installing sinks, toilets, and bathroom fittings.", "स्वच्छता गृह व पिण्याच्या पाण्याची व्यवस्था.", language)),
                Pair(txt("Drainage Work", "ड्रेनेज दुरुस्ती", language), txt("Clearing out and repairing drainage systems.", "ड्रेनेज आणि सांडपाणी व्यवस्था दुरुस्ती.", language))
              )
              "Welder", "Fitter", "Turner" -> listOf(
                Pair(txt("Metal Furniture", "लोखंडी फर्निचर", language), txt("Making and repairing iron furniture and racks.", "लोखंडी फर्निचर तयार करणे आणि दुरुस्ती.", language)),
                Pair(txt("Grill Works", "ग्रील बसविणे", language), txt("Creating gates, grills, and window frames.", "दारे, खिडक्या आणि ग्रील तयार करून बसविणे.", language)),
                Pair(txt("Fabrication", "फॅब्रिकेशन वर्कस", language), txt("All types of structural metal fabrication works.", "सर्व प्रकारची वेल्डिंग आणि फॅब्रिकेशनची कामे.", language))
              )
              "Carpenter" -> listOf(
                Pair(txt("Furniture Making", "लाकडी फर्निचर बनविणे", language), txt("Custom wood furniture, cabinets, and tables.", "तुमच्या आवडीनुसार लाकडी फर्निचर बनविणे.", language)),
                Pair(txt("Doors & Windows", "दरवाजे आणि खिडक्या", language), txt("Installing and repairing wooden doors/windows.", "दरवाजे, खिडक्या आणि चौकटी बनविणे व दुरुस्ती.", language)),
                Pair(txt("Computer Lab Setup", "कॉम्प्युटर रुम तयार करणे", language), txt("Woodwork for labs and aluminum partitions.", "कॉम्प्युटर रुम आणि ॲल्युमिनियम पार्टिशन.", language))
              )
              "MMV", "Tractor Mech" -> listOf(
                Pair(txt("Vehicle Servicing", "वाहनांची निगा व दुरुस्ती", language), txt("Complete engine and body servicing of vehicles.", "वाहनांची नियमित सर्व्हिसिंग आणि देखभाल.", language)),
                Pair(txt("Washing & Cleaning", "वॉशिंग आणि क्लिनिंग", language), txt("Detailed professional vehicle washing.", "शासकीय वॉशिंग सेंटरद्वारे वाहनांची धुलाई.", language)),
                Pair(txt("PUC & Testing", "पीयुसी टेस्टिंग", language), txt("Vehicle pollution check and diagnostics.", "वाहनांची पीयुसी (PUC) टेस्टिंग व इतर तपासणी.", language))
              )
              "Dress Making" -> listOf(
                Pair(txt("Uniform Stitching", "गणवेष तयार करणे", language), txt("School and industrial uniform stitching.", "शाळेचे आणि कारखान्यांचे गणवेष शिवून देणे.", language)),
                Pair(txt("Dungaree Making", "डांगरी तयार करणे", language), txt("Professional protective workwear.", "कामासाठी आवश्यक डांगरी तयार करणे.", language)),
                Pair(txt("General Alteration", "कपड्यांना शिलाई करणे", language), txt("All types of sewing and clothes alteration.", "कपड्यांची शिलाई व दुरुस्तीची सर्व प्रकारची कामे.", language))
              )
              "Mason" -> listOf(
                Pair(txt("Building Repair", "इमारत बांधकाम दुरुस्ती", language), txt("General masonry and wall repairs.", "भिंत आणि इमारत बांधकाम दुरुस्तीची कामे.", language)),
                Pair(txt("Plastering Work", "प्लास्टरची कामे", language), txt("Internal and external wall plastering.", "भिंतींचे अंतर्गत आणि बाह्य प्लास्टर करणे.", language)),
                Pair(txt("Tiles & Flooring", "फरशी बसविणे", language), txt("Fixing floor tiles and bathroom flooring.", "घरात आणि बाथरूममध्ये फरशी बसविणे.", language))
              )
              "COPA" -> listOf(
                Pair(txt("Computer Training", "संगणक प्रशिक्षण", language), txt("Basic computer skills and software training.", "कर्मचाऱ्यांचे आणि नागरिकांचे संगणक प्रशिक्षण.", language)),
                Pair(txt("Data Entry", "संगणक डेटा एंट्री", language), txt("Fast and accurate documentation work.", "डेटा एंट्रीची कामे आणि टायपिंग.", language)),
                Pair(txt("Software Installation", "सॉफ्टवेअर तयार करणे", language), txt("Installing OS, antivirus, and basic programs.", "सॉफ्टवेअर लोड करणे आणि संगणक वापरावेळी मार्गदर्शन.", language))
              )
              else -> listOf(
                Pair(txt("Professional Service", "व्यावसायिक सेवा", language), txt("Standard ITI certified service execution.", "आयटीआय प्रमाणित उत्कृष्ट सेवा.", language)),
                Pair(txt("Quality Parts", "दर्जेदार साहित्य", language), txt("Use of durable and verified materials.", "कामात दर्जेदार आणि टिकाऊ साहित्याचा वापर.", language)),
                Pair(txt("Safety Ensured", "सुरक्षा हमी", language), txt("Follows all standard safety protocols.", "कामाच्या ठिकाणी सुरक्षिततेची पूर्ण काळजी.", language))
              )
            }

            serviceInclusions.forEach { inclusion ->
              Row(verticalAlignment = Alignment.Top) {
                Text("•", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = BrandPrimary)
                Spacer(modifier = Modifier.width(8.dp))
                Column {
                  Text(inclusion.first, fontSize = 14.sp, fontWeight = FontWeight.Bold, color = BrandOnSurface)
                  Text(inclusion.second, fontSize = 11.sp, color = BrandOnSurfaceVariant)
                }
              }
              Spacer(modifier = Modifier.height(12.dp))
            }
          }
        }
      }

      // 4. Trainee Professional Profile
      item {
        Card(
          modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
          colors = CardDefaults.cardColors(containerColor = BrandSecondary)
        ) {
          Row(
            modifier = Modifier
              .fillMaxWidth()
              .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
          ) {
            AsyncImage(
              model = ImageRequest.Builder(LocalContext.current)
                .data("https://lh3.googleusercontent.com/aida-public/AB6AXuCID21nwQCR2ZmHIlC7GwD6Jzf-ZBSyCXGrVkP5QLI9OH3XiKO6VannECMPouQ__QbQ5yb17EcQTTu6awiXW5oF3cEUauN36DRCSIfpr1BpUur2SbcvXwp4npFBjcQZQWx0hMKKMupd1M6HKGU8Gn7E31Gekb4V2ylDx4-tyJdlpMxbzPb9rhunLvSYQaQdEva_2azuGg3y-EDvDz5yfEe0xJQWma5Z9vDtCW3kKXRk5N-c815JtanH9o1MyeIlQg1TxWsDRd8HOfc-")
                .crossfade(true)
                .build(),
              contentDescription = "Amit Kulkarni",
              modifier = Modifier
                .size(70.dp)
                .clip(CircleShape)
                .border(2.dp, Color.White.copy(alpha = 0.5f), CircleShape),
              contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column {
              Text(
                text = "Amit Kulkarni",
                color = Color.White,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
              )
              Text(
                text = "ITI Electrician Trade • 2nd Year",
                color = Color.White.copy(alpha = 0.9f),
                fontSize = 12.sp
              )
              Spacer(modifier = Modifier.height(4.dp))
              Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.Star, "star", tint = Color.Yellow, modifier = Modifier.size(14.dp))
                Spacer(modifier = Modifier.width(4.dp))
                Text("4.9 (124 reviews)", color = Color.White, fontSize = 11.sp, fontWeight = FontWeight.Bold)
              }
            }
          }
        }
      }

      // 5. Safety Protocols
      item {
        Card(
          modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
          colors = CardDefaults.cardColors(containerColor = BrandSurfaceContainerLow)
        ) {
          Column(modifier = Modifier.padding(16.dp)) {
            Text(
              text = "SKILLSEVA SAFETY PROTOCOLS",
              fontSize = 11.sp,
              fontWeight = FontWeight.Bold,
              color = BrandSecondary,
              letterSpacing = 1.sp
            )
            Spacer(modifier = Modifier.height(10.dp))
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
              Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Icon(Icons.Default.Badge, "Verified", tint = BrandPrimary, modifier = Modifier.size(20.dp))
                Text("ID Verified", fontSize = 10.sp, color = BrandOnSurface)
              }
              Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Icon(Icons.Default.VerifiedUser, "Safety", tint = BrandPrimary, modifier = Modifier.size(20.dp))
                Text("Safe Check", fontSize = 10.sp, color = BrandOnSurface)
              }
              Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Icon(Icons.Default.Contactless, "No-Contact", tint = BrandPrimary, modifier = Modifier.size(20.dp))
                Text("Online Pay", fontSize = 10.sp, color = BrandOnSurface)
              }
              Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Icon(Icons.Default.Book, "Certified", tint = BrandPrimary, modifier = Modifier.size(20.dp))
                Text("Certified", fontSize = 10.sp, color = BrandOnSurface)
              }
            }
          }
        }
        Spacer(modifier = Modifier.height(40.dp))
      }
    }
  }
}

// ----------------------------------------------------
// 5. Schedule Service Screen
// ----------------------------------------------------
@Composable
fun ScheduleScreen(viewModel: SkillSevaViewModel, language: Language) {
  val selectedDate by viewModel.selectedDate.collectAsState()
  val selectedTimeSlot by viewModel.selectedTimeSlot.collectAsState()

  Scaffold(
    topBar = {
      Row(
        modifier = Modifier
          .fillMaxWidth()
          .statusBarsPadding()
          .background(BrandSurface)
          .padding(horizontal = 8.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
      ) {
        IconButton(
          onClick = { viewModel.navigateBack() },
          modifier = Modifier.testTag("schedule_back_button")
        ) {
          Icon(Icons.AutoMirrored.Filled.ArrowBack, "Back", tint = BrandPrimary)
        }
        Text(
          text = txt("Schedule Service", "वेळ निश्चित करा", language),
          fontSize = 18.sp,
          fontWeight = FontWeight.Bold,
          color = BrandPrimary
        )
      }
    },
    bottomBar = {
      Surface(
        tonalElevation = 8.dp,
        color = BrandSurface,
        modifier = Modifier.windowInsetsPadding(WindowInsets.navigationBars)
      ) {
        Row(
          modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
          horizontalArrangement = Arrangement.SpaceBetween,
          verticalAlignment = Alignment.CenterVertically
        ) {
          Column {
            Text(txt("Selected slot", "निवडलेली वेळ", language), fontSize = 11.sp, color = BrandOnSurfaceVariant)
            Text(
              text = "$selectedDate • $selectedTimeSlot",
              fontSize = 13.sp,
              fontWeight = FontWeight.Bold,
              color = BrandOnSurface
            )
          }

          Button(
            onClick = { viewModel.navigateTo(Screen.BookingSummary) },
            colors = ButtonDefaults.buttonColors(containerColor = BrandPrimary),
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier
              .height(48.dp)
              .testTag("schedule_continue_button")
          ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
              Text(txt("Continue", "पुढे जा", language), fontSize = 15.sp, fontWeight = FontWeight.Bold)
              Spacer(modifier = Modifier.width(4.dp))
              Icon(Icons.AutoMirrored.Filled.ArrowForward, "Continue")
            }
          }
        }
      }
    }
  ) { innerPadding ->
    LazyColumn(
      modifier = Modifier
        .fillMaxSize()
        .padding(innerPadding)
        .background(BrandBackground)
    ) {
      // Step indicator
      item {
        Row(
          modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp),
          horizontalArrangement = Arrangement.Center
        ) {
          Text("1. Schedule  ", fontSize = 13.sp, color = BrandPrimary, fontWeight = FontWeight.Bold)
          Text(">  2. Summary  ", fontSize = 13.sp, color = BrandSecondary.copy(alpha = 0.5f))
          Text(">  3. Pay", fontSize = 13.sp, color = BrandSecondary.copy(alpha = 0.5f))
        }
      }

      // Date cards slider heading
      item {
        Row(
          modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
          horizontalArrangement = Arrangement.SpaceBetween,
          verticalAlignment = Alignment.Bottom
        ) {
          Text(
            text = txt("Select Date", "तारीख निवडा", language),
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = BrandOnSurface
          )
          Text("October 2026", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = BrandSecondary)
        }
        Spacer(modifier = Modifier.height(12.dp))
      }

      // Calendar dates row
      val datesList = listOf("Mon, 12 Oct", "Tue, 13 Oct", "Wed, 14 Oct", "Thu, 15 Oct", "Fri, 16 Oct")
      item {
        LazyRow(
          contentPadding = PaddingValues(horizontal = 16.dp),
          horizontalArrangement = Arrangement.spacedBy(8.dp),
          modifier = Modifier.fillMaxWidth()
        ) {
          items(datesList) { dateItem ->
            val isSelected = selectedDate == dateItem
            Card(
              modifier = Modifier
                .clickable { viewModel.selectDate(dateItem) }
                .testTag("date_card_${dateItem.split(",")[0].lowercase()}"),
              shape = RoundedCornerShape(12.dp),
              colors = CardDefaults.cardColors(
                containerColor = if (isSelected) BrandPrimary.copy(alpha = 0.1f) else BrandSurface
              ),
              border = BorderStroke(1.dp, if (isSelected) BrandPrimary else MaterialTheme.colorScheme.outlineVariant)
            ) {
              Column(
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
              ) {
                val pieces = dateItem.split(" ")
                Text(
                  text = pieces[0].replace(",", ""),
                  fontSize = 12.sp,
                  color = if (isSelected) BrandPrimary else BrandOnSurfaceVariant,
                  fontWeight = FontWeight.Bold
                )
                Text(
                  text = pieces[1],
                  fontSize = 20.sp,
                  fontWeight = FontWeight.ExtraBold,
                  color = BrandOnSurface
                )
              }
            }
          }
        }
        Spacer(modifier = Modifier.height(24.dp))
      }

      // Time slots morning
      item {
        Text(
          text = txt("☀️ Morning Slots", "☀️ सकाळची वेळ", language),
          fontSize = 14.sp,
          fontWeight = FontWeight.Bold,
          color = BrandSecondary,
          modifier = Modifier.padding(horizontal = 16.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
      }

      val morningSlots = listOf("09:00 AM", "10:00 AM", "11:00 AM")
      item {
        Row(
          modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
          horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
          for (slot in morningSlots) {
            val isSelected = selectedTimeSlot == slot
            Box(
              modifier = Modifier
                .weight(1f)
                .clickable { viewModel.selectTimeSlot(slot) }
                .background(
                  if (isSelected) BrandPrimary else BrandSurface,
                  RoundedCornerShape(8.dp)
                )
                .border(
                  1.dp,
                  if (isSelected) BrandPrimary else MaterialTheme.colorScheme.outlineVariant,
                  RoundedCornerShape(8.dp)
                )
                .padding(vertical = 14.dp)
                .testTag("time_slot_${slot.replace(" ", "_").replace(":", "_").lowercase()}"),
              contentAlignment = Alignment.Center
            ) {
              Text(
                text = slot,
                fontSize = 13.sp,
                color = if (isSelected) Color.White else BrandOnSurface,
                fontWeight = FontWeight.SemiBold
              )
            }
          }
        }
        Spacer(modifier = Modifier.height(16.dp))
      }

      // Afternoon slots
      item {
        Text(
          text = txt("☀️ Afternoon Slots", "☀️ दुपारची वेळ", language),
          fontSize = 14.sp,
          fontWeight = FontWeight.Bold,
          color = BrandSecondary,
          modifier = Modifier.padding(horizontal = 16.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
      }

      val afternoonSlotsRow1 = listOf("12:00 PM", "01:00 PM", "02:00 PM")
      item {
        Row(
          modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
          horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
          for (slot in afternoonSlotsRow1) {
            val isSelected = selectedTimeSlot == slot
            Box(
              modifier = Modifier
                .weight(1f)
                .clickable { viewModel.selectTimeSlot(slot) }
                .background(
                  if (isSelected) BrandPrimary else BrandSurface,
                  RoundedCornerShape(8.dp)
                )
                .border(
                  1.dp,
                  if (isSelected) BrandPrimary else MaterialTheme.colorScheme.outlineVariant,
                  RoundedCornerShape(8.dp)
                )
                .padding(vertical = 14.dp)
                .testTag("time_slot_${slot.replace(" ", "_").replace(":", "_").lowercase()}"),
              contentAlignment = Alignment.Center
            ) {
              Text(
                text = slot,
                fontSize = 13.sp,
                color = if (isSelected) Color.White else BrandOnSurface,
                fontWeight = FontWeight.SemiBold
              )
            }
          }
        }
        Spacer(modifier = Modifier.height(16.dp))
      }

      // Evening slots
      item {
        Text(
          text = txt("🌙 Evening Slots", "🌙 संध्याकाळची वेळ", language),
          fontSize = 14.sp,
          fontWeight = FontWeight.Bold,
          color = BrandSecondary,
          modifier = Modifier.padding(horizontal = 16.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
      }

      val eveningSlots = listOf("05:00 PM", "06:00 PM")
      item {
        Row(
          modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
          horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
          for (slot in eveningSlots) {
            val isSelected = selectedTimeSlot == slot
            Box(
              modifier = Modifier
                .weight(1f)
                .clickable { viewModel.selectTimeSlot(slot) }
                .background(
                  if (isSelected) BrandPrimary else BrandSurface,
                  RoundedCornerShape(8.dp)
                )
                .border(
                  1.dp,
                  if (isSelected) BrandPrimary else MaterialTheme.colorScheme.outlineVariant,
                  RoundedCornerShape(8.dp)
                )
                .padding(vertical = 14.dp)
                .testTag("time_slot_${slot.replace(" ", "_").replace(":", "_").lowercase()}"),
              contentAlignment = Alignment.Center
            ) {
              Text(
                text = slot,
                fontSize = 13.sp,
                color = if (isSelected) Color.White else BrandOnSurface,
                fontWeight = FontWeight.SemiBold
              )
            }
          }
          // Filler box to balance row items size
          Box(modifier = Modifier.weight(1f))
        }
        Spacer(modifier = Modifier.height(30.dp))
      }
    }
  }
}

// ----------------------------------------------------
// 6. Booking Summary Screen
// ----------------------------------------------------
@Composable
fun BookingSummaryScreen(viewModel: SkillSevaViewModel, language: Language) {
  val selectedDate by viewModel.selectedDate.collectAsState()
  val selectedTimeSlot by viewModel.selectedTimeSlot.collectAsState()
  val selectedService by viewModel.selectedService.collectAsState()
  val paymentMethod by viewModel.paymentMethod.collectAsState()

  Scaffold(
    topBar = {
      Row(
        modifier = Modifier
          .fillMaxWidth()
          .statusBarsPadding()
          .background(BrandSurface)
          .padding(horizontal = 8.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
      ) {
        IconButton(
          onClick = { viewModel.navigateBack() },
          modifier = Modifier.testTag("summary_back_button")
        ) {
          Icon(Icons.AutoMirrored.Filled.ArrowBack, "Back", tint = BrandPrimary)
        }
        Text(
          text = txt("Booking Summary", "बुकिंग तपशील", language),
          fontSize = 18.sp,
          fontWeight = FontWeight.Bold,
          color = BrandPrimary
        )
      }
    },
    bottomBar = {
      Surface(
        tonalElevation = 8.dp,
        color = BrandSurface,
        modifier = Modifier.windowInsetsPadding(WindowInsets.navigationBars)
      ) {
        Column(modifier = Modifier.padding(16.dp)) {
          Button(
            onClick = { viewModel.confirmAndPlaceBooking() },
            colors = ButtonDefaults.buttonColors(containerColor = BrandPrimaryContainer),
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier
              .fillMaxWidth()
              .height(54.dp)
              .testTag("confirm_and_book_button")
          ) {
            Row(
              verticalAlignment = Alignment.CenterVertically,
              horizontalArrangement = Arrangement.Center
            ) {
              Text(
                text = txt("Confirm & Place Booking", "बुकिंग निश्चित करा", language),
                fontSize = 16.sp,
                fontWeight = FontWeight.Black,
                color = Color.White
              )
              Spacer(modifier = Modifier.width(8.dp))
              Icon(Icons.Default.Verified, "Verify Check", tint = Color.White, modifier = Modifier.size(20.dp))
            }
          }
        }
      }
    }
  ) { innerPadding ->
    LazyColumn(
      modifier = Modifier
        .fillMaxSize()
        .padding(innerPadding)
        .background(BrandBackground),
      contentPadding = PaddingValues(bottom = 24.dp)
    ) {
      // Step tracker indicator
      item {
        Row(
          modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp),
          horizontalArrangement = Arrangement.Center
        ) {
          Text("1. Schedule  ", fontSize = 13.sp, color = BrandSecondary.copy(alpha = 0.5f))
          Text(">  2. Summary  ", fontSize = 13.sp, color = BrandPrimary, fontWeight = FontWeight.Bold)
          Text(">  3. Pay", fontSize = 13.sp, color = BrandSecondary.copy(alpha = 0.5f))
        }
      }

      // Service item brief card representation with image annotation
      item {
        Card(
          modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
          colors = CardDefaults.cardColors(containerColor = BrandSurface),
          border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant)
        ) {
          Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
          ) {
            AsyncImage(
              model = ImageRequest.Builder(LocalContext.current)
                .data("https://lh3.googleusercontent.com/aida-public/AB6AXuBxY2AV82LLkFMUTknqJLaabFkUq59k7oieAQ-AAAjd44YKmT3tPfsuvJyLekNNjmSSY9bJ9DyTpfTnWHPt0kjSb7t1BfMKkrcAFFVIVptnOarHRtj1e0diR9zZ2bGjJUaYxsklsjVDwPYQxv3uG8oGt8EYuAcFIrG9gWtF88EjnDlOnkvndck2KYGC4zpetc2AEzKHyfle3kdpyEnf5QgHPB-30I8BYATopKy3r1d-ZDzp9zf967ru68A3oo7FCh_k8eHH8N5VqIhn")
                .crossfade(true)
                .build(),
              contentDescription = "Service graphic",
              modifier = Modifier
                .size(72.dp)
                .clip(RoundedCornerShape(8.dp)),
              contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column {
              Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                  text = "Complete $selectedService Service",
                  fontSize = 16.sp,
                  fontWeight = FontWeight.Bold,
                  color = BrandOnSurface
                )
              }
              Spacer(modifier = Modifier.height(6.dp))
              Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.CalendarToday, "date", tint = BrandOnSurfaceVariant, modifier = Modifier.size(14.dp))
                Spacer(modifier = Modifier.width(4.dp))
                Text(selectedDate, fontSize = 12.sp, color = BrandOnSurfaceVariant)
              }
              Spacer(modifier = Modifier.height(4.dp))
              Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.Schedule, "time", tint = BrandOnSurfaceVariant, modifier = Modifier.size(14.dp))
                Spacer(modifier = Modifier.width(4.dp))
                Text(selectedTimeSlot, fontSize = 12.sp, color = BrandOnSurfaceVariant)
              }
            }
          }
        }
      }

      // Address Home plot details
      item {
        Spacer(modifier = Modifier.height(16.dp))
        Card(
          modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
          colors = CardDefaults.cardColors(containerColor = BrandSurface),
          border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant)
        ) {
          Row(
            modifier = Modifier
              .fillMaxWidth()
              .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
          ) {
            Column {
              Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.LocationOn, "home pointer", tint = BrandSecondary, modifier = Modifier.size(16.dp))
                Spacer(modifier = Modifier.width(4.dp))
                Text(txt("Service Address", "पत्ता", language), fontSize = 12.sp, color = BrandSecondary, fontWeight = FontWeight.Bold)
              }
              Spacer(modifier = Modifier.height(6.dp))
              Text("Home", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = BrandOnSurface)
              Text("Plot 42, Viman Nagar, Pune, Maharashtra - 411014", fontSize = 12.sp, color = BrandOnSurfaceVariant)
            }
            Text(txt("Change", "बदला", language), fontSize = 12.sp, fontWeight = FontWeight.Bold, color = BrandPrimary, modifier = Modifier.clickable { /* action */ })
          }
        }
      }

      // Select Payment methods Radios
      item {
        Spacer(modifier = Modifier.height(16.dp))
        Text(
          text = txt("Select Payment Method", "पेमेंट पर्याय निवडा", language),
          fontSize = 14.sp,
          fontWeight = FontWeight.Bold,
          color = BrandSecondary,
          modifier = Modifier.padding(horizontal = 16.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))

        Column(
          modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
          verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
          // Alternative 1: Online Pay
          val isOnline = paymentMethod == "online"
          Card(
            modifier = Modifier
              .fillMaxWidth()
              .clickable { viewModel.selectPaymentMethod("online") }
              .testTag("pay_online_box"),
            colors = CardDefaults.cardColors(
              containerColor = if (isOnline) BrandPrimary.copy(alpha = 0.05f) else BrandSurface
            ),
            border = BorderStroke(1.5.dp, if (isOnline) BrandPrimary else MaterialTheme.colorScheme.outlineVariant)
          ) {
            Row(
              modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
              verticalAlignment = Alignment.CenterVertically,
              horizontalArrangement = Arrangement.SpaceBetween
            ) {
              Row(verticalAlignment = Alignment.CenterVertically) {
                RadioButton(selected = isOnline, onClick = { viewModel.selectPaymentMethod("online") })
                Spacer(modifier = Modifier.width(8.dp))
                Column {
                  Text(txt("Pay Online", "ऑनलाइन पेमेंट करा", language), fontSize = 14.sp, fontWeight = FontWeight.Bold)
                  Text("UPI, Cards, NetBanking (Secured)", fontSize = 11.sp, color = BrandOnSurfaceVariant)
                }
              }
              if (isOnline) {
                Icon(Icons.Default.Verified, "checked payment", tint = BrandPrimary)
              }
            }
          }

          // Alternative 2: Cash after Service
          val isCash = paymentMethod == "cash"
          Card(
            modifier = Modifier
              .fillMaxWidth()
              .clickable { viewModel.selectPaymentMethod("cash") }
              .testTag("pay_cash_box"),
            colors = CardDefaults.cardColors(
              containerColor = if (isCash) BrandPrimary.copy(alpha = 0.05f) else BrandSurface
            ),
            border = BorderStroke(1.5.dp, if (isCash) BrandPrimary else MaterialTheme.colorScheme.outlineVariant)
          ) {
            Row(
              modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
              verticalAlignment = Alignment.CenterVertically
            ) {
              RadioButton(selected = isCash, onClick = { viewModel.selectPaymentMethod("cash") })
              Spacer(modifier = Modifier.width(8.dp))
              Column {
                Text(txt("Cash after Service", "कामानंतर रोख रक्कम द्या", language), fontSize = 14.sp, fontWeight = FontWeight.Bold)
                Text("Pay directly to the professional", fontSize = 11.sp, color = BrandOnSurfaceVariant)
              }
            }
          }
        }
      }

      // Billing Breakdown Summary Table
      item {
        Spacer(modifier = Modifier.height(24.dp))
        Card(
          modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
          colors = CardDefaults.cardColors(containerColor = BrandSurface),
          border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant)
        ) {
          Column {
            Box(
              modifier = Modifier
                .fillMaxWidth()
                .background(BrandSurfaceContainerLow)
                .padding(horizontal = 16.dp, vertical = 8.dp)
            ) {
              Text(
                "PAYMENT SUMMARY",
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
                color = BrandSecondary,
                letterSpacing = 1.sp
              )
            }

            Column(modifier = Modifier.padding(16.dp)) {
              Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text(txt("Service Fee", "सेवा शुल्क", language), fontSize = 13.sp, color = BrandOnSurfaceVariant)
                Text("₹249", fontSize = 13.sp, color = BrandOnSurface, fontWeight = FontWeight.Bold)
              }
              Spacer(modifier = Modifier.height(10.dp))
              Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                  Text(txt("Safety & Insurance", "सुरक्षा व विमा शुल्क", language), fontSize = 13.sp, color = BrandOnSurfaceVariant)
                  Spacer(modifier = Modifier.width(4.dp))
                  Icon(Icons.Default.Info, "info", tint = BrandOnSurfaceVariant.copy(alpha = 0.5f), modifier = Modifier.size(14.dp))
                }
                Text("₹29", fontSize = 13.sp, color = BrandOnSurface, fontWeight = FontWeight.Bold)
              }

              Spacer(modifier = Modifier.height(10.dp))
              Divider(color = MaterialTheme.colorScheme.outlineVariant)
              Spacer(modifier = Modifier.height(10.dp))

              Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                Text(txt("Total Amount", "एकूण देय रक्कम", language), fontSize = 15.sp, fontWeight = FontWeight.Bold, color = BrandOnSurface)
                Text("₹278", fontSize = 22.sp, fontWeight = FontWeight.ExtraBold, color = BrandPrimary)
              }

              Spacer(modifier = Modifier.height(12.dp))
              Text(
                text = txt(
                  "* Includes government-fixed labor charges and SkillSeva coordination fees. No hidden costs.",
                  "* यामध्ये शासकीय निर्धारित मजुरी दर आणि प्रशासकीय शुल्काचा समावेश आहे. अतिरिक्त खर्च नाही.",
                  language
                ),
                fontSize = 10.sp,
                color = BrandOnSurfaceVariant,
                lineHeight = 13.sp,
                modifier = Modifier
                  .background(BrandSurfaceContainerLow, RoundedCornerShape(4.dp))
                  .padding(8.dp)
              )
            }
          }
        }
      }

      // Cancellation badge note
      item {
        Spacer(modifier = Modifier.height(16.dp))
        Row(
          modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .background(ColorStatusPending.copy(alpha = 0.08f), RoundedCornerShape(12.dp))
            .border(1.dp, ColorStatusPending.copy(alpha = 0.2f), RoundedCornerShape(12.dp))
            .padding(12.dp),
          verticalAlignment = Alignment.Top
        ) {
          Icon(Icons.Default.EventBusy, "busy", tint = ColorStatusPending)
          Spacer(modifier = Modifier.width(8.dp))
          Text(
            text = txt(
              "Free Cancellation until 2 hours before the scheduled slot. Soft convenience fee applicable thereafter.",
              "कामाच्या वेळेच्या २ तास अगोदर मोफत रद्द करण्याची सोय. त्यानंतर रद्द केल्यास नाममात्र शुल्क लागू होईल.",
              language
            ),
            fontSize = 11.sp,
            color = BrandOnSurfaceVariant,
            lineHeight = 14.sp
          )
        }
      }
    }
  }
}

// ----------------------------------------------------
// 7. Track Service Screen
// ----------------------------------------------------
@Composable
fun TrackServiceScreen(viewModel: SkillSevaViewModel, language: Language) {
  val currentActiveBooking by viewModel.currentActiveBooking.collectAsState()
  val chatMessages by viewModel.chatMessages.collectAsState()
  var chatInputText by remember { mutableStateOf("") }
  var isSupportingChannelOpen by remember { mutableStateOf(false) }

  Scaffold(
    topBar = {
      Row(
        modifier = Modifier
          .fillMaxWidth()
          .statusBarsPadding()
          .background(BrandSurface)
          .padding(horizontal = 8.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
      ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
          IconButton(
            onClick = { viewModel.navigateTo(Screen.Dashboard) },
            modifier = Modifier.testTag("track_back_button")
          ) {
            Icon(Icons.AutoMirrored.Filled.ArrowBack, "Back", tint = BrandPrimary)
          }
          Text(
            text = txt("Track Service", "सेवेचा माग घ्या", language),
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = BrandPrimary
          )
        }
        TextButton(
          onClick = { isSupportingChannelOpen = !isSupportingChannelOpen },
          modifier = Modifier.testTag("chat_toggle_channel")
        ) {
          Text(
            text = if (isSupportingChannelOpen) txt("Hide Chat", "चॅट लपवा", language) else txt("Open Chat", "चॅट सुरू करा", language),
            fontWeight = FontWeight.Bold,
            color = BrandSecondary
          )
        }
      }
    }
  ) { innerPadding ->
    Box(
      modifier = Modifier
        .fillMaxSize()
        .padding(innerPadding)
    ) {
      if (!isSupportingChannelOpen) {
        // Main map view & progress
        Column(modifier = Modifier.fillMaxSize()) {
          // Live Map simulation component block
          Box(
            modifier = Modifier
              .fillMaxWidth()
              .weight(1f)
          ) {
            // Hotlinked maps layout decoration from HTML instruction
            AsyncImage(
              model = ImageRequest.Builder(LocalContext.current)
                .data("https://lh3.googleusercontent.com/aida-public/AB6AXuCNAgijQ6RWHZ5hcii5LVlmP7ktavBo5sej9cvHrMyWc-kdxucnH54EC2EXSw1TCJO41VWiHKomovp3B8NEDTvaPo7Z619JWNfyXQFuVv7oFCP3Oe4GEefBtB7P6gMzFH_X_YprOAAUqMxlBIkq_emnHYQKMjpmbX5quOSy2jiTC94uuNWPh8KVo6P4fb_-snakX2frfBalXyymU1yuGyMBrCmCHxd5AVCa9MBHwzB8tcv6RnfYhCvuKiZa0TNOFT32u2wgqKlCnFyH")
                .crossfade(true)
                .build(),
              contentDescription = "Simulated track map screen",
              modifier = Modifier.fillMaxSize(),
              contentScale = ContentScale.Crop
            )

            // Trainee and user custom overlapping badge layout markers
            // Pulsing blue safe marker
            Box(
              modifier = Modifier
                .align(Alignment.Center)
                .offset(y = (-30).dp, x = (-40).dp)
                .background(BrandPrimaryContainer, CircleShape)
                .border(2.dp, Color.White, CircleShape)
                .padding(8.dp)
            ) {
              Icon(Icons.Default.Bolt, "worker location indicator", tint = Color.White, modifier = Modifier.size(24.dp))
            }

            Box(
              modifier = Modifier
                .align(Alignment.Center)
                .offset(y = (40).dp, x = (40).dp)
                .background(BrandSecondary, CircleShape)
                .border(2.dp, Color.White, CircleShape)
                .padding(8.dp)
            ) {
              Icon(Icons.Default.Home, "home location indicator", tint = Color.White, modifier = Modifier.size(20.dp))
            }

            // Floating Distance indicator card overlay
            Card(
              modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(16.dp),
              shape = RoundedCornerShape(12.dp),
              colors = CardDefaults.cardColors(containerColor = BrandSurface.copy(alpha = 0.95f)),
              border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant)
            ) {
              Row(
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 10.dp),
                verticalAlignment = Alignment.CenterVertically
              ) {
                Box(
                  modifier = Modifier
                    .size(8.dp)
                    .background(BrandPrimary, CircleShape)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                  text = txt("Arrival in 12 mins  |  Distance: 2.4 km", "१२ मिनिटांत आगमन  |  अंतर: २.४ किमी", language),
                  fontSize = 12.sp,
                  fontWeight = FontWeight.Bold,
                  color = BrandPrimary
                )
              }
            }
          }

          // Bottom detailed information profile sheet
          Card(
            modifier = Modifier
              .fillMaxWidth()
              .padding(16.dp),
            colors = CardDefaults.cardColors(containerColor = BrandSurface),
            border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant)
          ) {
            Column(modifier = Modifier.padding(16.dp)) {
              // Trainee profile row
              Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
              ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                  AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                      .data("https://lh3.googleusercontent.com/aida-public/AB6AXuCOifNtXPmlfHgCBgtt1_z3NA2iccf_mLT9GPczEWAW9zjOPAGjIXZ4dnOjr6qJTi_j1D40UYGK4x8gJa_NSPRTytlKWqrF0QNeo6Vquxk3CDWycS2xxXvt0XmTw4cNcXr61lDYAoenYp17aHPKoULjyJPXgz25-3p8m3uCJ0n4VhASE-VCcdr5eUo7vOsuvI8S6-J8JeDTkONiNUcMr4gdccxVlNT7zVwLjhPFMx1crmrGzs3PwcNaLzToGa-gnXOm6k0E3zmpkj5M")
                      .crossfade(true)
                      .build(),
                    contentDescription = "Trainee profile avatar",
                    modifier = Modifier
                      .size(54.dp)
                      .clip(CircleShape)
                      .border(1.dp, ColorCertificationGold, CircleShape),
                    contentScale = ContentScale.Crop
                  )
                  Spacer(modifier = Modifier.width(12.dp))
                  Column {
                    Text(
                      text = currentActiveBooking?.traineeName ?: "Amit Kulkarni",
                      fontSize = 16.sp,
                      fontWeight = FontWeight.Bold,
                      color = BrandOnSurface
                    )
                    Row(verticalAlignment = Alignment.CenterVertically) {
                      Text("ITI Electrician", fontSize = 12.sp, color = BrandOnSurfaceVariant)
                      Spacer(modifier = Modifier.width(4.dp))
                      Icon(Icons.Default.Star, "star", tint = Color.Yellow, modifier = Modifier.size(12.dp))
                      Text("4.9", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = BrandOnSurface)
                    }
                  }
                }

                Column(horizontalAlignment = Alignment.End) {
                  Text("STATUS", fontSize = 9.sp, fontWeight = FontWeight.Bold, color = BrandOnSurfaceVariant)
                  Text(
                    text = txt("On the way", "येत आहेत", language),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = BrandPrimary
                  )
                }
              }

              Spacer(modifier = Modifier.height(12.dp))

              Row(
                modifier = Modifier
                  .background(ColorSuccessGreen.copy(alpha = 0.1f), RoundedCornerShape(4.dp))
                  .padding(horizontal = 8.dp, vertical = 4.dp),
                verticalAlignment = Alignment.CenterVertically
              ) {
                Icon(Icons.Default.Security, "shield lock icon", tint = ColorSuccessGreen, modifier = Modifier.size(12.dp))
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                  text = txt("Background Verified Trainee Scheme Approved", "पोलीस पार्श्वभूमी पडताळलेले प्रशिक्षणार्थी", language),
                  fontSize = 10.sp,
                  fontWeight = FontWeight.Bold,
                  color = ColorSuccessGreen
                )
              }

              Spacer(modifier = Modifier.height(16.dp))

              // Direct action buttons row
              Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
              ) {
                Button(
                  onClick = { /* call action simulating */ },
                  colors = ButtonDefaults.buttonColors(containerColor = BrandPrimary),
                  shape = RoundedCornerShape(8.dp),
                  modifier = Modifier
                    .weight(1f)
                    .height(48.dp)
                    .testTag("call_trainee_button")
                ) {
                  Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.Call, "call")
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(txt("Call Trainee", "कॉल करा", language), fontWeight = FontWeight.Bold)
                  }
                }

                OutlinedButton(
                  onClick = { isSupportingChannelOpen = true },
                  border = BorderStroke(1.dp, BrandSecondary),
                  shape = RoundedCornerShape(8.dp),
                  modifier = Modifier
                    .weight(1f)
                    .height(48.dp)
                    .testTag("chat_trainee_button")
                ) {
                  Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.ChatBubble, "chat", tint = BrandSecondary)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(txt("Chat", "चॅट संदेश", language), color = BrandSecondary, fontWeight = FontWeight.Bold)
                  }
                }
              }

              Spacer(modifier = Modifier.height(12.dp))

              // Safety PIN warning
              Row(
                verticalAlignment = Alignment.Top,
                modifier = Modifier
                  .fillMaxWidth()
                  .background(BrandSurfaceContainerLow, RoundedCornerShape(8.dp))
                  .padding(10.dp)
              ) {
                Icon(Icons.Default.Info, "info badge icon", tint = BrandSecondary, modifier = Modifier.size(16.dp))
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                  text = txt(
                    "Please share the 4-digit OTP only after the professional arrives at your location.",
                    "प्रशिक्षणार्थी घरपोच आल्यावर पडताळणी करण्यासाठी ४ अंकी सुरक्षित OTP शेअर करा.",
                    language
                  ),
                  fontSize = 11.sp,
                  color = BrandOnSurfaceVariant,
                  lineHeight = 14.sp
                )
              }
            }
          }
        }
      } else {
        // Simple Support Chat simulator view (isSupportingChannelOpen is true)
        Column(
          modifier = Modifier
            .fillMaxSize()
            .background(BrandBackground)
        ) {
          LazyColumn(
            modifier = Modifier
              .weight(1f)
              .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
          ) {
            items(chatMessages) { msg ->
              val isFromUser = msg.second
              Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = if (isFromUser) Arrangement.End else Arrangement.Start
              ) {
                Box(
                  modifier = Modifier
                    .background(
                      if (isFromUser) BrandPrimary else BrandSecondary.copy(alpha = 0.15f),
                      RoundedCornerShape(
                        topStart = 12.dp,
                        topEnd = 12.dp,
                        bottomStart = if (isFromUser) 12.dp else 0.dp,
                        bottomEnd = if (isFromUser) 0.dp else 12.dp
                      )
                    )
                    .padding(12.dp)
                    .widthIn(max = 240.dp)
                ) {
                  Text(
                    text = msg.first,
                    color = if (isFromUser) Color.White else BrandOnSurface,
                    fontSize = 14.sp
                  )
                }
              }
            }
          }

          // Input field row
          Row(
            modifier = Modifier
              .fillMaxWidth()
              .background(BrandSurface)
              .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
          ) {
            OutlinedTextField(
              value = chatInputText,
              onValueChange = { chatInputText = it },
              placeholder = { Text(txt("Type a message...", "संदेश टाईप करा...", language)) },
              modifier = Modifier
                .weight(1f)
                .testTag("chat_input_field"),
              shape = RoundedCornerShape(24.dp),
              singleLine = true
            )
            Spacer(modifier = Modifier.width(8.dp))
            IconButton(
              onClick = {
                if (chatInputText.isNotBlank()) {
                  viewModel.sendChatMessage(chatInputText)
                  chatInputText = ""
                }
              },
              modifier = Modifier
                .background(BrandPrimary, CircleShape)
                .testTag("chat_send_button")
            ) {
              Icon(Icons.Default.Send, "Send", tint = Color.White)
            }
          }
        }
      }
    }
  }
}

// ----------------------------------------------------
// 8. Admin Dashboard Screen
// ----------------------------------------------------
@Composable
fun AdminDashboardScreen(viewModel: SkillSevaViewModel, language: Language) {
  val bookingsList by viewModel.bookings.collectAsState()

  Scaffold(
    topBar = {
      Row(
        modifier = Modifier
          .fillMaxWidth()
          .statusBarsPadding()
          .background(BrandSecondary)
          .padding(horizontal = 16.dp, vertical = 12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
          IconButton(
            onClick = { viewModel.navigateTo(Screen.Dashboard) },
            modifier = Modifier.testTag("admin_back_button")
          ) {
            Icon(Icons.AutoMirrored.Filled.ArrowBack, "Back", tint = Color.White)
          }
          Column {
            Text(
              text = "SkillSeva Admin",
              color = Color.White,
              fontSize = 18.sp,
              fontWeight = FontWeight.Bold
            )
            Text(
              text = "Government of Maharashtra",
              color = Color.White.copy(alpha = 0.8f),
              fontSize = 10.sp
            )
          }
        }

        // Gold status system badge
        Box(
          modifier = Modifier
            .background(ColorCertificationGold, RoundedCornerShape(4.dp))
            .padding(horizontal = 8.dp, vertical = 4.dp)
        ) {
          Text("ITI SYSTEM", color = Color.White, fontSize = 9.sp, fontWeight = FontWeight.Bold)
        }
      }
    }
  ) { innerPadding ->
    LazyColumn(
      modifier = Modifier
        .fillMaxSize()
        .padding(innerPadding)
        .background(BrandBackground),
      contentPadding = PaddingValues(16.dp)
    ) {
      // Metric Statistics Grid
      item {
        Text(
          text = "System Performance Overview",
          fontSize = 16.sp,
          fontWeight = FontWeight.Bold,
          color = BrandOnSurface,
          modifier = Modifier.padding(bottom = 12.dp)
        )

        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
          Card(
            modifier = Modifier.weight(1f),
            colors = CardDefaults.cardColors(containerColor = BrandSurface),
            border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant)
          ) {
            Column(modifier = Modifier.padding(12.dp)) {
              Text("Total Bookings", fontSize = 11.sp, color = BrandOnSurfaceVariant)
              Text("1,240", fontSize = 18.sp, fontWeight = FontWeight.ExtraBold, color = BrandOnSurface)
              Text("+8.2% ↑", fontSize = 10.sp, color = ColorSuccessGreen, fontWeight = FontWeight.Bold)
            }
          }

          Card(
            modifier = Modifier.weight(1f),
            colors = CardDefaults.cardColors(containerColor = BrandSurface),
            border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant)
          ) {
            Column(modifier = Modifier.padding(12.dp)) {
              Text("Active Trainees", fontSize = 11.sp, color = BrandOnSurfaceVariant)
              Text("450", fontSize = 18.sp, fontWeight = FontWeight.ExtraBold, color = BrandOnSurface)
              Text("Stable", fontSize = 10.sp, color = BrandSecondary, fontWeight = FontWeight.Bold)
            }
          }
        }

        Spacer(modifier = Modifier.height(16.dp))
      }

      // Recent Bookings data list from Room database
      item {
        Text(
          text = "Registered Services (Room Database)",
          fontSize = 16.sp,
          fontWeight = FontWeight.Bold,
          color = BrandOnSurface,
          modifier = Modifier.padding(vertical = 8.dp)
        )
      }

      if (bookingsList.isEmpty()) {
        item {
          Box(
            modifier = Modifier
              .fillMaxWidth()
              .height(120.dp),
            contentAlignment = Alignment.Center
          ) {
            Text("No active bookings in database.")
          }
        }
      } else {
        items(bookingsList) { booking ->
          Card(
            modifier = Modifier
              .fillMaxWidth()
              .padding(vertical = 6.dp)
              .testTag("admin_booking_card_${booking.customerName.replace(" ", "_").lowercase()}"),
            colors = CardDefaults.cardColors(containerColor = BrandSurface),
            border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant)
          ) {
            Column(modifier = Modifier.padding(12.dp)) {
              Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
              ) {
                Column {
                  Text(
                    text = booking.customerName,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = BrandOnSurface
                  )
                  Text(
                    text = "${booking.serviceType} • ${booking.scheduledDate}",
                    fontSize = 11.sp,
                    color = BrandOnSurfaceVariant
                  )
                }

                // Interactive status click update representing true admin workflow
                Box(
                  modifier = Modifier
                    .clip(RoundedCornerShape(12.dp))
                    .clickable {
                      val nextStatus = when (booking.status) {
                        "Pending" -> "In Progress"
                        "In Progress" -> "Completed"
                        else -> "Pending"
                      }
                      viewModel.updateBookingStatus(booking, nextStatus)
                    }
                    .background(
                      when (booking.status) {
                        "Completed" -> ColorSuccessGreen.copy(alpha = 0.12f)
                        "In Progress" -> BrandPrimary.copy(alpha = 0.12f)
                        else -> ColorStatusPending.copy(alpha = 0.12f)
                      }
                    )
                    .padding(horizontal = 10.dp, vertical = 4.dp)
                    .testTag("booking_status_badge_${booking.id}")
                ) {
                  Text(
                    text = booking.status,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    color = when (booking.status) {
                      "Completed" -> ColorSuccessGreen
                      "In Progress" -> BrandPrimary
                      else -> ColorStatusPending
                    }
                  )
                }
              }

              Spacer(modifier = Modifier.height(10.dp))

              Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
              ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                  Icon(Icons.Default.LocationOn, "address icon", tint = BrandOnSurfaceVariant, modifier = Modifier.size(14.dp))
                  Spacer(modifier = Modifier.width(4.dp))
                  Text(
                    text = booking.customerAddress,
                    fontSize = 11.sp,
                    color = BrandOnSurfaceVariant,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.fillMaxWidth(0.7f)
                  )
                }
                Text(
                  text = "₹${booking.amount.toInt()}",
                  fontWeight = FontWeight.Bold,
                  fontSize = 14.sp,
                  color = BrandSecondary
                )
              }

              if (booking.status == "Completed") {
                Spacer(modifier = Modifier.height(12.dp))
                HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant)
                Spacer(modifier = Modifier.height(8.dp))
                
                val manpowerAmount = booking.amount * 0.5
                val principalShare = manpowerAmount * 0.03
                val vpShare = manpowerAmount * 0.03
                val staffShare = manpowerAmount * 0.40
                val storekeeperShare = manpowerAmount * 0.02
                val cashierShare = manpowerAmount * 0.02
                
                Text(
                  text = "POTS Prapatra 3 Bifurcation (50% Manpower = ₹${manpowerAmount.toInt()})", 
                  fontSize = 11.sp, 
                  fontWeight = FontWeight.Bold, 
                  color = BrandPrimary
                )
                Spacer(modifier = Modifier.height(6.dp))
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                  Column {
                    Text("Principal (3%)", fontSize = 10.sp, color = BrandOnSurfaceVariant)
                    Text("VP/Group Inst (3%)", fontSize = 10.sp, color = BrandOnSurfaceVariant)
                    Text("Staff/Trainee (40%)", fontSize = 10.sp, color = BrandOnSurfaceVariant)
                    Text("Storekeeper (2%)", fontSize = 10.sp, color = BrandOnSurfaceVariant)
                    Text("Cashier (2%)", fontSize = 10.sp, color = BrandOnSurfaceVariant)
                  }
                  Column(horizontalAlignment = Alignment.End) {
                    Text("₹${principalShare.toInt()}", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = BrandSecondary)
                    Text("₹${vpShare.toInt()}", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = BrandSecondary)
                    Text("₹${staffShare.toInt()}", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = BrandSecondary)
                    Text("₹${storekeeperShare.toInt()}", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = BrandSecondary)
                    Text("₹${cashierShare.toInt()}", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = BrandSecondary)
                  }
                }
              }
            }
          }
        }
      }

      // Trainee list summary info
      item {
        Spacer(modifier = Modifier.height(20.dp))
        Text(
          text = "Registered Trade Distribution",
          fontSize = 16.sp,
          fontWeight = FontWeight.Bold,
          color = BrandOnSurface,
          modifier = Modifier.padding(bottom = 8.dp)
        )

        val trades = listOf(
          "Electrician" to 0.85f,
          "Plumber" to 0.60f,
          "AC Repair" to 0.70f,
          "Welding" to 0.35f,
          "Fabrication" to 0.30f
        )

        Card(
          modifier = Modifier.fillMaxWidth(),
          colors = CardDefaults.cardColors(containerColor = BrandSurface),
          border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant)
        ) {
          Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
            for (trade in trades) {
              Column {
                Row(
                  modifier = Modifier.fillMaxWidth(),
                  horizontalArrangement = Arrangement.SpaceBetween
                ) {
                  Text(trade.first, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                  Text("${(trade.second * 500).toInt()} Bookings", fontSize = 11.sp, color = BrandOnSurfaceVariant)
                }
                Spacer(modifier = Modifier.height(4.dp))
                LinearProgressIndicator(
                  progress = trade.second,
                  modifier = Modifier.fillMaxWidth(),
                  color = BrandPrimary,
                  trackColor = BrandSurfaceContainerHigh
                )
              }
            }
          }
        }
      }
    }
  }
}
