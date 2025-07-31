// --- Imports ---
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.kunji.mijawharati.R
import com.kunji.mijawharati.ui.theme.EmeraldGreen
import com.kunji.mijawharati.ui.theme.mustard

// --- WomenScreen Composable ---
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WomenScreen(navController: NavController) {

    val mContext = LocalContext.current

    // Scaffold
    var selectedIndex by remember { mutableStateOf(0) }

    Scaffold(
        // TopAppBar
        topBar = {
            TopAppBar(
                title = { Text("Women's Collection") },
                navigationIcon = {
                    IconButton(onClick = { /* Handle back/nav */ }) {
                        Icon(Icons.Default.Menu, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = EmeraldGreen,
                    titleContentColor = Color.White,
                    navigationIconContentColor = Color.White
                )
            )
        },

        // BottomBar
        bottomBar = {
            NavigationBar(containerColor = EmeraldGreen) {
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
                    label = { Text("Home") },
                    selected = selectedIndex == 0,
                    onClick = {
                        selectedIndex = 0
                        // navController.navigate(ROUT_HOME)
                    }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Favorite, contentDescription = "Favorites") },
                    label = { Text("Favorites") },
                    selected = selectedIndex == 1,
                    onClick = {
                        selectedIndex = 1
                        // navController.navigate(ROUT_HOME)
                    }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Person, contentDescription = "Profile") },
                    label = { Text("Profile") },
                    selected = selectedIndex == 2,
                    onClick = {
                        selectedIndex = 2
                        // navController.navigate(ROUT_HOME)
                    }
                )
            }
        },

        // FloatingActionButton
        floatingActionButton = {
            FloatingActionButton(
                onClick = { /* Add action */ },
                containerColor = Color.LightGray
            ) {
                Icon(Icons.Default.Add, contentDescription = "Add")
            }
        },

        // Content
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .verticalScroll(rememberScrollState())
                    .fillMaxSize()
            ) {

                // Main Contents
                Text(
                    text = "Celebrate your sparkle with MiJawharati’s Women’s Collection — elegant pieces crafted to elevate every moment.",
                    fontSize = 20.sp,
                    modifier = Modifier.padding(16.dp)
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Banner image
                Image(
                    painter = painterResource(R.drawable.woman),
                    contentDescription = "banner",
                    modifier = Modifier.fillMaxWidth(),
                    contentScale = ContentScale.FillWidth
                )

                Spacer(modifier = Modifier.height(20.dp))

                // Search Bar
                var search by remember { mutableStateOf("") }

                OutlinedTextField(
                    value = search,
                    onValueChange = { search = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp),
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "search"
                        )
                    },
                    placeholder = { Text(text = "Search products") },
                    colors = OutlinedTextFieldDefaults.colors(
                        unfocusedBorderColor = EmeraldGreen,
                        focusedBorderColor = Color.Gray
                    )
                )

                Spacer(modifier = Modifier.height(10.dp))

                // Section Title
                Text(
                    text = "Other Products",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(10.dp))

                // --- Product 1 Row ---
                ProductItem(
                    imageRes = R.drawable.brace2,
                    title = "Stylish Ostrich coat",
                    brand = "Alexander Fashion",
                    price = "ksh 79000",
                    discountedPrice = "ksh 69000",
                    material = "Ostrich feather & Wool",
                    onBuyNow = {
                        val stkIntent = mContext.packageManager.getLaunchIntentForPackage("com.android.stk")
                        stkIntent?.let { mContext.startActivity(it) }
                    }
                )

                Spacer(modifier = Modifier.height(10.dp))

                // --- Product 2 Row ---
                ProductItem(
                    imageRes = R.drawable.brace1,
                    title = "Elegant Women's Coat",
                    brand = "Tien Fashion",
                    price = "ksh 99000",
                    discountedPrice = "ksh 89000",
                    material = "Kiwi feather & Wool",
                    onBuyNow = {
                        val stkIntent = mContext.packageManager.getLaunchIntentForPackage("com.android.stk")
                        stkIntent?.let { mContext.startActivity(it) }
                    }
                )

                Spacer(modifier = Modifier.height(20.dp))
            }
        }
    )
}

// --- Reusable Product Row ---
@Composable
fun ProductItem(
    imageRes: Int,
    title: String,
    brand: String,
    price: String,
    discountedPrice: String,
    material: String,
    onBuyNow: () -> Unit
) {
    Row(modifier = Modifier.padding(start = 20.dp, end = 10.dp)) {

        Image(
            painter = painterResource(imageRes),
            contentDescription = title,
            modifier = Modifier
                .size(width = 200.dp, height = 250.dp)
                .clip(RoundedCornerShape(10.dp)),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.width(10.dp))

        Column(modifier = Modifier.weight(1f)) {
            Text(title, fontSize = 18.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(5.dp))
            Text("Brand: $brand", fontSize = 15.sp)
            Text("Price: $price", fontSize = 15.sp, textDecoration = TextDecoration.LineThrough)
            Text("Now: $discountedPrice", fontSize = 15.sp)
            Text("Material: $material", fontSize = 15.sp)
            Spacer(modifier = Modifier.height(5.dp))

            // Stars
            Row {
                repeat(4) {
                    Icon(Icons.Default.Star, contentDescription = "", tint = mustard)
                }
                Icon(Icons.Default.Star, contentDescription = "", tint = Color.Black)
            }

            Spacer(modifier = Modifier.height(5.dp))

            Button(
                onClick = onBuyNow,
                colors = ButtonDefaults.buttonColors(EmeraldGreen),
                shape = RectangleShape,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp)
            ) {
                Text("Buy Now")
            }
        }
    }
}

// --- Preview Function ---
@Preview(showBackground = true)
@Composable
fun WomenScreenPreview() {
    WomenScreen(navController = rememberNavController())
}
