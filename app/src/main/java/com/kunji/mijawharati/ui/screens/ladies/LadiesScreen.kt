package com.kunji.mijawharati.ui.screens.ladies

import android.widget.Toast
import androidx.compose.foundation.Image
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.kunji.mijawharati.R
import com.kunji.mijawharati.ui.theme.EmeraldGreen
import com.kunji.mijawharati.ui.theme.mustard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LadiesScreen(navController: NavController) {

    val mContext = LocalContext.current

    var selectedIndex by remember { mutableStateOf(0) }

    Scaffold(
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

        bottomBar = {
            NavigationBar(containerColor = EmeraldGreen) {
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
                    label = { Text("Home") },
                    selected = selectedIndex == 0,
                    onClick = { selectedIndex = 0 }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Favorite, contentDescription = "Favorites") },
                    label = { Text("Favorites") },
                    selected = selectedIndex == 1,
                    onClick = { selectedIndex = 1 }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Person, contentDescription = "Profile") },
                    label = { Text("Profile") },
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
                    .verticalScroll(rememberScrollState())
                    .fillMaxSize()
            ) {
                Text(
                    text = "Celebrate your sparkle with MiJawharati’s Women’s Collection elegant pieces crafted to elevate every moment.",
                    fontSize = 15.sp,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )

                Spacer(modifier = Modifier.height(8.dp))

                Image(
                    painter = painterResource(R.drawable.img_1),
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
                        painter = painterResource(R.drawable.brace2),
                        contentDescription = "cloth",
                        modifier = Modifier
                            .size(width = 150.dp, height = 150.dp)
                            .clip(shape = RoundedCornerShape(10.dp)),
                        contentScale = ContentScale.Crop
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Column {
                        Text(
                            text = "Diamond Plated Clover Lucky Bracelet",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(text = "Brand: AIPPK", fontSize = 15.sp)
                        Spacer(modifier = Modifier.height(5.dp))
                        Text(text = "price: ksh 1000", fontSize = 15.sp)
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
                        painter = painterResource(R.drawable.brace1),
                        contentDescription = "cloth",
                        modifier = Modifier
                            .size(width = 150.dp, height = 150.dp)
                            .clip(shape = RoundedCornerShape(10.dp)),
                        contentScale = ContentScale.Crop
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Column {
                        Text(
                            text = "Gold-Plated Four Leaf Clover Bracelet",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(5.dp))
                        Text(text = "Brand:AIPPK", fontSize = 15.sp)

                        Text(text = "Price: ksh 1000", fontSize = 15.sp)
                        Spacer(modifier = Modifier.height(5.dp))
                        Text(text = "Material: Stainless Steal", fontSize = 15.sp)
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
                        painter = painterResource(R.drawable.earing1),
                        contentDescription = "earing",
                        modifier = Modifier
                            .size(width = 150.dp, height = 150.dp)
                            .clip(shape = RoundedCornerShape(10.dp)),
                        contentScale = ContentScale.Crop
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Column {
                        Text(
                            text = "Gold Plated Sterling Silver Posts Oval Chunky Hoop Earring",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(5.dp))
                        Text(text = "Brand:PAVOI jewellery", fontSize = 15.sp)

                        Text(text = "Price: ksh 2000", fontSize = 15.sp)
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

                //BEGINING OF ROW-5
                Row(modifier = Modifier.padding(start = 20.dp)) {
                    Image(
                        painter = painterResource(R.drawable.chain2),
                        contentDescription = "chain",
                        modifier = Modifier
                            .size(width = 150.dp, height = 150.dp)
                            .clip(shape = RoundedCornerShape(10.dp)),
                        contentScale = ContentScale.Crop
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Column {
                        Text(
                            text = "Layered Gold Necklaces for Women",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(5.dp))
                        Text(text = "Brand:Picuzzy", fontSize = 15.sp)

                        Text(text = "Price: ksh 1000", fontSize = 15.sp)
                        Spacer(modifier = Modifier.height(5.dp))
                        Text(text = "Material:Gold Plated", fontSize = 15.sp)
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
                        painter = painterResource(R.drawable.chain3),
                        contentDescription = "chain",
                        modifier = Modifier
                            .size(width = 150.dp, height = 150.dp)
                            .clip(shape = RoundedCornerShape(10.dp)),
                        contentScale = ContentScale.Crop
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Column {
                        Text(
                            text = "Otxas Layered Gold Necklace for Women",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(5.dp))
                        Text(text = "Brand:OUO-US", fontSize = 15.sp)

                        Text(text = "Price: ksh 1300", fontSize = 15.sp)
                        Spacer(modifier = Modifier.height(5.dp))
                        Text(text = "Material:Zinc", fontSize = 15.sp)
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
                        painter = painterResource(R.drawable.rings1),
                        contentDescription = "chain",
                        modifier = Modifier
                            .size(width = 150.dp, height = 150.dp)
                            .clip(shape = RoundedCornerShape(10.dp)),
                        contentScale = ContentScale.Crop
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Column {
                        Text(
                            text = "Chunky Gold Rings for Women Stackable Silver Rings Set",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(5.dp))
                        Text(text = "Brand:VRNGI", fontSize = 15.sp)

                        Text(text = "Price: ksh 2500", fontSize = 15.sp)
                        Spacer(modifier = Modifier.height(5.dp))
                        Text(text = "Material:Alloy Steel", fontSize = 15.sp)
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
                        painter = painterResource(R.drawable.rings2),
                        contentDescription = "chain",
                        modifier = Modifier
                            .size(width = 150.dp, height = 150.dp)
                            .clip(shape = RoundedCornerShape(10.dp)),
                        contentScale = ContentScale.Crop
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Column {
                        Text(
                            text = "Fansilver Stackable Chunky Gold Rings for Women ",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(5.dp))
                        Text(text = "Brand:Fansilver Jewellery", fontSize = 15.sp)

                        Text(text = "Price: ksh 1900", fontSize = 15.sp)
                        Spacer(modifier = Modifier.height(5.dp))
                        Text(text = "Material:Alloy Gold", fontSize = 15.sp)
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
                        painter = painterResource(R.drawable.watch3),
                        contentDescription = "watch",
                        modifier = Modifier
                            .size(width = 150.dp, height = 150.dp)
                            .clip(shape = RoundedCornerShape(10.dp)),
                        contentScale = ContentScale.Crop
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Column {
                        Text(
                            text = "Lola Rose Dainty Women's Wrist Watch ",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(5.dp))
                        Text(text = "Brand:Lola Rose", fontSize = 15.sp)

                        Text(text = "Price: ksh 3000", fontSize = 15.sp)
                        Spacer(modifier = Modifier.height(5.dp))
                        Text(text = "Material: Alloy Gold", fontSize = 15.sp)
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
                        painter = painterResource(R.drawable.watch4),
                        contentDescription = "watch",
                        modifier = Modifier
                            .size(width = 150.dp, height = 200.dp)
                            .clip(shape = RoundedCornerShape(10.dp)),
                        contentScale = ContentScale.Crop
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Column {
                        Text(
                            text = "CARLIEN Women Vintage Petite Bracelet Gold Dainty Watch",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(5.dp))
                        Text(text = "Brand:CARLIEN", fontSize = 15.sp)

                        Text(text = "Price: ksh 4000", fontSize = 15.sp)
                        Spacer(modifier = Modifier.height(5.dp))
                        Text(text = "Material:Gold", fontSize = 15.sp)
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
                        painter = painterResource(R.drawable.anklet1),
                        contentDescription = "anklet",
                        modifier = Modifier
                            .size(width = 150.dp, height = 200.dp)
                            .clip(shape = RoundedCornerShape(10.dp)),
                        contentScale = ContentScale.Crop
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Column {
                        Text(
                            text = "Gold Ankle Bracelets for women",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(5.dp))
                        Text(text = "Brand:MFYRK", fontSize = 15.sp)

                        Text(text = "Price: ksh 1200", fontSize = 15.sp)
                        Spacer(modifier = Modifier.height(5.dp))
                        Text(text = "Material: Brass ", fontSize = 15.sp)
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
                        painter = painterResource(R.drawable.anklet2),
                        contentDescription = "anklet",
                        modifier = Modifier
                            .size(width = 150.dp, height = 200.dp)
                            .clip(shape = RoundedCornerShape(10.dp)),
                        contentScale = ContentScale.Crop
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Column {
                        Text(
                            text = "Silver Plated Ankle Bracelets Trendy Waterproof Boho Anklets",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(5.dp))
                        Text(text = "Brand: Rontso ", fontSize = 15.sp)

                        Text(text = "Price: ksh 1000", fontSize = 15.sp)
                        Spacer(modifier = Modifier.height(5.dp))
                        Text(text = "Material: Brass ", fontSize = 15.sp)
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
fun    LadiesScreenPreview() {
    LadiesScreen(navController = rememberNavController())
}
