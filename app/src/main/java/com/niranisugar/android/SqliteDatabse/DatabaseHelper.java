package com.niranisugar.android.SqliteDatabse;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "nirani_sugar";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {

        // create notes table
        db.execSQL(Cart.CREATE_TABLE_CART);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + Cart.TABLE_NAME);

        // Create tables again
        onCreate(db);
    }

    public long InsertCart(Cart cart) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Cart.COLUMN_PRODUCT_ID, cart.getProduct_id());
        values.put(Cart.COLUMN_PRODUCT_NAME, cart.getProduct_name());
        values.put(Cart.COLUMN_PRODUCT_CATEGORY, cart.getProduct_category());
        values.put(Cart.COLUMN_PRODUCT_COUNT, cart.getProduct_count());
        values.put(Cart.COLUMN_PRODUCT_PRICE, cart.getProduct_price());
        values.put(Cart.COLUMN_PRODUCT_IMAGE, cart.getProduct_image());
        long id = db.insert(Cart.TABLE_NAME, null, values);
        db.close();
        return id;
    }

    public Cart GetCart(long id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(Cart.TABLE_NAME,
                new String[]{Cart.COLUMN_ID, Cart.COLUMN_PRODUCT_ID, Cart.COLUMN_PRODUCT_NAME, Cart.COLUMN_PRODUCT_CATEGORY
                        , Cart.COLUMN_PRODUCT_COUNT, Cart.COLUMN_PRODUCT_PRICE, Cart.COLUMN_PRODUCT_IMAGE},
                Cart.COLUMN_PRODUCT_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        // prepare note object
        Cart note = new Cart();
        if (cursor != null && cursor.getCount()>0) {
                note.setId(cursor.getInt(cursor.getColumnIndex(Cart.COLUMN_ID)));
                note.setProduct_id(cursor.getInt(cursor.getColumnIndex(Cart.COLUMN_PRODUCT_ID)));
                note.setProduct_name(cursor.getString(cursor.getColumnIndex(Cart.COLUMN_PRODUCT_NAME)));
                note.setProduct_category(cursor.getString(cursor.getColumnIndex(Cart.COLUMN_PRODUCT_CATEGORY)));
                note.setProduct_count(cursor.getInt(cursor.getColumnIndex(Cart.COLUMN_PRODUCT_COUNT)));
                note.setProduct_price(cursor.getString(cursor.getColumnIndex(Cart.COLUMN_PRODUCT_PRICE)));
                note.setProduct_image(cursor.getString(cursor.getColumnIndex(Cart.COLUMN_PRODUCT_IMAGE)));

                // close the db connection
                cursor.close();

                return note;
            }else{
                return null;
            }
    }

    public ArrayList<Cart> GetAllCartProduct() {
        ArrayList<Cart> carts = new ArrayList<>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + Cart.TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Cart cart = new Cart();
                cart.setId(cursor.getInt(cursor.getColumnIndex(Cart.COLUMN_ID)));
                cart.setProduct_id(cursor.getInt(cursor.getColumnIndex(Cart.COLUMN_PRODUCT_ID)));
                cart.setProduct_name(cursor.getString(cursor.getColumnIndex(Cart.COLUMN_PRODUCT_NAME)));
                cart.setProduct_category(cursor.getString(cursor.getColumnIndex(Cart.COLUMN_PRODUCT_CATEGORY)));
                cart.setProduct_count(cursor.getInt(cursor.getColumnIndex(Cart.COLUMN_PRODUCT_COUNT)));
                cart.setProduct_price(cursor.getString(cursor.getColumnIndex(Cart.COLUMN_PRODUCT_PRICE)));
                cart.setProduct_image(cursor.getString(cursor.getColumnIndex(Cart.COLUMN_PRODUCT_IMAGE)));
                carts.add(cart);
            } while (cursor.moveToNext());
        }

        // close db connection
        db.close();

        // return notes list
        return carts;
    }

    public int GetCartCount() {
        String countQuery = "SELECT  * FROM " + Cart.TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        int count = cursor.getCount();
        cursor.close();


        // return count
        return count;
    }

    public int UpdateCart(Cart cart) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Cart.COLUMN_PRODUCT_ID, cart.getProduct_id());
        values.put(Cart.COLUMN_PRODUCT_NAME, cart.getProduct_name());
        values.put(Cart.COLUMN_PRODUCT_CATEGORY, cart.getProduct_category());
        values.put(Cart.COLUMN_PRODUCT_COUNT, cart.getProduct_count());
        values.put(Cart.COLUMN_PRODUCT_PRICE, cart.getProduct_price());
        values.put(Cart.COLUMN_PRODUCT_IMAGE, cart.getProduct_image());
        return db.update(Cart.TABLE_NAME, values, Cart.COLUMN_ID + " = ?",
                new String[]{String.valueOf(cart.getId())});
    }

    public void DeleteCart(Cart note) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Cart.TABLE_NAME, Cart.COLUMN_ID + " = ?",
                new String[]{String.valueOf(note.getId())});
        db.close();
    }
}
