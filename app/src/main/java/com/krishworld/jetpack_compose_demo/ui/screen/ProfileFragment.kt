package com.krishworld.jetpack_compose_demo.ui.screen


import android.Manifest
import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.google.accompanist.glide.rememberGlidePainter
import com.krishworld.jetpack_compose_demo.utils.ComposeFileProvider


@Composable
fun ProfileFragment(navController: NavHostController) {

    Column(
        modifier = Modifier.padding(20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(text = "Profile", style = TextStyle(fontSize = 40.sp, fontFamily = FontFamily.Serif))

        Spacer(modifier = Modifier.height(20.dp))

        ImagePicker()
    }
}

@Composable
fun ImagePicker(
    modifier: Modifier = Modifier,
) {

    val context = LocalContext.current

    var imagePickerType by remember {
        mutableStateOf("")
    }

    var hasImage by remember {
        mutableStateOf(false)
    }
    var imageUri by remember {
        mutableStateOf<Uri?>(null)
    }

    val imagePicker = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri ->
            hasImage = uri != null
            imageUri = uri
        }
    )

    val cameraLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicture(),
        onResult = { success ->
            hasImage = success
        }
    )


    val launcherMultiplePermissionsReq = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        permissions.forEach { permission ->
            when (permission.key) {
                Manifest.permission.READ_EXTERNAL_STORAGE -> {
                    if (permission.value) {
                        // Permission Accepted: Do something
                        Log.d("ProfileFragment", "READ_EXTERNAL_STORAGE PERMISSION GRANTED")
                        if (imagePickerType == "Gallery")
                            imagePicker.launch("image/*")
                    } else {
                        // Permission Denied: Do something
                        Log.d("ProfileFragment", "READ_EXTERNAL_STORAGE PERMISSION DENIED")
                    }
                }
                Manifest.permission.CAMERA -> {
                    if (permission.value) {
                        // Permission Accepted: Do something
                        Log.d("ProfileFragment", "CAMERA PERMISSION GRANTED")
                        if (imagePickerType == "Camera") {
                            val uri = ComposeFileProvider.getImageUri(context)
                            imageUri = uri
                            cameraLauncher.launch(uri)
                        }
                    } else {
                        // Permission Denied: Do something
                        Log.d("ProfileFragment", "CAMERA PERMISSION DENIED")
                    }

                }

            }
        }
    }


    /*  val launcher = rememberLauncherForActivityResult(
          ActivityResultContracts.RequestPermission()
      ) { isGranted: Boolean ->
          if (isGranted) {
              // Permission Accepted: Do something
              Log.d("ProfileFragment", "PERMISSION GRANTED")

          } else {
              // Permission Denied: Do something
              Log.d("ProfileFragment", "PERMISSION DENIED")
          }
      }
  */


    Box(
        modifier = modifier,
    ) {
        if (hasImage && imageUri != null) {
            Image(
                painter = rememberGlidePainter(imageUri.toString()),
                contentDescription = "",
            )
        }
        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Button(
                onClick = {
//                    when (PackageManager.PERMISSION_GRANTED) {
//                        ContextCompat.checkSelfPermission(
//                            context,
//                            Manifest.permission.READ_EXTERNAL_STORAGE
//                        ) -> {
//                            // Some works that require permission
//                            Log.d("ExampleScreen", "Code requires permission")
//                            imagePicker.launch("image/*")
//                        }
//                        else -> {
//                            // Asking for permission
//                            launcher.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
//                        }
//                    }
                    imagePickerType = "Gallery"
                    launcherMultiplePermissionsReq.launch(
                        arrayOf(
                            Manifest.permission.CAMERA,
                            Manifest.permission.READ_EXTERNAL_STORAGE
                        )
                    )
                },
            ) {
                Text(
                    text = "Select Image"
                )
            }
            Button(
                modifier = Modifier.padding(top = 16.dp),
                onClick = {
                    /* when (PackageManager.PERMISSION_GRANTED) {
                         ContextCompat.checkSelfPermission(
                             context,
                             Manifest.permission.CAMERA
                         ) -> {
                             // Some works that require permission
                             Log.d("ExampleScreen", "Code requires permission")
                             val uri = ComposeFileProvider.getImageUri(context)
                             imageUri = uri
                             cameraLauncher.launch(uri)
                         }
                         else -> {
                             // Asking for permission
                             launcher.launch(Manifest.permission.CAMERA)
                         }
                     }
 */
                    imagePickerType = "Camera"
                    launcherMultiplePermissionsReq.launch(
                        arrayOf(
                            Manifest.permission.CAMERA,
                            Manifest.permission.READ_EXTERNAL_STORAGE
                        )
                    )
                },
            ) {
                Text(
                    text = "Take photo"
                )
            }
        }
    }
}




