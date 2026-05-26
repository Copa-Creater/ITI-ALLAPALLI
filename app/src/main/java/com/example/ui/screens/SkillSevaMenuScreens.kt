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
    topBar = { SimpleTopBar(txt("What is POTS?", "POTS योजना काय आहे?", language), viewModel) }
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
              text = txt("Production Oriented Training Scheme", "उत्पादनाभिमुख प्रशिक्षण योजना", language),
              fontSize = 20.sp,
              fontWeight = FontWeight.Bold,
              color = BrandPrimary
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
              text = txt(
                "The Government of Maharashtra has introduced the POTS scheme to give practical, hands-on experience to ITI trainees while providing quality and affordable services to the public.\n\nTrainees work under the strict supervision of expert instructors.",
                "महाराष्ट्र शासनाने आयटीआय मधील प्रशिक्षणार्थ्यांना प्रत्यक्ष कामाचा अनुभव मिळावा आणि नागरिकांना वाजवी दरात दर्जेदार सेवा मिळावी यासाठी ही योजना सुरू केली आहे.\n\nयामध्ये सर्व कामे तज्ज्ञ निदेशकांच्या (Instructors) देखरेखीखाली केली जातात.",
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
    topBar = { SimpleTopBar(txt("About Us", "आमच्याबद्दल", language), viewModel) }
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
              text = txt("Created By", "निर्मिती", language) + ": AMIT .A WASNIK (INSTRUCTOR COPA)",
              fontSize = 14.sp,
              fontWeight = FontWeight.SemiBold,
              color = BrandSecondary
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
              text = txt(
                "This SkillSeva app connects the citizens with ITI Allapalli for various household and professional services. It ensures safety, reliability, and empowers local youth with real-world skills.",
                "हे स्किल-सेवा ॲप नागरिकांना आयटीआय अहेरी/अल्लापल्ली शी जोडते. यातून घरगुती आणि व्यावसायिक सेवा सुरक्षित व खात्रीशीररीत्या मिळतात आणि स्थानिक तरुणांना प्रत्यक्ष कामाचा अनुभव मिळतो.",
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
    topBar = { SimpleTopBar(txt("POTS GR PDF", "POTS शासन निर्णय", language), viewModel) }
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
        text = txt("Official Government Resolution for POTS", "POTS योजनेचा अधिकृत शासन निर्णय", language),
        fontSize = 16.sp,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center
      )
      Spacer(modifier = Modifier.height(8.dp))
      Text(
        text = txt(
          "As per GR dated 03 Feb 2022, 50% of the labor charges generated from services are distributed among trainees, instructors, and staff as an honorarium.",
          "०३ फेब्रुवारी २०२२ च्या शासन निर्णयानुसार, सेवांमधून मिळणाऱ्या मजुरीच्या उत्पन्नापैकी ५०% रक्कम प्रशिक्षणार्थी, निदेशक आणि कर्मचारी यांच्यात मानधन म्हणून विभागली जाते.",
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
    topBar = { SimpleTopBar(txt("My Account", "माझे खाते", language), viewModel) }
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
            Text(txt("My Booked Services", "माझ्या सेवा", language), fontWeight = FontWeight.Bold)
            Text(txt("No past services found.", "कोणतीही जुनी सेवा आढळली नाही.", language), fontSize = 12.sp, color = BrandOnSurfaceVariant)
            Spacer(modifier = Modifier.height(16.dp))
            Text(txt("Payment History", "पेमेंट इतिहास", language), fontWeight = FontWeight.Bold)
            Text(txt("No payments found.", "कोणतेही पेमेंट आढळले नाही.", language), fontSize = 12.sp, color = BrandOnSurfaceVariant)
          }
        }
      }
    }
  }
}
