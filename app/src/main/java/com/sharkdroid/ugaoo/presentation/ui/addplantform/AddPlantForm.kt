package com.sharkdroid.ugaoo.presentation.ui.addplantform

import android.Manifest
import android.net.Uri
import android.content.pm.PackageManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.navigation.NavHostController
import coil3.compose.AsyncImage
import com.sharkdroid.ugaoo.R
import com.sharkdroid.ugaoo.presentation.viewmodels.AddPlantViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddPlantForm(navHostController: NavHostController,addPlantViewModel: AddPlantViewModel) {
    var isFormVisible by remember { mutableStateOf(false) }
    var plantName by remember { mutableStateOf("") }
    var plantPrice by remember { mutableStateOf("") }
    var isIndoor by remember { mutableStateOf(true) }
    var plantImageUri by remember { mutableStateOf<Uri?>(null) }
    var isDialogVisible by remember { mutableStateOf(false) }
    val context = LocalContext.current
    val  plantType= if (isIndoor) "Indoor Plant" else "Outdoor Plant"


    // Camera permission state
    val cameraPermissionGranted = remember {
        ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED
    }

    // Launcher to take a photo from camera
    val cameraLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicturePreview(),
        onResult = { bitmap ->
            // Handle bitmap from camera here
        }
    )

    // Permission launcher for camera
    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { isGranted: Boolean ->
            if (isGranted) {
                cameraLauncher.launch(null)
            } else {
                // Handle permission denial
            }
        }
    )

    // Launcher to pick image from gallery
    val galleryLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri: Uri? ->
            plantImageUri = uri
        }
    )



    // Image selection option dialog
    if (isDialogVisible) {
        AlertDialog(
            onDismissRequest = { isDialogVisible = false },
            confirmButton = {
                TextButton(onClick = {
                    galleryLauncher.launch("image/*")
                    isDialogVisible = false
                }) {
                    Text("Pick from Gallery")
                }
            },
            dismissButton = {
                TextButton(onClick = {
                    if (cameraPermissionGranted) {
                        cameraLauncher.launch(null)
                    } else {
                        permissionLauncher.launch(Manifest.permission.CAMERA)
                    }
                    isDialogVisible = false
                }) {
                    Text("Take Photo")
                }
            },
            title = { Text("Select Image Source") }
        )
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Scaffold(
            floatingActionButton = {
                FloatingActionButton(
                    onClick = { isFormVisible = !isFormVisible },
                    containerColor = colorResource(id = R.color.Deep_Teal),
                    shape = MaterialTheme.shapes.medium,
                    elevation = FloatingActionButtonDefaults.elevation(8.dp)
                ) {
                    Icon(
                        imageVector = Icons.Filled.Add,
                        contentDescription = "Add Plant",
                        tint = Color.White
                    )
                }
            },
            topBar = {
                TopAppBar(
                    title = {
                        Row(
                            modifier = Modifier.fillMaxWidth().padding(end = 16.dp),
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = "Add Plant",
                                color = Color.White,
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = colorResource(id = R.color.Deep_Teal)
                    ),
                    navigationIcon = {
                        Icon(
                            imageVector = Icons.Filled.KeyboardArrowLeft,
                            contentDescription = "Back",
                            tint = colorResource(id = R.color.white),
                            modifier = Modifier
                                .padding(start = 8.dp)
                                .size(34.dp)
                        )
                    }
                )
            }
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                Text(
                    text = "Click the + button to add a new plant.",
                    color = Color.Gray,
                    fontSize = 16.sp
                )
            }
        }

        if (isFormVisible) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .graphicsLayer { alpha = 0.8f }
                    .blur(16.dp)
                    .background(Color.Black.copy(alpha = 0.4f))
            )

            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp)
            ) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    elevation = CardDefaults.cardElevation(12.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Add Plant Details",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = colorResource(id = R.color.Deep_Teal)
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        BasicTextField(
                            value = plantName,
                            onValueChange = { plantName = it },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 8.dp)
                                .padding(horizontal = 16.dp),
                            textStyle = TextStyle(
                                color = Color.Black,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Normal
                            ),
                            decorationBox = { innerTextField ->
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(8.dp)
                                        .border(1.dp, colorResource(id = R.color.Deep_Teal), MaterialTheme.shapes.small)
                                        .padding(8.dp)
                                ) {
                                    innerTextField()
                                    if (plantName.isEmpty()) {
                                        Text(
                                            text = "Enter Plant Name",
                                            color = Color.Gray,
                                            fontSize = 16.sp
                                        )
                                    }
                                }
                            }
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        BasicTextField(
                            value = plantPrice,
                            onValueChange = { plantPrice = it},
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 8.dp)
                                .padding(horizontal = 16.dp),
                            textStyle = TextStyle(
                                color = Color.Black,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Normal
                            ),
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                            decorationBox = { innerTextField ->
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(8.dp)
                                        .border(1.dp, colorResource(id = R.color.Deep_Teal), MaterialTheme.shapes.small)
                                        .padding(8.dp)
                                ) {
                                    innerTextField()
                                    if (plantPrice.isEmpty()) {
                                        Text(
                                            text = "Enter Price",
                                            color = Color.Gray,
                                            fontSize = 16.sp
                                        )
                                    }
                                }
                            }
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(text = if (isIndoor) "Indoor Plant" else "Outdoor Plant", fontSize = 16.sp)
                            Spacer(modifier = Modifier.width(8.dp))
                            Switch(checked = isIndoor, onCheckedChange = { isIndoor = it }, colors = SwitchDefaults.colors(checkedTrackColor = colorResource(
                                id = R.color.Deep_Teal
                            )))
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            if (plantImageUri == null) {
                                Image(
                                    painter = painterResource(id = R.drawable.add_image),
                                    contentDescription = "Placeholder Image",
                                    modifier = Modifier
                                        .size(100.dp)
                                        .clip(RoundedCornerShape(8.dp)),
                                )
                            } else {
                                AsyncImage(
                                    model = plantImageUri.toString(),
                                    contentDescription = "Plant Image",
                                    modifier = Modifier
                                        .size(100.dp)
                                        .border(2.dp, colorResource(id = R.color.Deep_Teal)),
                                    contentScale = ContentScale.Crop
                                )
                            }
                            Spacer(modifier = Modifier.height(8.dp))
                            Button(
                                onClick = { isDialogVisible = true },
                                colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.Deep_Teal))
                            ) {
                                Text(text = "Upload Image", color = Color.White)
                            }
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        Button(
                            onClick = {
                                isFormVisible = false
                                plantImageUri?.let { addPlantViewModel.addPlantForCurrentUser(
                                    imageUri = it, plantName =plantName,
                                    plantPrice =plantPrice, plantType = plantType ) }
                            },
                            colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.Deep_Teal))
                        ) {
                            Text(text = "Submit", color = Color.White)
                        }
                    }
                }
            }
        }
    }
}
