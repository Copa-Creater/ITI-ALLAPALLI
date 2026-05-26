package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.PictureAsPdf
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.*
import com.example.viewmodel.Language
import com.example.viewmodel.SkillSevaViewModel

@Composable
fun SimpleTopBar(title: String, viewModel: SkillSevaViewModel) {
  Row(
    modifier = Modifier
      .fillMaxWidth()
      .statusBarsPadding()
      .background(BrandSurface)
      .padding(horizontal = 8.dp, vertical = 8.dp),
    verticalAlignment = Alignment.CenterVertically
  ) {
    IconButton(onClick = { viewModel.navigateBack() }) {
      Icon(Icons.AutoMirrored.Filled.ArrowBack, "Back", tint = BrandPrimary)
    }
    Text(
      text = title,
      fontSize = 18.sp,
      fontWeight = FontWeight.Bold,
      color = BrandPrimary
    )
  }
}

@Composable
fun AboutPOTSScreen(viewModel: SkillSevaViewModel, language: Language) {
  Scaffold(
    topBar = { SimpleTopBar(txt("What is POTS?", "POTS à¤¯à¥‹à¤œà¤¨à¤¾ à¤•à¤¾à¤¯ à¤†à¤¹à¥‡?", language), viewModel) }
  ) { innerPadding ->
    LazyColumn(
      modifier = Modifier
        .fillMaxSize()
        .padding(innerPadding)
        .background(BrandBackground)
        .padding(16.dp)
    ) {
      item {
        Card(
          colors = CardDefaults.cardColors(containerColor = BrandSurface),
          modifier = Modifier.fillMaxWidth()
        ) {
          Column(modifier = Modifier.padding(16.dp)) {
            Text(
              text = txt("Production Oriented Training Scheme", "à¤‰à¤¤à¥à¤ªà¤¾à¤¦à¤¨à¤¾à¤­à¤¿à¤®à¥à¤– à¤ªà¥à¤°à¤¶à¤¿à¤•à¥à¤·à¤£ à¤¯à¥‹à¤œà¤¨à¤¾", language),
              fontSize = 20.sp,
              fontWeight = FontWeight.Bold,
              color = BrandPrimary
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
              text = txt(
                "The Government of Maharashtra has introduced the POTS scheme to give practical, hands-on experience to ITI trainees while providing quality and affordable services to the public.\n\nTrainees work under the strict supervision of expert instructors.",
                "à¤®à¤¹à¤¾à¤°à¤¾à¤·à¥à¤Ÿà¥à¤° à¤¶à¤¾à¤¸à¤¨à¤¾à¤¨à¥‡ à¤†à¤¯à¤Ÿà¥€à¤†à¤¯ à¤®à¤§à¥€à¤² à¤ªà¥à¤°à¤¶à¤¿à¤•à¥à¤·à¤£à¤¾à¤°à¥à¤¥à¥à¤¯à¤¾à¤‚à¤¨à¤¾ à¤ªà¥à¤°à¤¤à¥à¤¯à¤•à¥à¤· à¤•à¤¾à¤®à¤¾à¤šà¤¾ à¤…à¤¨à¥à¤­à¤µ à¤®à¤¿à¤³à¤¾à¤µà¤¾ à¤†à¤£à¤¿ à¤¨à¤¾à¤—à¤°à¤¿à¤•à¤¾à¤‚à¤¨à¤¾ à¤µà¤¾à¤œà¤µà¥€ à¤¦à¤°à¤¾à¤¤ à¤¦à¤°à¥à¤œà¥‡à¤¦à¤¾à¤° à¤¸à¥‡à¤µà¤¾ à¤®à¤¿à¤³à¤¾à¤µà¥€ à¤¯à¤¾à¤¸à¤¾à¤ à¥€ à¤¹à¥€ à¤¯à¥‹à¤œà¤¨à¤¾ à¤¸à¥à¤°à¥‚ à¤•à¥‡à¤²à¥€ à¤†à¤¹à¥‡.\n\nà¤¯à¤¾à¤®à¤§à¥à¤¯à¥‡ à¤¸à¤°à¥à¤µ à¤•à¤¾à¤®à¥‡ à¤¤à¤œà¥à¤œà¥à¤ž à¤¨à¤¿à¤¦à¥‡à¤¶à¤•à¤¾à¤‚à¤šà¥à¤¯à¤¾ (Instructors) à¤¦à¥‡à¤–à¤°à¥‡à¤–à¥€à¤–à¤¾à¤²à¥€ à¤•à¥‡à¤²à¥€ à¤œà¤¾à¤¤à¤¾à¤¤.",
                language
              ),
              fontSize = 14.sp,
              color = BrandOnSurfaceVariant,
              lineHeight = 22.sp
            )
          }
        }
      }
    }
  }
}

