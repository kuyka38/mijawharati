package com.kunji.mijawharati.navigation

const val ROUT_HOME ="home"
const val ROUT_ABOUT ="about"
const val ROUT_SPLASH ="splash"
const val ROUT_INTENT ="intent"
const val ROUT_LANDING ="landing"
const val ROUT_CART = "CartScreen"
const val ROUT_PICTURE = "picture"
const val ROUT_ONBOARDING = "onboarding"
const val ROUT_ADMINDASHBOARD = "admindashboard"
const val ROUT_NECKLACES = "necklaces"
const val ROUT_BRACELETS = "bracelets"
const val ROUT_RINGS = "rings"
const val ROUT_EARRINGS = "earrings"
const val ROUT_WATCHES = "watches"
const val ROUT_ANKLETS = "anklets"
const val ROUT_PRODUCT_SCREEN_LIST = "productscreenlist"
const val ROUT_FAVORITES = "favorites"
const val ROUT_PROFILE = "profile"
const val ROUT_PRIVACY = "privacy"

//Content
const val ROUT_UPLOAD_CONTACT = "upload_contact"
const val ROUT_VIEW_CONTACT = "view_contact"






//Auth
const val ROUT_REGISTER= "register"
const val ROUT_LOGIN= "login"


//CRUD
//Product
const val ROUT_ADD_PRODUCT = "add_product"
const val ROUT_PRODUCT_LIST = "product_list"
const val ROUT_EDIT_PRODUCT = "edit_product/{productId}"

// ✅ Helper function for navigation
fun editProductRoute(productId: Int) = "edit_product/$productId"