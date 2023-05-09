package com.example.healthcare.Database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class Database(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    companion object { // static değişken yapısı için (toplu tanımlama)
        private const val DATABASE_NAME = "my_database.db"
        private const val DATABASE_VERSION = 6
        private const val TABLE_USERS = "Users"
        private const val COLUMN_USERNAME = "Username"
        private const val COLUMN_EMAIL = "Email"
        private const val COLUMN_PASSWORD = "Password"
        private const val COLUMN_BMI_ID = "ID"
        private const val TABLE_BMI = "BMI_Values"
        private const val COLUMN_GENDER = "Gender"
        private const val COLUMN_BMI_VALUE = "BMI"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val createTableQuery = "CREATE TABLE $TABLE_USERS ($COLUMN_USERNAME TEXT, $COLUMN_EMAIL TEXT, $COLUMN_PASSWORD TEXT);"
        db.execSQL(createTableQuery)

        val createBMITableQuery = "CREATE TABLE $TABLE_BMI ($COLUMN_BMI_ID INTEGER PRIMARY KEY AUTOINCREMENT,$COLUMN_USERNAME TEXT,$COLUMN_EMAIL TEXT, $COLUMN_GENDER TEXT, $COLUMN_BMI_VALUE DOUBLE);"
        db.execSQL(createBMITableQuery)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_USERS")
        db.execSQL("DROP TABLE IF EXISTS $TABLE_BMI")
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
        // kullanıcı adı ve şifre değerlerini dizi içinde sakla
        val str = arrayOf(email, password)
        val db = readableDatabase

        // kullanıcı adı ve şifreye göre sorgu yap
        val c = db.rawQuery("SELECT * FROM users WHERE email=? AND password=?", str)

        // sorgu sonucunda bir kayıt varsa result değişkenini 1 olarak ayarla
        if (c.moveToFirst()) {
            result = 1
        }
        // Cursor ve veritabanı nesnelerini kapat
        c.close()
        db.close()
        // sonucu döndür
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
}
