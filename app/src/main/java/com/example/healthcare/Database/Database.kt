package com.example.healthcare.Database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class Database(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    companion object { // static değişken yapısı için (toplu tanımlama)
        private const val DATABASE_NAME = "my_database.db"
        private const val DATABASE_VERSION = 13
        private const val TABLE_USERS = "Users"
        private const val COLUMN_USERNAME = "Username"
        private const val COLUMN_EMAIL = "Email"
        private const val COLUMN_PASSWORD = "Password"
        private const val COLUMN_BMI_ID = "ID"
        private const val TABLE_BMI = "BMI_Values"
        private const val COLUMN_GENDER = "Gender"
        private const val COLUMN_BMI_VALUE = "BMI"
        private const val CART = "Cart"
        private const val PRODUCT_PRICE = "Product_Price"
        private const val PRODUCT = "Product"
        private const val TYPE = "Type"
        private const val TABLE_ORDER = "Orders"
        private const val FULLNAME = "FULLName"
        private const val ADDRESS = "Address"
        private const val CONTACT_NO = "Contact_No"
        private const val PIN_CODE = "Pin_Code"
        private const val DATE = "Date"
        private const val TIME = "Time"
        private const val AMOUNT = "Amount"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val createTableQuery = "CREATE TABLE $TABLE_USERS ($COLUMN_USERNAME TEXT, $COLUMN_EMAIL TEXT, $COLUMN_PASSWORD TEXT);"
        db.execSQL(createTableQuery)

        val createBMITableQuery = "CREATE TABLE $TABLE_BMI ($COLUMN_BMI_ID INTEGER PRIMARY KEY AUTOINCREMENT,$COLUMN_USERNAME TEXT,$COLUMN_EMAIL TEXT, $COLUMN_GENDER TEXT, $COLUMN_BMI_VALUE DOUBLE);"
        db.execSQL(createBMITableQuery)

        val createAddCartQuery = "CREATE TABLE $CART ($COLUMN_USERNAME TEXT, $PRODUCT TEXT, $PRODUCT_PRICE FLOAT, $TYPE TEXT);"
        db.execSQL(createAddCartQuery)

        val createOrderQuery =  "CREATE TABLE $TABLE_ORDER ($COLUMN_USERNAME TEXT, $FULLNAME TEXT, $ADDRESS TEXT, $CONTACT_NO TEXT, $PIN_CODE INT, $DATE TEXT, $TIME TEXT, $AMOUNT FLOAT, $TYPE TEXT, $PRODUCT TEXT);"
        db.execSQL(createOrderQuery)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_USERS")
        db.execSQL("DROP TABLE IF EXISTS $TABLE_BMI")
        db.execSQL("DROP TABLE IF EXISTS $CART")
        db.execSQL("DROP TABLE IF EXISTS $TABLE_ORDER")
        onCreate(db)
    }

    fun register(username: String, email: String, password: String) {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_USERNAME, username)
            put(COLUMN_EMAIL, email)
            put(COLUMN_PASSWORD, password)
        }
        db.insert(TABLE_USERS, null, values) // null parametresi, insert() işleminin başarılı olup olmadığını belirtir.
        db.close()
    }

    fun login(email: String, password: String): Int {
        var result = 0

        val str = arrayOf(email, password)
        val db = readableDatabase

        val c = db.rawQuery("SELECT * FROM users WHERE email=? AND password=?", str)

        // sorgu sonucunda bir kayıt varsa result değişkenini 1 olarak ayarla
        if (c.moveToFirst()) {
            result = 1
        }
        c.close()
        db.close()
        return result
    }

    fun saveBMI(username: String, email: String, gender: String, value: Double) {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_USERNAME, username)
            put(COLUMN_EMAIL, email)
            put(COLUMN_GENDER, gender)
            put(COLUMN_BMI_VALUE, value)
        }
        db.insert(TABLE_BMI, null, values)
        db.close()
    }

    fun addCart(username: String, product: String, price:Float, type:String){
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_USERNAME, username)
            put(PRODUCT, product)
            put(PRODUCT_PRICE, price)
            put(TYPE, type)
        }
        db.insert(CART, null, values)
        db.close()
    }

    fun checkCart(username: String, product: String): Int {
        var result = 0
        val str = arrayOfNulls<String>(2)
        str[0] = username
        str[1] = product
        val db = readableDatabase
        val c = db.rawQuery("select * from CART where username = ? and product = ?", str)
        if (c.moveToFirst()) {
            result = 1
        }
        db.close()
        return result
    }
    fun removeCart(username: String, type: String) {
        val db = writableDatabase
        val query = "username = ? AND type = ?"
        val args = arrayOf(username, type)
        db.delete("CART", query, args)
        db.close()
    }

    fun getCartData(username: String, type: String): ArrayList<String> {
        val cartData = ArrayList<String>()
        val db = readableDatabase
        val args = arrayOf(username, type)
        val query = "SELECT * FROM cart WHERE username = ? AND type = ?"
        val cursor = db.rawQuery(query, args)

        if (cursor.moveToFirst()) {
            do {
                val product = cursor.getString(1)
                val price = cursor.getString(2)
                cartData.add(product + "₺" + price)
            } while (cursor.moveToNext())
        }
        cursor.close()
        db.close()

        return cartData
    }

    fun addOrder(username: String, fullname: String, address: String, contact_no: String, pin_code: Int, date: String, time: String, amount: Float, type: String, packageNameList: List<String>){
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_USERNAME,username)
            put(FULLNAME,fullname)
            put(ADDRESS,address)
            put(CONTACT_NO,contact_no)
            put(PIN_CODE,pin_code)
            put(DATE,date)
            put(TIME,time)
            put(AMOUNT,amount)
            put(TYPE,type)
            put(PRODUCT,packageNameList.joinToString(separator = ", "))
        }
        db.insert(TABLE_ORDER, null, values)
        db.close()
    }

    fun removeOrder(username: String, product: String, type: String) {
        val db = writableDatabase
        val selection = "$COLUMN_USERNAME = ? AND $PRODUCT = ? AND $TYPE = ?"
        val selectionArgs = arrayOf(username, product, type)
        db.delete(TABLE_ORDER, selection, selectionArgs)
        db.close()
    }


    fun getOrderData(username: String): ArrayList<String> {
        val arr = ArrayList<String>()
        val db = readableDatabase
        val str = arrayOf(username)
        val query = "SELECT * FROM $TABLE_ORDER WHERE $COLUMN_USERNAME = ?"
        val cursor = db.rawQuery(query, str)

        if (cursor.moveToFirst()) {
            do {
                val orderData = StringBuilder().apply {
                    append(cursor.getString(0)) // Username
                    append("₺")
                    append(cursor.getString(1)) // FULLName
                    append("₺")
                    append(cursor.getString(2)) // Address
                    append("₺")
                    append(cursor.getString(3)) // Contact_No
                    append("₺")
                    append(cursor.getInt(4)) // Pin_Code
                    append("₺")
                    append(cursor.getString(5)) // Date
                    append("₺")
                    append(cursor.getString(6)) // Time
                    append("₺")
                    append(cursor.getFloat(7)) // Amount
                    append("₺")
                    append(cursor.getString(8)) // Type
                    append("₺")
                    append(cursor.getString(9)) // Product Name / Doctor Name
                }.toString()

                arr.add(orderData)
            } while (cursor.moveToNext())
        }

        cursor.close()
        db.close()

        return arr
    }

    fun checkAppointmentExists(username: String, fullname: String, address: String, contact_no: String, date: String, time: String) : Int {
        var result = 0
        val str = arrayOfNulls<String>(6)
        str[0] = username
        str[1] = fullname
        str[2] = address
        str[3] = contact_no
        str[4] = date
        str[5] = time
        val db = readableDatabase
        val c = db.rawQuery("select * from $TABLE_ORDER WHERE $COLUMN_USERNAME = ? AND fullname = ? AND address = ? AND contact_no = ? AND date = ? AND time = ?", str)
        if (c.moveToFirst()) {
            result = 1
        }
        db.close()
        return result
    }
}