@Composable
fun AboutUsScreen(viewModel: SkillSevaViewModel, language: Language) {
  Scaffold(
    topBar = { SimpleTopBar(txt("About Us", "à¤†à¤®à¤šà¥à¤¯à¤¾à¤¬à¤¦à¥à¤¦à¤²", language), viewModel) }
  ) { innerPadding ->
    LazyColumn(
      modifier = Modifier
        .fillMaxSize()
        .padding(innerPadding)
        .background(BrandBackground)
        .padding(16.dp)
    ) {
      item {
        Card(
          colors = CardDefaults.cardColors(containerColor = BrandSurface),
          modifier = Modifier.fillMaxWidth()
        ) {
          Column(modifier = Modifier.padding(16.dp)) {
            Text(
              text = "GOVERNMENT ITI ALLAPALLI",
              fontSize = 20.sp,
              fontWeight = FontWeight.Bold,
              color = BrandPrimary
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
              text = txt("Created By", "à¤¨à¤¿à¤°à¥à¤®à¤¿à¤¤à¥€", language) + ": AMIT .A WASNIK (INSTRUCTOR COPA)",
              fontSize = 14.sp,
              fontWeight = FontWeight.SemiBold,
              color = BrandSecondary
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
              text = txt(
                "This SkillSeva app connects the citizens with ITI Allapalli for various household and professional services. It ensures safety, reliability, and empowers local youth with real-world skills.",
                "à¤¹à¥‡ à¤¸à¥à¤•à¤¿à¤²-à¤¸à¥‡à¤µà¤¾ à¥²à¤ª à¤¨à¤¾à¤—à¤°à¤¿à¤•à¤¾à¤‚à¤¨à¤¾ à¤†à¤¯à¤Ÿà¥€à¤†à¤¯ à¤…à¤¹à¥‡à¤°à¥€/à¤…à¤²à¥à¤²à¤¾à¤ªà¤²à¥à¤²à¥€ à¤¶à¥€ à¤œà¥‹à¤¡à¤¤à¥‡. à¤¯à¤¾à¤¤à¥‚à¤¨ à¤˜à¤°à¤—à¥à¤¤à¥€ à¤†à¤£à¤¿ à¤µà¥à¤¯à¤¾à¤µà¤¸à¤¾à¤¯à¤¿à¤• à¤¸à¥‡à¤µà¤¾ à¤¸à¥à¤°à¤•à¥à¤·à¤¿à¤¤ à¤µ à¤–à¤¾à¤¤à¥à¤°à¥€à¤¶à¥€à¤°à¤°à¥€à¤¤à¥à¤¯à¤¾ à¤®à¤¿à¤³à¤¤à¤¾à¤¤ à¤†à¤£à¤¿ à¤¸à¥à¤¥à¤¾à¤¨à¤¿à¤• à¤¤à¤°à¥à¤£à¤¾à¤‚à¤¨à¤¾ à¤ªà¥à¤°à¤¤à¥à¤¯à¤•à¥à¤· à¤•à¤¾à¤®à¤¾à¤šà¤¾ à¤…à¤¨à¥à¤­à¤µ à¤®à¤¿à¤³à¤¤à¥‹.",
                language
              ),
              fontSize = 14.sp,
              color = BrandOnSurfaceVariant,
              lineHeight = 22.sp
            )
          }
        }
      }
    }
  }
}

