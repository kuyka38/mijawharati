package com.kunji.mijawharati.ui.screens.men


import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.kunji.mijawharati.R
import com.kunji.mijawharati.ui.theme.CreamWhite
import com.kunji.mijawharati.ui.theme.EmeraldGreen
import com.kunji.mijawharati.ui.theme.mustard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenScreen(navController: NavController) {

    val mContext = LocalContext.current

    var selectedIndex by remember { mutableStateOf(0) }

    Scaffold(
        topBar = @androidx.compose.runtime.Composable {
            TopAppBar(
                title = { Text("Men's Collection") },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.navigate("ROUT_CATEGORY")
                    }) {
                        Icon(Icons.Default.KeyboardArrowLeft, contentDescription = "Back")
                    }
                },
                actions = {
                    IconButton(onClick = {
                        navController.navigate("CartScreen")
                    }) {
                        Icon(Icons.Default.ShoppingCart, contentDescription = "Cart", tint = Color.White)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = EmeraldGreen,
                    titleContentColor = Color.White,
                    navigationIconContentColor = Color.White
                )
            )
        },





        bottomBar = {
            NavigationBar(containerColor = EmeraldGreen) {
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Home, contentDescription = "Home",  tint = Color.White) },
                    label = { Text("Home") },
                    selected = selectedIndex == 0,
                    onClick = { selectedIndex = 0 }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Favorite, contentDescription = "Favorites",  tint = Color.White) },
                    label = { Text("Favorites") },
                    selected = selectedIndex == 1,
                    onClick = { selectedIndex = 1 }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Person, contentDescription = "Profile",  tint = Color.White) },
                    label = { Text("Profile",) },
                    selected = selectedIndex == 2,
                    onClick = { selectedIndex = 2 }
                )
            }
        },

        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    Toast.makeText(mContext, "FAB clicked", Toast.LENGTH_SHORT).show()
                },
                containerColor = Color.LightGray
            ) {
                Icon(Icons.Default.Add, contentDescription = "Add")
            }
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .background(CreamWhite)
                    .verticalScroll(rememberScrollState())
                    .fillMaxSize()
            ) {
                Text(
                    text = "Define your legacy with MiJawharati’s Men’s Collection bold, refined pieces crafted to elevate your every stride.",
                    fontSize = 15.sp,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )

                Spacer(modifier = Modifier.height(8.dp))

                Image(
                    painter = painterResource(R.drawable.img),
                    contentDescription = "coat",
                    modifier = Modifier
                        .fillMaxWidth()
                        .size(width = 50.dp, height = 150.dp)
                        .padding(horizontal = 16.dp),
                    contentScale = ContentScale.FillWidth
                )

                Spacer(modifier = Modifier.height(20.dp))

                var search by remember { mutableStateOf("") }
                OutlinedTextField(
                    value = search,
                    onValueChange = { search = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 20.dp, end = 20.dp),
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

                Text(
                    text = "Our Products",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(start = 20.dp)

                )

                Spacer(modifier = Modifier.height(10.dp))
                //BEGINING OF ROW-1

                Row(modifier = Modifier.padding(start = 20.dp)) {
                    Image(
                        painter = painterResource(R.drawable.menbrace1),
                        contentDescription = "bracelet",
                        modifier = Modifier
                            .size(width = 150.dp, height = 150.dp)
                            .clip(shape = RoundedCornerShape(10.dp)),
                        contentScale = ContentScale.Crop
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Column {
                        Text(
                            text = "Men's Silver Bracelet Set",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(text = "Brand: Beibaobao2024", fontSize = 15.sp)
                        Spacer(modifier = Modifier.height(5.dp))
                        Text(text = "price: ksh 1600", fontSize = 15.sp)
                        Spacer(modifier = Modifier.height(5.dp))
                        Text(text = "Material: Brass", fontSize = 15.sp)
                        Spacer(modifier = Modifier.height(5.dp))
                        Row {
                            Icon(Icons.Default.Star, contentDescription = "", tint = mustard)
                            Icon(Icons.Default.Star, contentDescription = "", tint = mustard)
                            Icon(Icons.Default.Star, contentDescription = "", tint = mustard)
                            Icon(Icons.Default.Star, contentDescription = "", tint = mustard)
                            Icon(Icons.Default.Star, contentDescription = "", tint = Color.Black)
                        }
                        Button(
                            onClick = {
                                val simToolKitLaunchIntent =
                                    mContext.packageManager.getLaunchIntentForPackage("com.android.stk")
                                if (simToolKitLaunchIntent != null) {
                                    mContext.startActivity(simToolKitLaunchIntent)
                                } else {
                                    Toast.makeText(mContext, "STK app not found", Toast.LENGTH_SHORT)
                                        .show()
                                }
                            },
                            colors = ButtonDefaults.buttonColors(EmeraldGreen),
                            shape = RectangleShape,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 15.dp, end = 15.dp)
                        ) {
                            Text(text = "Buy Now")
                        }
                    }
                }
                //END OF ROW

                Spacer(modifier = Modifier.height(10.dp))

                //GEGINING OF ROW-2
                Row(modifier = Modifier.padding(start = 20.dp)) {
                    Image(
                        painter = painterResource(R.drawable.menbrace2),
                        contentDescription = "bracelet",
                        modifier = Modifier
                            .size(width = 150.dp, height = 150.dp)
                            .clip(shape = RoundedCornerShape(10.dp)),
                        contentScale = ContentScale.Crop
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Column {
                        Text(
                            text = "Moodear Stack 6.5-8In Mens Bracelet Set",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(5.dp))
                        Text(text = "Brand: Moodear", fontSize = 15.sp)

                        Text(text = "Price: ksh 1400", fontSize = 15.sp)
                        Spacer(modifier = Modifier.height(5.dp))
                        Text(text = "Material: Yellow Gold", fontSize = 15.sp)
                        Spacer(modifier = Modifier.height(5.dp))
                        Row {
                            Icon(Icons.Default.Star, contentDescription = "", tint = mustard)
                            Icon(Icons.Default.Star, contentDescription = "", tint = mustard)
                            Icon(Icons.Default.Star, contentDescription = "", tint = mustard)
                            Icon(Icons.Default.Star, contentDescription = "", tint = mustard)
                            Icon(Icons.Default.Star, contentDescription = "", tint = Color.Black)
                        }
                        Button(
                            onClick = {
                                val simToolKitLaunchIntent =
                                    mContext.packageManager.getLaunchIntentForPackage("com.android.stk")
                                if (simToolKitLaunchIntent != null) {
                                    mContext.startActivity(simToolKitLaunchIntent)
                                } else {
                                    Toast.makeText(mContext, "STK app not found", Toast.LENGTH_SHORT)
                                        .show()
                                }
                            },
                            colors = ButtonDefaults.buttonColors(EmeraldGreen),
                            shape = RectangleShape,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 20.dp, end = 20.dp)
                        ) {
                            Text(text = "Buy Now")
                        }
                    }
                }
                //END OF ROW

                Spacer(modifier = Modifier.height(10.dp))


                //BEGINING OF ROW-3
                Row(modifier = Modifier.padding(start = 20.dp)) {
                    Image(
                        painter = painterResource(R.drawable.menear1),
                        contentDescription = "earing",
                        modifier = Modifier
                            .size(width = 150.dp, height = 150.dp)
                            .clip(shape = RoundedCornerShape(10.dp)),
                        contentScale = ContentScale.Crop
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Column {
                        Text(
                            text = "18K White Gold Plated Sparkling CZ Diamond Stud Earrings",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(5.dp))
                        Text(text = "Brand: Jiahanzb jewellery", fontSize = 15.sp)

                        Text(text = "Price: ksh 3000", fontSize = 15.sp)
                        Spacer(modifier = Modifier.height(5.dp))
                        Text(text = "Material: Cubic Zirconia, Sterling Silver", fontSize = 15.sp)
                        Spacer(modifier = Modifier.height(5.dp))
                        Row {
                            Icon(Icons.Default.Star, contentDescription = "", tint = mustard)
                            Icon(Icons.Default.Star, contentDescription = "", tint = mustard)
                            Icon(Icons.Default.Star, contentDescription = "", tint = mustard)
                            Icon(Icons.Default.Star, contentDescription = "", tint = mustard)
                            Icon(Icons.Default.Star, contentDescription = "", tint = Color.Black)
                        }
                        Button(
                            onClick = {
                                val simToolKitLaunchIntent =
                                    mContext.packageManager.getLaunchIntentForPackage("com.android.stk")
                                if (simToolKitLaunchIntent != null) {
                                    mContext.startActivity(simToolKitLaunchIntent)
                                } else {
                                    Toast.makeText(mContext, "STK app not found", Toast.LENGTH_SHORT)
                                        .show()
                                }
                            },
                            colors = ButtonDefaults.buttonColors(EmeraldGreen),
                            shape = RectangleShape,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 20.dp, end = 20.dp)
                        ) {
                            Text(text = "Buy Now")
                        }
                    }
                }
                //END OF ROW

                Spacer(modifier = Modifier.height(10.dp))

                //BEGINING OF ROW-5
                Row(modifier = Modifier.padding(start = 20.dp)) {
                    Image(
                        painter = painterResource(R.drawable.menear2),
                        contentDescription = "earing",
                        modifier = Modifier
                            .size(width = 150.dp, height = 150.dp)
                            .clip(shape = RoundedCornerShape(10.dp)),
                        contentScale = ContentScale.Crop
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Column {
                        Text(
                            text = "Stainless Steel Mens Hoop Earrings",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(5.dp))
                        Text(text = "Brand: Gulaka", fontSize = 15.sp)

                        Text(text = "Price: ksh 1000", fontSize = 15.sp)
                        Spacer(modifier = Modifier.height(5.dp))
                        Text(text = "Material:Stainless Steel", fontSize = 15.sp)
                        Spacer(modifier = Modifier.height(5.dp))
                        Row {
                            Icon(Icons.Default.Star, contentDescription = "", tint = mustard)
                            Icon(Icons.Default.Star, contentDescription = "", tint = mustard)
                            Icon(Icons.Default.Star, contentDescription = "", tint = mustard)
                            Icon(Icons.Default.Star, contentDescription = "", tint = mustard)
                            Icon(Icons.Default.Star, contentDescription = "", tint = Color.Black)
                        }
                        Button(
                            onClick = {
                                val simToolKitLaunchIntent =
                                    mContext.packageManager.getLaunchIntentForPackage("com.android.stk")
                                if (simToolKitLaunchIntent != null) {
                                    mContext.startActivity(simToolKitLaunchIntent)
                                } else {
                                    Toast.makeText(mContext, "STK app not found", Toast.LENGTH_SHORT)
                                        .show()
                                }
                            },
                            colors = ButtonDefaults.buttonColors(EmeraldGreen),
                            shape = RectangleShape,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 20.dp, end = 20.dp)
                        ) {
                            Text(text = "Buy Now")
                        }
                    }
                }
                //END OF ROW

                Spacer(modifier = Modifier.height(10.dp))

                //BEGINING OF ROW-6
                Row(modifier = Modifier.padding(start = 20.dp)) {
                    Image(
                        painter = painterResource(R.drawable.menchain1),
                        contentDescription = "chain",
                        modifier = Modifier
                            .size(width = 150.dp, height = 150.dp)
                            .clip(shape = RoundedCornerShape(10.dp)),
                        contentScale = ContentScale.Crop
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Column {
                        Text(
                            text = "Stainless Steel Cuban Link Chains for Men",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(5.dp))
                        Text(text = "Brand: chains pro-max", fontSize = 15.sp)

                        Text(text = "Price: ksh 2000", fontSize = 15.sp)
                        Spacer(modifier = Modifier.height(5.dp))
                        Text(text = "Material: Stainless Steel", fontSize = 15.sp)
                        Spacer(modifier = Modifier.height(5.dp))
                        Row {
                            Icon(Icons.Default.Star, contentDescription = "", tint = mustard)
                            Icon(Icons.Default.Star, contentDescription = "", tint = mustard)
                            Icon(Icons.Default.Star, contentDescription = "", tint = mustard)
                            Icon(Icons.Default.Star, contentDescription = "", tint = mustard)
                            Icon(Icons.Default.Star, contentDescription = "", tint = Color.Black)
                        }
                        Button(
                            onClick = {
                                val simToolKitLaunchIntent =
                                    mContext.packageManager.getLaunchIntentForPackage("com.android.stk")
                                if (simToolKitLaunchIntent != null) {
                                    mContext.startActivity(simToolKitLaunchIntent)
                                } else {
                                    Toast.makeText(mContext, "STK app not found", Toast.LENGTH_SHORT)
                                        .show()
                                }
                            },
                            colors = ButtonDefaults.buttonColors(EmeraldGreen),
                            shape = RectangleShape,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 20.dp, end = 20.dp)
                        ) {
                            Text(text = "Buy Now")
                        }
                    }
                }
                //END OF ROW

                Spacer(modifier = Modifier.height(10.dp))

                //BEGINING OF ROW-7
                Row(modifier = Modifier.padding(start = 20.dp)) {
                    Image(
                        painter = painterResource(R.drawable.menchain2),
                        contentDescription = "chain",
                        modifier = Modifier
                            .size(width = 150.dp, height = 150.dp)
                            .clip(shape = RoundedCornerShape(10.dp)),
                        contentScale = ContentScale.Crop
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Column {
                        Text(
                            text = "Silver Chain for Men - Stainless Steel Cuban Link Tone",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(5.dp))
                        Text(text = "Brand: Silverdore UK", fontSize = 15.sp)

                        Text(text = "Price: ksh 3500", fontSize = 15.sp)
                        Spacer(modifier = Modifier.height(5.dp))
                        Text(text = "Material:Cuban Steel", fontSize = 15.sp)
                        Spacer(modifier = Modifier.height(5.dp))
                        Row {
                            Icon(Icons.Default.Star, contentDescription = "", tint = mustard)
                            Icon(Icons.Default.Star, contentDescription = "", tint = mustard)
                            Icon(Icons.Default.Star, contentDescription = "", tint = mustard)
                            Icon(Icons.Default.Star, contentDescription = "", tint = mustard)
                            Icon(Icons.Default.Star, contentDescription = "", tint = Color.Black)
                        }
                        Button(
                            onClick = {
                                val simToolKitLaunchIntent =
                                    mContext.packageManager.getLaunchIntentForPackage("com.android.stk")
                                if (simToolKitLaunchIntent != null) {
                                    mContext.startActivity(simToolKitLaunchIntent)
                                } else {
                                    Toast.makeText(mContext, "STK app not found", Toast.LENGTH_SHORT)
                                        .show()
                                }
                            },
                            colors = ButtonDefaults.buttonColors(EmeraldGreen),
                            shape = RectangleShape,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 20.dp, end = 20.dp)
                        ) {
                            Text(text = "Buy Now")
                        }
                    }
                }
                //END OF ROW

                Spacer(modifier = Modifier.height(10.dp))

                //BEGINING OF ROW-8
                Row(modifier = Modifier.padding(start = 20.dp)) {
                    Image(
                        painter = painterResource(R.drawable.menwatch1),
                        contentDescription = "watch",
                        modifier = Modifier
                            .size(width = 150.dp, height = 150.dp)
                            .clip(shape = RoundedCornerShape(10.dp)),
                        contentScale = ContentScale.Crop
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Column {
                        Text(
                            text = " Timex Men's Waterbury Classic Chrono Dress Watch",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(5.dp))
                        Text(text = "Brand: Amazon.com", fontSize = 15.sp)

                        Text(text = "Price: ksh 10000", fontSize = 15.sp)
                        Spacer(modifier = Modifier.height(5.dp))
                        Text(text = "Material: Leather & Silver", fontSize = 15.sp)
                        Spacer(modifier = Modifier.height(5.dp))
                        Row {
                            Icon(Icons.Default.Star, contentDescription = "", tint = mustard)
                            Icon(Icons.Default.Star, contentDescription = "", tint = mustard)
                            Icon(Icons.Default.Star, contentDescription = "", tint = mustard)
                            Icon(Icons.Default.Star, contentDescription = "", tint = mustard)
                            Icon(Icons.Default.Star, contentDescription = "", tint = Color.Black)
                        }
                        Button(
                            onClick = {
                                val simToolKitLaunchIntent =
                                    mContext.packageManager.getLaunchIntentForPackage("com.android.stk")
                                if (simToolKitLaunchIntent != null) {
                                    mContext.startActivity(simToolKitLaunchIntent)
                                } else {
                                    Toast.makeText(mContext, "STK app not found", Toast.LENGTH_SHORT)
                                        .show()
                                }
                            },
                            colors = ButtonDefaults.buttonColors(EmeraldGreen),
                            shape = RectangleShape,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 20.dp, end = 20.dp)
                        ) {
                            Text(text = "Buy Now")
                        }
                    }
                }
                //END OF ROW

                Spacer(modifier = Modifier.height(10.dp))

                //BEGINING OF ROW-9
                Row(modifier = Modifier.padding(start = 20.dp)) {
                    Image(
                        painter = painterResource(R.drawable.menwatch2),
                        contentDescription = "watch",
                        modifier = Modifier
                            .size(width = 150.dp, height = 150.dp)
                            .clip(shape = RoundedCornerShape(10.dp)),
                        contentScale = ContentScale.Crop
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Column {
                        Text(
                            text = " MVMT Men’s Legacy Slim Watch - Analog Watch for Men ",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(5.dp))
                        Text(text = "Brand: MVMT watches ", fontSize = 15.sp)

                        Text(text = "Price: ksh 15000", fontSize = 15.sp)
                        Spacer(modifier = Modifier.height(5.dp))
                        Text(text = "Material: Leather & Glass", fontSize = 15.sp)
                        Spacer(modifier = Modifier.height(5.dp))
                        Row {
                            Icon(Icons.Default.Star, contentDescription = "", tint = mustard)
                            Icon(Icons.Default.Star, contentDescription = "", tint = mustard)
                            Icon(Icons.Default.Star, contentDescription = "", tint = mustard)
                            Icon(Icons.Default.Star, contentDescription = "", tint = mustard)
                            Icon(Icons.Default.Star, contentDescription = "", tint = Color.Black)
                        }
                        Button(
                            onClick = {
                                val simToolKitLaunchIntent =
                                    mContext.packageManager.getLaunchIntentForPackage("com.android.stk")
                                if (simToolKitLaunchIntent != null) {
                                    mContext.startActivity(simToolKitLaunchIntent)
                                } else {
                                    Toast.makeText(mContext, "STK app not found", Toast.LENGTH_SHORT)
                                        .show()
                                }
                            },
                            colors = ButtonDefaults.buttonColors(EmeraldGreen),
                            shape = RectangleShape,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 20.dp, end = 20.dp)
                        ) {
                            Text(text = "Buy Now")
                        }
                    }
                }
                //END OF ROW

                Spacer(modifier = Modifier.height(10.dp))

                //BEGINING OF ROW-10
                Row(modifier = Modifier.padding(start = 20.dp)) {
                    Image(
                        painter = painterResource(R.drawable.menring1),
                        contentDescription = "ring",
                        modifier = Modifier
                            .size(width = 150.dp, height = 200.dp)
                            .clip(shape = RoundedCornerShape(10.dp)),
                        contentScale = ContentScale.Crop
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Column {
                        Text(
                            text = "MYNENEY 14K Gold/Silver/Black Plated Square Signet Ring",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(5.dp))
                        Text(text = "Brand: MYNENEY Jewellery Store", fontSize = 15.sp)

                        Text(text = "Price: ksh 1600", fontSize = 15.sp)
                        Spacer(modifier = Modifier.height(5.dp))
                        Text(text = "Material: Stainless Gold", fontSize = 15.sp)
                        Spacer(modifier = Modifier.height(5.dp))
                        Row {
                            Icon(Icons.Default.Star, contentDescription = "", tint = mustard)
                            Icon(Icons.Default.Star, contentDescription = "", tint = mustard)
                            Icon(Icons.Default.Star, contentDescription = "", tint = mustard)
                            Icon(Icons.Default.Star, contentDescription = "", tint = mustard)
                            Icon(Icons.Default.Star, contentDescription = "", tint = Color.Black)
                        }
                        Button(
                            onClick = {
                                val simToolKitLaunchIntent =
                                    mContext.packageManager.getLaunchIntentForPackage("com.android.stk")
                                if (simToolKitLaunchIntent != null) {
                                    mContext.startActivity(simToolKitLaunchIntent)
                                } else {
                                    Toast.makeText(mContext, "STK app not found", Toast.LENGTH_SHORT)
                                        .show()
                                }
                            },
                            colors = ButtonDefaults.buttonColors(EmeraldGreen),
                            shape = RectangleShape,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 20.dp, end = 20.dp)
                        ) {
                            Text(text = "Buy Now")
                        }
                    }
                }
                //END OF ROW

                Spacer(modifier = Modifier.height(10.dp))

                //BEGINING OF ROW-10
                Row(modifier = Modifier.padding(start = 20.dp)) {
                    Image(
                        painter = painterResource(R.drawable.mencuff1),
                        contentDescription = "cuff",
                        modifier = Modifier
                            .size(width = 150.dp, height = 150.dp)
                            .clip(shape = RoundedCornerShape(10.dp)),
                        contentScale = ContentScale.Crop
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Column {
                        Text(
                            text = "Cufflinks with Presentation Box",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(5.dp))
                        Text(text = "Brand: Cuff daddy", fontSize = 15.sp)

                        Text(text = "Price: ksh 3000", fontSize = 15.sp)
                        Spacer(modifier = Modifier.height(5.dp))
                        Text(text = "Material: Rhodium platted Brass", fontSize = 15.sp)
                        Spacer(modifier = Modifier.height(5.dp))
                        Row {
                            Icon(Icons.Default.Star, contentDescription = "", tint = mustard)
                            Icon(Icons.Default.Star, contentDescription = "", tint = mustard)
                            Icon(Icons.Default.Star, contentDescription = "", tint = mustard)
                            Icon(Icons.Default.Star, contentDescription = "", tint = mustard)
                            Icon(Icons.Default.Star, contentDescription = "", tint = Color.Black)
                        }
                        Button(
                            onClick = {
                                val simToolKitLaunchIntent =
                                    mContext.packageManager.getLaunchIntentForPackage("com.android.stk")
                                if (simToolKitLaunchIntent != null) {
                                    mContext.startActivity(simToolKitLaunchIntent)
                                } else {
                                    Toast.makeText(mContext, "STK app not found", Toast.LENGTH_SHORT)
                                        .show()
                                }
                            },
                            colors = ButtonDefaults.buttonColors(EmeraldGreen),
                            shape = RectangleShape,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 20.dp, end = 20.dp)
                        ) {
                            Text(text = "Buy Now")
                        }
                    }
                }
                //END OF ROW

                Spacer(modifier = Modifier.height(10.dp))

                //BEGINING OF ROW-10
                Row(modifier = Modifier.padding(start = 20.dp)) {
                    Image(
                        painter = painterResource(R.drawable.mencuff2),
                        contentDescription = "cuff",
                        modifier = Modifier
                            .size(width = 150.dp, height = 150.dp)
                            .clip(shape = RoundedCornerShape(10.dp)),
                        contentScale = ContentScale.Crop
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Column {
                        Text(
                            text = "Cuff-Daddy US Navy Cufflinks Gold Silver with Presentation Box",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(5.dp))
                        Text(text = "Brand: Cuff daddy ", fontSize = 15.sp)

                        Text(text = "Price: ksh: 3000", fontSize = 15.sp)
                        Spacer(modifier = Modifier.height(5.dp))
                        Text(text = "Material: Rhodium - Plated - Brass ", fontSize = 15.sp)
                        Spacer(modifier = Modifier.height(5.dp))
                        Row {
                            Icon(Icons.Default.Star, contentDescription = "", tint = mustard)
                            Icon(Icons.Default.Star, contentDescription = "", tint = mustard)
                            Icon(Icons.Default.Star, contentDescription = "", tint = mustard)
                            Icon(Icons.Default.Star, contentDescription = "", tint = mustard)
                            Icon(Icons.Default.Star, contentDescription = "", tint = Color.Black)
                        }
                        Button(
                            onClick = {
                                val simToolKitLaunchIntent =
                                    mContext.packageManager.getLaunchIntentForPackage("com.android.stk")
                                if (simToolKitLaunchIntent != null) {
                                    mContext.startActivity(simToolKitLaunchIntent)
                                } else {
                                    Toast.makeText(mContext, "STK app not found", Toast.LENGTH_SHORT)
                                        .show()
                                }
                            },
                            colors = ButtonDefaults.buttonColors(EmeraldGreen),
                            shape = RectangleShape,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 20.dp, end = 20.dp)
                        ) {
                            Text(text = "Buy Now")
                        }
                    }
                }
                //END OF ROW







            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun MenScreenPreview() {
    MenScreen(navController = rememberNavController())
}