@Composable
fun POTSDocumentScreen(viewModel: SkillSevaViewModel, language: Language) {
  Scaffold(
    topBar = { SimpleTopBar(txt("POTS GR PDF", "POTS à¤¶à¤¾à¤¸à¤¨ à¤¨à¤¿à¤°à¥à¤£à¤¯", language), viewModel) }
  ) { innerPadding ->
    Column(
      modifier = Modifier
        .fillMaxSize()
        .padding(innerPadding)
        .background(BrandBackground)
        .padding(16.dp),
      horizontalAlignment = Alignment.CenterHorizontally,
      verticalArrangement = Arrangement.Center
    ) {
      Icon(Icons.Default.PictureAsPdf, contentDescription = "PDF", tint = BrandPrimary, modifier = Modifier.size(64.dp))
      Spacer(modifier = Modifier.height(16.dp))
      Text(
        text = txt("Official Government Resolution for POTS", "POTS à¤¯à¥‹à¤œà¤¨à¥‡à¤šà¤¾ à¤…à¤§à¤¿à¤•à¥ƒà¤¤ à¤¶à¤¾à¤¸à¤¨ à¤¨à¤¿à¤°à¥à¤£à¤¯", language),
        fontSize = 16.sp,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center
      )
      Spacer(modifier = Modifier.height(8.dp))
      Text(
        text = txt(
          "As per GR dated 03 Feb 2022, 50% of the labor charges generated from services are distributed among trainees, instructors, and staff as an honorarium.",
          "à¥¦à¥© à¤«à¥‡à¤¬à¥à¤°à¥à¤µà¤¾à¤°à¥€ à¥¨à¥¦à¥¨à¥¨ à¤šà¥à¤¯à¤¾ à¤¶à¤¾à¤¸à¤¨ à¤¨à¤¿à¤°à¥à¤£à¤¯à¤¾à¤¨à¥à¤¸à¤¾à¤°, à¤¸à¥‡à¤µà¤¾à¤‚à¤®à¤§à¥‚à¤¨ à¤®à¤¿à¤³à¤£à¤¾à¤±à¥à¤¯à¤¾ à¤®à¤œà¥à¤°à¥€à¤šà¥à¤¯à¤¾ à¤‰à¤¤à¥à¤ªà¤¨à¥à¤¨à¤¾à¤ªà¥ˆà¤•à¥€ à¥«à¥¦% à¤°à¤•à¥à¤•à¤® à¤ªà¥à¤°à¤¶à¤¿à¤•à¥à¤·à¤£à¤¾à¤°à¥à¤¥à¥€, à¤¨à¤¿à¤¦à¥‡à¤¶à¤• à¤†à¤£à¤¿ à¤•à¤°à¥à¤®à¤šà¤¾à¤°à¥€ à¤¯à¤¾à¤‚à¤šà¥à¤¯à¤¾à¤¤ à¤®à¤¾à¤¨à¤§à¤¨ à¤®à¥à¤¹à¤£à¥‚à¤¨ à¤µà¤¿à¤­à¤¾à¤—à¤²à¥€ à¤œà¤¾à¤¤à¥‡.",
          language
        ),
        fontSize = 14.sp,
        textAlign = TextAlign.Center,
        color = BrandOnSurfaceVariant
      )
    }
  }
}

@Composable
fun ProfileScreen(viewModel: SkillSevaViewModel, language: Language) {
  Scaffold(
    topBar = { SimpleTopBar(txt("My Account", "à¤®à¤¾à¤à¥‡ à¤–à¤¾à¤¤à¥‡", language), viewModel) }
  ) { innerPadding ->
    LazyColumn(
      modifier = Modifier
        .fillMaxSize()
        .padding(innerPadding)
        .background(BrandBackground)
        .padding(16.dp)
    ) {
      item {
        Card(
          colors = CardDefaults.cardColors(containerColor = BrandSurface),
          modifier = Modifier.fillMaxWidth()
        ) {
          Column(modifier = Modifier.padding(16.dp)) {
            Text("Customer Dashboard", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = BrandPrimary)
            Spacer(modifier = Modifier.height(16.dp))
            Text(txt("My Booked Services", "à¤®à¤¾à¤à¥à¤¯à¤¾ à¤¸à¥‡à¤µà¤¾", language), fontWeight = FontWeight.Bold)
            Text(txt("No past services found.", "à¤•à¥‹à¤£à¤¤à¥€à¤¹à¥€ à¤œà¥à¤¨à¥€ à¤¸à¥‡à¤µà¤¾ à¤†à¤¢à¤³à¤²à¥€ à¤¨à¤¾à¤¹à¥€.", language), fontSize = 12.sp, color = BrandOnSurfaceVariant)
            Spacer(modifier = Modifier.height(16.dp))
            Text(txt("Payment History", "à¤ªà¥‡à¤®à¥‡à¤‚à¤Ÿ à¤‡à¤¤à¤¿à¤¹à¤¾à¤¸", language), fontWeight = FontWeight.Bold)
            Text(txt("No payments found.", "à¤•à¥‹à¤£à¤¤à¥‡à¤¹à¥€ à¤ªà¥‡à¤®à¥‡à¤‚à¤Ÿ à¤†à¤¢à¤³à¤²à¥‡ à¤¨à¤¾à¤¹à¥€.", language), fontSize = 12.sp, color = BrandOnSurfaceVariant)
          }
        }
      }
    }
  }
}
